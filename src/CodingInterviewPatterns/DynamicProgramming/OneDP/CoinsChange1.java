package CodingInterviewPatterns.DynamicProgramming.OneDP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoinsChange1 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //https://leetcode.com/problems/coin-change/description/
        //https://www.youtube.com/watch?v=H9bfqozjoqs&ab_channel=NeetCode
        //https://www.youtube.com/watch?v=1mtvm2ubHCY&list=RDCMUCBr_Fu6q9iHYQCh13jmpbrg&index=3&ab_channel=Errichto
        //https://www.youtube.com/watch?v=jgiZlGzXMBw&ab_channel=BackToBackSWE

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

    class Solution {
        /**
         * The time complexity of this solution is O(amount * coins.Length)
         * and the space complexity is O(amount) since we are using a single-dimensional
         * array to store the minimum number of coins for each amount.
         *
         * @param coins
         * @param amount
         * @return
         */
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1); // can be any value greater than amount
            dp[0] = 0;

            for (int i = 1; i <= amount; i++) {
                //get the min number of coins for each amount from 0 ---> n;
                for (int coin : coins) { //go through each coin
                    if (i - coin >= 0) {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                        // dp[i - coin] represent minimum number of coins required
                        //to make up the remaining amount after subtracting coin, and
                        //adding 1 represents the current coin we're considering.
                    }
                }
            }

            return dp[amount] > amount ? -1 : dp[amount];
        }


    }


    class Solution2 {
        //Top down ---> recursive
        private Map<Integer, Integer> memo = new HashMap<>();

        public int coinChange(int[] coins, int amount) {
            if (amount == 0) {
                return 0; // Base case: amount reached, no coins needed
            }
            if (amount < 0) {
                return -1; // Base case: invalid amount, cannot make change
            }

            if (memo.containsKey(amount)) {
                return memo.get(amount); // Use cached result if available
            }

            int minCoins = Integer.MAX_VALUE; // Initialize minimum coins to maximum value

            for (int coin : coins) {
                int remainingAmount = amount - coin;
                int result = coinChange(coins, remainingAmount); // Recursive call

                // If valid result (not -1) and less than current minimum, update minimum
                if (result != -1) {
                    minCoins = Math.min(minCoins, result + 1);
                }
            }

            minCoins = (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
            memo.put(amount, minCoins); // Cache the result

            return minCoins;
        }
    }
}
