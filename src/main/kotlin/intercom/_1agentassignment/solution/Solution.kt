package intercom._1agentassignment.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.note
import intercom.Harness.scenario
import java.util.PriorityQueue
import java.util.TreeSet

/*
 * Problem 1 – Assign conversations to customer support agents.
 *
 * Rules
 *  1. Pick the agent with the FEWEST active conversations.
 *  2. Tie -> the agent who has waited LONGEST since their last assignment.
 *     Never-assigned agents beat assigned ones; among them, initial list order wins.
 *  3. Default limit is 2. Agents at (or above) their limit are skipped.
 *  4. previewAssignments must NOT mutate state.
 *  5. Nobody has capacity -> assign returns null / preview stops early.
 *
 * "Waited longest" is modelled with a logical clock: every assignment stamps the agent with
 * `++clock`. Smaller stamp = waited longer. Never assigned = NEVER (-1), which sorts first.
 */

const val DEFAULT_LIMIT = 2
private const val NEVER = -1L

/** Common API so both implementations can share examples and tests. */
interface ConversationAssigner {
    fun setLimit(agent: String, limit: Int)
    fun assign(conversationId: Int): String?
    fun previewAssignments(count: Int): List<String>
    fun closeConversation(conversationId: Int): Boolean
    fun setOnline(agent: String, online: Boolean)
}

/** Mutable on purpose; `copy()` gives preview a cheap independent snapshot. */
private data class AgentState(
    val name: String,
    val order: Int,
    var limit: Int = DEFAULT_LIMIT,
    var active: Int = 0,
    var lastAssignedAt: Long = NEVER,
    var online: Boolean = true,
) {
    val hasCapacity: Boolean get() = online && active < limit
}

/** Fewest active -> waited longest (smallest stamp, NEVER first) -> initial list order. */
private val PRIORITY: Comparator<AgentState> =
    compareBy({ it.active }, { it.lastAssignedAt }, { it.order })

/**
 * Version 1 – linear scan. This is what you should write first in the interview.
 *
 * - assign:   O(n)
 * - preview:  O(n + k·n)   (copy + k scans)
 * - close / setLimit / setOnline: O(1)
 * - space:    O(n + c)     agents + open conversations
 */
class AssignmentSystem(agents: List<String>) : ConversationAssigner {
    private val agentsByName = LinkedHashMap<String, AgentState>()
    private val ownerOf = HashMap<Int, AgentState>()
    private var clock = 0L

    init {
        require(agents.toSet().size == agents.size) { "Duplicate agent names: $agents" }
        agents.forEachIndexed { index, name -> agentsByName[name] = AgentState(name, index) }
    }

    override fun setLimit(agent: String, limit: Int) {
        require(limit >= 0) { "limit must be >= 0, was $limit" }
        // Lowering below the current load never un-assigns work: the agent simply
        // receives nothing new until they drop below the new limit.
        agentOrThrow(agent).limit = limit
    }

    override fun assign(conversationId: Int): String? {
        ownerOf[conversationId]?.let { return it.name } // idempotent re-delivery
        val agent = pickNext(agentsByName.values) ?: return null
        agent.active++
        agent.lastAssignedAt = ++clock
        ownerOf[conversationId] = agent
        return agent.name
    }

    override fun previewAssignments(count: Int): List<String> {
        require(count >= 0) { "count must be >= 0" }
        val simulated = agentsByName.values.map { it.copy() }
        var simulatedClock = clock
        val result = ArrayList<String>(count)
        repeat(count) {
            val next = pickNext(simulated) ?: return result
            next.active++
            next.lastAssignedAt = ++simulatedClock
            result += next.name
        }
        return result
    }

    override fun closeConversation(conversationId: Int): Boolean {
        val agent = ownerOf.remove(conversationId) ?: return false
        agent.active--
        return true
    }

    override fun setOnline(agent: String, online: Boolean) {
        agentOrThrow(agent).online = online
    }

    private fun pickNext(candidates: Collection<AgentState>): AgentState? =
        candidates.asSequence().filter { it.hasCapacity }.minWithOrNull(PRIORITY)

    private fun agentOrThrow(name: String) =
        requireNotNull(agentsByName[name]) { "Unknown agent: $name" }
}

/**
 * Version 2 – ordered set of agents that currently have capacity.
 *
 * Invariant: an agent is in [available] iff `hasCapacity`, and its ordering fields have not
 * changed since it was inserted. So every mutation is remove -> mutate -> re-add.
 *
 * - assign:   O(log n)
 * - preview:  O(n log n + k log n)  (heap of copies, then k poll/offer)
 * - close / setLimit / setOnline: O(log n)
 */
class TreeSetAssignmentSystem(agents: List<String>) : ConversationAssigner {
    private val agentsByName = LinkedHashMap<String, AgentState>()
    private val available = TreeSet(PRIORITY) // `order` is unique, so no two agents compare equal
    private val ownerOf = HashMap<Int, AgentState>()
    private var clock = 0L

    init {
        require(agents.toSet().size == agents.size) { "Duplicate agent names: $agents" }
        agents.forEachIndexed { index, name ->
            val state = AgentState(name, index)
            agentsByName[name] = state
            available += state
        }
    }

