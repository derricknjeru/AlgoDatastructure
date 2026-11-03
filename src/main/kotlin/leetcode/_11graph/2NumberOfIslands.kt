package leetcode._11graph

fun main() {

}
// https://www.youtube.com/watch?v=pV2kpPD66nE
/*
 * LeetCode 200: Number of Islands
 *
 * Analysis:
 *
 * An island is a group of land cells ('1') connected horizontally
 * or vertically.
 *
 * We scan every cell in the grid. When we find unvisited land:
 *
 * 1. Increment the island count.
 * 2. Run DFS from that cell.
 * 3. DFS visits every connected land cell and changes it to '0'.
 *
 * Changing visited land to '0' ensures that the same island is not
 * counted more than once.
 *
 * Example:
 *
 * grid = [
 *   ['1', '1', '0'],
 *   ['1', '0', '0'],
 *   ['0', '0', '1']
 * ]
 *
 * Starting at (0, 0), DFS visits:
 *
 *   (0, 0) -> (1, 0) -> (0, 1)
 *
 * These cells form the first island.
 *
 * The remaining land at (2, 2) forms the second island.
 *
 * Answer: 2
 *
 * Time complexity: O(rows * columns)
 *
 * Every cell is processed at most once.
 *
 * Space complexity: O(rows * columns)
 *
 * In the worst case, the entire grid is land, so the recursive DFS
 * call stack can contain up to rows * columns calls.
 */


fun numIslands(grid: Array<CharArray>): Int {
    // Return early if the grid does not contain any cells.
    if (grid.isEmpty() || grid[0].isEmpty()) return 0

    val rows = grid.size
    val cols = grid[0].size

    // Marks all land connected to (row, col) as visited.
    fun dfs(row: Int, col: Int) {
        /*
         * Stop if:
         * 1. The row is outside the grid, OR
         * 2. The column is outside the grid, OR
         * 3. The cell is water or has already been visited.
         *
         * The boundary checks must come before grid[row][col].
         * Kotlin evaluates || from left to right and stops as soon
         * as one condition is true, preventing an invalid grid access.
         */
        if (
            row !in 0 until rows ||
            col !in 0 until cols ||
            grid[row][col] != '1'
        ) {
            return
        }

        // Mark the current land cell as visited.
        grid[row][col] = '0'

        // Visit all horizontally and vertically adjacent cells.
        dfs(row + 1, col) // Down
        dfs(row - 1, col) // Up
        dfs(row, col + 1) // Right
        dfs(row, col - 1) // Left
    }

    var islands = 0

    // Examine every cell in the grid.
    for (row in 0 until rows) {
        for (col in 0 until cols) {
            // Unvisited land represents the beginning of a new island.
            if (grid[row][col] == '1') {
                islands++

                // Mark this cell and all connected land as visited.
                dfs(row, col)
            }
        }
    }

    return islands
}


/*
 * LeetCode 200: Number of Islands
 *
 * Approach:
 * Scan every cell in the grid. When an unvisited land cell ('1') is found:
 *
 * 1. Increment the number of islands.
 * 2. Run BFS from that cell.
 * 3. BFS visits all horizontally and vertically connected land cells.
 * 4. Mark visited land as water ('0') so it is not counted again.
 *
 * Example:
 *
 * grid = [
 *   ['1', '1', '0'],
 *   ['1', '0', '0'],
 *   ['0', '0', '1']
 * ]
 *
 * We first find land at (0, 0), so the island count becomes 1.
 *
 * BFS visits:
 *   (0, 0) -> (1, 0) and (0, 1)
 *
 * These connected cells form one island and are changed to '0'.
 *
 * Later, we find the remaining land at (2, 2), so the island count
 * becomes 2.
 *
 * Final answer: 2
 *
 * Directions:
 *
 * Each direction is represented as:
 *   intArrayOf(row change, column change)
 *
 *   (1, 0)  -> down:  row + 1, column unchanged
 *   (-1, 0) -> up:    row - 1, column unchanged
 *   (0, 1)  -> right: row unchanged, column + 1
 *   (0, -1) -> left:  row unchanged, column - 1
 *
 * For example, suppose the current position is:
 *
 *   currentRow = 2
 *   currentCol = 3
 *
 * Moving down using (1, 0) gives:
 *
 *   nextRow = 2 + 1 = 3
 *   nextCol = 3 + 0 = 3
 *
 * Therefore, the next position is (3, 3).
 *
 * Diagonal directions such as (1, 1) are not included because islands
 * are connected only horizontally and vertically.
 *
 * Time complexity: O(rows * columns)
 * Space complexity: O(rows * columns) in the worst case
 */


fun numIslands2(grid: Array<CharArray>): Int {
    // Handle an empty grid.
    if (grid.isEmpty() || grid[0].isEmpty()) return 0

    val rows = grid.size
    val columns = grid[0].size

    // Visits and marks the entire island connected to (row, column).
    fun bfs(row: Int, column: Int) {
        val queue = ArrayDeque<Pair<Int, Int>>()

        // Add the first land cell and mark it as visited.
        queue.addLast(Pair(row, column))
        grid[row][column] = '0'

        val directions = arrayOf(
            intArrayOf(1, 0),   // Down
            intArrayOf(-1, 0),  // Up
            intArrayOf(0, 1),   // Right
            intArrayOf(0, -1)   // Left
        )

        while (queue.isNotEmpty()) {
            val (currentRow, currentColumn) = queue.removeFirst()

            // Check all four neighboring cells.
            //for ( directions in directions) {
            for ((dr, dc) in directions) {
                // val nextRow = currentRow + direction[0]
                //val nextColumn = currentColumn + direction[1]
                val nextRow = currentRow + dr
                val nextColumn = currentColumn + dc

                // Visit the neighbor if it is inside the grid
                // and contains unvisited land.
                if (
                    nextRow in 0 until rows &&
                    nextColumn in 0 until columns &&
                    grid[nextRow][nextColumn] == '1'
                ) {
                    // Mark the cell before adding it to the queue
                    // to prevent it from being added more than once.
                    grid[nextRow][nextColumn] = '0'
                    queue.addLast(Pair(nextRow, nextColumn))
                }
            }
        }
    }

    var islands = 0

    // Search the grid for the beginning of each island.
    for (row in 0 until rows) {
        for (column in 0 until columns) {
            if (grid[row][column] == '1') {
                islands++
                bfs(row, column)
            }
        }
    }

    return islands
}
