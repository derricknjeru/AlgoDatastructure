package DynamicProgramming;

public class UniquePaths {

    public static void main(String[] args) {

    }

    //Best implementation {@link https://www.youtube.com/watch?v=rBAxUTqvlQA&t=404s}
    public int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n]; //dp table

        //go through the matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //we are in the firt row or first col and we should set everything to 1
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    //internal cell you take some of top and left cell
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    int REnd = 0;
    int CEnd = 0;

    public int uniquePaths2(int m, int n) {
        int[][] cache = new int[m][n];
        int[][] grid = new int[m][n];
        REnd = m;
        CEnd = n;
        return pathCount(grid, 0, 0, cache);
    }

    //runtime O(m*n)
    //space complexity is O(m*n);
    //https://www.youtube.com/watch?v=P8Xa2BitN3I
    //recursive

    private int pathCount(int[][] obstacleGrid, int row, int col, int[][] cache) {
        //Is not a valid square
        if (row < 0 || row > REnd - 1 || col < 0 || col > CEnd - 1) {
            return 0;
        }
        //is the end of the grid
        if (row == REnd - 1 && col == CEnd - 1) {
            return 1;
        }

        if (cache[row][col] == 0) {
            //sum up number of ways going down and number of ways going to the right.
            cache[row][col] = pathCount(obstacleGrid, row, col + 1, cache) + pathCount(obstacleGrid, row + 1, col, cache);
        }

        return cache[row][col];
    }
}
