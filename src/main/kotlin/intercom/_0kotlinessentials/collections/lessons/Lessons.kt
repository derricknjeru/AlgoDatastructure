package intercom._0kotlinessentials.collections.lessons

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.note
import intercom.Harness.scenario
import intercom._0kotlinessentials.collections.CONVERSATIONS
import intercom._0kotlinessentials.collections.Channel.CHAT
import intercom._0kotlinessentials.collections.Channel.EMAIL
import intercom._0kotlinessentials.collections.Channel.WHATSAPP
import intercom._0kotlinessentials.collections.Conversation
import intercom._0kotlinessentials.collections.ids
import kotlin.random.Random

/*
 * Part 2 – Collections, following https://kotlinlang.org/docs/collections-overview.html section by section.
 * Sample data (6 conversations) is in ../Data.kt. Every line below is checked when you run it:
 *
 *   ./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.collections.lessons.LessonsKt
 */

// ── Overview & collection types ──────────────────────────────────────────────────────────────
fun overviewAndTypes() = scenario("1. Overview & collection types") {
    // List: ordered, indexed, duplicates. Set: unique. Map: unique keys -> values.
    expect("List keeps duplicates and order", listOf(3, 1, 3), listOf(3, 1, 3))
    expect("Set drops duplicates (setOf keeps first-seen order)", setOf(3, 1, 3).toList(), listOf(3, 1))
    expect("Map key -> value", mapOf(1 to "Alice", 2 to "Bob")[2], "Bob")
    expect("sortedSetOf = TreeSet (sorted)", sortedSetOf(3, 1, 2).toList(), listOf(1, 2, 3))
    expect("sortedMapOf = TreeMap (sorted keys)", sortedMapOf(2 to "b", 1 to "a").keys.toList(), listOf(1, 2))
    expect("hashSetOf: fastest, but no order promise", hashSetOf(3, 1, 2).size, 3)

    note("Read-only (List) vs mutable (MutableList). Read-only is NOT immutable:")
    val backing = mutableListOf(1, 2)
    val readOnly: List<Int> = backing
    backing += 3
    expect("a read-only view still sees changes", readOnly, listOf(1, 2, 3))
    expect("toList() makes a defensive copy", backing.toList().also { backing += 4 }, listOf(1, 2, 3))
    note("Collection<T> is the parent of List and Set (size, contains, iteration). Map is separate.")
}

// ── Collections basics: create ───────────────────────────────────────────────────────────────
fun create() = scenario("2. Create") {
    expect("List(n) { index -> }", List(4) { it * it }, listOf(0, 1, 4, 9))
    expect("emptyList<T>() needs the type", emptyList<String>().size, 0)
    expect("listOfNotNull skips nulls", listOfNotNull("Alice", null, "Bob"), listOf("Alice", "Bob"))
    expect(
        "buildList { }",
        buildList {
            add("fin")
            addAll(listOf("Alice", "Bob"))
            if (size > 2) add("team")
        },
        listOf("fin", "Alice", "Bob", "team"),
    )
    expect("buildMap { }", buildMap { put("Alice", 2); put("Bob", 4) }, mapOf("Alice" to 2, "Bob" to 4))
    expect("`a to b` creates a Pair", ("Alice" to 2).second, 2)
    expect("from a range / a string", (1..3).toList() to "fin".toList(), listOf(1, 2, 3) to listOf('f', 'i', 'n'))
    expect("copy into another type", listOf(3, 1, 3).toSet(), setOf(1, 3))
    val counts = IntArray(3)
    counts[1]++
    expect("IntArray(3): fixed size, primitive, zero-filled", counts.toList(), listOf(0, 1, 0))
}

