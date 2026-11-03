package leetcode._9Backtracking

fun main() {

}

// https://www.youtube.com/watch?v=3jvWodd7ht0
fun partition(s: String): List<List<String>> {
    val result = mutableListOf<List<String>>()
    val currentPartition = mutableListOf<String>()

    fun isPalindrome(left: Int, right: Int): Boolean {
        var start = left
        var end = right

        while (start < end) {
            if (s[start] != s[end]) {
                return false
            }

            start++
            end--
        }

        return true
    }

    fun dfs(startIndex: Int) {
        // Every character has been included in the partition.
        if (startIndex == s.length) {
            // Save a copy because currentPartition changes during backtracking.
            result.add(currentPartition.toList())
            return
        }

        // Try every possible substring beginning at startIndex.
        for (endIndex in startIndex until s.length) {
            // Only palindrome substrings can be included.
            if (!isPalindrome(startIndex, endIndex)) {
                continue
            }

            // Choose the palindrome substring.
            currentPartition.add(
                s.substring(startIndex, endIndex + 1)
            )

            // Continue immediately after the chosen substring.
            dfs(endIndex + 1)

            // Undo the choice before trying a different ending index.
            currentPartition.removeLast()
        }
    }

    dfs(startIndex = 0)
    return result
}

/*
Time complexity:
O(N * 2^N) in the worst case.

There are up to 2^(N - 1) ways to place cuts between characters.
Copying completed partitions and checking palindrome substrings add
up to O(N) work per path in the common upper-bound analysis.

Space complexity:
O(N), excluding the returned result.

The recursion depth and currentPartition can each contain at most
N elements.
*/