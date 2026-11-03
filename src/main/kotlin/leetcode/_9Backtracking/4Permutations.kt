package leetcode._9Backtracking

fun main() {

}


fun permute(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val currentPermutation = mutableListOf<Int>()
    val used = BooleanArray(nums.size)

    fun dfs() {
        // A permutation is complete when it contains every number.
        if (currentPermutation.size == nums.size) {
            // Save a copy because currentPermutation changes during backtracking.
            result.add(currentPermutation.toList())
            return
        }

        for (i in nums.indices) {
            // Each array position may appear only once in a permutation.
            if (used[i]) continue

            // Choose nums[i].
            used[i] = true
            currentPermutation.add(nums[i])

            // Continue building the permutation.
            dfs()

            // Undo the choice before trying another number.
            currentPermutation.removeLast()
            used[i] = false
        }
    }

    dfs()
    return result
}


/*
Time complexity:
O(N * N!)

There are N! permutations, and copying each completed permutation
into the result takes O(N) time.

Space complexity:
O(N), excluding the returned result.

The recursion stack, currentPermutation, and used array each
require O(N) space.
*/