// ── Collections basics: iterate ──────────────────────────────────────────────────────────────
fun iterate() = scenario("3. Iterate") {
    val agents = listOf("Alice", "Bob", "Carol")
    val seen = mutableListOf<String>()
    for (agent in agents) seen += agent
    for (i in agents.indices) seen += "$i"
    for ((index, agent) in agents.withIndex()) seen += "$index:$agent"
    agents.forEachIndexed { index, agent -> if (index == 0) seen += "first=$agent" }
    for ((name, limit) in mapOf("Alice" to 2)) seen += "$name/$limit"
    for (i in agents.lastIndex downTo 0) seen += agents[i]
    expect(
        "for / indices / withIndex / forEachIndexed / map destructuring / backwards",
        seen,
        listOf("Alice", "Bob", "Carol", "0", "1", "2", "0:Alice", "1:Bob", "2:Carol", "first=Alice", "Alice/2", "Carol", "Bob", "Alice"),
    )

    note("GOTCHA: changing a list while for-looping over it -> ConcurrentModificationException")
    val open = mutableListOf(1, 2, 3, 4)
    expectThrows("remove inside a for loop", ConcurrentModificationException::class) {
        for (id in open) if (id == 2) open.remove(id)
    }
    val safe = mutableListOf(1, 2, 3, 4)
    safe.removeAll { it % 2 == 0 }
    expect("removeAll { } is the safe way", safe, listOf(1, 3))
    val viaIterator = mutableListOf(1, 2, 3, 4)
    val iterator = viaIterator.iterator()
    while (iterator.hasNext()) if (iterator.next() > 2) iterator.remove()
    expect("or MutableIterator.remove()", viaIterator, listOf(1, 2))
}

// ── Collections basics: change ───────────────────────────────────────────────────────────────
fun change() = scenario("4. Change") {
    val queue = mutableListOf("c1", "c2")
    queue += "c3"
    queue.add(0, "vip")
    queue.addAll(listOf("c4", "c5"))
    expect("+= / add(index, x) / addAll", queue, listOf("vip", "c1", "c2", "c3", "c4", "c5"))
    queue[1] = "c1-edited"
    queue.remove("c2")
    queue.removeAt(0)
    queue.removeAt(queue.lastIndex)
    expect("set / remove(element) / removeAt(index)", queue, listOf("c1-edited", "c3", "c4"))

    note("GOTCHA: MutableList<Int>.remove(1) removes the VALUE 1; removeAt(1) removes INDEX 1")
    val numbers = mutableListOf(5, 1, 7)
    numbers.remove(1)
    expect("remove(1) -> value", numbers, listOf(5, 7))
    numbers.removeAt(1)
    expect("removeAt(1) -> index", numbers, listOf(5))

    val ids = mutableListOf(1, 2, 3, 4, 5)
    ids.retainAll { it > 2 }
    expect("retainAll { }", ids, listOf(3, 4, 5))
    ids.clear()
    expect("clear()", ids.isEmpty(), true)

    var team = listOf("Alice") // read-only list in a var
    team = team + "Bob" // + always builds a NEW list: O(n) every time
    team += "Carol" // same as team = team + "Carol"
    expect("+ on a read-only list creates a copy", team, listOf("Alice", "Bob", "Carol"))
    expect("Set.add returns false for a duplicate", mutableSetOf("Alice").add("Alice"), false)

    val limits = mutableMapOf("Alice" to 2)
    limits["Bob"] = 4
    limits += "Carol" to 3
    limits.remove("Alice")
    expect("map[k] = v / += pair / remove", limits, mapOf("Bob" to 4, "Carol" to 3))

    val scores = mutableListOf(3, 1, 2)
    val copy = scores.sorted()
    scores.sortDescending()
    expect("sorted() returns a copy; sort()/sortDescending() mutate", copy to scores, listOf(1, 2, 3) to listOf(3, 2, 1))
}

// ── Collections operations: filter ───────────────────────────────────────────────────────────
fun filter() = scenario("5. Filter") {
    val c = CONVERSATIONS
    expect("filter", c.filter { it.channel == CHAT }.ids(), listOf(1, 4, 5))
    expect("filterNot", c.filterNot { it.channel == CHAT }.ids(), listOf(2, 3, 6))
    expect("filterIndexed", c.filterIndexed { index, _ -> index % 2 == 0 }.ids(), listOf(1, 3, 5))
    expect("filterNotNull", c.map { it.rating }.filterNotNull(), listOf(5, 3, 4, 2))
    val mixed: List<Any> = listOf(1, "two", 3.0, "four")
    expect("filterIsInstance<String>()", mixed.filterIsInstance<String>(), listOf("two", "four"))
    val (rated, unrated) = c.partition { it.rating != null }
    expect("partition -> Pair(matching, rest)", rated.ids() to unrated.ids(), listOf(1, 2, 5, 6) to listOf(3, 4))
    expect(
        "any / all / none",
        Triple(c.any { it.assignee == null }, c.all { it.messages > 0 }, c.none { it.channel == WHATSAPP }),
        Triple(true, true, false),
    )
    expect("count { }", c.count { it.customer == "acme" }, 3)
}

