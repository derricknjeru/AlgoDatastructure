package intercom._0kotlinessentials.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.scenario
import java.util.PriorityQueue
import java.util.TreeMap

/* Answers to ../practice/Practice.kt. Try the drills first. */

data class Agent(val name: String, val active: Int = 0, val limit: Int = 2, val lastAssigned: Long = -1)

enum class Status { OPEN, SNOOZED, CLOSED }

data class Conversation(val id: Int, val assignee: String?, val status: Status)

data class Message(val sender: String, val recipients: List<String>, val text: String)

// 1. Named arguments make copy() read like the requirement.
fun recordAssignment(agent: Agent, now: Long): Agent =
    agent.copy(active = agent.active + 1, lastAssigned = now)

// 2a. O(n log n)
fun assignmentOrder(agents: List<Agent>): List<String> =
    agents.sortedWith(compareBy<Agent> { it.active }.thenBy { it.lastAssigned }.thenBy { it.name })
        .map { it.name }

// 2b. O(n log n). For huge maps use the top-k heap from 5c: O(n log k).
fun topMacros(uses: Map<String, Int>, k: Int): List<String> =
    uses.entries
        .sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
        .take(k)
        .map { it.key }

// 3a. groupBy(keySelector, valueTransform). O(n)
fun idsByAssignee(conversations: List<Conversation>): Map<String, List<Int>> =
    conversations.groupBy({ it.assignee ?: "unassigned" }, { it.id })

// 3b. No intermediate lists. O(n)
fun countByStatus(conversations: List<Conversation>): Map<Status, Int> =
    conversations.groupingBy { it.status }.eachCount()

// 4. O(total recipients)
fun buildInboxes(messages: List<Message>): Map<String, List<String>> {
    val inboxes = LinkedHashMap<String, MutableList<String>>()
    for (message in messages) {
        for (recipient in message.recipients) {
            inboxes.getOrPut(recipient) { mutableListOf() } += message.text
        }
    }
    return inboxes
}

// 5a. O(log n + results)
fun eventsBetween(timeline: TreeMap<Int, String>, from: Int, to: Int): List<String> =
    if (from > to) emptyList() else timeline.subMap(from, true, to, true).values.toList()

// 5b. O(log n)
fun lastEventAtOrBefore(timeline: TreeMap<Int, String>, t: Int): String? =
    timeline.floorEntry(t)?.value

// 5c. O(n log k). The heap's head is the WORST of the current top k, so it's the one to evict.
fun busiestAgents(load: Map<String, Int>, k: Int): List<String> {
    require(k >= 0) { "k must be >= 0" }
    val best = compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key }
    val heap = PriorityQueue(best.reversed()) // worst on top
    for (entry in load.entries) {
        heap += entry
        if (heap.size > k) heap.poll()
    }
    return heap.sortedWith(best).map { it.key }
}

// 6a. Each failure gets its own message – that's what the interviewer will read.
fun parseLimit(input: String?): Int {
    val text = requireNotNull(input) { "limit is required" }.trim()
    require(text.isNotEmpty()) { "limit must not be blank" }
    val limit = requireNotNull(text.toIntOrNull()) { "limit must be a number, was '$text'" }
    require(limit >= 0) { "limit must be >= 0, was $limit" }
    return limit
}

// 6b. check = "this object is in the wrong state for this call".
class Ticket {
    var closed = false
        private set

    fun close() {
        check(!closed) { "Ticket is already closed" }
        closed = true
    }
}

// 6c.
fun ownerOf(conversationId: Int, owners: Map<Int, String>): String = owners[conversationId] ?: "unassigned"

private val CONVERSATIONS = listOf(
    Conversation(1, "Alice", Status.OPEN),
    Conversation(2, "Bob", Status.CLOSED),
    Conversation(3, "Alice", Status.SNOOZED),
    Conversation(4, null, Status.OPEN),
    Conversation(5, "Bob", Status.OPEN),
)

private fun timeline() = TreeMap(mapOf(0 to "created", 60 to "fin replied", 120 to "assigned", 300 to "closed"))

fun main() {
    scenario("1. data class + copy()") {
        val alice = Agent("Alice")
        expect("recordAssignment", recordAssignment(alice, now = 7), Agent("Alice", active = 1, lastAssigned = 7))
        expect("original unchanged", alice.active, 0)
    }

    scenario("2. compareBy / thenBy") {
        val agents = listOf(
            Agent("Carol", 1, lastAssigned = 3),
            Agent("Bob", 1, lastAssigned = 2),
            Agent("Alice", 2, lastAssigned = 1),
            Agent("Eve", 0),
            Agent("Dan", 0),
        )
        expect("assignmentOrder", assignmentOrder(agents), listOf("Dan", "Eve", "Bob", "Carol", "Alice"))
        val uses = mapOf("refund-status" to 30, "pricing" to 40, "reset-password" to 30, "report-bug" to 5)
        expect("topMacros(k=3)", topMacros(uses, 3), listOf("pricing", "refund-status", "reset-password"))
    }

    scenario("3. groupBy / groupingBy") {
        expect("idsByAssignee", idsByAssignee(CONVERSATIONS), mapOf("Alice" to listOf(1, 3), "Bob" to listOf(2, 5), "unassigned" to listOf(4)))
        expect("countByStatus", countByStatus(CONVERSATIONS), mapOf(Status.OPEN to 3, Status.CLOSED to 1, Status.SNOOZED to 1))
    }

    scenario("4. getOrPut") {
        val messages = listOf(
            Message("cust", listOf("fin", "alice"), "Hi"),
            Message("fin", listOf("cust", "alice"), "Hello!"),
            Message("alice", listOf("cust", "fin"), "Alice here"),
        )
        expect(
            "buildInboxes", buildInboxes(messages),
            mapOf("fin" to listOf("Hi", "Alice here"), "alice" to listOf("Hi", "Hello!"), "cust" to listOf("Hello!", "Alice here")),
        )
    }

    scenario("5. TreeMap / PriorityQueue") {
        expect("eventsBetween(50, 120)", eventsBetween(timeline(), 50, 120), listOf("fin replied", "assigned"))
        expect("lastEventAtOrBefore(100)", lastEventAtOrBefore(timeline(), 100), "fin replied")
        expect("lastEventAtOrBefore(-5)", lastEventAtOrBefore(timeline(), -5), null)
        val load = mapOf("Alice" to 5, "Bob" to 9, "Carol" to 2, "Dan" to 7, "Eve" to 9)
        expect("busiestAgents(k=3)", busiestAgents(load, 3), listOf("Bob", "Eve", "Dan"))
    }

    scenario("6. require / check / null safety") {
        expect("parseLimit(\" 4 \")", parseLimit(" 4 "), 4)
        expectThrows("parseLimit(null)", IllegalArgumentException::class) { parseLimit(null) }
        expectThrows("parseLimit(\"abc\")", IllegalArgumentException::class) { parseLimit("abc") }
        expectThrows("parseLimit(\"-1\")", IllegalArgumentException::class) { parseLimit("-1") }
        val ticket = Ticket()
        ticket.close()
        expect("closed", ticket.closed, true)
        expectThrows("close twice", IllegalStateException::class) { ticket.close() }
        expect("ownerOf(1)", ownerOf(1, mapOf(1 to "Alice")), "Alice")
        expect("ownerOf(2)", ownerOf(2, mapOf(1 to "Alice")), "unassigned")
    }

    Harness.summary()
}
