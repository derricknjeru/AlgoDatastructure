package CodingInterviewPatterns.matrix;

public class IslandPerimeter {
    //https://leetcode.com/problems/island-perimeter/description/
    //https://www.youtube.com/watch?v=fISIuAFRM2s&ab_channel=NeetCode

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };

        int perimeter = solution.islandPerimeter(grid); // 16
        System.out.println("Perimeter of the island: " + perimeter); //16
    }

    /**
     * Let's analyze the time complexity of both the direct approach and the DFS approach.
     * -
     * Direct Approach:
     * -
     * The direct approach iterates through each cell in the grid exactly once. In each iteration,
     * it performs constant-time operations.
     * Therefore, the time complexity of the direct approach is O(rows * cols),
     * where 'rows' is the number of rows in the grid and 'cols' is the number of columns.
     * DFS Approach:
     * -
     * In the DFS approach, the depth-first search is performed from each land cell, and each cell is visited only once.
     * The worst-case scenario occurs when the entire grid is a single island, and the DFS is called for every cell.
     * In this case, the time complexity is still O(rows * cols) because each cell is visited once and each edge is
     * traversed once.
     * In terms of time complexity, both approaches are equivalent at O(rows * cols).
     * However, the constant factors may differ,
     * and the direct approach is often more efficient in practice due to its simplicity and avoidance of recursive function calls.
     * It's important to note that constant factors and actual performance can vary based on specific implementation
     * details and language/runtime characteristics.
     */

    static class Solution {
        public int islandPerimeter(int[][] grid) {
            int rows = grid.length;
            if (rows == 0) {
                return 0;
            }

            int cols = grid[0].length;
            int perimeter = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // Check if the current cell is a land cell
                    if (grid[i][j] == 1) {
                        // Each land cell contributes a base perimeter of 4
                        perimeter += 4;

                        // Checking Above Cell (i - 1):
                        // - if (i > 0) ensures that we are not checking the top row, as there is no cell above the first row.
                        // - grid[i - 1][j] == 1 checks if the cell above the current cell (grid[i][j]) is a land cell.
                        // - If both conditions are true, it means there is a land cell above the current cell, so we subtract 2 from the total perimeter.
                        if (i > 0 && grid[i - 1][j] == 1) {
                            perimeter -= 2;
                        }

                        // Checking Left Cell (j - 1):
                        // - if (j > 0) ensures that we are not checking the leftmost column, as there is no cell to the left of the first column.
                        // - grid[i][j - 1] == 1 checks if the cell to the left of the current cell is a land cell.
                        // - If both conditions are true, it means there is a land cell to the left of the current cell, so we subtract 2 from the total perimeter.
                        if (j > 0 && grid[i][j - 1] == 1) {
                            perimeter -= 2;
                        }
                    }
                }
            }

            // Return the calculated perimeter
            return perimeter;
        }
    }


    static class Solution2 {
        private int rows;
        private int cols;
        // private final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public int islandPerimeter(int[][] grid) {
            if (grid.length == 0 || grid[0].length == 0) return 0;
            rows = grid.length;
            cols = grid[0].length;
            boolean[][] visited = new boolean[rows][cols];
            int sum = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1 && !visited[i][j]) {
                        return dfs(grid, visited, i, j);
                    }
                }
            }
            return 0;
        }

        private int dfs(int[][] grid, boolean[][] visited, int i, int j) {
            if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == 0) {
                return 1;
            }

            if (visited[i][j]) {
                return 0;
            }

            visited[i][j] = true; // Mark the cell as visited

            int perimeter = 0;
            perimeter += dfs(grid, visited, i + 1, j);
            perimeter += dfs(grid, visited, i - 1, j);
            perimeter += dfs(grid, visited, i, j + 1);
            perimeter += dfs(grid, visited, i, j - 1);


            return perimeter;
        }
    }

    class Solution3 {
        private int rows = 0;
        private int cols = 0;
        private int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public int islandPerimeter(int[][] grid) {
            if (grid.length == 0 || grid[0].length == 0) return 0;
            rows = grid.length;
            cols = grid[0].length;
            boolean[][] visited = new boolean[rows + 1][cols + 1];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1 && !visited[i][j]) {
                        return dfs(grid, visited, i, j);
                    }
                }
            }
            return 0;
        }

        private int dfs(int[][] grid, boolean[][] visited, int i, int j) {
            if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == 0) {
                return 1;
            }

            if (visited[i][j]) {
                return 0;
            }

            visited[i][j] = true; // Mark the cell as visited

            int perimeter = 0;
            for (int[] dir : directions) {
                int x = dir[0] + i;
                int y = dir[1] + j;

                perimeter += dfs(grid, visited, x, y);
            }

            return perimeter;
        }

    }
}
