package CodingInterviewPatterns.matrix;

import java.util.*;

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

    //https://www.youtube.com/watch?v=__98uL6wst8&t=596s
    static class Solution {

        public int numIslands(char[][] grid) {
            int rows = grid.length;
            if (rows == 0) return 0;

            int cols = grid[0].length;

            int count = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                        dfs(grid, i, j);
                    }
                }
            }
            return count;
        }

        private void dfs(char[][] grid, int r, int c) {
            if (r < 0 || r >= grid.length || c < 0 || c >= grid[r].length || grid[r][c] != '1') return;

            grid[r][c] = '0';

            dfs(grid, r + 1, c);
            dfs(grid, r - 1, c);
            dfs(grid, r, c + 1);
            dfs(grid, r, c - 1);

        }
    }

    static class Solution2 {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public int numIslands(char[][] grid) {
            int rows = grid.length;
            if (rows == 0) return 0;

            int cols = grid[0].length;

            int count = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                        dfs(grid, i, j);
                    }
                }
            }
            return count;
        }

        private void dfs(char[][] grid, int r, int c) {
            if (r < 0 || r >= grid.length || c < 0 || c >= grid[r].length || grid[r][c] != '1') return;

            grid[r][c] = '0';

            for (int[] dir : directions) {
                int x = dir[0] + r;
                int y = dir[1] + c;
                dfs(grid, x, y);
            }

        }
    }

    static class Solution3 {
        public int numIslands(char[][] grid) {
            int rows = grid.length;
            if (rows == 0) return 0;

            int cols = grid[0].length;

            int count = 0;
            boolean[][] visited = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        count++;
                        bfs(i, j, grid, visited);
                    }
                }
            }
            return count;
        }

        private void bfs(int startRow, int startCol, char[][] grid, boolean[][] visited) {
            int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            int numRows = grid.length;
            int numCols = grid[0].length;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{startRow, startCol});
            visited[startRow][startCol] = true;

            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int row = curr[0];
                int col = curr[1];

                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols && grid[newRow][newCol] == '1' && !visited[newRow][newCol]) {
                        queue.offer(new int[]{newRow, newCol});
                        visited[newRow][newCol] = true;
                    }
                }
            }
        }
    }


    //Solutions
    // https://www.youtube.com/watch?v=U6-X_QOwPcs
    //https://www.programcreek.com/2014/04/leetcode-number-of-islands-java/
    //https://www.youtube.com/watch?v=o8S2bO3pmO4
    //https://interviewing.io/questions/number-of-islands


    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};

        Solution solution = new Solution();
        int numIslands = solution.numIslands(grid);
        System.out.println("Number of islands: " + numIslands);
    }
}
