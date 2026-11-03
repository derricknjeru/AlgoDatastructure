package leetcode._10heappriorityqueue

import java.util.PriorityQueue

fun main() {

}

fun findKthLargest(nums: IntArray, k: Int): Int {
    /*
     * Maintain a min-heap containing the k largest values seen so far.
     * The smallest of those k values stays at the root.
     */
    val minHeap = PriorityQueue<Int>()

    for (number in nums) {
        minHeap.offer(number)

        /*
         * If the heap contains more than k values, remove its smallest.
         * This leaves only the k largest values encountered so far.
         */
        if (minHeap.size > k) {
            minHeap.poll()
        }
    }

    /*
     * The root is the smallest among the k largest values,
     * which is exactly the kth largest value.
     */
    return minHeap.peek()
}


/*
 * Complexity:
 *
 * Time: O(n log k)
 * - We process all n numbers.
 * - Each offer() and poll() operation takes O(log k) because the heap
 *   contains at most k + 1 elements.
 *
 * Space: O(k)
 * - The min-heap stores at most k elements.
 */
