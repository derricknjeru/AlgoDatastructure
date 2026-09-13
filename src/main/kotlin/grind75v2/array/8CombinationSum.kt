package grind75v2.array

fun main() {
    /*val candidates = IntArray(4)
    candidates[0] = 2
    candidates[1] = 3
    candidates[2] = 6
    candidates[3] = 7*/
    //val candidates = listOf(2, 3, 6, 7).toIntArray()
    val candidates = intArrayOf(2, 3, 6, 7)
    val res = combinationSum(candidates, 7)
    println(res) // [[2, 2, 3], [7]]
}


// https://www.youtube.com/watch?v=GBKI9VSKdGg
/**
 * Complexity:
 *
 * Time: O(2^(T / M + N))
 * - Two choices per step: include or exclude.
 * - Maximum include depth: T / M.
 * - Copying valid combinations: O(T / M).
 *
 * Space: O(T / M) auxiliary
 * - Recursion stack and current combination.
 *
 * Where:
 * - T = target
 * - M = minimum candidate
 * - N = number of candidates
 *
 * Excludes output storage.
 */

fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    val currentCombination = mutableListOf<Int>()

    dfsAlsoBacktracking(target, 0, 0, candidates, currentCombination, res)
    return res
}

private fun dfsAlsoBacktracking(
    target: Int,
    currentIndex: Int,
    currentTotal: Int,
    candidates: IntArray,
    currentCombination: MutableList<Int>,
    res: MutableList<List<Int>>
) {
    // base case
    if (currentTotal > target || currentIndex >= candidates.size) return

    if (currentTotal == target) {
        res.add(currentCombination.toList())
        return
    }

    // Include the current candidate and explore possibilities
    currentCombination.add(candidates[currentIndex])
    dfsAlsoBacktracking(
        target,
        currentIndex,
        currentTotal + candidates[currentIndex],
        candidates,
        currentCombination,
        res
    )

    // Exclude the current candidate and move to the next index
    currentCombination.removeAt(currentCombination.size - 1);
    dfsAlsoBacktracking(target, currentIndex + 1, currentTotal, candidates, currentCombination, res)
}


