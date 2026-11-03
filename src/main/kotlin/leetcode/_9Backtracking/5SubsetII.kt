package leetcode._9Backtracking

fun main() {

}

// https://www.youtube.com/watch?v=Vn2v6ajA7U0
fun subsetsWithDup(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val currentSubset = mutableListOf<Int>()

    // Sorting places duplicate values next to each other.
    nums.sort()

    fun dfs(index: Int) {
        // All numbers have been considered.
        if (index == nums.size) {
            // Save a copy because currentSubset changes during backtracking.
            result.add(currentSubset.toList())
            return
        }

        // Include the current number.
        currentSubset.add(nums[index])
        dfs(index + 1)

        // Undo the inclusion before exploring the exclude branch.
        currentSubset.removeLast()

        // Exclude the current number and all identical values.
        // This prevents duplicate subsets.
        var nextIndex = index + 1

        while (
            nextIndex < nums.size &&
            nums[nextIndex] == nums[index]
        ) {
            nextIndex++
        }

        dfs(nextIndex)
    }

    dfs(index = 0)
    return result
}


/*
Time complexity:
O(N * 2^N) in the worst case.

There can be up to 2^N unique subsets, and copying each subset
into the result can take O(N).

Sorting takes O(N log N), which is dominated by the subset generation.

Space complexity:
O(N), excluding the returned result.

The recursion depth and currentSubset can each contain at most N elements.
*/