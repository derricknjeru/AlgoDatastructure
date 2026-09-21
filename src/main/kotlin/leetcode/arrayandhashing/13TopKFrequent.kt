package leetcode.arrayandhashing

import java.util.PriorityQueue

fun main() {

}
// https://www.youtube.com/watch?v=YPTqKIgVk-k

fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val freq = mutableMapOf<Int, Int>()

    // Count the frequency of each number.
    // Time: O(n)
    for (num in nums) {
        freq[num] = freq.getOrDefault(num, 0) + 1
    }

    // buckets[i] contains numbers that appear exactly i times.
    // Index represents frequency.
    // Time: O(n) space for the buckets.
    val buckets = Array(nums.size + 1) {
        mutableListOf<Int>()
    }

    // Group numbers by their frequency.
    // Time: O(m), where m = distinct numbers.
    for ((num, count) in freq) {
        buckets[count].add(num)
    }

    // Example:
    // nums = [1,1,1,2,2,3]
    //
    // Frequency:
    // 1 -> 3
    // 2 -> 2
    // 3 -> 1
    //
    // buckets:
    // [[], [3], [2], [1], [], [], []]
    //
    // buckets[1] = [3]
    // buckets[2] = [2]
    // buckets[3] = [1]

    val res = IntArray(k)
    var index = 0

    // Traverse from highest frequency to lowest.
    // Time: O(n) across all buckets.
    for (i in buckets.size - 1 downTo 1) {
        for (num in buckets[i]) {
            res[index++] = num

            // Stop once we have k elements.
            if (index == k) return res
        }
    }

    return res
}


/*
Time Complexity: O(n)

- Build frequency map: O(n)
- Build buckets: O(n + m)
- Traverse buckets: O(n)
- Total: O(n)

Space Complexity: O(n)

- Frequency map: O(m)
- Buckets: O(n + m)
- Result: O(k)
- Total: O(n)

Where:
n = number of elements in nums
m = number of distinct elements
k = number of elements to return
*/

/*
 * Complexity Analysis:
 *
 * Time Complexity: O(N log K)
 * - Frequency Map: O(N) time to count frequencies of N elements.
 * - Min-Heap Operations: Loop runs U times (U = unique elements, U <= N).
 *   Each insertion/removal takes O(log K) because the heap size is capped at K.
 *   Total heap maintenance is O(N log K).
 * - Result Extraction: Extracting K elements takes O(K log K).
 * - Overall Time: O(N) + O(N log K) + O(K log K) = O(N log K)
 *
 * Space Complexity: O(N)
 * - Frequency Map: O(U) space where U is the number of unique elements (up to O(N)).
 * - Min-Heap: O(K) space to store at most K elements at a time.
 * - Overall Space: O(N + K) = O(N) auxiliary space.
 */

fun topKFrequent2(nums: IntArray, k: Int): IntArray {
    // Step 1: Count frequency of each unique number
    val freq = mutableMapOf<Int, Int>()
    for (num in nums) {
        freq[num] = freq.getOrDefault(num, 0) + 1
    }

    // Step 2: Min-Heap storing elements prioritized by lowest frequency first
    val minHeap = PriorityQueue<Int>(compareBy { freq[it] })
    /* val minHeap2 = PriorityQueue<Int> { a, b ->
         freq.getValue(a).compareTo(freq.getValue(b))
     }
     val minHeap3 = PriorityQueue<Int>(
         compareBy { freq.getValue(it) }
     )

     val minHeap4 = PriorityQueue<Int> { a, b ->
         freq[a]!! - freq[b]!!
     }*/

    // Step 3: Keep only top K most frequent elements in the heap
    for (num in freq.keys) {
        minHeap.add(num)
        if (minHeap.size > k) {
            minHeap.poll() // Evict the least frequent element
        }
    }

    // Step 4: Extract the top K elements into the result array
    val res = IntArray(k)
    var index = 0
    while (minHeap.isNotEmpty()) {
        res[index++] = minHeap.poll()
    }

    return res
}
