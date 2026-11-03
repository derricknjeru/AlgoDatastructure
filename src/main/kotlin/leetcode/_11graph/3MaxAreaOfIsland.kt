package leetcode._11graph

fun main() {

}


fun maxAreaOfIsland(grid: Array<IntArray>): Int {
    /*
     * Approach: Depth-First Search (DFS)
     *
     * An island is a group of land cells (1s) connected vertically
     * or horizontally. For every unvisited land cell, we start a DFS
     * and count all the cells belonging to that island.
     *
     * To avoid visiting a land cell more than once, we change its value
     * from 1 to 0 when it is visited.
     */

    val rows = grid.size
    val columns = grid[0].size
    var maximumArea = 0

    /**
     * Returns the area of the island connected to (row, column).
     */
    fun dfs(row: Int, column: Int): Int {
        /*
         * Stop exploring when:
         * 1. The position is outside the grid.
         * 2. The position is water (0).
         * 3. The position is already visited, because visited land
         *    cells are also changed to 0.
         */
        if (
            row !in 0 until rows ||
            column !in 0 until columns ||
            grid[row][column] == 0
        ) {
            return 0
        }

        // Mark the current land cell as visited.
        grid[row][column] = 0

        /*
         * The current cell contributes an area of 1.
         * Add the areas found in all four directions.
         */
        return 1 +
                dfs(row + 1, column) + // Down
                dfs(row - 1, column) + // Up
                dfs(row, column + 1) + // Right
                dfs(row, column - 1)   // Left
    }

    // Examine every cell in the grid.
    for (row in 0 until rows) {
        for (column in 0 until columns) {
            // A value of 1 means that we found an unvisited island.
            if (grid[row][column] == 1) {
                val currentArea = dfs(row, column)

                // Keep the largest island area found so far.
                maximumArea = maxOf(maximumArea, currentArea)
            }
        }
    }

    return maximumArea
}


/*
 * Complexity analysis:
 *
 * Time: O(rows * columns)
 * Every grid cell is processed at most once.
 *
 * Space: O(rows * columns)
 * In the worst case, the entire grid is one island, so the recursive
 * DFS call stack can contain rows * columns calls.
 *
 * Note:
 * This implementation modifies the input grid by replacing visited
 * land cells with 0.
 */