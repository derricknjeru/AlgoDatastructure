package leetcode._9Backtracking

fun main() {

}

fun exist(board: Array<CharArray>, word: String): Boolean {
    val rows = board.size
    val columns = board[0].size

    fun dfs(row: Int, column: Int, wordIndex: Int): Boolean {
        // Every character in the word has been matched.
        if (wordIndex == word.length) {
            return true
        }

        // Stop if the position is outside the board.
        if (
            row !in 0 until rows ||
            column !in 0 until columns
        ) {
            return false
        }

        // The current cell must match the required character.
        if (board[row][column] != word[wordIndex]) {
            return false
        }

        // Temporarily mark this cell as visited so it cannot be
        // reused in the current path.
        val originalCharacter = board[row][column]
        board[row][column] = '#'

        // Search the four adjacent cells.
        val found = dfs(row + 1, column, wordIndex + 1) ||
                dfs(row - 1, column, wordIndex + 1) ||
                dfs(row, column + 1, wordIndex + 1) ||
                dfs(row, column - 1, wordIndex + 1)

        // Restore the cell before exploring another path.
        board[row][column] = originalCharacter

        return found
    }

    // Every board cell is a possible starting position.
    for (row in 0 until rows) {
        for (column in 0 until columns) {
            if (dfs(row, column, wordIndex = 0)) {
                return true
            }
        }
    }

    return false
}

/*
Time complexity:
O(R * C * 4^L)

R * C cells may be used as starting positions. For each character,
the search can explore up to four directions. L is word.length.

A tighter bound is O(R * C * 3^(L - 1)) because after the first move,
the search cannot immediately return to the previously visited cell.

Space complexity:
O(L)

The recursion stack can contain at most L calls. The board itself is
used to track visited cells, so no separate visited array is required.
*/