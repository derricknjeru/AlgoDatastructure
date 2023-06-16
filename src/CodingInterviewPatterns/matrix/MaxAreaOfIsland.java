package CodingInterviewPatterns.matrix;

public class MaxAreaOfIsland {
    //https://www.youtube.com/watch?v=iJGr1OtmH0c
    //https://leetcode.com/problems/max-area-of-island/
    //https://github.com/neetcode-gh/leetcode/blob/main/695-Max-Area-of-Island.py
    //https://www.youtube.com/watch?v=W8VuDt0eb5c
    //https://leetcode.com/problems/max-area-of-island/editorial/

    /**
     * Complexity Analysis
     * <p>
     * Time Complexity: O(R∗C), where R is the number of rows in the given grid, and C is the number of columns. We visit every square once.
     * <p>
     * Space complexity: O(R∗C), the space used by seen to keep track of visited squares, and the space used by the call stack during our recursion.
     */


    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        if (row == 0) return 0;
        int col = grid[0].length;
        int max = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j, row, col));
                }
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int i, int j, int row, int col) {
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0) return 0;

        grid[i][j] = 0; //reset since we have visited

        int count = 1;

        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];

            count += dfs(grid, x, y, row, col);
        }

        return count;
    }


}
