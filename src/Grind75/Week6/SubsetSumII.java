package Grind75.Week6;

import java.util.ArrayList;
import java.util.List;

public class SubsetSumII {
    public static void main(String[] args) {
        //https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
        //https://www.youtube.com/watch?v=mBk4I0X46oI
        //https://www.techiedelight.com/subset-sum-problem/
        //https://www.youtube.com/watch?v=obhWqDfzwQQ
        //https://www.youtube.com/watch?v=kyLxTdsT8ws

        /**
         * Given a set of non-negative integers and a value sum, the task is to check if there is a subset of the given set whose sum is equal to the given sum.
         *-
         * Examples:
         *-
         * Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
         * Output: True
         * Explanation: There is a subset (4, 5) with sum 9.
         *-
         * Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 30
         * Output: False
         * Explanation: There is no subset that add up to 30.
         */

        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        Solution obj = new Solution();
        System.out.println("The subset is:");
        System.out.println(obj.isSubsetSum(arr, sum));
    }

    static class Solution {
        //https://www.youtube.com/watch?v=34l1kTIQCIA
        //https://www.techiedelight.com/subset-sum-problem/

        /**
         * TIme complexity is O(no of elements * sum)
         * @param arr
         * @param sum
         * @return
         */
        public List<Integer> isSubsetSum(int[] arr, int sum) {
            int n = arr.length;
            boolean[][] dp = new boolean[n + 1][sum + 1];

            // Initialize the first column as true (0 sum is always possible with an empty subset)
            for (int i = 0; i <= n; i++) {
                dp[i][0] = true;
            }

            // Fill the dp table
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= sum; j++) {
                    if (arr[i - 1] <= j) {
                        // If the current element can be included in the subset
                        // Then find the subset with sum `j` by excluding or including the i'th item
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                    } else {
                        // If the current element is greater than the sum, exclude it
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            // Reconstruct the subset
            if (!dp[n][sum]) {
                return null; // No subset found
            }

            List<Integer> subset = new ArrayList<>();
            int i = n, j = sum;
            while (i > 0 && j > 0) {
                if (!dp[i - 1][j]) {
                    subset.add(arr[i - 1]);
                    j -= arr[i - 1];
                }
                i--;
            }

            return subset;

        }
    }
}
