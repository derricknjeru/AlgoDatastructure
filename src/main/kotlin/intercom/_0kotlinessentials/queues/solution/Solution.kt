package intercom._0kotlinessentials.queues.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.scenario
import intercom._0kotlinessentials.queues.RELATED_ARTICLES
import intercom._0kotlinessentials.queues.TICKETS
import intercom._0kotlinessentials.queues.Ticket
import java.util.PriorityQueue

/* Answers to ../practice/Practice.kt (Part 3 – Queues). Try the drills first. */

// 1. Queue rotation. O(conversations)
fun roundRobin(agents: List<String>, conversations: Int): List<String> {
    require(agents.isNotEmpty() || conversations == 0) { "No agents to assign to" }
    val rotation = ArrayDeque(agents)
    return List(conversations) {
        rotation.removeFirst().also { rotation.addLast(it) }
    }
}

// 2. Bounded queue: drop from the front when over capacity. add O(1).
class RecentMessages(private val capacity: Int) {
    private val messages = ArrayDeque<String>()

    init {
        require(capacity > 0) { "capacity must be > 0" }
    }

    fun add(message: String) {
        messages.addLast(message)
        if (messages.size > capacity) messages.removeFirst()
    }

    fun latest(): List<String> = messages.toList()
}

// 3. Stack of open brackets. O(n)
fun isBalanced(template: String): Boolean {
    val opening = mapOf(')' to '(', ']' to '[', '}' to '{')
    val open = ArrayDeque<Char>()
    for (ch in template) {
        when (ch) {
            '(', '[', '{' -> open.addLast(ch)
            ')', ']', '}' -> if (open.removeLastOrNull() != opening[ch]) return false
        }
    }
    return open.isEmpty()
}

// 4. Undo stack. O(total text)
fun applyEdits(operations: List<String>): String {
    val typed = ArrayDeque<String>()
    for (operation in operations) {
        when {
            operation == "undo" -> typed.removeLastOrNull() // undo with nothing to undo is ignored
            operation.startsWith("type:") -> typed.addLast(operation.removePrefix("type:"))
            else -> throw IllegalArgumentException("Unknown operation: $operation")
        }
    }
    return typed.joinToString("")
}

// 5. BFS with distances. O(V + E)
fun hops(graph: Map<String, List<String>>, from: String, to: String): Int? {
    val distance = mutableMapOf(from to 0) // doubles as the "seen" set
    val queue = ArrayDeque(listOf(from))
    while (queue.isNotEmpty()) {
        val article = queue.removeFirst()
        if (article == to) return distance.getValue(article)
        for (next in graph[article].orEmpty()) {
            if (next !in distance) {
                distance[next] = distance.getValue(article) + 1
                queue.addLast(next)
            }
        }
    }
    return null
}

// 6. Monotonic deque, increasing front -> back so the front is the minimum. O(n)
fun minPerWindow(values: List<Int>, k: Int): List<Int> {
    require(k > 0) { "k must be > 0" }
    val indices = ArrayDeque<Int>()
    val result = mutableListOf<Int>()
    for (i in values.indices) {
        if (indices.isNotEmpty() && indices.first() <= i - k) indices.removeFirst()
        while (indices.isNotEmpty() && values[indices.last()] >= values[i]) indices.removeLast()
        indices.addLast(i)
        if (i >= k - 1) result += values[indices.first()]
    }
    return result
}

// 7. PriorityQueue with a tie-breaker. O(n log n)
fun serveOrder(tickets: List<Ticket>): List<Int> {
    val queue = PriorityQueue(compareBy<Ticket>({ it.priority }, { it.arrivedAt }))
    queue.addAll(tickets)
    return generateSequence { queue.poll() }.map { it.id }.toList()
}

fun main() {
    scenario("Queue / bounded queue") {
        expect("1. roundRobin", roundRobin(listOf("Alice", "Bob", "Carol"), 5), listOf("Alice", "Bob", "Carol", "Alice", "Bob"))
        expect("1. roundRobin(no conversations)", roundRobin(emptyList(), 0), emptyList<String>())
        val recent = RecentMessages(capacity = 3)
        (1..5).forEach { recent.add("m$it") }
        expect("2. RecentMessages(3).latest()", recent.latest(), listOf("m3", "m4", "m5"))
    }
    scenario("Stack") {
        expect("3. isBalanced(ok)", isBalanced("Hi {{name}}, see (docs [here])"), true)
        expect("3. isBalanced(missing })", isBalanced("Hi {{name}"), false)
        expect("3. isBalanced(wrong order)", isBalanced("a)b("), false)
        expect("4. applyEdits", applyEdits(listOf("type:Hello", "type: world", "undo", "type: there")), "Hello there")
        expect("4. applyEdits(undo on empty)", applyEdits(listOf("undo", "type:Hi")), "Hi")
        expectThrows("4. applyEdits(bad op)", IllegalArgumentException::class) { applyEdits(listOf("delete")) }
    }
    scenario("BFS / deque / priority queue") {
        expect("5. hops(reset-password -> contact-support)", hops(RELATED_ARTICLES, "reset-password", "contact-support"), 2)
        expect("5. hops(same article)", hops(RELATED_ARTICLES, "reset-password", "reset-password"), 0)
        expect("5. hops(unreachable)", hops(RELATED_ARTICLES, "contact-support", "reset-password"), null)
        expect("6. minPerWindow(k=3)", minPerWindow(listOf(1, 3, 0, 2, 5, 3, 6, 7), 3), listOf(0, 0, 0, 2, 3, 3))
        expect("7. serveOrder", serveOrder(TICKETS), listOf(2, 4, 1, 3, 5))
    }
    Harness.summary()
}
