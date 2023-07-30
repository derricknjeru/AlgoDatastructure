package Grind75.week2;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {
    public static void main(String[] args) {

    }

    class Solution {
        private Map<Integer, Integer> memo = new HashMap<>();

        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }

            if (memo.containsKey(n)) {
                return memo.get(n);
            }

            int ways = climbStairs(n - 1) + climbStairs(n - 2);
            memo.put(n, ways);

            return ways;
        }

    }
}
