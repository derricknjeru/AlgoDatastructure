# 0 – Kotlin essentials for the Intercom round

The Kotlin features the practical-coding problems (#1–#8) actually use. Learn them here first,
then you won't lose interview minutes to syntax.

Every part has the same three files: **`lessons/Lessons.kt`** (runnable, commented examples, every
line checked – read + run), **`practice/Practice.kt`** (drills with `TODO()`s – your turn) and
**`solution/Solution.kt`** (answers).

| Part | Folder | Covers | Drills |
|------|--------|--------|--------|
| 1 – Essentials | [`lessons/`](lessons/Lessons.kt), [`practice/`](practice/Practice.kt), [`solution/`](solution/Solution.kt) | data classes, comparators, grouping, `getOrPut`, `TreeMap`/`PriorityQueue`, `require`/`check`, null safety, `kotlin.test` | 13 |
| 2 – Collections | [`collections/`](collections/lessons/Lessons.kt) | The whole [Collections docs](https://kotlinlang.org/docs/collections-overview.html) section: types, create, iterate, change, filter, order, transform, group, aggregate, combine, retrieve, list/map ops, sequences, ranges | 14 |
| 3 – Queues | [`queues/`](queues/lessons/Lessons.kt) | Queue, stack, deque (`ArrayDeque`), monotonic deque, `java.util.Queue` API, `PriorityQueue`, BFS, sliding window, thread-safe queues | 7 |
| 4 – Scope functions | [`scopefunctions/`](scopefunctions/lessons/Lessons.kt) | `let`, `run`, `with`, `apply`, `also`, `takeIf`/`takeUnless`, gotchas | 6 |

`src/test/kotlin/intercom/_0kotlinessentials/KotlinTestBasicsTest.kt` is lesson 7 of Part 1: `kotlin.test` as a copy-paste template.

```bash
# Part 1 (swap lessons.LessonsKt for practice.PracticeKt / solution.SolutionKt)
./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.lessons.LessonsKt
# Parts 2–4: insert the part name
./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.collections.lessons.LessonsKt
./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.queues.practice.PracticeKt
./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.scopefunctions.solution.SolutionKt
# kotlin.test example
./gradlew test --tests 'intercom._0kotlinessentials.*'
```

---

## Part 1 cheat sheet

### 1. data classes and `copy()`
```kotlin
data class Agent(val name: String, val active: Int = 0, val limit: Int = 2)
val busier = alice.copy(active = alice.active + 1)   // new object, only `active` changed
val (name, active) = busier                          // destructuring
```
- Free `equals`/`hashCode`/`toString`/`copy` from **primary-constructor** properties only.
- `copy()` is **shallow** – a `MutableList` field is shared. Copy it too: `copy(members = members.toMutableList())`.
- Used in: Problem 1 preview (`agentsMap.values.map { it.copy() }`).

### 2. `compareBy` / `thenBy`
```kotlin
val priority = compareBy<Agent> { it.active }.thenBy { it.lastAssigned }.thenBy { it.order }
agents.sortedWith(priority); agents.minWithOrNull(priority)
compareByDescending<Entry> { it.value }.thenBy { it.key }     // most used, then A→Z
```
- `sortedBy { }` for one key; `.reversed()` flips the whole comparator.
- **TreeSet/TreeMap drop elements that compare equal** – always end with a unique key.
- Used in: Problems 1, 6, 7.

### 3. `groupBy` and `groupingBy().eachCount()`
```kotlin
conversations.groupBy({ it.assignee ?: "unassigned" }, { it.id })  // Map<String, List<Int>>
conversations.groupingBy { it.status }.eachCount()                 // Map<Status, Int>
words.groupingBy { it }.eachCount()                                 // word frequencies
```
- `groupingBy` is lazy – `eachCount` / `fold` / `reduce` build no lists.
- Relatives: `associateBy` (one per key), `partition`, `count { }`.
- Used in: Problems 6, 3 follow-ups.

### 4. `getOrPut`
```kotlin
inboxes.getOrPut(participant) { mutableListOf() } += message
counts.merge(word, 1, Int::plus)            // counting alternative
```
- Inserts the default if missing (`map[key] ?: default` does **not** insert).
- On `ConcurrentHashMap` use `computeIfAbsent` (atomic).
- Used in: Problems 2, 3, 6, 7.

### 5. `TreeMap` / `PriorityQueue`
```kotlin
val t = TreeMap<Int, String>()
t.floorKey(x)  t.ceilingKey(x)  t.lowerKey(x)  t.higherKey(x)  t.floorEntry(x)?.value
t.subMap(from, true, to, true)  t.headMap(k) /*exclusive*/  t.tailMap(k) /*inclusive*/
t.firstKey()  t.lastEntry()  t.pollFirstEntry()  t.descendingMap()

val minHeap = PriorityQueue<Int>()                         // smallest first
val maxHeap = PriorityQueue<Int>(compareByDescending { it })
heap += x; heap.peek(); heap.poll()                          // poll() -> null when empty
```
- TreeMap: O(log n) everything, keys always sorted. Heap: O(log n) add/poll, O(1) peek.
- Heap **iteration order is not sorted** – only `poll()` is.
- Top-k: min-heap of size k, `poll()` when `size > k` → O(n log k).
- Never change an element's priority while it's inside: remove → change → re-add.
- Used in: Problems 1 (TreeSet/heap), 3 (TreeMap), 6 (top-k), 7 (sorted set).

### 6. `require` / `check` and null safety
```kotlin
require(limit >= 0) { "limit must be >= 0, was $limit" }   // bad argument -> IllegalArgumentException
check(!closed) { "already closed" }                         // bad state    -> IllegalStateException
val agent = agents[name] ?: throw IllegalArgumentException("Unknown agent: $name")
val agent = agents[name] ?: return null
owner?.length   owner ?: "unassigned"   owners[1]?.let { … }   "x".toIntOrNull()
```
- `requireNotNull(x)` / `checkNotNull(x)` return `x` as non-null. `error("msg")` throws `IllegalStateException`.
- Avoid `!!`. Map lookups return nullable; `getValue(key)` throws if missing.

### 7. `kotlin.test`
```kotlin
class AgentTest {
    @BeforeTest fun setUp() { … }
    @Test fun `assigns to the agent with fewest conversations`() {
        assertEquals(expected, actual)                       // EXPECTED first
        val e = assertFailsWith<IllegalArgumentException> { setLimit("Zed", 1) }
        assertEquals("Unknown agent: Zed", e.message)
        val v: String = assertNotNull(map[1])                // returns non-null
    }
}
```
- Lives in `src/test/kotlin/<same package>`; run with `./gradlew test --tests 'pkg.*'`.
- Lists/maps compare by content; arrays need `assertContentEquals`.

---

## Speed drill – the `main` template

In the interview, write the example as a runnable check **before** the implementation. Practise
typing this from memory in under 2 minutes:

```kotlin
fun <T> check(label: String, actual: T, expected: T) =
    println("${if (actual == expected) "PASS" else "FAIL"} $label: $actual${if (actual != expected) " (expected $expected)" else ""}")

fun main() {
    val system = AssignmentSystem(listOf("Alice", "Bob", "Charlie"))
    system.setLimit("Bob", 4)
    check("preview(4)", system.previewAssignments(4), listOf("Alice", "Bob", "Charlie", "Alice"))
    check("assign(101)", system.assign(101), "Alice")
    // edge cases: nobody has capacity, unknown agent, preview doesn't mutate
}
```

(In this repo, `intercom.Harness` gives you `scenario` / `expect` / `expectThrows` / `summary`.)

---

## Part 2 cheat sheet – Collections

| Docs section | Know these |
|--------------|-----------|
| Types | `List` (ordered, dupes) · `Set` (unique; `setOf` keeps insertion order) · `Map` · `sortedSetOf`/`sortedMapOf` = Tree*. Read-only ≠ immutable. |
| Create | `listOf` `mutableListOf` `List(n) { }` `buildList { }` `buildMap { }` `listOfNotNull` `emptyList<T>()` `a to b` `toList()/toSet()/toMutableList()` |
| Iterate | `for (x in xs)` `indices` `withIndex()` `forEachIndexed` `for ((k, v) in map)` `lastIndex downTo 0`. Don't remove inside `for` → `removeAll { }` |
| Change | `+=` `add(i, x)` `addAll` `xs[i] = x` `remove(x)` vs `removeAt(i)` `retainAll { }` `clear()` `sort()` vs `sorted()` |
| Filter | `filter` `filterNot` `filterIndexed` `filterNotNull` `filterIsInstance<T>()` `partition` `any` `all` `none` `count { }` |
| Order | `sorted` `sortedBy` `sortedByDescending` `sortedWith(compareBy(…))` `nullsLast(reverseOrder())` `reversed()` vs `asReversed()` – sorting is stable |
| Transform | `map` `mapIndexed` `mapNotNull` `flatMap` `flatten` `zip` `unzip` `associate` `associateBy` `associateWith` `joinToString(sep, prefix, postfix, limit)` `mapValues` `mapKeys` |
| Group | `groupBy(key, value)` `groupingBy { }.eachCount() / fold / reduce` |
| Aggregate | `sum` `sumOf` `average` `minOrNull` `maxByOrNull` (element) vs `maxOf` (value) `fold` `reduce` `runningFold` (prefix sums). `max()` on empty throws |
| Combine | `union` `intersect` `subtract` `list + x` `list - x` (first) vs `list - listOf(x)` (all) `map + (k to v)` `map - k` |
| Single element | `getOrNull` `getOrElse` `first`/`firstOrNull { }` `find` `findLast` `single`/`singleOrNull` `indexOf` `indexOfFirst` `in` |
| Parts | `take` `drop` `takeWhile` `dropWhile` `slice` `chunked` (pages) `windowed` (sliding) `zipWithNext` (gaps) `distinct` `distinctBy`. `subList` is a view |
| List-specific | `binarySearch` (missing → `-(insertion) - 1`) `lastIndexOf` `fill` – front insert/remove is O(n) |
| Map-specific | `getOrDefault` `getOrElse` `getValue` `filterValues` `filterKeys` `maxByOrNull { it.value }` `toSortedMap` `merge` `compute` `withDefault` |
| Sequences | `asSequence()` → lazy, element by element, stops early. `generateSequence` `sequence { yield() }`. Nothing runs before a terminal op |
| Ranges | `1..5` `0 until n` / `0..<n` `downTo` `step` `in` `coerceIn` `('a'..'z')` |

## Part 3 cheat sheet – Queues, stacks, deques

```kotlin
val queue = ArrayDeque<String>()     // kotlin.collections – O(1) at both ends
queue.addLast(x); queue.removeFirst(); queue.first(); queue.removeFirstOrNull()   // FIFO
stack.addLast(x); stack.removeLast(); stack.last();  stack.removeLastOrNull()    // LIFO
deque.addFirst(x); deque.addLast(x)                                               // both ends
agents.removeFirst().also { agents.addLast(it) }                                  // round-robin
val pq = PriorityQueue(compareBy<Ticket>({ it.priority }, { it.arrivedAt }))      // most urgent first
```
- **BFS:** `ArrayDeque` + `seen` set; mark seen when you **enqueue**. Use a stack instead and it's DFS.
- **Monotonic deque:** sliding-window max/min in O(n). Store indices; pop the back while it can't win; pop the front when it leaves the window.
- **Sliding log:** append timestamps, pop the front while it's too old (rate limiter, hit counter).
- **`java.util.Queue` API:** `offer`/`poll`/`peek` return `false`/`null`; `add`/`remove`/`element` throw. `java.util.ArrayDeque` rejects `null`.
- **Avoid:** `MutableList.removeAt(0)` as a queue (O(n)) and `java.util.Stack` (legacy).
- **Threads:** `LinkedBlockingQueue` (`put`/`take` block → producer/consumer), `ConcurrentLinkedQueue` (non-blocking). The others aren't thread-safe.

## Part 4 cheat sheet – Scope functions

| Function | Object is | Returns | Use for |
|----------|-----------|---------|---------|
| `let` | `it` | lambda result | `x?.let { … } ?: default`, transforming a value |
| `run` | `this` | lambda result | computing a result from an object's members |
| `with(x)` | `this` | lambda result | several calls on one object |
| `apply` | `this` | the object | configuring/building an object |
| `also` | `it` | the object | side effects: logging, validation, `a = b.also { b = a }` |
| `takeIf` / `takeUnless` | `it` | object or `null` | `text.toIntOrNull()?.takeIf { it > 0 }` |

Pick by: object back (`apply`/`also`) or a result (`let`/`run`/`with`)? `this` or `it`? Don't nest them.

---

## Suggested routine (≈ 30 min/day)

| Day | Do |
|-----|----|
| 1 | Part 1: lessons 1–3, drills 1–3. |
| 2 | Part 1: lessons 4–6, drills 4–6. Read `KotlinTestBasicsTest.kt`; write 3 tests for your Problem 1 `AssignmentSystem`. |
| 3 | Part 2: lessons 1–8, then drills 1–5. |
| 4 | Part 2: lessons 9–16, then drills 6–14. |
| 5 | Part 3: all lessons, drills 1–7. |
| 6 | Part 4: lessons + drills. Then reset every `Practice.kt` (`git checkout -- <file>`; works once the stubs are committed) and redo all drills against a timer. |
