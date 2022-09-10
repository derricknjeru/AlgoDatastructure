package DynamicProgramming;

public class UniquePathsII {
    //https://leetcode.com/problems/unique-paths-ii/
    /**
     * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m-1][n-1]). The robot can only move either down or right at any point in time.
     * <p>
     * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
     * <p>
     * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
     * <p>
     * The testcases are generated so that the answer will be less than or equal to 2 * 109.
     */
    //https://www.youtube.com/watch?v=z6kelCB0ww4&t=100s

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        /**
         Time complexity Analysis

         Time Complexity: O(M \times N)O(M×N). The rectangular grid given to us is of size M \times NM×N and we process each cell just once.
         Space Complexity: O(m * N) dp space
         **/
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;


        int dp[][] = new int[row][col];


        //fill first row
        boolean flag = false;
        for (int i = 0; i < col; i++) {
            if (flag || obstacleGrid[0][i] == 1) {
                flag = true;
                dp[0][i] = 0;
            } else {
                dp[0][i] = 1;
            }
        }

        //fill first column
        flag = false;
        for (int i = 0; i < row; i++) {
            if (flag || obstacleGrid[i][0] == 1) {
                flag = true;
                dp[i][0] = 0;
            } else {
                dp[i][0] = 1;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[row - 1][col - 1];
    }


    int REnd = 0;
    int CEnd = 0;

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int row = 0;
        int col = 0;
        REnd = obstacleGrid.length;
        CEnd = obstacleGrid[0].length;
        int[][] cache = new int[REnd][CEnd];
        return pathCount(obstacleGrid, row, col, cache);

    }

    //runtime O(m*n)
    //space complexity is O(m*n);
    //https://www.youtube.com/watch?v=P8Xa2BitN3I
    //recursive
    private int pathCount(int[][] obstacleGrid, int row, int col, int[][] cache) {
        //Is not a valid square
        if (row < 0 || row > REnd - 1 || col < 0 || col > CEnd - 1 || obstacleGrid[row][col] == 1) {
            return 0;
        }
        //is the end of the grid return 1
        if (row == REnd - 1 && col == CEnd - 1) {
            return 1;
        }

        if (cache[row][col] == 0) {
            //sum up number of ways going down and number of ways going to the right.
            cache[row][col] = pathCount(obstacleGrid, row + 1, col, cache) + pathCount(obstacleGrid, row, col + 1, cache);
        }

        return cache[row][col];
    }
}
