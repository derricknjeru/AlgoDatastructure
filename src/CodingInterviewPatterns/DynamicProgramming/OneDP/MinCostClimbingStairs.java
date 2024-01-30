package CodingInterviewPatterns.DynamicProgramming.OneDP;

public class MinCostClimbingStairs {
    //https://leetcode.com/problems/min-cost-climbing-stairs/description/
    //https://www.youtube.com/watch?v=ktmzAZWkEZ0&ab_channel=NeetCode

    /**
     * The time complexity of the provided solution is O(n), where n is the length of the cost array. This is because we iterate through the cost array once to calculate the minimum cost at each step.
     * Each iteration takes constant time, as we perform simple arithmetic operations and comparisons.
     */
    static class Solution {

        public int minCostClimbingStairs(int[] cost) {
            for (int i = cost.length - 3; i >= 0; i--) {
                cost[i] += Math.min(cost[i + 1], cost[i + 2]);
            }
            return Math.min(cost[0], cost[1]);
        }

        public int minCostClimbingStairs2(int[] cost) {
            int n = cost.length;
            int[] dp = new int[n];

            dp[0] = cost[0];
            dp[1] = cost[1];

            for (int i = 2; i < n; i++) {
                dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
            }

            return Math.min(dp[n - 1], dp[n - 2]);
        }
    }

    public static void main(String[] args) {
        Solution sln = new Solution();
        int[] cost = {10,15,20}; //Output: 15
        System.out.println(sln.minCostClimbingStairs(cost));

        int[] cost1 = {1,100,1,1,1,100,1,1,100,1}; //Output: 6
        System.out.println(sln.minCostClimbingStairs(cost1));
    }
}
