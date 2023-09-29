package Grind75.Week6;

import java.util.HashMap;
import java.util.Map;

public class SubsetSum {
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
        if (obj.isSubsetSum(arr, sum)) {
            System.out.println("Output: 1");
            System.out.println("Explanation: There exists a subset with sum = " + sum);
        } else {
            System.out.println("Output: 0");
            System.out.println("Explanation: There is no subset with sum " + sum);

        }
    }

    static class Solution {
        //https://www.youtube.com/watch?v=34l1kTIQCIA
        //https://www.techiedelight.com/subset-sum-problem/
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


    static class Solution2 {

        public boolean isSubsetSum(int[] arr, int sum) {
            Map<String, Boolean> lookup = new HashMap<>();
            return subsetSum(arr, arr.length - 1, sum, lookup);
        }

        // Returns true if there exists a subsequence of `A[0…n]` with the given sum
        public boolean subsetSum(int[] A, int n, int k, Map<String, Boolean> lookup) {
            // return true if the sum becomes 0 (subset found)
            if (k == 0) {
                return true;
            }

            // base case: no items left, or sum becomes negative
            if (n < 0 || k < 0) {
                return false;
            }

            // construct a unique map key from dynamic elements of the input
            String key = n + "|" + k;

            // if the subproblem is seen for the first time, solve it and
            // store its result in a map
            if (!lookup.containsKey(key)) {
                // Case 1. Include the current item `A[n]` in the subset and recur
                // for the remaining items `n-1` with the decreased total `k-A[n]`
                boolean include = subsetSum(A, n - 1, k - A[n], lookup);

                // Case 2. Exclude the current item `A[n]` from the subset and recur for
                // the remaining items `n-1`
                boolean exclude = subsetSum(A, n - 1, k, lookup);

                // assign true if we get subset by including or excluding
                // current item
                lookup.put(key, include || exclude);
            }

            // return solution to the current subproblem
            return lookup.get(key);
        }
    }
}