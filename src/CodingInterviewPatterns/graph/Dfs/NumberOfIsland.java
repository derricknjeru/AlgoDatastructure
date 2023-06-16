package CodingInterviewPatterns.graph.Dfs;

public class NumberOfIsland {
    int row = 0;
    int col = 0;
    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

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

    //https://www.youtube.com/watch?v=__98uL6wst8&t=596s
    void markCurrentIsland(char[][] matrix, int x, int y, int r, int c) {
        if (x < 0 || x >= r || y < 0 || y >= c || matrix[x][y] != '1')  //Boundary case for matrix
            return;

        //Mark current cell as visited
        matrix[x][y] = '2';

        //Make recursive call in all 4 adjacent directions
        markCurrentIsland(matrix, x + 1, y, r, c);  //DOWN
        markCurrentIsland(matrix, x, y + 1, r, c);  //RIGHT
        markCurrentIsland(matrix, x - 1, y, r, c);  //TOP
        markCurrentIsland(matrix, x, y - 1, r, c);  //LEFT
    }

    public int numIslands(char[][] grid) {

        int rows = grid.length;
        if (rows == 0)     //Empty grid boundary case
            return 0;
        int cols = grid[0].length;

        //Iterate for all cells of the array
        int noOfIslands = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1') {
                    markCurrentIsland(grid, i, j, rows, cols);
                    noOfIslands += 1;
                }
            }
        }
        return noOfIslands;
    }


    //Solutions
    // https://www.youtube.com/watch?v=U6-X_QOwPcs
    //https://www.programcreek.com/2014/04/leetcode-number-of-islands-java/
    //https://www.youtube.com/watch?v=o8S2bO3pmO4
    public int numIslands1(char[][] grid) {
        if (grid == null && grid.length == 0) {
            return 0;
        }

        int numOfIslands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == '1') {
                    numOfIslands++;
                    //Turn everything that is one and around this 1 to 0 recursively over dfs.
                    dfs1(grid, i, j);
                }
            }

        }

        return numOfIslands;
    }

    private void dfs1(char[][] grid, int i, int j) {
        //check boundaries
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        //traverse all the sides
        dfs1(grid, i + 1, j);
        dfs1(grid, i - 1, j);
        dfs1(grid, i, j + 1);
        dfs1(grid, i, j - 1);
    }

    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean visited[][] = new boolean[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || grid[i][j] == '0')
                    continue;
                count++;
                dfs2(i, j, grid, visited, n, m);
            }
        }
        return count;
    }

    void dfs2(int i, int j, char[][] grid, boolean[][] visited, int n, int m) {
        if (i < 0 || i >= n || j < 0 || j >= m || visited[i][j])
            return;
        if (grid[i][j] == '0') {
            visited[i][j] = true;
            return;
        }
        visited[i][j] = true;
        dfs2(i + 1, j, grid, visited, n, m);
        dfs2(i - 1, j, grid, visited, n, m);
        dfs2(i, j + 1, grid, visited, n, m);
        dfs2(i, j - 1, grid, visited, n, m);
    }


    public int numIslandsX(char[][] grid) {
        if (grid == null && grid.length == 0) {
            return 0;
        }
        row = grid.length;
        col = grid[0].length;

        int islands = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    dfsX(grid, i, j);
                }

            }
        }

        return islands;

    }

    private void dfsX(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] == '0') return;

        grid[i][j] = '0';

        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];

            dfsX(grid, x, y);
        }

    }
}
