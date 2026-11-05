package intercom._0kotlinessentials.lessons

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.note
import intercom.Harness.scenario
import java.util.PriorityQueue
import java.util.TreeMap
import java.util.TreeSet

/*
 * Kotlin essentials for the Intercom practical-coding round.
 * Read a lesson, run this file, then do the matching drill in ../practice/Practice.kt.
 *
 *   ./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.lessons.LessonsKt
 */

// ─────────────────────────────────────────────────────────────────────────────────────────────
// 1. data classes and copy()
// ─────────────────────────────────────────────────────────────────────────────────────────────
// `data` generates equals/hashCode/toString/copy/componentN from the PRIMARY CONSTRUCTOR properties.
// Use them for values: agents, messages, events, results.

data class Agent(
    val name: String,
    val active: Int = 0,
    val limit: Int = 2,
    val lastAssigned: Long = -1, // -1 = never assigned
)

data class Team(val name: String, val members: MutableList<String>)

fun lesson1DataClasses() = scenario("1. data classes and copy()") {
    val alice = Agent("Alice")
    expect("toString for free", alice.toString(), "Agent(name=Alice, active=0, limit=2, lastAssigned=-1)")
    expect("== compares values", alice == Agent("Alice"), true)
    expect("=== compares identity", alice === Agent("Alice"), false)
    expect("same values -> same hashCode -> one entry in a Set", setOf(alice, Agent("Alice")).size, 1)

    val busier = alice.copy(active = 1, lastAssigned = 7)
    expect("copy changes only the named fields", busier, Agent("Alice", active = 1, lastAssigned = 7))
    expect("original untouched", alice.active, 0)

    val (name, active) = busier // destructuring uses component1(), component2()
    //val (name2, limit) = busier.copy()
    expect("destructuring", "$name has $active", "Alice has 1")

    note("GOTCHA: copy() is SHALLOW. Nested mutable objects are shared between the copies.")
    val support = Team("Support", mutableListOf("Alice"))
    val shallow = support.copy()
    shallow.members += "Bob"
    expect("original list changed too!", support.members, listOf("Alice", "Bob"))
    val deep = support.copy(members = support.members.toMutableList())
    deep.members += "Carol"
    expect("copying the list as well protects the original", support.members, listOf("Alice", "Bob"))
}

// ─────────────────────────────────────────────────────────────────────────────────────────────
// 2. compareBy / thenBy  (+ sortedBy vs sortedWith vs minByOrNull)
// ─────────────────────────────────────────────────────────────────────────────────────────────
// Build a Comparator from key selectors, in priority order. Use with sortedWith, minWithOrNull,
// maxWithOrNull, TreeSet, TreeMap, PriorityQueue.
//
//   function                     returns                 you give it                       cost
//   sortedBy { key }             new sorted List         ONE key (must be Comparable)      O(n log n)
//   sortedWith(comparator)       new sorted List         a Comparator (many keys/dirs)     O(n log n)
//   minByOrNull { key }          ONE element or null     one key                           O(n)
//   minWithOrNull(comparator)    ONE element or null     a Comparator                      O(n)
//
// - sortedBy { k } is shorthand for sortedWith(compareBy { k }). Need a 2nd key or mixed
//   directions (compareByDescending { … }.thenBy { … })? -> sortedWith.
// - Don't sort to find one element: sortedBy { }.first() is O(n log n) + a new list;
//   minByOrNull { } is O(n), no list. That's why Problem 1's assign uses minWithOrNull.
// - Ties: sorting is STABLE (tied elements keep their original order);
//   minByOrNull / maxByOrNull return the FIRST of the tied elements.
// - Empty: minByOrNull -> null, minBy -> throws NoSuchElementException, sortedBy -> empty list.
// - Descending: sortedByDescending { }, compareByDescending { }, maxByOrNull { }, comparator.reversed().
// - In place (MutableList): sortBy { } / sortWith(…) mutate and return Unit; sorted… return a copy.
// - minOf { } returns the VALUE (0); minByOrNull { } returns the ELEMENT (Dan).
// Rule of thumb: whole order -> sortedBy / sortedWith. Just the best -> minByOrNull / minWithOrNull (or max…).

