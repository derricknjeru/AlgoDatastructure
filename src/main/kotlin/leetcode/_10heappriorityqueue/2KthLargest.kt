package leetcode._10heappriorityqueue

import java.util.PriorityQueue

fun main() {

}
// https://www.youtube.com/watch?v=hOjcdrqMoQ8
/*
 * LeetCode 703: Kth Largest Element in a Stream
 *
 * Approach:
 * Maintain a min-heap containing only the k largest elements
 * encountered in the stream.
 *
 * Why a min-heap?
 * - The root is the smallest of the k largest elements.
 * - Therefore, the root is the kth-largest element overall.
 *
 * Example when k = 3:
 *
 * Stream values: 4, 5, 8, 2
 * Heap contains:  [4, 5, 8]
 *
 * The smallest value in the heap is 4.
 * Therefore, 4 is the third-largest value.
 *
 * Complexity:
 * - Constructor: O(n log k)
 * - add(): O(log k)
 * - Space: O(k)
 */

class KthLargest(
    // Store k as a class property so add() can access it.
    private val k: Int,

    // Initial values in the stream.
    nums: IntArray
) {
    /*
     * The heap stores at most k elements.
     *
     * PriorityQueue is a min-heap by default, so peek()
     * returns the smallest element currently in the heap.
     */
    private val minHeap = PriorityQueue<Int>()

    init {
        /*
         * Process the initial values using add() so initialization
         * follows the same logic as future stream additions.
         */
        for (num in nums) {
            add(num)
        }
    }

    fun add(value: Int): Int {
        // Add the new value to the heap.
        minHeap.offer(value)

        /*
         * If the heap contains more than k elements, remove its
         * smallest element.
         *
         * This ensures that only the k largest values remain.
         */
        if (minHeap.size > k) {
            minHeap.poll()
        }

        /*
         * The heap contains the k largest values seen so far.
         *
         * Because this is a min-heap, its smallest value is the
         * kth-largest value overall.
         */
        return minHeap.peek()
    }
}

/*
 * Example:
 *
 * val kthLargest = KthLargest(
 *     3,
 *     intArrayOf(4, 5, 8, 2)
 * )
 *
 * kthLargest.add(3)  returns 4
 * kthLargest.add(5)  returns 5
 * kthLargest.add(10) returns 5
 * kthLargest.add(9)  returns 8
 * kthLargest.add(4)  returns 8
 */