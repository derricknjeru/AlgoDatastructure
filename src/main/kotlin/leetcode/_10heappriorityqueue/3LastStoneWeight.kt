package leetcode._10heappriorityqueue

import java.util.PriorityQueue

fun main() {

}

/*
 * // https://www.youtube.com/watch?v=B-QCq79-Vfw
 * LeetCode 1046: Last Stone Weight
 *
 * Approach:
 * Use a max-heap to repeatedly retrieve the two heaviest stones.
 *
 * For the two heaviest stones:
 * - If their weights are equal, both stones are destroyed.
 * - If their weights are different, insert their difference
 *   back into the heap.
 *
 * Kotlin's PriorityQueue is a min-heap by default.
 * reverseOrder() changes it into a max-heap.
 *
 * Complexity:
 * - Building the heap: O(n log n)
 * - Processing the stones: O(n log n)
 * - Space: O(n)
 */

class Solution {
    fun lastStoneWeight(stones: IntArray): Int {
        // Max-heap: the largest stone is always at the top.
        //val maxHeap = PriorityQueue<Int>(reverseOrder())
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })
        //val maxHeap = PriorityQueue<Int>{a,b -> b.compareTo(a)}


        // Add every stone to the max-heap.
        for (stone in stones) {
            maxHeap.offer(stone)
        }

        /*
         * Continue while at least two stones remain.
         *
         * We need two stones for each smash operation.
         */
        while (maxHeap.size > 1) {
            // Remove the heaviest stone.
            val heaviest = maxHeap.poll()

            // Remove the second-heaviest stone.
            val secondHeaviest = maxHeap.poll()

            /*
             * If the stones have different weights, the remaining
             * stone has the difference between their weights.
             *
             * If they are equal, both are destroyed, so nothing
             * needs to be added back.
             */
            if (heaviest != secondHeaviest) {
                val remainingWeight = heaviest - secondHeaviest
                maxHeap.offer(remainingWeight)
            }
        }

        /*
         * If one stone remains, return its weight.
         *
         * If no stones remain, return 0.
         */
        return maxHeap.peek() ?: 0
    }
}