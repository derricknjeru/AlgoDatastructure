package CodingInterviewPatterns.Recursion;

import java.util.HashMap;

public class Fibonacci {
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
