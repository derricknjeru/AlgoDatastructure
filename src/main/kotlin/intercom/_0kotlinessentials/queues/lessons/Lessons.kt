package intercom._0kotlinessentials.queues.lessons

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.note
import intercom.Harness.scenario
import java.util.PriorityQueue
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

/*
 * Part 3 – Queues, stacks and deques.
 *
 *   ./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.queues.lessons.LessonsKt
 *
 * Which one?
 *   Need                                   Use                                   Cost
 *   FIFO queue (waiting conversations)     ArrayDeque: addLast / removeFirst     O(1)
 *   LIFO stack (undo, brackets, DFS)       ArrayDeque: addLast / removeLast      O(1)
 *   Both ends (sliding window, 0-1 BFS)    ArrayDeque: add/remove First/Last     O(1)
 *   Most urgent first (SLA, priority)      java.util.PriorityQueue               O(log n)
 *   Sorted + remove any element            java.util.TreeSet / TreeMap           O(log n)
 *   Many threads (webhooks -> workers)     LinkedBlockingQueue / ConcurrentLinkedQueue
 *
 * `ArrayDeque` here is kotlin.collections.ArrayDeque (imported by default). It is also a MutableList.
 * Avoid: MutableList.removeAt(0) as a queue (O(n)), java.util.Stack (legacy, synchronized).
 */

fun queueFifo() = scenario("1. Queue (FIFO) with ArrayDeque") {
    val waiting = ArrayDeque<String>()
    waiting.addLast("c1")
    waiting.addLast("c2")
    waiting += "c3" // += adds at the END
    expect("first() peeks at the front", waiting.first(), "c1")
    expect("removeFirst() dequeues", waiting.removeFirst(), "c1")
    expect("remaining, front to back", waiting.toList(), listOf("c2", "c3"))
    waiting.clear()
    expect("removeFirstOrNull() on empty", waiting.removeFirstOrNull(), null)
    expect("firstOrNull() on empty", waiting.firstOrNull(), null)
    expectThrows("removeFirst() on empty throws", NoSuchElementException::class) { waiting.removeFirst() }

    note("Round-robin = take from the front, put back at the end")
    val agents = ArrayDeque(listOf("Alice", "Bob", "Carol"))
    val next = agents.removeFirst().also { agents.addLast(it) }
    expect("rotated", next to agents.toList(), "Alice" to listOf("Bob", "Carol", "Alice"))
}

fun stackLifo() = scenario("2. Stack (LIFO) with ArrayDeque") {
    val undo = ArrayDeque<String>()
    undo.addLast("typed Hello")
    undo.addLast("typed world")
    undo.addLast("bold")
    expect("last() peeks at the top", undo.last(), "bold")
    expect("removeLast() pops", undo.removeLast(), "bold")
    expect("pop again", undo.removeLast(), "typed world")
    expect("removeLastOrNull() on empty", ArrayDeque<Int>().removeLastOrNull(), null)

    // Classic stack problem (LeetCode 20). Return early as soon as something doesn't match.
    fun balanced(text: String): Boolean {
        val opening = mapOf(')' to '(', ']' to '[', '}' to '{')
        val open = ArrayDeque<Char>()
        for (ch in text) {
            when (ch) {
                '(', '[', '{' -> open.addLast(ch)
                ')', ']', '}' -> if (open.removeLastOrNull() != opening[ch]) return false
            }
        }
        return open.isEmpty()
    }
    expect("balanced(\"{[()]}\")", balanced("{[()]}"), true)
    expect("balanced(\"([)]\")", balanced("([)]"), false)
    expect("balanced(\"((\")", balanced("(("), false)
}

fun dequeBothEnds() = scenario("3. Deque – both ends, and the monotonic deque") {
    val d = ArrayDeque(listOf(2, 3))
    d.addFirst(1)
    d.addLast(4)
    expect("addFirst / addLast", d.toList(), listOf(1, 2, 3, 4))
    expect("removeFirst + removeLast", d.removeFirst() to d.removeLast(), 1 to 4)
    expect("it's also a List: d[0]", d[0], 2)

    // LeetCode 239 – Sliding Window Maximum in O(n).
    // Keep INDICES whose values decrease front -> back. The front is always the window's max.
    fun windowMax(values: List<Int>, k: Int): List<Int> {
        val indices = ArrayDeque<Int>()
        val result = mutableListOf<Int>()
        for (i in values.indices) {
            if (indices.isNotEmpty() && indices.first() <= i - k) indices.removeFirst() // left the window
            while (indices.isNotEmpty() && values[indices.last()] <= values[i]) indices.removeLast() // can't be max anymore
            indices.addLast(i)
            if (i >= k - 1) result += values[indices.first()]
        }
        return result
    }
    expect("max messages in each 3-minute window", windowMax(listOf(1, 3, 0, 2, 5, 3, 6, 7), 3), listOf(3, 3, 5, 5, 6, 7))
    note("Each index is added once and removed at most once -> O(n), vs O(n·k) for windowed { max }.")
}

