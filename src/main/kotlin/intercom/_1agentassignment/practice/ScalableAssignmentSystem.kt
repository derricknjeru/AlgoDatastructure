package intercom._1agentassignment.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.note
import intercom.Harness.scenario
import java.util.PriorityQueue
import java.util.TreeSet
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/*
 * Problem 1 – follow-ups, built on top of AssignmentSystem (Practice.kt).
 * Same rules, same names (agentsMap, conversationMap, activeCount, lastTimeAssigned, order).
 *
 * ─────────────────────────────────────────────────────────────────────────────────────────────
 * Q1. How would you make `assign` O(log n)?
 * ─────────────────────────────────────────────────────────────────────────────────────────────
 * AssignmentSystem scans all n agents on every assign. Instead, keep the agents that CAN take
 * work in a TreeSet sorted by the same comparator. The best agent is always `first()`, so:
 *
 *     assign = pollFirst()  -> mutate -> re-add if still has capacity      O(log n)
 *
 * Rules for using a TreeSet with mutable objects:
 *  1. The set is ordered by the comparator fields (activeCount, lastTimeAssigned, order).
 *     If you change those fields while the agent is inside, the tree is now in the wrong order
 *     and remove()/contains() silently stop finding it. So every change is:
 *         remove(agent) -> change fields -> add(agent) if it has capacity      (see `update`)
 *  2. TreeSet treats compare(a, b) == 0 as "same element" and drops the second one.
 *     `order` is unique, so the comparator never returns 0 for two different agents.
 *  3. Invariant: an agent is in `available` <=> it is online and activeCount < limit.
 *     Full and offline agents are simply not in the set, so assign never has to skip them.
 *
 * Preview can't touch the real set, so it copies the available agents into a PriorityQueue
 * and simulates: O(n log n + k log n).
 *
 * ─────────────────────────────────────────────────────────────────────────────────────────────
 * Q2. What happens when an agent goes offline?
 * ─────────────────────────────────────────────────────────────────────────────────────────────
 * Offline agents must not receive NEW conversations: we remove them from `available`.
 * Their OPEN conversations are a product decision – ask the interviewer:
 *   a) Keep them (short break / lunch). They come back to the same queue.  -> default
 *   b) Reassign them (end of shift) so customers aren't left waiting.     -> reassign = true
 *      Each is reassigned with the normal rules, oldest first. If nobody has capacity the
 *      conversation is returned with a null agent and the caller should put it in an
 *      "unassigned" queue (drained when someone closes a conversation or comes online).
 * Going back online re-adds the agent to `available` if they still have capacity.
 * lastTimeAssigned is not changed, so they keep their place in the "waited longest" order.
 *
 * ─────────────────────────────────────────────────────────────────────────────────────────────
 * Q3. What happens when two `assign` calls run at the same time?
 * ─────────────────────────────────────────────────────────────────────────────────────────────
 * Without protection, AssignmentSystem has a check-then-act race:
 *
 *     Thread A: selectAgent -> Alice (1/2)          Thread B: selectAgent -> Alice (1/2)
 *     Thread A: Alice.activeCount++                 Thread B: Alice.activeCount++
 *
 *  - Both pick Alice: she ends up with 3 conversations but a limit of 2.
 *  - activeCount++ is read-modify-write, not atomic: one increment can be lost, so the count
 *    says 2 while she really has 3. The ++clock stamp can be duplicated the same way.
 *  - HashMap / TreeSet are not thread-safe: concurrent writes can corrupt them (lost entries,
 *    ConcurrentModificationException, a broken red-black tree).
 *
 * Fix here: ONE lock around every public method, so each operation runs start-to-finish
 * without interleaving (it is atomic).
 *  - Why one lock, not one per agent? Choosing an agent depends on ALL agents' state, and
 *    one operation touches several structures (available, conversationMap, clock).
 *  - Why not just ConcurrentSkipListSet / ConcurrentHashMap? Each single call would be safe,
 *    but "poll best agent, increment, re-add, record conversation" is several calls; another
 *    thread can run between them. Thread-safe pieces don't make a thread-safe whole.
 *  - Preview takes the lock too, so it simulates from a consistent snapshot.
 *  - Cost: assigns are serialised. Each holds the lock for O(log n), which is fine for one
 *    team. If it isn't, shard: one lock (or one single-threaded actor/queue) per team/inbox.
 *
 * Across multiple servers, an in-memory lock doesn't help. Options:
 *  - Database: SELECT … FOR UPDATE on the agent rows, or an atomic conditional update
 *    "UPDATE agents SET active = active + 1 WHERE id = ? AND active < limit" (0 rows = retry).
 *  - Single writer: route all assignments for a team through one partition/queue consumer.
 *  - Idempotency on conversationId (already done) makes retries after timeouts safe.
 *
 * ─────────────────────────────────────────────────────────────────────────────────────────────
 * Complexity (n agents, c open conversations, k preview count, m = conversations of one agent)
 * ─────────────────────────────────────────────────────────────────────────────────────────────
 * | Operation                 | AssignmentSystem | ScalableAssignmentSystem |
 * |---------------------------|------------------|--------------------------|
 * | assign                    | O(n)             | O(log n)                 |
 * | previewAssignments(k)     | O(n + k·n)       | O(n log n + k log n)     |
 * | closeConversation         | O(1)             | O(log n)                 |
 * | setLimit / setOnline      | O(1)             | O(log n)                 |
 * | setOffline(reassign=true) | –                | O(c + m log n)           |
 * | Space                     | O(n + c)         | O(n + c)                 |
 */
