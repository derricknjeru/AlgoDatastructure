package CodingInterviewPatterns.DynamicProgramming.OneDP;

import java.util.HashMap;

public class ClimbStairs {
    //https://leetcode.com/problems/climbing-stairs/
    //https://www.youtube.com/watch?v=ZMNRb9TYiQM
    //https://www.youtube.com/watch?v=AWezsCJDHSk&t=5s
    //https://www.youtube.com/watch?v=Y0lT9Fck7qI&ab_channel=NeetCode

    /**
     * Time complexity without memoization or DP
     * 2^n - Because we need to make 2 decisions and the height of the tree is roughly n. I.e the longest tree height.
     *
     Without memoization, the time complexity of the algorithm is O(2^n), where n is the number of steps.
     In the recursive approach, without memoization, we consider all possible paths
     by recursively calling the function for (n-1)th step and (n-2)th step.
     Each function call branches into two more function calls, resulting in an exponential number of function calls.
     Without memoization, the space complexity of the algorithm is O(n) due to the recursive calls on the call stack.

     With memoization and Dp time complexity is O(n)
     *
     *
     */

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
        if (n == 1) return 1; //Very important

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

    public int climbStairs3(int n) {
        HashMap<Integer, Integer> memo = new HashMap<>();

        return climbStairsHelper(n, memo);
    }

    private int climbStairsHelper(int n, HashMap<Integer, Integer> memo) {
        // Check if the result is already memoized
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Base cases
        if (n == 0 || n == 1) {
            return 1;
        }

        // Recursive cases
        int distinctWays = climbStairsHelper(n - 1, memo) + climbStairsHelper(n - 2, memo);

        // Memoize the result
        memo.put(n, distinctWays);

        return distinctWays;
    }
}
