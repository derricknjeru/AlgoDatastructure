package CodingInterviewPatterns.DynamicProgramming;

public class ClimbStairs {
    //https://leetcode.com/problems/climbing-stairs/
    //https://www.youtube.com/watch?v=ZMNRb9TYiQM
    //https://www.youtube.com/watch?v=AWezsCJDHSk&t=5s

    /**
     * You are climbing a staircase. It takes n steps to reach the top.
     * <p>
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: n = 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * Example 2:
     * <p>
     * Input: n = 3
     * Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) return n;

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public int climbStairs2(int n) {
        if (n == 1) return n;

        int oneStep = 1;
        int twoSteps = 2;

        for (int i = 3; i <= n; i++) {
            int temp = twoSteps;
            twoSteps = twoSteps + oneStep;
            oneStep = temp;
        }

        return twoSteps;
    }
}