class ScalableAssignmentSystem(agents: List<String>) {

    private data class AgentState(
        val name: String,
        /** Position in the constructor list: final tie-breaker, unique -> TreeSet never sees a "duplicate". */
        val order: Int,
        var activeCount: Int = 0,
        var limit: Int = DEFAULT_LIMIT,
        var lastTimeAssigned: Long = NEVER_ASSIGNED,
        var online: Boolean = true,
    ) {
        val hasCapacity: Boolean get() = online && activeCount < limit
    }

    private companion object {
        const val DEFAULT_LIMIT = 2
        const val NEVER_ASSIGNED = -1L
    }

    /** Same priority as AssignmentSystem: fewest active -> waited longest -> initial list order. */
    private val agentComparator = compareBy<AgentState> { it.activeCount }
        .thenBy { it.lastTimeAssigned }
        .thenBy { it.order }

    private val agentsMap = LinkedHashMap<String, AgentState>()

    /** Only agents that can take work right now (online && activeCount < limit), best first. */
    private val available = TreeSet(agentComparator)

    /** conversationId -> owner. LinkedHashMap keeps assignment order, so reassignment is oldest first. */
    private val conversationMap = LinkedHashMap<Int, AgentState>()

    private var clock = 0L

    /** Guards ALL state above. Every public method takes it; private helpers assume it is held. */
    private val lock = ReentrantLock()

    init {
        require(agents.toSet().size == agents.size) { "Agent names must be unique: $agents" }
        agents.forEachIndexed { index, name ->
            val state = AgentState(name = name, order = index)
            agentsMap[name] = state
            available += state
        }
    }

    /** O(log n). */
    fun setLimit(agent: String, limit: Int) {
        require(limit >= 0) { "Limit cannot be negative: $limit" }
        lock.withLock {
            update(agentOrThrow(agent)) { it.limit = limit }
        }
    }

    /** O(log n). Idempotent per conversationId; null when nobody has capacity. */
    fun assign(conversationId: Int): String? = lock.withLock { assignLocked(conversationId) }

    /** O(n log n + k log n). Simulates on copies inside a PriorityQueue; real state untouched. */
    fun previewAssignments(count: Int): List<String> {
        require(count >= 0) { "count must be >= 0: $count" }
        lock.withLock {
            val heap = PriorityQueue(agentComparator)
            available.mapTo(heap) { it.copy() }
            var copyClock = clock
            val result = ArrayList<String>(count)

            repeat(count) {
                val selected = heap.poll() ?: return result
                selected.activeCount++
                selected.lastTimeAssigned = ++copyClock
                result += selected.name
                // Safe to re-add: the copy was polled out before its fields changed.
                if (selected.hasCapacity) heap += selected
            }
            return result
        }
    }

