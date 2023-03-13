package DynamicProgramming;

import java.util.HashMap;

public class CoinsChange1 {
    public static void main(String[] args) {
        HashMap<Integer,Integer> map = new HashMap<>();
        //https://leetcode.com/problems/coin-change/description/
        //https://www.youtube.com/watch?v=jl68j3vLHZk
        //https://www.youtube.com/watch?v=J2eoCvk59Rc&t=852s
        /**
         * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
         *
         * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
         *
         * You may assume that you have an infinite number of each kind of coin.
         *
         *
         *
         * Example 1:
         *
         * Input: coins = [1,2,5], amount = 11
         * Output: 3
         * Explanation: 11 = 5 + 5 + 1
         * Example 2:
         *
         * Input: coins = [2], amount = 3
         * Output: -1
         * Example 3:
         *
         * Input: coins = [1], amount = 0
         * Output: 0
         *
         *
         * Constraints:
         *
         * 1 <= coins.length <= 12
         * 1 <= coins[i] <= 231 - 1
         * 0 <= amount <= 104
         */
    }

    //Time complexity O(m*n)
    //SPace complexity is O(m*n)
    public int coinChange(int[] coins, int amount) {
        int m = coins.length + 1; //The row
        int n = amount + 1; //columns
        int[][] dp = new int[m][n];

        for (int i = 1; i < n; i++) {
            dp[0][i] = Integer.MAX_VALUE - 1; //fill the first row with INF
            // // We need to minus 1 because we are adding 1 in the DP below and that would overflow
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);
                }
            }
        }

        return dp[m - 1][n - 1] == Integer.MAX_VALUE - 1 ? -1 : dp[m - 1][n - 1];

    }
}