// ── Collections operations: order ────────────────────────────────────────────────────────────
fun order() = scenario("6. Order") {
    val c = CONVERSATIONS
    expect("sorted / sortedDescending", listOf(3, 1, 2).sorted() to listOf(3, 1, 2).sortedDescending(), listOf(1, 2, 3) to listOf(3, 2, 1))
    expect("sortedBy", c.sortedBy { it.messages }.ids(), listOf(4, 3, 1, 5, 2, 6))
    expect("sortedByDescending", c.sortedByDescending { it.messages }.ids(), listOf(6, 2, 5, 1, 3, 4))
    expect("sortedWith(compareBy(…, …)) – customer, then most messages", c.sortedWith(compareBy({ it.customer }, { -it.messages })).ids(), listOf(6, 1, 3, 2, 5, 4))

    note("Nullable keys: nulls sort FIRST by default")
    expect("sortedBy { it.rating }", c.sortedBy { it.rating }.ids(), listOf(3, 4, 6, 2, 5, 1))
    expect(
        "best rating first, nulls last",
        c.sortedWith(compareBy<Conversation, Int?>(nullsLast(reverseOrder())) { it.rating }).ids(),
        listOf(1, 5, 2, 6, 3, 4),
    )
    expect("reversed() is a copy", listOf(1, 2, 3).reversed(), listOf(3, 2, 1))
    val live = mutableListOf(1, 2, 3)
    val view = live.asReversed()
    live += 4
    expect("asReversed() is a live view", view, listOf(4, 3, 2, 1))
    expect("shuffled(Random(seed)) is reproducible", listOf(1, 2, 3, 4).shuffled(Random(42)) == listOf(1, 2, 3, 4).shuffled(Random(42)), true)
    note("Kotlin sorting is STABLE: equal keys keep their original order (ids 3, 4 above).")
}

// ── Collections operations: transform ────────────────────────────────────────────────────────
fun transform() = scenario("7. Transform") {
    val c = CONVERSATIONS
    expect("map", c.map { it.messages }, listOf(4, 9, 2, 1, 6, 12))
    expect("mapIndexed", c.take(2).mapIndexed { index, conv -> "$index:${conv.id}" }, listOf("0:1", "1:2"))
    expect("mapNotNull", c.mapNotNull { it.assignee }.distinct(), listOf("Alice", "Bob", "Carol"))
    expect("flatMap", listOf("Alice:billing,api", "Bob:api").flatMap { it.substringAfter(":").split(",") }, listOf("billing", "api", "api"))
    expect("flatten", listOf(listOf(1, 2), listOf(3)).flatten(), listOf(1, 2, 3))
    expect("zip -> pairs", listOf("Alice", "Bob").zip(listOf(2, 4)), listOf("Alice" to 2, "Bob" to 4))
    expect("zip with transform", listOf(1, 2, 3).zip(listOf(10, 20, 30)) { a, b -> a + b }, listOf(11, 22, 33))
    expect("unzip", listOf("Alice" to 2, "Bob" to 4).unzip(), listOf("Alice", "Bob") to listOf(2, 4))
    expect("associate { key to value }", c.take(2).associate { it.id to it.customer }, mapOf(1 to "acme", 2 to "globex"))
    expect("associateBy { key }", c.associateBy { it.id }[6]?.assignee, "Carol")
    expect("associateWith { value }", listOf("Alice", "Bob").associateWith { it.length }, mapOf("Alice" to 5, "Bob" to 3))
    expect("joinToString(…)", c.take(3).joinToString(separator = " | ", prefix = "[", postfix = "]") { "#${it.id}" }, "[#1 | #2 | #3]")
    expect("joinToString(limit = 2)", c.joinToString(limit = 2) { "#${it.id}" }, "#1, #2, ...")
    expect("mapValues / mapKeys", mapOf("alice" to 2).mapValues { it.value * 2 }.mapKeys { it.key.uppercase() }, mapOf("ALICE" to 4))
    expect("digits", "2024".map { it.digitToInt() }, listOf(2, 0, 2, 4))
}

