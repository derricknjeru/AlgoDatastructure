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

        int perimeter = solution.islandPerimeter(grid);
        System.out.println("Perimeter of the island: " + perimeter);
    }

    static class Solution {
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

    class Solution2 {
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
