# Heap / Priority Queue Essentials in Kotlin

A **heap** is a tree-based data structure designed for quickly accessing the smallest or largest element.

- A **min-heap** keeps the smallest element at the top.
- A **max-heap** keeps the largest element at the top.
- Kotlin/JVM uses Java's `PriorityQueue`.
- `PriorityQueue` is a **min-heap by default**.

```kotlin
import java.util.PriorityQueue
```

## 1. Min-Heap

```kotlin
val minHeap = PriorityQueue<Int>()

minHeap.offer(5)
minHeap.offer(2)
minHeap.offer(8)
minHeap.offer(1)

println(minHeap.peek()) // 1

while (minHeap.isNotEmpty()) {
    print("${minHeap.poll()} ")
}

// Output: 1 2 5 8
```

## 2. Essential Operations

| Operation | Kotlin | Time complexity |
|---|---|---:|
| Insert an element | `offer(value)` or `add(value)` | `O(log n)` |
| Read the top element | `peek()` | `O(1)` |
| Remove the top element | `poll()` | `O(log n)` |
| Get the number of elements | `size` | `O(1)` |
| Search for an element | `contains(value)` | `O(n)` |
| Remove a specific element | `remove(value)` | `O(n)` |

`peek()` and `poll()` return `null` when the priority queue is empty.

```kotlin
val heap = PriorityQueue<Int>()

println(heap.peek()) // null
println(heap.poll()) // null
```

## 3. Max-Heap

To create a max-heap, reverse the natural ordering.

```kotlin
val maxHeap = PriorityQueue<Int>(reverseOrder())

maxHeap.addAll(listOf(5, 2, 8, 1))

while (maxHeap.isNotEmpty()) {
    print("${maxHeap.poll()} ")
}

// Output: 8 5 2 1
```

You can also use `compareByDescending`:

```kotlin
val maxHeap = PriorityQueue<Int>(
    compareByDescending { it }
)
```

Avoid subtraction-based comparators:

```kotlin
// Avoid this because subtraction can overflow.
val maxHeap = PriorityQueue<Int> { a, b ->
    b - a
}
```

Prefer:

```kotlin
val maxHeap = PriorityQueue<Int> { a, b ->
    b.compareTo(a)
}
```

## 4. Priority Queue with Custom Objects

Suppose jobs with lower priority numbers should run first.

```kotlin
data class Job(
    val name: String,
    val priority: Int
)

val jobs = PriorityQueue<Job>(
    compareBy<Job> { it.priority }
)

jobs.offer(Job("Send email", 3))
jobs.offer(Job("Fix production", 1))
jobs.offer(Job("Write tests", 2))

while (jobs.isNotEmpty()) {
    println(jobs.poll())
}
```

Output:

```text
Job(name=Fix production, priority=1)
Job(name=Write tests, priority=2)
Job(name=Send email, priority=3)
```

## 5. Comparing Multiple Properties

If two jobs have the same priority, they can be ordered by name.

```kotlin
val jobs = PriorityQueue<Job>(
    compareBy<Job> { it.priority }
        .thenBy { it.name }
)
```

For descending priority:

```kotlin
val jobs = PriorityQueue<Job>(
    compareByDescending<Job> { it.priority }
        .thenBy { it.name }
)
```

## 6. Common Pattern: K Largest Elements

To find the `k` largest values efficiently, maintain a min-heap of size `k`.

```kotlin
import java.util.PriorityQueue

fun kLargest(numbers: IntArray, k: Int): List<Int> {
    require(k in 1..numbers.size)

    val minHeap = PriorityQueue<Int>()

    for (number in numbers) {
        minHeap.offer(number)

        if (minHeap.size > k) {
            minHeap.poll()
        }
    }

    return minHeap.toList().sortedDescending()
}

fun main() {
    val numbers = intArrayOf(7, 2, 9, 4, 1, 8)

    println(kLargest(numbers, 3))
}
```

Output:

```text
[9, 8, 7]
```

Complexity:

- Time: `O(n log k)`
- Space: `O(k)`

## 7. Finding the Kth Largest Element

The smallest element in a min-heap containing the `k` largest values is the kth-largest value.

```kotlin
import java.util.PriorityQueue

fun findKthLargest(numbers: IntArray, k: Int): Int {
    require(k in 1..numbers.size)

    val minHeap = PriorityQueue<Int>()

    for (number in numbers) {
        minHeap.offer(number)

        if (minHeap.size > k) {
            minHeap.poll()
        }
    }

    return minHeap.peek()
}

fun main() {
    val numbers = intArrayOf(3, 2, 1, 5, 6, 4)

    println(findKthLargest(numbers, 2))
}
```

Output:

```text
5
```

## 8. Important Pitfalls

### Iteration is not sorted

A priority queue guarantees only that the highest-priority element is at the top.

```kotlin
val heap = PriorityQueue<Int>()

heap.addAll(listOf(5, 1, 4, 2))

println(heap)
```

The printed internal order is not guaranteed to be sorted.

To retrieve elements in sorted order, repeatedly call `poll()`:

```kotlin
while (heap.isNotEmpty()) {
    println(heap.poll())
}
```

### Avoid mutating priority fields

Consider this mutable class:

```kotlin
data class Task(
    val name: String,
    var priority: Int
)
```

Changing `priority` after inserting a task does not automatically reposition it inside the heap.

```kotlin
val queue = PriorityQueue<Task>(
    compareBy { it.priority }
)

val task = Task("Deploy", 5)

queue.offer(task)

// This does not reorganize the queue.
task.priority = 1
```

Remove, update, and reinsert the object instead:

```kotlin
queue.remove(task)
task.priority = 1
queue.offer(task)
```

### Equal priorities are not stable

Elements with equal priorities may be removed in any order.

Use a sequence number if insertion order matters:

```kotlin
data class Entry(
    val priority: Int,
    val sequence: Long,
    val value: String
)

val queue = PriorityQueue<Entry>(
    compareBy<Entry> { it.priority }
        .thenBy { it.sequence }
)
```

## 9. When to Use a Priority Queue

Priority queues are useful for:

- Finding the top `k` elements
- Finding the kth-largest or kth-smallest element
- Task scheduling
- Processing events by timestamp
- Merging sorted collections
- Dijkstra's shortest-path algorithm
- Prim's minimum-spanning-tree algorithm
- Running median problems
- Processing the next minimum or maximum value efficiently

## 10. Quick Reference

### Min-heap

```kotlin
val minHeap = PriorityQueue<Int>()
```

### Max-heap

```kotlin
val maxHeap = PriorityQueue<Int>(reverseOrder())
```

### Add an element

```kotlin
heap.offer(value)
```

### Read the top element

```kotlin
val top = heap.peek()
```

### Remove the top element

```kotlin
val top = heap.poll()
```

### Check whether it is empty

```kotlin
if (heap.isEmpty()) {
    println("Heap is empty")
}
```

### Process all elements in priority order

```kotlin
while (heap.isNotEmpty()) {
    println(heap.poll())
}
```

## Key Takeaway

Use a heap when you repeatedly need the **next smallest or largest element**.

A heap avoids sorting the entire collection after every insertion:

- Insertion: `O(log n)`
- Top element lookup: `O(1)`
- Top element removal: `O(log n)`