fun lesson2Comparators() = scenario("2. compareBy / thenBy") {
    val agents = listOf(
        Agent("Carol", active = 1, lastAssigned = 3),
        Agent("Bob", active = 1, lastAssigned = 2),
        Agent("Alice", active = 2, lastAssigned = 1),
        Agent("Dan", active = 0),
    )
    // Fewest active -> waited longest -> name. This is Problem 1's rule.
    val byPriority = compareBy<Agent> { it.active }.thenBy { it.lastAssigned }.thenBy { it.name }
    expect("sortedWith", agents.sortedWith(byPriority).map { it.name }, listOf("Dan", "Bob", "Carol", "Alice"))
    expect("minWithOrNull = best agent", agents.minWithOrNull(byPriority)?.name, "Dan")
    expect("reversed()", agents.sortedWith(byPriority.reversed()).first().name, "Alice")
    expect(
        "vararg form compareBy({…}, {…})",
        agents.sortedWith(compareBy({ it.active }, { it.lastAssigned })).map { it.name },
        listOf("Dan", "Bob", "Carol", "Alice"),
    )
    expect("one key: sortedBy", agents.sortedBy { it.name }.map { it.name }, listOf("Alice", "Bob", "Carol", "Dan"))

    // Mixed directions: most used first, then alphabetical.
    val uses = mapOf("refund" to 30, "pricing" to 40, "reset" to 30)
    val mostUsed = compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key }
    expect("descending then ascending", uses.entries.sortedWith(mostUsed).map { it.key }, listOf("pricing", "refund", "reset"))

    note("sortedBy vs sortedWith vs minByOrNull")
    expect("sortedBy { active } – one key, tie (Carol/Bob) keeps original order", agents.sortedBy { it.active }.map { it.name }, listOf("Dan", "Carol", "Bob", "Alice"))
    expect("sortedWith(compareBy(active, lastAssigned)) – 2nd key breaks the tie", agents.sortedWith(compareBy({ it.active }, { it.lastAssigned })).map { it.name }, listOf("Dan", "Bob", "Carol", "Alice"))
    expect("minByOrNull { active } – just the best, O(n)", agents.minByOrNull { it.active }?.name, "Dan")
    expect("same answer as sortedBy { }.first(), without sorting", agents.sortedBy { it.active }.first().name, "Dan")
    val tied = agents.filter { it.active == 1 } // Carol, Bob
    expect("minByOrNull on a tie returns the FIRST tied element", tied.minByOrNull { it.active }?.name, "Carol")
    expect("minWithOrNull(comparator) uses the tie-breaker", tied.minWithOrNull(byPriority)?.name, "Bob")
    expect("maxByOrNull – the max version", agents.maxByOrNull { it.active }?.name, "Alice")
    expect("minOf returns the VALUE, minByOrNull the ELEMENT", agents.minOf { it.active } to agents.minByOrNull { it.active }?.name, 0 to "Dan")
    expect("empty: minByOrNull -> null", emptyList<Agent>().minByOrNull { it.active }, null)
    expectThrows("empty: minBy -> throws", NoSuchElementException::class) { emptyList<Agent>().minBy { it.active } }
    expect("empty: sortedBy -> empty list", emptyList<Agent>().sortedBy { it.active }, emptyList<Agent>())
    val inPlace = agents.toMutableList()
    inPlace.sortBy { it.name } // mutates, returns Unit
    expect("sortBy { } sorts a MutableList in place", inPlace.map { it.name }, listOf("Alice", "Bob", "Carol", "Dan"))
    expect("… and the original list is untouched", agents.first().name, "Carol")

    note("GOTCHA: TreeSet/TreeMap treat compare == 0 as 'same element' and DROP it. End with a unique key.")
    val byActiveOnly = TreeSet(compareBy<Agent> { it.active })
    byActiveOnly.addAll(agents)
    expect("Bob and Carol both have 1 active -> one was dropped", byActiveOnly.size, 3)
}

// ─────────────────────────────────────────────────────────────────────────────────────────────
// 3. groupBy and groupingBy().eachCount()
// ─────────────────────────────────────────────────────────────────────────────────────────────
// groupBy    -> Map<K, List<V>>  (builds every list)
// groupingBy -> lazy Grouping; finish with eachCount(), fold(), reduce() – no lists built.
// Both keep keys in first-appearance order (LinkedHashMap).

enum class Status { OPEN, SNOOZED, CLOSED }

data class Conversation(val id: Int, val assignee: String?, val status: Status)

val CONVERSATIONS = listOf(
    Conversation(1, "Alice", Status.OPEN),
    Conversation(2, "Bob", Status.CLOSED),
    Conversation(3, "Alice", Status.SNOOZED),
    Conversation(4, null, Status.OPEN),
    Conversation(5, "Bob", Status.OPEN),
)

