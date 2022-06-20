package stack_and_queue;

public class NumberOfIsland {
    /**
     * Number of Islands
     * Solution
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
     * <p>
     * Example 1:
     * <p>
     * Input:
     * 11110
     * 11010
     * 11000
     * 00000
     * <p>
     * Output: 1
     * Example 2:
     * <p>
     * Input:
     * 11000
     * 11000
     * 00100
     * 00011
     * <p>
     * Output: 3
     */

    //Solutions
    // https://www.youtube.com/watch?v=U6-X_QOwPcs
    //https://www.programcreek.com/2014/04/leetcode-number-of-islands-java/
    //https://www.youtube.com/watch?v=o8S2bO3pmO4
    public int numIslands(char[][] grid) {
        if (grid == null && grid.length == 0) {
            return 0;
        }

        int numOfIslands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == '1') {
                    numOfIslands++;
                    //Turn everything that is one and around this 1 to 0 recursively over dfs.
                    dfs(grid, i, j);
                }
            }

        }

        return numOfIslands;
    }

    private void dfs(char[][] grid, int i, int j) {
        //check boundaries
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        //traverse all the sides
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
