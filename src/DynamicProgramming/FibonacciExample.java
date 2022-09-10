package DynamicProgramming;

import java.util.HashMap;

public class FibonacciExample {
    static HashMap<Integer, Integer> cache = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(fibonacciBasicExampleWithCache(20));
    }

    //Time complexity is O(2^n); because there always 2 possibilities
    //Space complexity is O(n) -> since recursion involves stack

    static int fibonacciBasicExample(int n) {
        if (n <= 0)
            return n;
        else if (n == 1)
            return n;
        else return fibonacciBasicExample(n - 1) + fibonacciBasicExample(n - 2);
    }

    //with Memoization
    //https://leetcode.com/explore/learn/card/recursion-i/255/recursion-memoization/1495/
    //Time complexity is O(n); -> since are caching repeated calculations
    //Space complexity is O(n) -> since we are using a stack
    //https://www.youtube.com/watch?v=kIzjdvU_QIU
    //https://www.youtube.com/watch?v=P8Xa2BitN3I
    //DP Method
    static int fibonacciBasicExampleWithCache(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int res = 0;
        if (n <= 0)
            return n;
        else if (n == 1)
            return n;
        else {
            res = fibonacciBasicExample(n - 1) + fibonacciBasicExample(n - 2);
        }
        cache.put(n, res);
        return res;
    }

    public int fibWithCache(int n) {
        int[] array = new int[n + 1];
        return fibRes(array, n);
    }

    public int fibRes(int[] cache, int n) {

        if (cache[n] != 0) return cache[n];

        int res;
        if (n < 2) {
            return n;
        } else {
            res = fibRes(cache, n - 1) + fibRes(cache, n - 2);
        }
        cache[n] = res;
        return res;
    }


}


