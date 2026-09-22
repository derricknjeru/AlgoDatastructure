package leetcode.arrayandhashing

fun main() {

}


// Time Complexity: O(1) - The board size is fixed at 9x9, so the nested loops run at most 81 times.
// Space Complexity: O(1) - The total number of elements stored across rows, cols, and boxes sets is bounded by at most 3 * 81 items.
// https://www.youtube.com/watch?v=TjFXEUCMqI8
/**
 * Breakdown for interviews
 * Generalized N X N board: If the board were size N X N, the time complexity would be O(N^2)
 * and space complexity would be O(N^2).
 * Fixed 9 X 9 board:Because N = 9 is a constant given in the problem statement,
 * both time and space simplify to O(1).
 */
fun isValidSudoku(board: Array<CharArray>): Boolean {
    // Track numbers seen in each row, column, and 3x3 box.
    val rows = Array(9) { mutableSetOf<Char>() }
    val cols = Array(9) { mutableSetOf<Char>() }
    val boxes = Array(9) { mutableSetOf<Char>() }

    for (r in 0 until 9) {
        for (c in 0 until 9) {
            val num = board[r][c]

            // Ignore empty cells.
            if (num == '.') continue

            // Calculate which 3x3 box this cell belongs to.
            val box = (r / 3) * 3 + (c / 3)

            // If the number already exists in the row,
            // column, or box, the board is invalid.
            if (num in rows[r] ||
                num in cols[c] ||
                num in boxes[box]
            ) {
                return false
            }

            // Add the number to the corresponding sets.
            rows[r].add(num)
            cols[c].add(num)
            boxes[box].add(num)
        }
    }

    return true
}