    override fun setLimit(agent: String, limit: Int) {
        require(limit >= 0) { "limit must be >= 0, was $limit" }
        update(agentOrThrow(agent)) { it.limit = limit }
    }

    override fun assign(conversationId: Int): String? {
        ownerOf[conversationId]?.let { return it.name }
        val agent = available.pollFirst() ?: return null
        agent.active++
        agent.lastAssignedAt = ++clock
        if (agent.hasCapacity) available += agent
        ownerOf[conversationId] = agent
        return agent.name
    }

    override fun previewAssignments(count: Int): List<String> {
        require(count >= 0) { "count must be >= 0" }
        val heap = PriorityQueue(PRIORITY)
        available.forEach { heap += it.copy() }
        var simulatedClock = clock
        val result = ArrayList<String>(count)
        repeat(count) {
            val next = heap.poll() ?: return result
            next.active++
            next.lastAssignedAt = ++simulatedClock
            if (next.hasCapacity) heap += next
            result += next.name
        }
        return result
    }

    override fun closeConversation(conversationId: Int): Boolean {
        val agent = ownerOf.remove(conversationId) ?: return false
        update(agent) { it.active-- }
        return true
    }

    override fun setOnline(agent: String, online: Boolean) {
        update(agentOrThrow(agent)) { it.online = online }
    }

    private inline fun update(agent: AgentState, mutate: (AgentState) -> Unit) {
        available.remove(agent) // must happen BEFORE the comparator fields change
        mutate(agent)
        if (agent.hasCapacity) available += agent
    }

    private fun agentOrThrow(name: String) =
        requireNotNull(agentsByName[name]) { "Unknown agent: $name" }
}

private fun runExamples(label: String, create: (List<String>) -> ConversationAssigner) {
    scenario("[$label] Example 1 – the reported interview example") {
        val system = create(listOf("Alice", "Bob", "Charlie"))
        system.setLimit("Bob", 4)
        system.setLimit("Charlie", 3)
        expect("previewAssignments(4)", system.previewAssignments(4), listOf("Alice", "Bob", "Charlie", "Alice"))
        expect("assign(101)", system.assign(101), "Alice")
        expect("assign(102)", system.assign(102), "Bob")
        expect("assign(103)", system.assign(103), "Charlie")
        expect("assign(104)", system.assign(104), "Alice")
        note("Alice is now 2/2 (full), Bob 1/4, Charlie 1/3")
        expect("previewAssignments(5)", system.previewAssignments(5), listOf("Bob", "Charlie", "Bob", "Charlie", "Bob"))
        expect("preview did not mutate: previewAssignments(5) again", system.previewAssignments(5), listOf("Bob", "Charlie", "Bob", "Charlie", "Bob"))
    }

    scenario("[$label] Extension – closeConversation") {
        val system = create(listOf("Alice", "Bob", "Charlie"))
        system.setLimit("Alice", 2)
        system.setLimit("Bob", 3)
        system.setLimit("Charlie", 2)
        expect("assign(101)", system.assign(101), "Alice")
        expect("assign(102)", system.assign(102), "Bob")
        expect("assign(103)", system.assign(103), "Charlie")
        expect("assign(104)", system.assign(104), "Alice")
        expect("closeConversation(101)", system.closeConversation(101), true)
        note("Everyone has 1 active. Last assigned: Bob@t2, Charlie@t3, Alice@t4 -> Bob waited longest")
        expect("assign(105)", system.assign(105), "Bob")
        expect("closeConversation(999) unknown", system.closeConversation(999), false)
    }

    scenario("[$label] Nobody has capacity") {
        val system = create(listOf("Alice", "Bob"))
        expect("assign 1..4", (1..4).map { system.assign(it) }, listOf("Alice", "Bob", "Alice", "Bob"))
        expect("assign(5)", system.assign(5), null)
        expect("previewAssignments(3)", system.previewAssignments(3), emptyList<String>())
    }

    scenario("[$label] Lowering a limit below current load") {
        val system = create(listOf("Alice", "Bob"))
        system.setLimit("Bob", 5)
        expect("assign 1..3", (1..3).map { system.assign(it) }, listOf("Alice", "Bob", "Alice"))
        system.setLimit("Alice", 1)
        note("Alice keeps both conversations but gets nothing new until she is below 1")
        expect("assign(4)", system.assign(4), "Bob")
        system.closeConversation(1)
        expect("still 1 active == limit 1 -> assign(5)", system.assign(5), "Bob")
        system.closeConversation(3)
        expect("Alice now 0/1 -> assign(6)", system.assign(6), "Alice")
    }

    scenario("[$label] Offline agents are skipped") {
        val system = create(listOf("Alice", "Bob"))
        system.setOnline("Alice", false)
        expect("previewAssignments(3)", system.previewAssignments(3), listOf("Bob", "Bob"))
        system.setOnline("Alice", true)
        expect("assign(1)", system.assign(1), "Alice")
    }
}

fun main() {
    runExamples("scan") { AssignmentSystem(it) }
    runExamples("treeset") { TreeSetAssignmentSystem(it) }
    Harness.summary()
}
