package leetcode.stack

import kotlin.math.max
import kotlin.math.min

fun main() {

}
// https://www.youtube.com/watch?v=ZI2z5pq0TqA
/**
 * Calculates total trapped rainwater using the Two-Pointer approach.
 *
 * Time Complexity:  O(n) - Single pass where pointers l and r meet in the middle.
 * Space Complexity: O(1) - Uses a constant number of scalar variables.
 */
fun trap(height: IntArray): Int {
    val n = height.size
    // Edge case: empty input cannot trap water
    if (height.isEmpty()) return 0

    var l = 0
    var r = n - 1
    var ans = 0

    // Track the maximum height seen so far from the left and right
    var leftMax = height[0]
    var rightMax = height[n - 1]

    // Process bars from both ends moving inwards
    while (l < r) {
        // The bottleneck determines which side we can safely process
        if (leftMax < rightMax) {
            l++
            // Update left peak encountered so far
            leftMax = max(leftMax, height[l])
            // Add water trapped above bar l bounded by leftMax
            ans += leftMax - height[l]
        } else {
            r--
            // Update right peak encountered so far
            rightMax = max(rightMax, height[r])
            // Add water trapped above bar r bounded by rightMax
            ans += rightMax - height[r]
        }
    }

    return ans
}


/**
 * Calculates total trapped rainwater using the Dynamic Programming (Prefix/Suffix) approach.
 *
 * Time Complexity:  O(n) - We make 3 separate linear passes over the array of size n.
 * Space Complexity: O(n) - We allocate two additional arrays of size n (maxLeft and maxRight).
 */
fun trap2(height: IntArray): Int {
    val n = height.size
    // Edge case: empty input cannot trap any water
    if (n == 0) return 0

    // maxLeft[i] stores the tallest bar from index 0 up to index i (inclusive)
    val maxLeft = IntArray(n)

    // maxRight[i] stores the tallest bar from index i up to index n-1 (inclusive)
    val maxRight = IntArray(n)

    // Step 1: Populate maxLeft array (left-to-right pass)
    // Time: O(n)
    maxLeft[0] = height[0]
    for (i in 1 until n) { // Fixed: iterate through all elements up to n-1
        maxLeft[i] = max(maxLeft[i - 1], height[i])
    }
    // H  [0,1,0,2,1,0,1,3,2,1,2,1]
    // ML [0,1,1,2,2,2,2,3,3,3,3,3]

    // Step 2: Populate maxRight array (right-to-left pass)
    // Time: O(n)
    maxRight[n - 1] = height[n - 1]
    for (i in n - 2 downTo 0) {
        maxRight[i] = max(maxRight[i + 1], height[i])
    }
    // H  [0,1,0,2,1,0,1,3,2,1,2,1]
    // MR [3,3,3,3,3,3,3,3,2,2,2,1]

    // Step 3: Compute trapped water at each bar
    // The water level above bar i is bounded by min(tallest left, tallest right).
    // Trapped water at i = min(maxLeft[i], maxRight[i]) - height[i]
    // Time: O(n)
    var ans = 0
    for (i in 0 until n) {
        ans += min(maxLeft[i], maxRight[i]) - height[i]
    }

    // H  [0,1,0,2,1,0,1,3,2,1,2,1]
    // MR [3,3,3,3,3,3,3,3,2,2,2,1]
    // ML [0,1,1,2,2,2,2,3,3,3,3,3]
    // ans = 0+0+1+0+1+2+1+0+0+1+0+0 = 6
    return ans
}
