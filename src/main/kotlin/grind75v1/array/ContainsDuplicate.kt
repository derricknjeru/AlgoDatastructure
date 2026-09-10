package grind75v1.array

fun main() {
    // Test cases
    val testCases = listOf(
        intArrayOf(1, 2, 3, 4, 5) to false,  // No duplicates
        intArrayOf(1, 2, 3, 4, 5, 1) to true,  // Duplicate 1
        intArrayOf(1, 1, 1, 1, 1) to true,  // All duplicates
        intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) to false,  // No duplicates, larger array
        intArrayOf() to false,  // Empty array
        intArrayOf(0, 0) to true,  // Duplicate 0
        intArrayOf(100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 500) to true  // Duplicate 500
    )
    val duplicate = ContainsDuplicate()
    // Running test cases
    for ((input, expected) in testCases) {
        val result = duplicate.containsDuplicate(input)
        println("Input: ${input.joinToString(", ")} -> Output: $result, Expected: $expected")
        assert(result == expected) { "Test failed for input: ${input.joinToString(", ")}" }
    }

    println("All tests passed!")
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
    fun containsDuplicate(nums: IntArray): Boolean {
        return nums.toSet().size != nums.size
    }
}
