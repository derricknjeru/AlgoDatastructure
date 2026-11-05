package intercom._1agentassignment.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.scenario

/*
 * Problem 1 – Assign conversations to customer support agents (45–60 min).
 * Read ../README.md first, then implement the TODOs and run this file.
 *
 *  - Assign to the agent with the fewest active conversations.
 *  - Tie -> the agent waiting longest since their last assignment
 *    (never-assigned agents first, in initial list order).
 *  - Default limit = 2. Skip agents at their limit.
 *  - previewAssignments must not change any state.
 *  - Return null / stop when nobody has capacity.
 */
/**
 * Linear-scan implementation. n = number of agents, c = open conversations, k = preview count.
 *
 * | Operation            | Time      | Why                                                   |
 * |----------------------|-----------|-------------------------------------------------------|
 * | init                 | O(n)      | one AgentState per agent                              |
 * | setLimit             | O(1)      | hash-map lookup                                       |
 * | assign               | O(n)      | scan every agent for the best candidate               |
 * | previewAssignments   | O(n + k·n)| copy n agents, then k scans of n agents               |
 * | closeConversation    | O(1)      | hash-map remove + decrement                           |
 * | Space                | O(n + c)  | agents + conversationId -> agent map                  |
 *
 * Scaling up: keep agents with capacity in a TreeSet ordered by [agentComparator] to make
 * assign O(log n). Always remove an agent from the set before mutating it, then re-add.
 */
class AssignmentSystem(agents: List<String>) {

    /**
     * Mutable per-agent state. A data class so `copy()` gives preview a cheap snapshot
     * (all fields are primitives/strings, so a shallow copy is a full copy).
     */
    private data class AgentState(
        val name: String,
        /** Position in the constructor list: final tie-breaker. */
        val order: Int,
        var activeCount: Int = 0,
        var limit: Int = DEFAULT_LIMIT,
        /** Logical time of the last assignment; NEVER_ASSIGNED sorts before every real stamp. */
        var lastTimeAssigned: Long = NEVER_ASSIGNED,
    ) {
        // Using `<` (not `!=`) also covers a limit lowered below the current load.
        val hasCapacity: Boolean get() = activeCount < limit
    }

    private companion object {
        const val DEFAULT_LIMIT = 2
        const val NEVER_ASSIGNED = -1L
    }

    /** Priority: fewest active -> waited longest (smallest stamp) -> initial list order. */
    private val agentComparator = compareBy<AgentState> { it.activeCount }
        .thenBy { it.lastTimeAssigned }
        .thenBy { it.order }

    // LinkedHashMap keeps the constructor order (handy for debugging/printing).
    private val agentsMap = LinkedHashMap<String, AgentState>()

    // Store the AgentState itself, so closing a conversation needs no second lookup.
    private val conversationMap = HashMap<Int, AgentState>()

    // Logical clock: ++clock on every assignment gives unique, increasing stamps, so the
    // "waited longest" tie-break is deterministic (no equal wall-clock timestamps).
    private var clock = 0L

    init {
        require(agents.toSet().size == agents.size) { "Agent names must be unique: $agents" }
        agents.forEachIndexed { index, name ->
            agentsMap[name] = AgentState(name = name, order = index)
        }
    }

    /** O(1). Lowering a limit never un-assigns work; the agent just gets nothing new until below it. */
    fun setLimit(agent: String, limit: Int) {
        require(limit >= 0) { "Limit cannot be negative: $limit" }
        val state = agentsMap[agent] ?: throw IllegalArgumentException("Unknown agent: $agent")
        state.limit = limit
    }

    /**
     * O(n). Returns the chosen agent, or null when nobody has capacity.
     *
     * Idempotent: re-assigning an already-assigned conversation (e.g. a retried webhook)
     * returns the existing agent instead of double-counting. Alternative: throw – ask the interviewer.
     */
    fun assign(conversationId: Int): String? {
        conversationMap[conversationId]?.let { return it.name }

        val agent = selectAgent(agentsMap.values) ?: return null
        agent.activeCount++
        agent.lastTimeAssigned = ++clock // increment first, then stamp

        conversationMap[conversationId] = agent
        return agent.name
    }