    /** O(log n). Frees a slot; the agent re-enters `available` if they were full. */
    fun closeConversation(conversationId: Int): Boolean {
        lock.withLock {
            val agent = conversationMap.remove(conversationId) ?: return false
            update(agent) { it.activeCount-- }
            return true
        }
    }

    /** O(log n). Back online: eligible again if below their limit. */
    fun setOnline(agent: String) {
        lock.withLock {
            update(agentOrThrow(agent)) { it.online = true }
        }
    }

    /**
     * O(log n), or O(c + m log n) with reassignment.
     *
     * @param reassignOpenConversations false = keep their open conversations (short break);
     *        true = hand each one to another agent using the normal rules, oldest first.
     * @return conversationId -> new agent for each reassigned conversation. A null agent means
     *         nobody had capacity: the caller should queue it as unassigned.
     */
    fun setOffline(agent: String, reassignOpenConversations: Boolean = false): Map<Int, String?> {
        lock.withLock {
            val state = agentOrThrow(agent)
            update(state) { it.online = false } // removed from `available` -> gets nothing new
            if (!reassignOpenConversations) return emptyMap()

            // O(c) scan; keep a per-agent set of conversation ids if this needs to be faster.
            val theirs = conversationMap.filterValues { it === state }.keys.toList()
            val reassigned = LinkedHashMap<Int, String?>()
            for (conversationId in theirs) {
                conversationMap.remove(conversationId)
                update(state) { it.activeCount-- } // stays out of `available`: they're offline
                reassigned[conversationId] = assignLocked(conversationId)
            }
            return reassigned
        }
    }

    /** Snapshot for tests/dashboards. */
    fun activeCounts(): Map<String, Int> = lock.withLock { agentsMap.mapValues { it.value.activeCount } }

    // ---- private helpers: caller must hold `lock` ----

    private fun assignLocked(conversationId: Int): String? {
        conversationMap[conversationId]?.let { return it.name }

        val agent = available.pollFirst() ?: return null // best agent, already removed from the set
        agent.activeCount++
        agent.lastTimeAssigned = ++clock
        if (agent.hasCapacity) available += agent        // re-insert at its new position

        conversationMap[conversationId] = agent
        return agent.name
    }

    /** The only safe way to change an agent: remove BEFORE touching comparator fields, then re-add. */
    private inline fun update(agent: AgentState, change: (AgentState) -> Unit) {
        available.remove(agent) // no-op if they were full/offline (not in the set)
        change(agent)
        if (agent.hasCapacity) available += agent
    }

    private fun agentOrThrow(name: String) =
        agentsMap[name] ?: throw IllegalArgumentException("Unknown agent: $name")
}

/** Starts all threads at the same moment (latch) to maximise interleaving, then waits for them. */
private fun runConcurrently(threads: Int, task: () -> Unit) {
    val pool = Executors.newFixedThreadPool(threads)
    val start = CountDownLatch(1)
    repeat(threads) { pool.execute { start.await(); task() } }
    start.countDown()
    pool.shutdown()
    check(pool.awaitTermination(10, TimeUnit.SECONDS)) { "threads did not finish" }
}

