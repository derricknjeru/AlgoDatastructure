package revision_v2.Interesting;

public class PathWithMaximumGold {
    /**
     * In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
     * <p>
     * Return the maximum amount of gold you can collect under the conditions:
     * <p>
     * Every time you are located in a cell you will collect all the gold in that cell.
     * From your position, you can walk one step to the left, right, up, or down.
     * You can't visit the same cell more than once.
     * Never visit a cell with 0 gold.
     * You can start and stop collecting gold from any position in the grid that has some gold.
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
     * Output: 24
     * Explanation:
     * [[0,6,0],
     * [5,8,7],
     * [0,9,0]]
     * Path to get the maximum gold, 9 -> 8 -> 7.
     * Example 2:
     * <p>
     * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
     * Output: 28
     * Explanation:
     * [[1,0,7],
     * [2,0,6],
     * [3,4,5],
     * [0,3,0],
     * [9,0,20]]
     * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 15
     * 0 <= grid[i][j] <= 100
     *
     *
     * Time Complexity > O(m * n)
     * Space complexity -> O(m * n) ---> seen array and recursion depth.
     */

    class Solution {
        int row, col;
        boolean[][] seen;
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public int getMaximumGold(int[][] grid) {
            row = grid.length;
            col = grid[0].length;
            seen = new boolean[row][col];

            int maxSum = 0;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] > 0) {
                        maxSum = Math.max(maxSum, dfs(grid, i, j, seen));
                    }
                }
            }

            return maxSum;

        }

        private int dfs(int[][] grid, int i, int j, boolean[][] seen) {
            if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0 || seen[i][j]) return 0;

            seen[i][j] = true;
            int val = 0;

            for (int[] dir : directions) {
                int x = dir[0] + i;
                int y = dir[1] + j;
                val = Math.max(val, dfs(grid, x, y, seen));
            }

            //means we are done visiting everything in our current cell;

            seen[i][j] = false;

            return val + grid[i][j];
        }
    }
}