// ── Collections operations: group ────────────────────────────────────────────────────────────
fun group() = scenario("8. Group (see Part 1, lesson 3)") {
    val c = CONVERSATIONS
    expect("groupBy(key, value)", c.groupBy({ it.customer }, { it.id }), mapOf("acme" to listOf(1, 3, 6), "globex" to listOf(2, 5), "initech" to listOf(4)))
    expect("groupingBy().eachCount()", c.groupingBy { it.channel }.eachCount(), mapOf(CHAT to 3, EMAIL to 2, WHATSAPP to 1))
    expect(
        "groupingBy().fold – total messages per customer",
        c.groupingBy { it.customer }.fold(0) { total, conv -> total + conv.messages },
        mapOf("acme" to 18, "globex" to 15, "initech" to 1),
    )
    expect(
        "groupingBy().reduce – longest conversation per channel",
        c.groupingBy { it.channel }.reduce { _, best, conv -> if (conv.messages > best.messages) conv else best }.mapValues { it.value.id },
        mapOf(CHAT to 5, EMAIL to 6, WHATSAPP to 3),
    )
}

// ── Collections operations: aggregate ────────────────────────────────────────────────────────
fun aggregate() = scenario("9. Aggregate") {
    val c = CONVERSATIONS
    val messages = c.map { it.messages } // [4, 9, 2, 1, 6, 12]
    expect("count / sum", messages.count() to messages.sum(), 6 to 34)
    expect("average", c.mapNotNull { it.rating }.average(), 3.5)
    expect("sumOf { }", c.sumOf { it.messages }, 34)
    expect("minOrNull / maxOrNull", messages.minOrNull() to messages.maxOrNull(), 1 to 12)
    expect("maxByOrNull { } returns the ELEMENT", c.maxByOrNull { it.messages }?.id, 6)
    expect("maxOf { } returns the VALUE", c.maxOf { it.messages }, 12)
    expect("minOfOrNull on an empty list", emptyList<Int>().minOfOrNull { it }, null)
    expectThrows("max() of an empty list throws", NoSuchElementException::class) { emptyList<Int>().max() }
    expect("fold(initial) { acc, x -> }", messages.fold(0) { acc, m -> acc + m }, 34)
    expect("reduce (starts from the first element)", messages.reduce { a, b -> maxOf(a, b) }, 12)
    expect("runningFold – prefix sums", messages.runningFold(0) { acc, m -> acc + m }, listOf(0, 4, 13, 15, 16, 22, 34))
    expect("runningReduce", messages.runningReduce { acc, m -> acc + m }, listOf(4, 13, 15, 16, 22, 34))
}

// ── Collections operations: combine (plus / minus / set operations) ─────────────────────────
fun combine() = scenario("10. Combine") {
    val c = CONVERSATIONS
    val chat = c.filter { it.channel == CHAT }.map { it.customer }.toSet() // acme, initech, globex
    val email = c.filter { it.channel == EMAIL }.map { it.customer }.toSet() // globex, acme
    expect("union", chat union email, setOf("acme", "globex", "initech"))
    expect("intersect", chat intersect email, setOf("acme", "globex"))
    expect("subtract", chat subtract email, setOf("initech"))
    expect("list + list - element", listOf(1, 2) + listOf(3) - 1, listOf(2, 3))
    expect("minus a single element removes the FIRST occurrence", listOf(1, 2, 1, 3) - 1, listOf(2, 1, 3))
    expect("minus a collection removes ALL occurrences", listOf(1, 2, 1, 3) - listOf(1), listOf(2, 3))
    expect("map + pair (overwrites)", mapOf("Alice" to 2) + ("Alice" to 5) + ("Bob" to 3), mapOf("Alice" to 5, "Bob" to 3))
    expect("map - key", mapOf("Alice" to 2, "Bob" to 3) - "Alice", mapOf("Bob" to 3))
}

// ── Collections operations: retrieve single elements ─────────────────────────────────────────
fun retrieveSingle() = scenario("11. Retrieve single elements") {
    val c = CONVERSATIONS
    val ids = c.ids()
    expect("[i] / getOrNull / getOrElse", Triple(ids[0], ids.getOrNull(99), ids.getOrElse(99) { -1 }), Triple(1, null, -1))
    expectThrows("[99] throws", IndexOutOfBoundsException::class) { ids[99] }
    expect("first() / last()", ids.first() to ids.last(), 1 to 6)
    expect("first { } / firstOrNull { }", c.first { it.assignee == null }.id to c.firstOrNull { it.messages > 100 }, 4 to null)
    expectThrows("first { } with no match throws", NoSuchElementException::class) { c.first { it.messages > 100 } }
    expect("find (= firstOrNull) / findLast", c.find { it.customer == "acme" }?.id to c.findLast { it.customer == "acme" }?.id, 1 to 6)
    expect("single { } – exactly one match", c.single { it.channel == WHATSAPP }.id, 3)
    expect("singleOrNull – null when 0 OR 2+ match", c.singleOrNull { it.channel == EMAIL }, null)
    expect("indexOf / indexOfFirst { }", ids.indexOf(5) to c.indexOfFirst { it.assignee == "Bob" }, 4 to 1)
    expect("in / contains", (3 in ids) to ids.contains(42), true to false)
    expect("elementAt (any Iterable)", setOf("a", "b").elementAt(1), "b")
}

