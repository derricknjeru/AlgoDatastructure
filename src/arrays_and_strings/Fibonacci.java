package arrays_and_strings;

import java.util.HashMap;

public class Fibonacci {
    /**
     * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
     * <p>
     * F(0) = 0, F(1) = 1
     * F(n) = F(n - 1) + F(n - 2), for n > 1.
     * Given n, calculate F(n).
     * <p>
     * Example 1:
     * <p>
     * Input: n = 2
     * Output: 1
     * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
     * Example 2:
     * <p>
     * Input: n = 3
     * Output: 2
     * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
     * Example 3:
     * <p>
     * Input: n = 4
     * Output: 3
     * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
     *
     * @param n
     * @return
     */
    //Time complexity is O(2^n);
    //Space complexity is O(n) -> since recursion involves stack
    public static int fibonacci2(int n) {
        if (n < 2) {
            return n;
        } else {
            return fibonacci2(n - 1) + fibonacci2(n - 2);
        }
    }

    //with Memoization
    //https://leetcode.com/explore/learn/card/recursion-i/255/recursion-memoization/1495/
    //Time complexity is O(n); -> since are caching repeated calculations
    //Space complexity is O(n) -> since we are using a stack
    //https://www.youtube.com/watch?v=kIzjdvU_QIU
    //DP Method

    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();

    private int fib(int N) {
        if (cache.containsKey(N)) {
            return cache.get(N);
        }
        int result;
        if (N < 2) {
            result = N;
        } else {
            result = fib(N - 1) + fib(N - 2);
        }
        // keep the result in cache.
        cache.put(N, result);
        return result;
    }

    //improved dynamic programming
    //Time complexity is O(n); -> since it is iterative - linear
    //Space complexity is O(1) -> no extra space
    //https://www.youtube.com/watch?v=WrWFfzt3ayc
    //https://www.youtube.com/watch?v=AWezsCJDHSk&t=1s
    public int fibonacci(int n) {
        if (n < 2) return n;

        int x = 0;
        int y = 1;

        for (int i = 2; i < n; i++) {
            int temp = y;
            y = y + x;
            x = temp;
        }

        return (x + y);


    }

}
