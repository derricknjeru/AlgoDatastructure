package grind75v2.array

fun main() {
    /**
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     *
     * Notice that the solution set must not contain duplicate triplets.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [-1,0,1,2,-1,-4]
     * Output: [[-1,-1,2],[-1,0,1]]
     * Explanation:
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
     * The distinct triplets are [-1,0,1] and [-1,-1,2].
     * Notice that the order of the output and the order of the triplets does not matter.
     * Example 2:
     *
     * Input: nums = [0,1,1]
     * Output: []
     * Explanation: The only possible triplet does not sum up to 0.
     * Example 3:
     *
     * Input: nums = [0,0,0]
     * Output: [[0,0,0]]
     * Explanation: The only possible triplet sums up to 0.
     *
     *
     * Constraints:
     *
     * 3 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     */

}

// https://www.youtube.com/watch?v=jzZsG8n2R9A

/**
 * Key Edge Case Handling
 * Duplicate Avoidance: Duplicate triplets are prevented by checking nums[i] == nums[i - 1] for the outer loop and advancing left/right pointers past identical values once a valid triplet is found.
 *
 * Early Termination: If nums[i] > 0, the loop terminates early because all subsequent numbers in a sorted array will also be positive, making a zero sum impossible.
 *
 * Complexity
 * Time Complexity: (N^2)
 * Sorting takes O(NlogN), and the nested two-pointer search takes O(N^2)
 * Space Complexity: O(1) or O(N) depending on the underlying sorting algorithm implementation, excluding the output storage.
 */

fun threeSum(nums: IntArray): List<List<Int>> {

    val result = mutableListOf<List<Int>>()

    // Sort array to easily use two pointers and skip duplicates
    nums.sort()

    for (i in 0 until nums.size - 2) {
        // Optimization: If the smallest number in the triplet is > 0, sum can't be 0
        if (nums[i] > 0) break

        // Skip duplicate values for the fixed first element
        if (i > 0 && nums[i] == nums[i - 1]) continue

        var left = i + 1
        var right = nums.size - 1

        while (left < right) {
            val sum = nums[i] + nums[left] + nums[right]

            when {
                sum == 0 -> {
                    result.add(listOf(nums[i], nums[left], nums[right]))
                    left++
                    right--

                    // Skip duplicate values for the second element
                    while (left < right && nums[left] == nums[left - 1]) left++
                }

                sum < 0 -> left++  // Sum is too small, move left pointer right to increase sum
                else -> right--    // Sum is too large, move right pointer left to decrease sum
            }
        }
    }

    return result
}


fun threeSum2(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    // Sort array to easily use two pointers and skip duplicates
    nums.sort()

    for (i in 0 until nums.size - 2) {
        // Optimization: If the smallest number in the triplet is > 0, sum can't be 0
        if (nums[i] > 0) break

        // Skip duplicate values for the fixed first element
        if (i > 0 && nums[i] == nums[i - 1]) continue

        var left = i + 1
        var right = nums.size - 1

        while (left < right) {
            val sum = nums[i] + nums[left] + nums[right]

            if (sum == 0) {
                result.add(listOf(nums[i], nums[left], nums[right]))
                left++
                right--

                // Skip duplicate values for the second element
                while (left < right && nums[left] == nums[left - 1]) left++
                // Skip duplicate values for the third element
                while (left < right && nums[right] == nums[right + 1]) right--
            } else if (sum < 0) {
                left++  // Sum is too small, move left pointer right to increase sum
            } else {
                right-- // Sum is too large, move right pointer left to decrease sum
            }
        }
    }

    return result
}
