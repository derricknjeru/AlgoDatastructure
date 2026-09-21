package leetcode.arrayandhashing

fun main() {

}

class ContainsDuplicate {
    //time complexity of O(n) on worse case
    // space complexity of O(n) on worse case
    fun containsDuplicate(nums: IntArray): Boolean {
        val set = mutableSetOf<Int>()
        for (num in nums) {
            if (!set.add(num)) return true
        }
        return false
    }
}

class ContainsDuplicate2 {
    //time complexity of O(n) on worse case
    // space complexity of O(n) on worse case
    fun containsDuplicate(nums: IntArray): Boolean {
        val set = mutableSetOf<Int>()
        return nums.any { !set.add(it) }
    }
}

class ContainsDuplicate3 {
    //time complexity of O(n) on worse case
    // space complexity of O(n) on worse case
    // Processes the entire array without early exit if a duplicate appears early.
    fun containsDuplicate(nums: IntArray): Boolean {
        return nums.toSet().size != nums.size
    }
}
class ContainsDuplicate4 {
    //time complexity of O(n log n) on worse case
    // space complexity of O(1) on worse case
    fun containsDuplicate(nums: IntArray): Boolean {
        nums.sort()
        for (i in 0 until nums.size - 1) {
            if (nums[i] == nums[i + 1]) return true
        }
        return false
    }
}