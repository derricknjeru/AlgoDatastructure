package grind75v2.array

fun main() {
    val nums = intArrayOf(3, 2, 3)
    val mostFreq = majorityElement(nums)
    println("The number is $mostFreq")
    /**
     * Given an array nums of size n, return the majority element.
     *
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [3,2,3]
     * Output: 3
     * Example 2:
     *
     * Input: nums = [2,2,1,1,1,2,2]
     * Output: 2
     *
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 5 * 104
     * -109 <= nums[i] <= 109
     * The input is generated such that a majority element will exist in the array.
     *
     *
     * Follow-up: Could you solve the problem in linear time and in O(1) space?
     */



}
// https://www.youtube.com/watch?v=7pnhv842keE
//Time Complexity: O(N)
//Space Complexity: O(1)
fun majorityElement(nums: IntArray): Int {
    var count = 0
    var res = 0
    for(n in nums) {
        if(count == 0) res = n
        count += if(n == res) 1 else -1
    }
    return res
}

//Time Complexity: O(N)
//Space Complexity: O(N)
fun majorityElement2(nums: IntArray): Int {
    val count = mutableMapOf<Int, Int>()
    var maxCount = 0
    var res = 0 // Changed from val to var

    for (n in nums) {
        val currentCount = count.getOrDefault(n, 0) + 1
        count[n] = currentCount

        if (currentCount > maxCount) {
            res = n
            maxCount = currentCount
        }
    }
    return res
}

//Time Complexity: O(N)
//Space Complexity: O(N)
private fun majorityElement3(nums: IntArray): Int {
    val occurrencesMap = mutableMapOf<Int, Int>()
    for(num in nums){
        occurrencesMap[num] = occurrencesMap.getOrDefault(num, 0) + 1
    }
    val most = occurrencesMap.maxBy { it.value }
    val maxCount = 0; val res = 0
    // val mostFrequent = occurrenceMap.maxByOrNull { it.value }
    // return mostFrequent?.key ?: 0
    return most.key
}
