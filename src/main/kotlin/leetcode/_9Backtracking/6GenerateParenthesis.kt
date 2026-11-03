package leetcode._9Backtracking

fun main() {

}


fun generateParenthesis(n: Int): List<String> {
    val result = mutableListOf<String>()
    val current = StringBuilder()

    fun dfs(openCount: Int, closeCount: Int) {
        // A valid result contains n opening and n closing parentheses.
        if (openCount == n && closeCount == n) {
            result.add(current.toString())
            return
        }

        // Add an opening parenthesis while fewer than n have been used.
        if (openCount < n) {
            current.append('(')

            dfs(openCount + 1, closeCount)

            // Backtrack by removing the appended parenthesis.
            current.deleteCharAt(current.lastIndex)
        }

        // Add a closing parenthesis only when it can match an
        // opening parenthesis that has already been added.
        if (closeCount < openCount) {
            current.append(')')

            dfs(openCount, closeCount + 1)

            // Backtrack by removing the appended parenthesis.
            current.deleteCharAt(current.lastIndex)
        }
    }

    dfs(openCount = 0, closeCount = 0)
    return result
}


/*
Time complexity:
O(Cn * N), where Cn is the nth Catalan number.

There are Cn valid combinations, and converting each StringBuilder
to a String takes O(N), where each result has a length of 2N.

Cn = (1 / (N + 1)) * (2N choose N)

Space complexity:
O(N), excluding the returned result.

The recursion depth and StringBuilder contain at most 2N characters.
*/