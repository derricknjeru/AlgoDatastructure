package CodingInterviewPatterns.matrix;

public class LongestIncreasingPathInAMatrix {
//https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/
    //https://leetcode.com/discuss/interview-question/3120664/Google-WA-US-or-Phone-1-and-2
    //https://www.youtube.com/watch?v=wCc_nd-GiEc&ab_channel=NeetCode
    //https://www.youtube.com/watch?v=bI27Vnwakxc&t=3s&ab_channel=ShiranAfergan''

    public static void main(String[] args) {
        int[][] matrix = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };

        Solution solution = new Solution();
        int longestPath = solution.longestIncreasingPath(matrix);

        System.out.println("The longest increasing path length is: " + longestPath);
    }

    static class Solution {
        private int rows;
        private int cols;
        private int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return 0;

            rows = matrix.length;
            cols = matrix[0].length;

            int[][] cache = new int[rows][cols];

            int maxLen = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    maxLen = Math.max(maxLen, dfs(matrix, cache, i, j, Integer.MIN_VALUE));
                }
            }

            return maxLen;
        }

        private int dfs(int[][] matrix, int[][] cache, int r, int c, int prevValue) {
            if (r < 0 || r >= rows || c < 0 || c >= cols || matrix[r][c] <= prevValue) return 0;

            if (cache[r][c] != 0) return cache[r][c];

            int maxLength = 0;
            // Calculate max length through the 4 directions
            for (int[] dir : directions) {
                int x = dir[0] + r;
                int y = dir[1] + c;
                maxLength = Math.max(maxLength, 1 + dfs(matrix, cache, x, y, matrix[r][c]));
            }

            // Store the current value in the cache
            cache[r][c] = maxLength;

            return cache[r][c];
        }
    }

    class Solution2 {
        private int rows;
        private int cols;

        public int longestIncreasingPath(int[][] matrix) {

            if (matrix.length == 0 || matrix[0].length == 0) return 0;

            rows = matrix.length;
            cols = matrix[0].length;

            int[][] cache = new int[rows][cols];
            int maxLen = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    maxLen = Math.max(maxLen, dfs(matrix, cache, i, j, Integer.MIN_VALUE));
                }
            }

            return maxLen;
        }

        private int dfs(int[][] matrix, int[][] cache, int r, int c, int prevValue) {
            if (r < 0 || r >= rows || c < 0 || c >= cols || matrix[r][c] <= prevValue) return 0;

            if (cache[r][c] != 0) return cache[r][c];

            int maxLength = 0;

            maxLength = Math.max(maxLength, 1 + dfs(matrix, cache, r + 1, c, matrix[r][c]));
            maxLength = Math.max(maxLength, 1 + dfs(matrix, cache, r - 1, c, matrix[r][c]));
            maxLength = Math.max(maxLength, 1 + dfs(matrix, cache, r, c + 1, matrix[r][c]));
            maxLength = Math.max(maxLength, 1 + dfs(matrix, cache, r, c - 1, matrix[r][c]));

            cache[r][c] = maxLength;

            return cache[r][c];
        }
    }
}
