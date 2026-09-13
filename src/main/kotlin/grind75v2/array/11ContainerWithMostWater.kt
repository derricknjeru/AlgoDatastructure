package grind75v2.array

import kotlin.math.max
import kotlin.math.min

fun main() {

}
// https://www.youtube.com/watch?v=UuiTKBwPgAo
/**
 * Finds the maximum area of water a container can store.
 *
 * TIME COMPLEXITY: O(n)
 * - Single pass through the array of length n using two pointers.
 * - In each iteration, either `left` increments or `right` decrements,
 *   ensuring the loop runs at most n times.
 *
 * SPACE COMPLEXITY: O(1)
 * - Uses a constant amount of extra memory for pointer variables and max calculation.
 */
fun maxArea(height: IntArray): Int {
    var left = 0
    var right = height.size - 1
    var maxWater = 0

    while (left < right) {
        // Calculate width and bottleneck height
        val width = right - left
        //val minHeight = height[left].coerceAtMost(height[right])
        val minHeight = min(height[left], height[right])

        // Update maximum water area seen so far
        val currentArea = width * minHeight
        // maxWater = maxWater.coerceAtLeast(currentArea)
        maxWater = max(maxWater, height[right])

        // Move the pointer pointing to the shorter line to potentially find a taller boundary
        if (height[left] < height[right]) {
            left++
        } else {
            right--
        }
    }

    return maxWater
}
