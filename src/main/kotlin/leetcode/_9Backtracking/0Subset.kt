package leetcode._9Backtracking

fun main() {

}

/**
 *
 */
// https://www.youtube.com/watch?v=REOH22Xwdkk
fun subsets(nums: IntArray): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    val subset = mutableListOf<Int>()

    fun backtracking(index: Int) {
        // We have made an include/exclude decision for every number.
        if (index >= nums.size) {
            // Add a copy because subset is mutable and will continue
            // changing as the recursion backtracks.
            res.add(subset.toList()) // copy is used by using .toList()
            return
        }

        // Choice 1: Include the current number.
        subset.add(nums[index])
        backtracking(index + 1)

        // Undo the inclusion before exploring the next choice.
        //subset.removeAt(subset.lastIndex)
        subset.removeLast()
        // Choice 2: Exclude the current number.
        backtracking(index + 1)
    }

    // Start making decisions from the first number.
    backtracking(0)

    return res
}


/*
Complexity analysis:

Let n be the number of elements in nums.

Time complexity: O(n * 2^n)
- Each element has two choices: include or exclude.
- This produces 2^n subsets.
- Copying a subset into the result can take up to O(n) time.

Space complexity: O(n), excluding the output
- The recursion stack can reach a depth of n.
- The current subset can contain at most n elements.

Output space: O(n * 2^n)
- There are 2^n subsets.
- Each subset can contain up to n elements.
*/