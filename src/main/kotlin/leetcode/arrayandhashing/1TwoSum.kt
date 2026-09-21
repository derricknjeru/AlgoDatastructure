package leetcode.arrayandhashing

fun main() {/*
     * 1. Two Sum
     * Solved
     * Easy
     * Topics
     * Companies
     * Hint
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     *
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * You can return the answer in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
     * Example 2:
     *
     * Input: nums = [3,2,4], target = 6
     * Output: [1,2]
     * Example 3:
     *
     * Input: nums = [3,3], target = 6
     * Output: [0,1]
     *
     *
     * Constraints:
     *
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * Only one valid answer exists.
     *
     *
     * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
     */

    val solution = Solution()
    val solution2 = Solution2()

    // Example 1
    val nums1 = intArrayOf(2, 7, 11, 15)
    val target1 = 9
    println("Example 1:")
    println("Input: nums = ${nums1.contentToString()}, target = $target1")
    println("Output: ${solution.twoSum(nums1, target1).contentToString()}")

    // Example 2
    val nums2 = intArrayOf(3, 2, 4)
    val target2 = 6
    println("\nExample 2:")
    println("Input: nums = ${nums2.contentToString()}, target = $target2")
    println("Output: ${solution.twoSum(nums2, target2).contentToString()}")

    // Example 3
    val nums3 = intArrayOf(3, 3)
    val target3 = 6
    println("\nExample 3:")
    println("Input: nums = ${nums3.contentToString()}, target = $target3")
    println("Output: ${solution.twoSum(nums3, target3).contentToString()}")
}

class Solution {
    // space complexity is O(n)
   // Time complexity is O(n)
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        nums.forEachIndexed { index, num ->
            val comp = target - num
            map[comp]?.let {
                return intArrayOf(it, index)
            }
            map[num] = index
        }
        return intArrayOf()
    }
}

class Solution2 {
    // space complexity is O(n)
    // Time complexity is O(n)
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (index in nums.indices) {
            val comp = target - nums[index]
            val x = map.containsValue(comp)
            map[comp]?.let {
                return intArrayOf(it, index)
            }
            map[nums[index]] = index
        }
        return intArrayOf()
    }
}


class Solution3 {
    // space complexity is O(n)
    // Time complexity is O(n)
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        for ((num, index) in nums.withIndex()) {
            val comp = target - num
            map[comp]?.let {
                return intArrayOf(it, index)
            }
            map[num] = index
        }
        return intArrayOf()
    }
}

class Solution4 {
    // space complexity is O(n)
    // Time complexity is O(n)
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (index in 0..nums.lastIndex) {
            val comp = target - nums[index]
            map[comp]?.let {
                return intArrayOf(it, index)
            }
            map[nums[index]] = index
        }
        return intArrayOf()
    }
}

class Solution5 {
    // space complexity is O(n)
    // Time complexity is O(n)
    fun twoSum(nums: IntArray, target: Int): IntArray {
        //val map = HashMap<Int, Int>()
        val map  = mutableMapOf<Int, Int>()
        for ((index, value) in nums.withIndex()) {
            val x = target - value
            if (map.containsKey(x)) {
                return intArrayOf(index, map.getValue(x))
            }
            map[value] = index

        }
        return intArrayOf(0, 0)
    }
}