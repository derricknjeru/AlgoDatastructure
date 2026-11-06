package fundamental_data_structures

import java.util.PriorityQueue

/*
 * Reference solution: Sort Integers by Frequency
 *
 * Time: O(n log n)
 * Space: O(d), where d is the number of distinct values.
 */
fun sortIntegersByFrequencyReference(nums: IntArray): IntArray {
    val frequencies = HashMap<Int, Int>()
    for (number in nums) {
        frequencies[number] = frequencies.getOrDefault(number, 0) + 1
    }

    return nums
        .sortedWith(
            compareBy<Int> { frequencies.getValue(it) }
                .thenByDescending { it }
        )
        .toIntArray()
}

/*
 * Reference solution: Kth Largest in a Stream
 *
 * add: O(log k)
 * Space: O(k)
 */
class KthLargestReference(
    private val k: Int,
    nums: IntArray
) {
    private val largest = PriorityQueue<Int>()

    init {
        require(k > 0) { "k must be positive" }
        for (number in nums) {
            offer(number)
        }
    }

    fun add(value: Int): Int {
        offer(value)
        check(largest.size == k) { "The stream must contain at least k values" }
        return largest.peek()
    }

    private fun offer(value: Int) {
        largest.offer(value)
        if (largest.size > k) {
            largest.poll()
        }
    }
}
