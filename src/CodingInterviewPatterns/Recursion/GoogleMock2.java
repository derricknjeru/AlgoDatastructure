package CodingInterviewPatterns.Recursion;

import java.util.HashMap;
import java.util.Map;

public class GoogleMock2 {
    public static void main(String[] args) {

        /**
         * You are eating a candy bar that is made of pieces in a single row (e.g., a Toblerone). You can bite off 1, 2, or 3 pieces at a time. How many different ways can you eat a bar that is n pieces long?
         *
         * if(n<=3) return n;
         *
         * findWays(3)-> 111,(2,1),3,(1,2)
         * findWays(4)-> 1111,(1,1,2),(2,2)(2,1,1),(3,1)(1,3)(1,2,1)
         *
         * if(cache.get(n)){
         *     return result;
         * }
         *
         * result= findWays(n-1)+ findWays(n-2) + findWays(n-3);
         * cache.put(n,result);
         *
         *
         * //1,2,3
         *
         *
         * Time complexity = O(3 *n) ->
         * Space complexity = O(n)
         */

        Map<Integer, Integer> cache = new HashMap<>();

        int n = 5; // Number of pieces in the candy bar
        int ways = countWays(n, cache);
        System.out.println("Number of ways to eat the candy bar: " + ways);

        int ways1 = countWays(n);
        System.out.println("Number of ways to eat the candy bar dynamic: " + ways1);
    }


    public static int countWays(int n, Map<Integer, Integer> cache) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int result;
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        }
        result = countWays(n - 1, cache) + countWays(n - 2, cache) + countWays(n - 3, cache);
        cache.put(n, result);

        return result;


        /**
         * The time complexity of the given question can be analyzed by looking at the recursive function eatCandyBar(n).
         *
         * In the worst case, the function will make three recursive calls for each value of n greater than 3: eatCandyBar(n-1), eatCandyBar(n-2), and eatCandyBar(n-3). This means that the function will have a branching factor of 3.
         *
         * The number of recursive calls made by the function can be visualized as a tree, with each level representing the value of n decreasing by 1. The maximum depth of the tree will be n, and at each level, there will be three branches.
         *
         * Therefore, the number of recursive calls made by the function is roughly 3^0 + 3^1 + 3^2 + ... + 3^(n-1), which can be simplified as (3^n - 1) / 2.
         *
         * Since each recursive call involves a constant amount of work (comparisons and basic arithmetic operations), the time complexity of the eatCandyBar function can be approximated as O(3^n).
         *
         * It's worth noting that this time complexity is an upper bound and represents the worst-case scenario when each recursive call branches into three additional calls. In practice, due to overlapping subproblems, the function may exhibit improved performance with the help of memoization or dynamic programming techniques.
         */

        //improved
        /**
         *
         With the addition of memoization, the time complexity of the countWays function improves significantly compared to the previous recursive version.

         The memoization technique ensures that each distinct value of n is computed only once and stored in the cache. Subsequent recursive calls with the same value of n can directly retrieve the result from the cache, eliminating redundant computations.

         In terms of time complexity, the memoized version of countWays exhibits a much better performance. The computation time is significantly reduced compared to the previous version, which had a time complexity of O(3^n).

         With memoization, the time complexity is greatly reduced to O(n), as each distinct value of n is computed once and stored in the cache. Subsequent lookups in the cache take constant time.

         Therefore, the time complexity of the memoized countWays function is O(n), providing a more efficient solution for calculating the number of different ways to eat a candy bar with n pieces.
         */
    }

    public static int countWays(int n) {
        if (n <= 0) {
            return 0;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }
}