fun javaQueueApi() = scenario("4. java.util.Queue / Deque API (you'll see it in Java-style code)") {
    val q: java.util.Queue<String> = java.util.ArrayDeque()
    expect("offer() returns true", q.offer("c1"), true)
    q.add("c2")
    expect("peek() then poll()", q.peek() to q.poll(), "c1" to "c1")
    q.clear()
    expect("poll() on empty -> null", q.poll(), null)
    expect("peek() on empty -> null", q.peek(), null)
    expectThrows("remove() on empty throws", NoSuchElementException::class) { q.remove() }
    expectThrows("element() on empty throws", NoSuchElementException::class) { q.element() }
    note("throws an exception: add / remove / element     returns false/null: offer / poll / peek")
    expectThrows("java.util.ArrayDeque rejects null", NullPointerException::class) { java.util.ArrayDeque<String?>().add(null) }

    val stack = java.util.ArrayDeque<Int>()
    stack.push(1)
    stack.push(2)
    expect("Deque as a stack: push / pop work on the FRONT", stack.pop(), 2)
    val linked: java.util.Queue<Int> = java.util.LinkedList()
    linked.offer(7)
    expect("LinkedList is also a Queue (ArrayDeque is usually faster)", linked.poll(), 7)
}

fun priorityQueue() = scenario("5. PriorityQueue – most urgent first") {
    // conversation id -> SLA deadline (minutes). Serve the nearest deadline first.
    val bySla = PriorityQueue(compareBy<Pair<String, Int>> { it.second })
    bySla += "c1" to 30
    bySla += "c2" to 5
    bySla += "c3" to 15
    expect("peek()", bySla.peek().first, "c2")
    expect("drain with poll()", generateSequence { bySla.poll() }.map { it.first }.toList(), listOf("c2", "c3", "c1"))
    note("A PriorityQueue is a queue whose 'front' is the smallest by its comparator. Iteration is NOT sorted.")
    note("Same priority? Add a tie-breaker (arrival time / id) or the order between them is unspecified.")
}

fun bfs() = scenario("6. BFS – a queue explores level by level") {
    val related = mapOf(
        "reset-password" to listOf("account-locked", "two-factor"),
        "account-locked" to listOf("contact-support"),
        "two-factor" to listOf("authenticator-app"),
    )

    fun visitOrder(start: String): List<String> {
        val seen = mutableSetOf(start)
        val queue = ArrayDeque(listOf(start))
        val order = mutableListOf<String>()
        while (queue.isNotEmpty()) {
            val article = queue.removeFirst()
            order += article
            for (next in related[article].orEmpty()) {
                if (seen.add(next)) queue.addLast(next) // add() returns false if already seen
            }
        }
        return order
    }
    expect(
        "BFS from reset-password",
        visitOrder("reset-password"),
        listOf("reset-password", "account-locked", "two-factor", "contact-support", "authenticator-app"),
    )
    note("Mark visited when you ENQUEUE, not when you dequeue – otherwise a node can be queued twice.")
    note("Swap the queue for a stack (removeLast) and the same loop becomes an iterative DFS.")
}

fun slidingTimeWindow() = scenario("7. Expire old items from the front (sliding window log)") {
    val windowSeconds = 60
    val recent = ArrayDeque<Int>()
    fun hit(now: Int): Int {
        recent.addLast(now)
        while (recent.first() <= now - windowSeconds) recent.removeFirst()
        return recent.size
    }
    expect("requests in the last 60s", listOf(0, 10, 59, 60, 61, 130).map { hit(it) }, listOf(1, 2, 3, 3, 4, 1))
    note("This is the sliding-log rate limiter from Problem 8 and LeetCode 362 Design Hit Counter.")
}

fun concurrentQueues() = scenario("8. Thread-safe queues – producer / consumer") {
    val webhooks = LinkedBlockingQueue<Int>()
    val producer = thread {
        for (payload in 1..5) webhooks.put(payload)
        webhooks.put(-1) // "poison pill": tells the consumer to stop
    }
    var total = 0
    while (true) {
        val payload = webhooks.take() // blocks until something arrives
        if (payload == -1) break
        total += payload
    }
    producer.join()
    expect("consumer processed every payload", total, 15)

    val shared = ConcurrentLinkedQueue<Int>()
    List(4) { worker -> thread { repeat(250) { shared.offer(worker) } } }.forEach { it.join() }
    expect("ConcurrentLinkedQueue lost nothing", shared.size, 1_000)
    note("ArrayDeque / ArrayList / PriorityQueue are NOT thread-safe: use java.util.concurrent or a lock.")
}

fun main() {
    queueFifo()
    stackLifo()
    dequeBothEnds()
    javaQueueApi()
    priorityQueue()
    bfs()
    slidingTimeWindow()
    concurrentQueues()
    Harness.summary()
}
