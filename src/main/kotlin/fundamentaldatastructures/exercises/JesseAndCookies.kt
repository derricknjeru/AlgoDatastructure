package fundamentaldatastructures.exercises

import java.util.PriorityQueue

fun main() {

}


fun cookies(k: Int, A: Array<Int>): Int {
    /*
     * A min-heap keeps the least-sweet cookie at the head, so each operation
     * can remove the two smallest values without sorting the full collection.
     */
    val minHeap = PriorityQueue<Int>()
    minHeap.addAll(A)

    var operations = 0

    /*
     * Continue while the minimum sweetness is below k and two cookies remain.
     * If fewer than two remain below k, no further combination is possible.
     */
    while (minHeap.size >= 2 && minHeap.peek() < k) {
        val leastSweet = minHeap.poll()
        val secondLeastSweet = minHeap.poll()

        // HackerRank's combination rule: least + 2 * second least.
        minHeap.offer(leastSweet + 2 * secondLeastSweet)
        operations++
    }

    /*
     * The heap minimum is enough to decide success: if it is at least k,
     * every other cookie is also at least k because the heap is ordered.
     */
    return if (minHeap.isNotEmpty() && minHeap.peek() >= k) {
        operations
    } else {
        -1
    }
}

/*
 * Complexity:
 *
 * n = initial cookie count, c = number of combinations.
 * - addAll inserts n values into this PriorityQueue in O(n log n) time.
 * - Each combination performs two polls and one offer in O(log n) time.
 * - Total: O(n log n + c log n), which simplifies to O(n log n) because
 *   at most n - 1 combinations can occur.
 * - Space: O(n) for the heap.
 */
