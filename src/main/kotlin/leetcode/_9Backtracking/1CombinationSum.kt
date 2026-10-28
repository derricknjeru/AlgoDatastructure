package leetcode._9Backtracking

fun main() {

}


fun combinationSum(
    candidates: IntArray,
    target: Int
): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val currentCombination = mutableListOf<Int>()

    fun dfs(index: Int, total: Int) {
        // A valid combination has been found.
        if (total == target) {
            // Add a copy because currentCombination is modified during backtracking.
            result.add(currentCombination.toList())
            return
        }

        // Stop when the sum is too large or all candidates were considered.
        if (total > target || index >= candidates.size) {
            return
        }

        // Include the current candidate.
        currentCombination.add(candidates[index])

        // Keep the same index because a candidate can be reused.
        dfs(index, total + candidates[index])

        // Undo the choice before exploring the exclude branch.
        currentCombination.removeLast()

        // Exclude the current candidate and move to the next candidate.
        dfs(index + 1, total)
    }

    dfs(index = 0, total = 0)
    return result
}


/*
Time complexity:
O(2^(N + T/M)), where:
- N is the number of candidates.
- T is the target.
- M is the smallest candidate.

The recursion branches into "include" and "exclude" decisions.
A candidate can be included at most T/M times before exceeding the target.
Copying valid combinations also takes time proportional to their lengths.

Space complexity:
O(N + T/M), excluding the returned result.

This space is used by the recursion stack and currentCombination.
*/