fun main() {
    scenario("Q1 – same answers as AssignmentSystem, now O(log n)") {
        val system = ScalableAssignmentSystem(listOf("Alice", "Bob", "Charlie"))
        system.setLimit("Bob", 4)
        system.setLimit("Charlie", 3)
        expect("previewAssignments(4)", system.previewAssignments(4), listOf("Alice", "Bob", "Charlie", "Alice"))
        expect("assign 101..104", listOf(101, 102, 103, 104).map { system.assign(it) }, listOf("Alice", "Bob", "Charlie", "Alice"))
        expect("previewAssignments(5)", system.previewAssignments(5), listOf("Bob", "Charlie", "Bob", "Charlie", "Bob"))
    }

    scenario("Q1 – closeConversation puts a full agent back in the TreeSet") {
        val system = ScalableAssignmentSystem(listOf("Alice", "Bob", "Charlie"))
        system.setLimit("Bob", 3)
        listOf(101, 102, 103, 104).forEach { system.assign(it) }
        expect("closeConversation(101)", system.closeConversation(101), true)
        expect("assign(105) – Bob waited longest", system.assign(105), "Bob")
    }

    scenario("Q2 – offline, keep open conversations (short break)") {
        val system = ScalableAssignmentSystem(listOf("Alice", "Bob"))
        expect("assign(1)", system.assign(1), "Alice")
        expect("setOffline(Alice)", system.setOffline("Alice"), emptyMap<Int, String?>())
        expect("assign 2..3 skip Alice", listOf(2, 3).map { system.assign(it) }, listOf("Bob", "Bob"))
        expect("assign(4) – Bob full, Alice offline", system.assign(4), null)
        expect("Alice still owns conversation 1", system.activeCounts()["Alice"], 1)
        system.setOnline("Alice")
        expect("assign(4) after Alice is back", system.assign(4), "Alice")
    }

    scenario("Q2 – offline, reassign open conversations (end of shift)") {
        val system = ScalableAssignmentSystem(listOf("Alice", "Bob", "Charlie"))
        listOf(1, 2, 3, 4).forEach { system.assign(it) } // Alice, Bob, Charlie, Alice
        note("Alice owns 1 and 4. Bob (t2) waited longer than Charlie (t3) -> 1 goes to Bob, 4 to Charlie")
        expect("setOffline(Alice, reassign)", system.setOffline("Alice", reassignOpenConversations = true), mapOf(1 to "Bob", 4 to "Charlie"))
        expect("active counts", system.activeCounts(), mapOf("Alice" to 0, "Bob" to 2, "Charlie" to 2))
        expect("closing 1 now frees Bob", system.closeConversation(1) && system.assign(5) == "Bob", true)
    }

    scenario("Q2 – reassign when nobody has capacity") {
        val system = ScalableAssignmentSystem(listOf("Alice", "Bob"))
        (1..4).forEach { system.assign(it) } // everyone full
        expect("setOffline(Alice, reassign)", system.setOffline("Alice", true), mapOf(1 to null, 3 to null))
        note("Caller should put 1 and 3 in an unassigned queue")
    }

    scenario("Q3 – 8 threads, 1000 assigns, capacity 50: never over the limit") {
        val system = ScalableAssignmentSystem(listOf("A", "B", "C", "D", "E"))
        listOf("A", "B", "C", "D", "E").forEach { system.setLimit(it, 10) }
        val nextId = AtomicInteger()
        val assigned = AtomicInteger()
        runConcurrently(threads = 8) {
            repeat(125) { if (system.assign(nextId.incrementAndGet()) != null) assigned.incrementAndGet() }
        }
        expect("successful assigns", assigned.get(), 50)
        expect("every agent exactly at limit", system.activeCounts().values.toSet(), setOf(10))
    }

    scenario("Q3 – concurrent assign + close keeps counts consistent") {
        val system = ScalableAssignmentSystem(listOf("A", "B", "C"))
        listOf("A", "B", "C").forEach { system.setLimit(it, 20) }
        val nextId = AtomicInteger()
        val stillOpen = AtomicInteger()
        runConcurrently(threads = 8) {
            repeat(2_000) {
                val id = nextId.incrementAndGet()
                if (system.assign(id) != null) {
                    // Close even ids straight away, keep odd ones open.
                    if (id % 2 == 0) system.closeConversation(id) else stillOpen.incrementAndGet()
                }
            }
        }
        val counts = system.activeCounts()
        expect("sum of activeCount == conversations still open", counts.values.sum(), stillOpen.get())
        expect("nobody over limit", counts.values.all { it <= 20 }, true)
    }

    Harness.summary()
}
