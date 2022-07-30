package revision_v2.Interesting;

public class MaxAreaOfIsland {
    //https://www.youtube.com/watch?v=iJGr1OtmH0c
    //https://leetcode.com/problems/max-area-of-island/
    //https://github.com/neetcode-gh/leetcode/blob/main/695-Max-Area-of-Island.py
    //https://www.youtube.com/watch?v=W8VuDt0eb5c


    int row = 0;
    int col = 0;
    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    boolean[][] seen;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        row = grid.length;
        col = grid[0].length;
        int area = 0;
        seen = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    area = Math.max(area, dfs(grid, i, j));
                }
            }
        }

        return area;

    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] == 0 || seen[i][j])
            return 0;

        seen[i][j] = true;
        int area = 1; //for current point i,j

        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];
            area += dfs(grid, x, y); //add area for other directions
        }

        return area;

    }

}
