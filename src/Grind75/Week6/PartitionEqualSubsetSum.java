package Grind75.Week6;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        ///https://leetcode.com/problems/partition-equal-subset-sum/description/
    }
    class Solution {
        public boolean canPartition(int[] nums) {
            int totalSum = 0;
            for (int a : nums) totalSum += a;

            if (totalSum % 2 != 0) return false;

            return isSubsetSum(nums, totalSum / 2);
        }

        public boolean isSubsetSum(int[] arr, int sum) {
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

            // The result is stored in the bottom-right corner of the dp table
            return dp[n][sum];

        }
    }
}