    /** O(n). Sequence avoids allocating a filtered list on every call. */
    private fun selectAgent(agents: Iterable<AgentState>): AgentState? = agents
        .asSequence()
        .filter { it.hasCapacity }
        .minWithOrNull(agentComparator)

    /**
     * O(n + k·n). Simulates [count] assignments on copies of the agents and the clock,
     * so real state is untouched. Stops early when nobody has capacity.
     */
    fun previewAssignments(count: Int): List<String> {
        require(count >= 0) { "count must be >= 0: $count" }

        val copyAgents = agentsMap.values.map { it.copy() }
        var copyClock = clock
        val result = ArrayList<String>(count)

        repeat(count) {
            val selected = selectAgent(copyAgents) ?: return result
            selected.activeCount++
            selected.lastTimeAssigned = ++copyClock
            result += selected.name
        }
        return result
    }

    /**
     * O(1). Extension: frees one slot for the owning agent. Returns false if the id is unknown.
     * lastTimeAssigned is NOT touched: "waiting longest" is measured from the last assignment.
     */
    fun closeConversation(conversationId: Int): Boolean {
        val agent = conversationMap.remove(conversationId) ?: return false
        agent.activeCount--
        return true
    }
}

fun main() {
    scenario("Example 1 – the reported interview example") {
        val system = AssignmentSystem(listOf("Alice", "Bob", "Charlie"))
        system.setLimit("Bob", 4)
        system.setLimit("Charlie", 3)
        expect("previewAssignments(4)", system.previewAssignments(4), listOf("Alice", "Bob", "Charlie", "Alice"))
        expect("assign(101)", system.assign(101), "Alice")
        expect("assign(102)", system.assign(102), "Bob")
        expect("assign(103)", system.assign(103), "Charlie")
        expect("assign(104)", system.assign(104), "Alice")
        expect("previewAssignments(5)", system.previewAssignments(5), listOf("Bob", "Charlie", "Bob", "Charlie", "Bob"))
    }

    scenario("Extension – closeConversation") {
        val system = AssignmentSystem(listOf("Alice", "Bob", "Charlie"))
        system.setLimit("Alice", 2)
        system.setLimit("Bob", 3)
        system.setLimit("Charlie", 2)
        system.assign(101)
        system.assign(102)
        system.assign(103)
        system.assign(104)
        expect("closeConversation(101)", system.closeConversation(101), true)
        // Work it out before running: who gets 105, and why?
        expect("assign(105)", system.assign(105), "Bob")
    }

    scenario("Nobody has capacity") {
        val system = AssignmentSystem(listOf("Alice", "Bob"))
        expect("assign 1..4", (1..4).map { system.assign(it) }, listOf("Alice", "Bob", "Alice", "Bob"))
        expect("assign(5)", system.assign(5), null)
        expect("previewAssignments(3)", system.previewAssignments(3), emptyList<String>())
    }

    scenario("Idempotent assign (retried webhook)") {
        val system = AssignmentSystem(listOf("Alice", "Bob"))
        expect("assign(1)", system.assign(1), "Alice")
        expect("assign(1) again -> same agent, no double count", system.assign(1), "Alice")
        expect("assign(2)", system.assign(2), "Bob")
    }

    scenario("Lowering a limit below current load") {
        val system = AssignmentSystem(listOf("Alice", "Bob"))
        system.setLimit("Bob", 5)
        expect("assign 1..3", (1..3).map { system.assign(it) }, listOf("Alice", "Bob", "Alice"))
        system.setLimit("Alice", 1) // Alice keeps 2 conversations, gets nothing new
        expect("assign(4)", system.assign(4), "Bob")
        system.closeConversation(1)
        system.closeConversation(3)
        expect("Alice back under limit -> assign(5)", system.assign(5), "Alice")
    }

    Harness.summary()
}
