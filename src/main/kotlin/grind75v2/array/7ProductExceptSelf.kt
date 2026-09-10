package grind75v2.array

fun main() {

    // https://leetcode.com/problems/product-of-array-except-self/description/
    /**
     * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
     *
     * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
     *
     * You must write an algorithm that runs in O(n) time and without using the division operation.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,4]
     * Output: [24,12,8,6]
     * Example 2:
     *
     * Input: nums = [-1,1,0,-3,3]
     * Output: [0,0,9,0,0]
     *
     *
     * Constraints:
     *
     * 2 <= nums.length <= 105
     * -30 <= nums[i] <= 30
     * The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
     *
     *
     * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
     */

}

/**
 * ComplexityTime Complexity: O(n) — Two linear passes through the array.
 * Space Complexity: O(1) extra space — Output array result does not count towards auxiliary space per the problem constraints.
 */
fun productExceptSelf(nums: IntArray): IntArray {
    val res = IntArray(nums.size)
    res[0] = 1
    val size = nums.size
    for (i in 1 until size) {
        res[i] = res[i - 1] * nums[i - 1]
    }

    var postFix = 1
    for (i in size - 1 downTo 0) {
        res[i] *= postFix
        postFix *= nums[i]
    }
    return res
}