fun lesson3Grouping() = scenario("3. groupBy / groupingBy().eachCount()") {
    val byAssignee: Map<String?, List<Conversation>> = CONVERSATIONS.groupBy { it.assignee }
    expect("groupBy keys (null is a valid key)", byAssignee.keys.toList(), listOf("Alice", "Bob", null))

    // Second lambda transforms the values.
    expect(
        "groupBy(key, value)",
        CONVERSATIONS.groupBy({ it.assignee ?: "unassigned" }, { it.id }),
        mapOf("Alice" to listOf(1, 3), "Bob" to listOf(2, 5), "unassigned" to listOf(4)),
    )
    expect(
        "groupingBy { }.eachCount()",
        CONVERSATIONS.groupingBy { it.status }.eachCount(),
        mapOf(Status.OPEN to 3, Status.CLOSED to 1, Status.SNOOZED to 1),
    )
    expect(
        "word frequencies",
        "refund refund pricing".split(" ").groupingBy { it }.eachCount(),
        mapOf("refund" to 2, "pricing" to 1),
    )
    expect(
        "groupingBy { }.fold – sum per key",
        CONVERSATIONS.groupingBy { it.status }.fold(0) { sum, c -> sum + c.id },
        mapOf(Status.OPEN to 10, Status.CLOSED to 2, Status.SNOOZED to 3),
    )

    note("Relatives: associateBy (one value per key), partition, count { }")
    expect("associateBy { it.id }[4]", CONVERSATIONS.associateBy { it.id }[4]?.status, Status.OPEN)
    val (open, notOpen) = CONVERSATIONS.partition { it.status == Status.OPEN }
    expect("partition", open.size to notOpen.size, 3 to 2)
    expect("count { }", CONVERSATIONS.count { it.assignee == null }, 1)
}

// ─────────────────────────────────────────────────────────────────────────────────────────────
// 4. getOrPut
// ─────────────────────────────────────────────────────────────────────────────────────────────
// map.getOrPut(key) { default }: returns the existing value, or computes, STORES and returns the default.
// The go-to for "map of lists" (inboxes, postings, per-conversation TreeMaps).

fun lesson4GetOrPut() = scenario("4. getOrPut") {
    val inbox = HashMap<String, MutableList<Int>>()
    for (c in CONVERSATIONS) {
        inbox.getOrPut(c.assignee ?: "unassigned") { mutableListOf() } += c.id
    }
    expect("map of lists", inbox, mapOf("Alice" to listOf(1, 3), "Bob" to listOf(2, 5), "unassigned" to listOf(4)))

    // Counting: three equivalent ways.
    val words = listOf("refund", "pricing", "refund")
    val a = HashMap<String, Int>()
    words.forEach { a[it] = a.getOrDefault(it, 0) + 1 }
    val b = HashMap<String, Int>()
    words.forEach { b.merge(it, 1, Int::plus) }
    expect("getOrDefault == merge == groupingBy", a == b && b == words.groupingBy { it }.eachCount(), true)

    // Nested structures: per conversation, a sorted map of timestamp -> count.
    val events = HashMap<Int, TreeMap<Int, Int>>()
    listOf(1 to 60, 1 to 0, 2 to 30, 1 to 60).forEach { (conversation, ts) ->
        events.getOrPut(conversation) { TreeMap() }.merge(ts, 1, Int::plus)
    }
    expect("nested getOrPut", events[1], mapOf(0 to 1, 60 to 2))

    note("GOTCHA: map[key] ?: default does NOT insert; getOrPut does.")
    note("GOTCHA: on a ConcurrentHashMap getOrPut is not atomic – use computeIfAbsent.")
}

// ─────────────────────────────────────────────────────────────────────────────────────────────
// 5. TreeMap and PriorityQueue (java.util)
// ─────────────────────────────────────────────────────────────────────────────────────────────
// TreeMap: keys always sorted. O(log n) put/get + "nearest key" and range queries.
// PriorityQueue: binary heap. O(log n) add/poll, O(1) peek. MIN-heap by default.

