package CodingInterviewPatterns.Recursion;

import java.util.HashMap;

public class ClimbingStairs {
    //https://chat.openai.com/c/7b3a0dba-89eb-49c0-80c0-385dcb473d10
    //https://www.youtube.com/watch?v=ZMNRb9TYiQM
    //https://www.youtube.com/watch?v=Y0lT9Fck7qI

    /**
     * In the recursive solution, we use a function climbStairs that takes an integer n as input and returns the number of distinct ways to climb n steps.
     * <p>
     * The base cases of the recursion are when n is 0 or 1. In these cases, there is only one way to reach the top: by not taking any steps or by taking a single step.
     * <p>
     * For larger values of n, we make two recursive calls to the climbStairs function. The first call is climbStairs(n-1), which represents taking a single step from the (n-1)-th step. The second call is climbStairs(n-2), which represents taking two steps from the (n-2)-th step.
     * <p>
     * We then combine the results of these recursive calls by summing them up. The total number of distinct ways to reach the n-th step is obtained by adding the number of ways to reach (n-1)-th step and (n-2)-th step.
     * <p>
     * The recursion continues until it reaches the base cases, and the final result is returned.
     * <p>
     * While the recursive solution is simple and intuitive, it has an exponential time complexity. This is because it recalculates the same subproblems multiple times. For example, when calculating climbStairs(n-1), it recursively calculates climbStairs(n-2), climbStairs(n-3), and so on, resulting in redundant calculations.
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
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

    public int climbStairs2(int n) {

        if (n == 1) return 1; //Very important. Base case

        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];

    }



}
