package CodingInterviewPatterns.matrix;

public class MaximumNumberOfFishInAGrid {
    //https://chat.openai.com/c/544039aa-2281-408a-99b7-df44f57f772c
    static class Solution {

        public int findMaxFish(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int maxFish = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] > 0) {
                        boolean[][] visited = new boolean[m][n];
                        maxFish = Math.max(maxFish, dfs(grid, i, j, visited, m, n));
                    }
                }
            }

            return maxFish;
        }

        private int dfs(int[][] grid, int i, int j, boolean[][] visited, int m, int n) {
            if (!isWithinBounds(i, j, m, n) || grid[i][j] == 0 || visited[i][j]) {
                return 0;
            }
            visited[i][j] = true;

            int fish = grid[i][j];

            int up = dfs(grid, i - 1, j, visited, m, n);
            int down = dfs(grid, i + 1, j, visited, m, n);
            int left = dfs(grid, i, j - 1, visited, m, n);
            int right = dfs(grid, i, j + 1, visited, m, n);

            return fish + up + down + left + right;
        }

        private boolean isWithinBounds(int i, int j, int m, int n) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();

            int[][] grid1 = {{0, 2, 1, 0}, {4, 0, 0, 3}, {1, 0, 0, 4}, {0, 3, 2, 0}};
            System.out.println(solution.findMaxFish(grid1)); // Output: 7

            int[][] grid2 = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}};
            System.out.println(solution.findMaxFish(grid2)); // Output: 1
        }
    }

}
