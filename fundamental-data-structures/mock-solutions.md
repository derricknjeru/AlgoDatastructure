# Mock Solutions and Practice Exercises

Attempt each mock in [mocks.md](mocks.md) first, under the 35-minute timer. Only then read the solution. The practice exercises are new variants. Solve each one under a 15-minute timer, then compare with the solution.

---

## Mock 1: HashMap and Custom Sorting

### Solution

```kotlin
fun topKFrequent(words: List<String>, k: Int): List<String> {
    val freq = HashMap<String, Int>()
    for (w in words) freq[w] = freq.getOrDefault(w, 0) + 1

    return freq.keys
        .sortedWith(compareByDescending<String> { freq.getValue(it) }.thenBy { it })
        .take(k)
}

fun distinctByIncreasingFrequency(words: List<String>): List<String> {
    val freq = HashMap<String, Int>()
    for (w in words) freq[w] = freq.getOrDefault(w, 0) + 1

    return freq.keys
        .sortedWith(compareBy<String> { freq.getValue(it) }.thenByDescending { it })
}
```

**Complexity:** Counting is O(n) time and O(d) space, where d is the number of distinct words. Sorting the distinct words is O(d log d) time. Total: O(n + d log d) time, O(d) space.

**Stream follow-up:** Keep a min-heap of size k ordered by (count, then word), so the weakest candidate is at the head. Each new word is pushed or replaced in O(log k). Exact counts need the whole stream's word set, so for truly unbounded streams, use an approximate counter such as Count-Min Sketch with the same heap.

**Tie-break check:** `thenBy { it }` gives lexicographically smaller first for the top-k. `thenByDescending { it }` gives larger first for the increasing-frequency list.

### Practice Exercise (15 min)

**Sort Integers by Frequency.** Given `nums: IntArray`, sort it so that values with lower frequency come first. Break ties by putting larger values first.

- Example: `[1, 1, 2, 2, 2, 3]` returns `[3, 1, 1, 2, 2, 2]`
- Example: `[5]` returns `[5]`
- Constraints: `1 <= nums.size <= 10^5`, values in `-10^4..10^4`.

Clarify before coding: What should happen with empty input? Is the output a new array or an in-place change?

### Practice Solution

```kotlin
fun frequencySort(nums: IntArray): IntArray {
    val freq = HashMap<Int, Int>()
    for (n in nums) freq[n] = freq.getOrDefault(n, 0) + 1

    return nums
        .sortedWith(compareBy<Int> { freq.getValue(it) }.thenByDescending { it })
        .toIntArray()
}
```

**Complexity:** O(n log n) time for the sort, O(d) extra space for the map, where d is the number of distinct values.

**Edge cases:** Empty array returns an empty array. A single element returns itself. All values equal returns the original order.

---

## Mock 2: Heap and Data-Structure Design

### Solution: `RunningStats`

```kotlin
import java.util.PriorityQueue
import java.util.TreeMap

class RunningStats {
    // low is a max-heap holding the smaller half. high is a min-heap holding the larger half.
    private val low = PriorityQueue<Int>(compareByDescending { it })
    private val high = PriorityQueue<Int>()
    // counts keeps every value in sorted order, so topK can walk from the largest.
    private val counts = TreeMap<Int, Int>()

    fun add(value: Int) {
        counts.merge(value, 1, Int::plus)

        if (low.isEmpty() || value <= low.peek()) low.offer(value) else high.offer(value)

        if (low.size > high.size + 1) {
            high.offer(low.poll())
        } else if (high.size > low.size) {
            low.offer(high.poll())
        }
    }

    fun median(): Double {
        check(low.isNotEmpty()) { "no values added yet" }
        return if (low.size > high.size) {
            low.peek().toDouble()
        } else {
            // Long avoids overflow when both values are near 10^9.
            (low.peek().toLong() + high.peek().toLong()) / 2.0
        }
    }

    fun topK(k: Int): List<Int> {
        val out = ArrayList<Int>()
        for ((value, count) in counts.descendingMap()) {
            var i = 0
            while (i < count && out.size < k) {
                out.add(value)
                i++
            }
            if (out.size == k) break
        }
        return out
    }
}
```

**Why these structures:**
- Two heaps give O(log n) `add` and O(1) `median`, because the median sits at the top of one of them.
- `TreeMap` keeps values ordered, so `topK` reads from the largest value down.

**Complexity:**
- `add`: O(log n) time, O(1) extra space per value. Overall O(n) space.
- `median`: O(1) time.
- `topK(k)`: O(k + distinct values visited) time in the worst case, O(k) output space.

**Edge cases:** One value gives that value as the median. Two values give their average. `k` larger than the number of values returns all values in descending order.

**Removal follow-up:** Use lazy deletion. Keep a second "removed" heap for each side, and discard tops that are marked removed before reading. This keeps `add` and `remove` at O(log n) amortized. A `TreeMap` counts structure alone would need an O(n) walk for the median, so it does not scale.

### Practice Exercise (15 min)

**Kth Largest in a Stream.** Implement a class `KthLargest(k: Int, nums: IntArray)` with a method `add(value: Int): Int`. `add` appends `value` and returns the k-th largest element so far.

- Example: `k = 3`, `nums = [4, 5, 8, 2]`. Then `add(3)` returns `4`, `add(5)` returns `5`, `add(10)` returns `5`, `add(9)` returns `8`, `add(4)` returns `8`.
- Constraints: `1 <= k <= 10^4`, `0 <= nums.size <= 10^4`, values in `-10^4..10^4`. Assume `add` is called only when at least k values exist.

Clarify before coding: Do we need the whole stream? Which structure keeps only what matters?

### Practice Solution

```kotlin
import java.util.PriorityQueue

class KthLargest(private val k: Int, nums: IntArray) {
    // Min-heap of the k largest values seen so far. The head is the k-th largest.
    private val heap = PriorityQueue<Int>()

    init {
        for (n in nums) add(n)
    }

    fun add(value: Int): Int {
        if (heap.size < k) {
            heap.offer(value)
        } else if (value > heap.peek()) {
            heap.poll()
            heap.offer(value)
        }
        return heap.peek()
    }
}
```

**Complexity:** `add` is O(log k) time. The constructor is O(n log k) time. Space is O(k).

**Why a min-heap:** Only the top k values matter. The head is the weakest of them, so any new value that beats it replaces it. A max-heap would need all values and would be O(n) space.

**Edge cases:** Values equal to the current k-th largest are ignored, which is correct because the answer does not change. `k = 1` tracks the maximum.

---

## After Practicing

1. Write down which exercise you failed to finish, and why.
2. Re-solve it once after a day without looking at the solution.
3. Record the time in [TRACKER.md](TRACKER.md).