// ── Collections operations: retrieve collection parts ────────────────────────────────────────
fun retrieveParts() = scenario("12. Retrieve collection parts") {
    val c = CONVERSATIONS
    val ids = c.ids() // [1, 2, 3, 4, 5, 6]
    expect("take / takeLast", ids.take(2) to ids.takeLast(2), listOf(1, 2) to listOf(5, 6))
    expect("drop / dropLast", ids.drop(4) to ids.dropLast(4), listOf(5, 6) to listOf(1, 2))
    expect("takeWhile / dropWhile", ids.takeWhile { it < 3 } to ids.dropWhile { it < 3 }, listOf(1, 2) to listOf(3, 4, 5, 6))
    expect("slice(range)", ids.slice(1..3), listOf(2, 3, 4))
    expect("chunked – pages", ids.chunked(4), listOf(listOf(1, 2, 3, 4), listOf(5, 6)))
    expect("chunked { transform }", ids.chunked(2) { it.sum() }, listOf(3, 7, 11))
    expect("windowed(3) { } – sliding windows", c.map { it.messages }.windowed(3) { it.sum() }, listOf(15, 12, 9, 19))
    expect("windowed(size, step, partialWindows)", ids.windowed(4, 3, partialWindows = true), listOf(listOf(1, 2, 3, 4), listOf(4, 5, 6)))
    expect("zipWithNext – gaps between timestamps", listOf(0, 60, 180).zipWithNext { a, b -> b - a }, listOf(60, 120))
    expect("distinct / distinctBy", listOf(1, 1, 2).distinct() to c.distinctBy { it.customer }.ids(), listOf(1, 2) to listOf(1, 2, 4))

    note("GOTCHA: subList is a VIEW – changing it changes the original")
    val all = mutableListOf(1, 2, 3, 4)
    all.subList(0, 2).clear()
    expect("clearing the view removed 1 and 2", all, listOf(3, 4))
}

// ── Type-specific: list ──────────────────────────────────────────────────────────────────────
fun listSpecific() = scenario("13. List-specific operations") {
    val sorted = listOf(0, 60, 120, 300)
    expect("binarySearch – found -> index", sorted.binarySearch(120), 2)
    expect("binarySearch – missing -> -(insertionPoint) - 1", sorted.binarySearch(100), -3)
    expect("insertion point from that", -(sorted.binarySearch(100) + 1), 2)
    expect(
        "binarySearch { compare } on a list sorted by a key",
        CONVERSATIONS.sortedBy { it.messages }.binarySearch { it.messages.compareTo(6) },
        3,
    )
    expect("indices / lastIndex", sorted.indices to sorted.lastIndex, 0..3 to 3)
    expect("lastIndexOf", listOf(1, 2, 1).lastIndexOf(1), 2)
    val slots = MutableList(3) { "" }
    slots.fill("free")
    expect("MutableList(n) { } + fill", slots, listOf("free", "free", "free"))
    note("ArrayList: get/set/add-at-end O(1); add/remove at the FRONT is O(n) – use ArrayDeque for queues (Part 3).")
}

