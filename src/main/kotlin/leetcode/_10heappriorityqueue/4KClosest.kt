package leetcode._10heappriorityqueue

import java.util.PriorityQueue
import kotlin.math.sqrt

fun main() {

}


fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
    /*
     * Max-heap ordered by squared distance from the origin.
     *
     * The farthest point currently in the heap is kept at the root.
     * This lets us efficiently remove it whenever we have more than
     * k candidate points.
     */
    val maxHeap = PriorityQueue<IntArray> { pointA, pointB ->
        squaredDistance(pointB).compareTo(squaredDistance(pointA))
    }

    for (point in points) {
        // Add the current point as a candidate.
        maxHeap.offer(point)

        /*
         * Keep only k points.
         *
         * Because this is a max-heap, poll() removes the farthest
         * point among the candidates.
         */
        if (maxHeap.size > k) {
            maxHeap.poll()
        }
    }

    // The problem allows the answer to be returned in any order.
    return maxHeap.toTypedArray()
}

private fun squaredDistance(point: IntArray): Long {
    val x = point[0].toLong()
    val y = point[1].toLong()

    /*
     * Actual distance is sqrt(x² + y²), but square root is unnecessary:
     * comparing x² + y² produces the same ordering.
     *
     * Long is used to avoid potential Int overflow during multiplication.
     */
    return x * x + y * y
}


/**
 * Data class representing a point (x, y).
 *
 * Optimization Note:
 * Instead of computing the square root (sqrt(x^2 + y^2)), we use the squared distance: (x^2 + y^2).
 * Since square root is a monotonic function, comparing raw squared distances yields the
 * exact same relative order while saving expensive floating-point math and square root operations.
 */
data class Point(val x: Int, val y: Int) {
    val distance: Int get() = x * x + y * y
}

/**
 * Max-Heap configured using a PriorityQueue ordered by distance in descending order.
 * The point with the LARGEST distance remains at the top (root) of the heap.
 *
 * Note: In LeetCode, declaring this inside the `kClosest` function is recommended to avoid
 * retaining state across multiple test cases.
 */
val closeMaxHeap = PriorityQueue<Point>(compareByDescending { it.distance })

/**
 * Finds the k closest points to the origin (0, 0).
 *
 * =========================================================================
 * COMPLEXITY ANALYSIS
 * =========================================================================
 * Time Complexity: O(N log k)
 * - Let N be the total number of points in the input array.
 * - For each point, we offer it into the heap (O(log k)) and poll if size exceeds k (O(log k)).
 * - Because the heap size is capped at (k + 1), each operation takes O(log k) time.
 * - Total Time = N * O(log k) = O(N log k).
 * - This is significantly faster than sorting all points, which takes O(N log N).
 *
 * Space Complexity: O(k)
 * - The PriorityQueue maintains at most (k + 1) points at any given time.
 * - The resulting Array(k) requires O(k) space to hold the output.
 * - Total Auxiliary Space = O(k).
 * =========================================================================
 */
fun kClosest3(points: Array<IntArray>, k: Int): Array<IntArray> {
    // Step 1: Iterate through each [x, y] coordinate in the input array
    for ((x, y) in points) {
        val point = Point(x, y)

        // Insert the point into the Max-Heap
        closeMaxHeap.offer(point)

        // If heap size exceeds k, evict the root (which holds the farthest point currently in the heap)
        if (closeMaxHeap.size > k) {
            closeMaxHeap.poll()
        }
    }

    // Step 2: Extract the remaining k closest points from the heap into the output array
    val res = Array(k) { IntArray(2) }
    var index = 0

    while (closeMaxHeap.isNotEmpty()) {
        val point = closeMaxHeap.poll()
        res[index++] = intArrayOf(point.x, point.y)
    }

    return res
}

class KCloset {

    /**
     * Data class to hold point coordinates (x, y)
     * and its pre-calculated Euclidean distance from the origin.
     */
    data class Point(val x: Int, val y: Int, val distance: Double)

    /**
     * A Max-Heap using a PriorityQueue ordered by distance in descending order.
     * This keeps the farthest point at the root (top) of the heap so we can
     * easily evict it when the heap size exceeds k.
     */
    val closeMaxHeap = PriorityQueue<Point>(compareByDescending { it.distance })

    /**
     * Finds the k closest points to the origin (0, 0).
     *
     * Time Complexity Analysis:
     * - Let N be the number of points.
     * - For each point, we calculate its distance and offer it to the heap: O(log k).
     * - Since the heap size is capped at k, each insertion/deletion takes O(log k).
     * - Total Time Complexity: O(N log k).
     *
     * Space Complexity Analysis:
     * - The heap stores at most k + 1 elements at any time before polling.
     * - Total Space Complexity: O(k) to store the heap and result array.
     */
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        // Iterate through all given points in the input array
        for ((x, y) in points) {
            // Create a Point object with its calculated distance from origin
            val point = Point(x, y, dist(x, y))

            // Add the point into our max-heap
            closeMaxHeap.offer(point)

            // If heap size exceeds k, remove the farthest point (the root of the max-heap)
            if (closeMaxHeap.size > k) {
                closeMaxHeap.poll()
            }
        }

        // Prepare the result array of size k
        val res = Array(k) { IntArray(2) }
        var index = 0

        // Extract the remaining k closest points from the heap
        while (closeMaxHeap.isNotEmpty()) {
            val point = closeMaxHeap.poll()
            res[index++] = intArrayOf(point.x, point.y)
        }

        // Return the final array containing the k closest points
        return res
    }

    /**
     * Helper function to calculate the Euclidean distance from the origin (0,0).
     * Formula: -√( (x1 - 0)^2 + (y1 - 0)^2 ) -> √(x^2 + y^2)
     */
    private fun dist(x: Int, y: Int): Double {
        // Convert the sum of squares to Double and compute the square root
        return sqrt((x * x + y * y).toDouble())
    }
}