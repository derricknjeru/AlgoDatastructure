package CodingInterviewPatterns.Recursion;

public class Pow {
    //fails with time limit for this test cases

    /**
     * 1.00000
     * 2147483647
     */
   //https://leetcode.com/problems/powx-n/
    //https://www.youtube.com/watch?v=g9YQyYi4IQQ
    //https://www.youtube.com/watch?v=snOaKR2xgZg
    public double myPow(double x, int n) {

        double result = helper(x, Math.abs(n));

        if (n < 0) return 1 / result;

        return result;
    }

    private double helper(double x, long n) {
        if (n == 0) return 1.0;

        if (n == 1) return x;

        double res = helper(x * x, n / 2);

        if (n % 2 != 0) res *= x;

        return res;
    }
}
