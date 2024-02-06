package CodingInterviewPatterns.DynamicProgramming;

import java.util.Arrays;

public class CoinChange2 {
    //https://www.youtube.com/watch?v=1mtvm2ubHCY&list=RDCMUCBr_Fu6q9iHYQCh13jmpbrg&index=3&ab_channel=Errichto
    //https://bard.google.com/chat/9435065cf02b64c4

    class Solution {
        //the time complexity of the revised solution remains O(amount * number of coins) in the worst case.
        //For comparison, the original recursive solution without memoization had an exponential time complexity (O(2^n)),
        // leading to the StackOverflowError.


        private int[][] memo;

        public int change(int amount, int[] coins) {
            memo = new int[5001][301]; // Adjust dimensions based on expected input ranges
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }
            return dfs(amount, coins, 0);
        }

        private int dfs(int amount, int[] coins, int index) {
            if (index == coins.length) {
                return amount == 0 ? 1 : 0;
            }

            if (memo[amount][index] != -1) return memo[amount][index];

            int exclude = dfs(amount, coins, index + 1);
            int include = 0;
            if (amount >= coins[index]) {
                include = dfs(amount - coins[index], coins, index);
            }

            memo[amount][index] = include + exclude;
            return memo[amount][index];
        }


    }
}
