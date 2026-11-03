package leetcode._9Backtracking

fun main() {

}

class Solution {
    fun solveNQueens(n: Int): List<List<String>> {
        val result = mutableListOf<List<String>>()

        // Each row initially contains only empty cells.
        val board = Array(n) { CharArray(n) { '.' } }

        // Track the columns and diagonals that already contain queens.
        val usedColumns = mutableSetOf<Int>()
        val usedPositiveDiagonals = mutableSetOf<Int>() // row + column
        val usedNegativeDiagonals = mutableSetOf<Int>() // row - column

        fun dfs(row: Int) {
            // A queen has been placed successfully in every row.
            if (row == n) {
                result.add(board.map { it.concatToString() })
                return
            }

            // Try placing a queen in every column of the current row.
            for (column in 0 until n) {
                val positiveDiagonal = row + column
                val negativeDiagonal = row - column

                // Skip positions attacked by an existing queen.
                if (
                    column in usedColumns ||
                    positiveDiagonal in usedPositiveDiagonals ||
                    negativeDiagonal in usedNegativeDiagonals
                ) {
                    continue
                }

                // Place the queen and mark its column and diagonals.
                board[row][column] = 'Q'
                usedColumns.add(column)
                usedPositiveDiagonals.add(positiveDiagonal)
                usedNegativeDiagonals.add(negativeDiagonal)

                // Continue by placing a queen in the next row.
                dfs(row + 1)

                // Backtrack by removing the queen and its restrictions.
                board[row][column] = '.'
                usedColumns.remove(column)
                usedPositiveDiagonals.remove(positiveDiagonal)
                usedNegativeDiagonals.remove(negativeDiagonal)
            }
        }

        dfs(row = 0)
        return result
    }
}

/*
Time complexity:
O(N! * N²) as an output-aware upper bound.

Backtracking explores roughly O(N!) valid column placements after
pruning. Constructing each completed board requires O(N²) time.

Space complexity:
O(N²), excluding the returned boards.

The board requires O(N²) space. The recursion stack and the three
sets require O(N) additional space.
*/