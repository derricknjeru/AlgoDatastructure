package intercom._0kotlinessentials.queues.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.scenario
import intercom._0kotlinessentials.queues.RELATED_ARTICLES
import intercom._0kotlinessentials.queues.TICKETS
import intercom._0kotlinessentials.queues.Ticket

/*
 * Part 3 – Queue, stack and deque drills. Use kotlin's ArrayDeque unless told otherwise.
 * Data (tickets, article graph): ../Data.kt. Answers: ../solution/Solution.kt
 *
 *   ./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.queues.practice.PracticeKt
 */

// 1. Queue: assign `conversations` conversations round-robin; return who got each one.
fun roundRobin(agents: List<String>, conversations: Int): List<String> = TODO("1. ArrayDeque rotation")

// 2. Bounded queue: keep only the last `capacity` messages, oldest first.
class RecentMessages(private val capacity: Int) {
    fun add(message: String) {
        TODO("2. add")
    }

    fun latest(): List<String> = TODO("2. latest")
}

// 3. Stack: are (), [] and {} balanced in a macro template? Other characters are ignored.
fun isBalanced(template: String): Boolean = TODO("3. stack")

// 4. Stack: "type:<text>" appends text, "undo" removes the last typed chunk (ignored if none).
//    Anything else -> IllegalArgumentException. Return the final text.
fun applyEdits(operations: List<String>): String = TODO("4. undo stack")

// 5. BFS: fewest links from one help article to another; null if unreachable.
fun hops(graph: Map<String, List<String>>, from: String, to: String): Int? = TODO("5. BFS")

// 6. Monotonic deque: the minimum of every window of k values (quietest stretch for staffing).
fun minPerWindow(values: List<Int>, k: Int): List<Int> = TODO("6. monotonic deque")

// 7. PriorityQueue: ticket ids in serving order – priority 1 first, ties by earliest arrival.
fun serveOrder(tickets: List<Ticket>): List<Int> = TODO("7. PriorityQueue")

fun main() {
    scenario("Queue / bounded queue") {
        expect("1. roundRobin", roundRobin(listOf("Alice", "Bob", "Carol"), 5), listOf("Alice", "Bob", "Carol", "Alice", "Bob"))
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
