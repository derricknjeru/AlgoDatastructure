package leetcode._9Backtracking

fun main() {

}

// https://www.youtube.com/watch?v=FOyRpNUSFeA
fun combinationSum2(
    candidates: IntArray,
    target: Int
): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val currentCombination = mutableListOf<Int>()

    // Sorting places duplicate values next to each other.
    candidates.sort()

    fun dfs(index: Int, total: Int) {
        // The current combination adds up to the target.
        if (total == target) {
            // Save a copy because currentCombination is modified
            // while backtracking.
            result.add(currentCombination.toList())
            return
        }

        // Stop when the sum is too large or no candidates remain.
        if (total > target || index >= candidates.size) {
            return
        }

        // Include the current candidate.
        currentCombination.add(candidates[index])

        // Move to the next index because each array position
        // may be used only once.
        dfs(index + 1, total + candidates[index])

        // Undo the inclusion before exploring the exclude branch.
        currentCombination.removeLast()

        // Skip every duplicate of the current value when excluding it.
        // Without this loop, equal values at the same decision level
        // could generate identical combinations.
        var nextIndex = index + 1

        while (
            nextIndex < candidates.size &&
            candidates[nextIndex] == candidates[index]
        ) {
            nextIndex++
        }

        // Exclude the current value and continue from the first
        // different value.
        dfs(nextIndex, total)
    }

    dfs(index = 0, total = 0)
    return result
}


fun combinationSum22(
    candidates: IntArray,
    target: Int
): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    // Sorting places duplicate values next to each other.
    candidates.sort()

    fun dfs(index: Int, total: Int, currentCombination: MutableList<Int>) {
        // The current combination adds up to the target.
        if (total == target) {
            // Save a copy because currentCombination is modified
            // while backtracking.
            result.add(currentCombination.toList())
            return
        }

        // Stop when the sum is too large or no candidates remain.
        if (total > target || index >= candidates.size) {
            return
        }

        // Include the current candidate.
        currentCombination.add(candidates[index])

        // Move to the next index because each array position
        // may be used only once.
        dfs(index + 1, total + candidates[index], currentCombination)

        // Undo the inclusion before exploring the exclude branch.
        currentCombination.removeLast()

        // Skip every duplicate of the current value when excluding it.
        // Without this loop, equal values at the same decision level
        // could generate identical combinations.
        var nextIndex = index + 1

        while (
            nextIndex < candidates.size &&
            candidates[nextIndex] == candidates[index]
        ) {
            nextIndex++
        }

        // Exclude the current value and continue from the first
        // different value.
        dfs(nextIndex, total, currentCombination)
    }

    dfs(index = 0, total = 0, currentCombination = mutableListOf())
    return result
}
