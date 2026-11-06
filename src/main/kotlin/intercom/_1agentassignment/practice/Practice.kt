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
class AssignmentSystem(agents: List<String>) {

    fun setLimit(agent: String, limit: Int) {
        TODO("setLimit")
    }

    fun assign(conversationId: Int): String? {
        TODO("assign")
    }

    fun previewAssignments(count: Int): List<String> {
        TODO("previewAssignments")
    }

    /** Extension (asked after the core works). Returns false if the id is unknown. */
    fun closeConversation(conversationId: Int): Boolean {
        TODO("closeConversation (extension)")
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

    Harness.summary()
}