fun lesson5TreeMapAndHeap() = scenario("5. TreeMap / PriorityQueue") {
    val timeline = TreeMap<Int, String>() // timestamp -> event
    timeline[120] = "assigned"
    timeline[0] = "created"
    timeline[300] = "closed"
    timeline[60] = "fin replied"
    expect("keys are sorted", timeline.keys.toList(), listOf(0, 60, 120, 300))
    expect("floorKey(100)   greatest <= 100", timeline.floorKey(100), 60)
    expect("ceilingKey(100) smallest >= 100", timeline.ceilingKey(100), 120)
    expect("higherKey(120)  smallest >  120", timeline.higherKey(120), 300)
    expect("lowerKey(0)     nothing below -> null", timeline.lowerKey(0), null)
    expect("floorEntry(100)?.value", timeline.floorEntry(100)?.value, "fin replied")
    expect("subMap(60, true, 120, true)", timeline.subMap(60, true, 120, true).values.toList(), listOf("fin replied", "assigned"))
    expect("headMap(120) is exclusive", timeline.headMap(120).keys.toList(), listOf(0, 60))
    expect("tailMap(120) is inclusive", timeline.tailMap(120).keys.toList(), listOf(120, 300))
    expect("firstKey / lastEntry", timeline.firstKey() to timeline.lastEntry().value, 0 to "closed")
    expect("descendingMap", timeline.descendingMap().keys.toList(), listOf(300, 120, 60, 0))

    val minHeap = PriorityQueue<Int>()
    minHeap.addAll(listOf(5, 1, 4))
    expect("peek = smallest", minHeap.peek(), 1)
    note("GOTCHA: iteration/toString is HEAP order, not sorted. Only poll() gives sorted order.")
    expect("toList() is not sorted", minHeap.toList(), listOf(1, 5, 4))
    expect("poll() x3", List(3) { minHeap.poll() }, listOf(1, 4, 5))
    expect("poll() on empty -> null", minHeap.poll(), null)

    val maxHeap = PriorityQueue<Int>(compareByDescending { it })
    maxHeap.addAll(listOf(5, 1, 4))
    expect("max-heap", maxHeap.poll(), 5)

    // Top-k pattern: keep a MIN-heap of size k ("worst of the best" on top). O(n log k).
    val load = mapOf("Alice" to 5, "Bob" to 9, "Carol" to 2, "Dan" to 7)
    val heap = PriorityQueue<Map.Entry<String, Int>>(compareBy { it.value })
    for (entry in load.entries) {
        heap += entry
        if (heap.size > 2) heap.poll()
    }
    expect("2 busiest agents", heap.sortedByDescending { it.value }.map { it.key }, listOf("Bob", "Dan"))
    note("GOTCHA: never change an element's priority while it's inside a heap/TreeSet: remove, change, re-add.")
}

// ─────────────────────────────────────────────────────────────────────────────────────────────
// 6. require / check and null safety
// ─────────────────────────────────────────────────────────────────────────────────────────────
// require(cond) { msg }   bad ARGUMENT from the caller     -> IllegalArgumentException
// check(cond) { msg }     object in the wrong STATE        -> IllegalStateException
// requireNotNull(x)/checkNotNull(x) return x as non-null; error("msg") throws IllegalStateException.

class Ticket {
    var closed = false
        private set

    fun close() {
        check(!closed) { "Ticket is already closed" }
        closed = true
    }
}

fun setLimit(limit: Int): Int {
    require(limit >= 0) { "limit must be >= 0, was $limit" }
    return limit
}

fun lesson6RequireCheckNulls() = scenario("6. require / check / null safety") {
    expect("valid argument", setLimit(3), 3)
    expectThrows("require -> IllegalArgumentException", IllegalArgumentException::class) { setLimit(-1) }
    val ticket = Ticket()
    ticket.close()
    expectThrows("check -> IllegalStateException", IllegalStateException::class) { ticket.close() }

    val owners: Map<Int, String> = mapOf(1 to "Alice")
    val owner: String? = owners[2] // map lookup returns a nullable type
    expect("?.  safe call", owner?.length, null)
    expect("?:  elvis default", owner ?: "unassigned", "unassigned")
    expect("?.let runs only when non-null", owners[1]?.let { "assigned to $it" }, "assigned to Alice")
    expect("toIntOrNull instead of try/catch", "abc".toIntOrNull() ?: -1, -1)
    expectThrows("getValue throws for a missing key", NoSuchElementException::class) { owners.getValue(2) }

    // Elvis with return/throw: the idiomatic "look up or bail out".
    fun ownerOrThrow(id: Int): String = owners[id] ?: throw IllegalArgumentException("Unknown conversation: $id")
    expectThrows("?: throw", IllegalArgumentException::class) { ownerOrThrow(9) }

    // Smart cast: after a null check the compiler treats the value as non-null.
    val maybe: String? = owners[1]
    if (maybe != null) expect("smart cast to String", maybe.uppercase(), "ALICE")
    expect("requireNotNull returns non-null", requireNotNull(owners[1]).length, 5)
    note("Avoid !! – it throws a bare NullPointerException with no useful message.")
}

// ─────────────────────────────────────────────────────────────────────────────────────────────
// 7. kotlin.test -> see src/test/kotlin/intercom/_0kotlinessentials/KotlinTestBasicsTest.kt
// 8. The main template -> see README.md ("Speed drill")
// ─────────────────────────────────────────────────────────────────────────────────────────────

fun main() {
    lesson1DataClasses()
    lesson2Comparators()
    lesson3Grouping()
    /*lesson4GetOrPut()
    lesson5TreeMapAndHeap()
    lesson6RequireCheckNulls()*/
    Harness.summary()
}