// ── Type-specific: map ───────────────────────────────────────────────────────────────────────
fun mapSpecific() = scenario("14. Map-specific operations") {
    val limits = mapOf("Alice" to 2, "Bob" to 4, "Carol" to 3)
    expect(
        "[k] / getOrDefault / getOrElse",
        Triple(limits["Bob"], limits.getOrDefault("Zed", 2), limits.getOrElse("Zed") { 0 }),
        Triple(4, 2, 0),
    )
    expectThrows("getValue on a missing key throws", NoSuchElementException::class) { limits.getValue("Zed") }
    expect("keys (Set) / values (Collection)", limits.keys to limits.values.toList(), setOf("Alice", "Bob", "Carol") to listOf(2, 4, 3))
    expect("filterValues / filterKeys", limits.filterValues { it > 2 } to limits.filterKeys { it.startsWith("A") }, mapOf("Bob" to 4, "Carol" to 3) to mapOf("Alice" to 2))
    expect("filter { (k, v) -> }", limits.filter { (name, limit) -> name.length > 3 && limit > 2 }, mapOf("Carol" to 3))
    expect("maxByOrNull { it.value }?.key", limits.maxByOrNull { it.value }?.key, "Bob")
    expect("entries sorted by value", limits.entries.sortedByDescending { it.value }.map { it.key }, listOf("Bob", "Carol", "Alice"))
    expect("toSortedMap(reverseOrder())", limits.toSortedMap(reverseOrder()).keys.toList(), listOf("Carol", "Bob", "Alice"))
    expect("toList() -> pairs", limits.toList().first(), "Alice" to 2)
    expect("in / containsValue", ("Bob" in limits) to limits.containsValue(4), true to true)

    val m = limits.toMutableMap()
    m["Dan"] = 1
    m.putAll(mapOf("Eve" to 2))
    m.remove("Alice")
    m.merge("Bob", 1, Int::plus)
    m.compute("Carol") { _, old -> (old ?: 0) * 10 }
    expect("put / putAll / remove / merge / compute", m, mapOf("Bob" to 5, "Carol" to 30, "Dan" to 1, "Eve" to 2))
    expect("withDefault { } + getValue", limits.withDefault { 2 }.getValue("Zed"), 2)
    note("mapOf / mutableMapOf keep insertion order (LinkedHashMap). HashMap promises no order.")
}

// ── Sequences ────────────────────────────────────────────────────────────────────────────────
fun sequences() = scenario("15. Sequences (lazy)") {
    val c = CONVERSATIONS
    var eagerSteps = 0
    var lazySteps = 0
    val eager = c.map { eagerSteps++; it.messages }.first { it > 5 }
    val lazy = c.asSequence().map { lazySteps++; it.messages }.first { it > 5 }
    expect("same answer", eager to lazy, 9 to 9)
    expect("eager mapped all 6; lazy stopped after 2", eagerSteps to lazySteps, 6 to 2)
    expect("generateSequence – retry backoff", generateSequence(100) { it * 2 }.take(4).toList(), listOf(100, 200, 400, 800))
    expect("sequence { yield }", sequence { yield("fin"); yieldAll(listOf("Alice", "Bob")) }.toList(), listOf("fin", "Alice", "Bob"))
    expect("huge input, early exit", (1..1_000_000).asSequence().filter { it % 2 == 0 }.take(3).toList(), listOf(2, 4, 6))
    note("Nothing runs until a terminal op (toList, first, count, sum…). Small lists: plain collections are fine.")
}

// ── Ranges ───────────────────────────────────────────────────────────────────────────────────
fun ranges() = scenario("16. Ranges") {
    expect("1..5 is inclusive", (1..5).toList(), listOf(1, 2, 3, 4, 5))
    expect("0 until 3 == 0..<3 (exclusive end)", (0 until 3).toList() == (0..<3).toList(), true)
    expect("downTo / step", (10 downTo 0 step 5).toList(), listOf(10, 5, 0))
    expect("in / !in – business hours", (9 in 9..17) to (18 !in 9..17), true to true)
    expect("first / last / count / sum", listOf((9..17).first, (9..17).last, (9..17).count(), (1..10).sum()), listOf(9, 17, 9, 55))
    expect("reversed()", (1..3).reversed().toList(), listOf(3, 2, 1))
    expect("char ranges", ('a'..'e').joinToString(""), "abcde")
    expect("coerceIn / coerceAtMost – clamp a limit", Triple((-3).coerceIn(0, 10), 15.coerceIn(0, 10), 7.coerceAtMost(5)), Triple(0, 10, 5))
    expect("start > end -> empty", (5..1).isEmpty(), true)
    expect("ranges are Iterable: hours outside 9-17", (0..<24).count { it !in 9..17 }, 15)
}

fun main() {
    overviewAndTypes()
    create()
    iterate()
    change()
    filter()
    order()
    transform()
    group()
    aggregate()
    combine()
    retrieveSingle()
    retrieveParts()
    listSpecific()
    mapSpecific()
    sequences()
    ranges()
    Harness.summary()
}
