package CodingInterviewPatterns.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class MinimumPathSum {
    //https://www.youtube.com/watch?v=t1shZ8_s6jc
    //https://leetcode.com/problems/minimum-path-sum/description/
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage with a 3x3 grid
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int result = solution.minPathSum(grid);

        System.out.println("Minimum Path Sum: " + result);
    }

    static class Solution {
        public int minPathSum(int[][] grid) {
            //         dp table
            //1,3,1 ---> 1 4 5
            //1,5,1 ---> 2 7 6
            //4,2,1 ---> 6 8 7

            int numRows = grid.length;
            int numCols = grid[0].length;

            // Create a DP table to store the minimum path sum values
            int[][] dpTable = new int[numRows][numCols];
            dpTable[0][0] = grid[0][0];

            // Fill the first row of the DP table
            for (int col = 1; col < numCols; col++) {
                dpTable[0][col] = grid[0][col] + dpTable[0][col - 1];
            }

            // Fill the first column of the DP table
            for (int row = 1; row < numRows; row++) {
                dpTable[row][0] = grid[row][0] + dpTable[row - 1][0];
            }

            // Fill the rest of the DP table using the minimum path sum formula
            for (int row = 1; row < numRows; row++) {
                for (int col = 1; col < numCols; col++) {
                    dpTable[row][col] = grid[row][col] + Math.min(dpTable[row - 1][col], dpTable[row][col - 1]);
                }
            }

            // The bottom-right cell of the DP table contains the minimum path sum
            return dpTable[numRows - 1][numCols - 1];
        }
    }

    static class Solution2 {
        // Time complexity with memoization: O(m * n), where 'm' is the number of rows and 'n' is the number of columns in the grid.
        //Without memoization it would have been 2^(m * n)
        public int minPathSum(int[][] grid) {
            Map<String, Integer> memo = new HashMap<>();
            return calculateMinPath(grid, 0, 0, memo);
        }

        /**
         * The reason for using Integer.MAX_VALUE is to ensure that when calculating the minimum path sum,
         * the out-of-bounds paths are effectively ignored because any valid path sum would be smaller
         * than Integer.MAX_VALUE. When comparing the minimum path sum, these out-of-bounds paths will be excluded from consideration.
         *
         * @param grid
         * @param i
         * @param j
         * @param memo
         * @return
         */

        private int calculateMinPath(int[][] grid, int i, int j, Map<String, Integer> memo) {
            // Check if the current position is out of bounds
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
                return Integer.MAX_VALUE;
            }

            String key = i + "," + j;
            // Check if the result for the current position is already memoized
            if (memo.containsKey(key)) {
                return memo.get(key);
            }

            // Check if we have reached the bottom-right corner
            if (i == grid.length - 1 && j == grid[0].length - 1) {
                return grid[i][j];
            }

            // Explore moving right and moving down
            int right = calculateMinPath(grid, i, j + 1, memo);
            int down = calculateMinPath(grid, i + 1, j, memo);

            // Calculate the minimum path sum and memoize the result
            int minPath = grid[i][j] + Math.min(right, down);
            memo.put(key, minPath);

            return minPath;
        }


    }

}
