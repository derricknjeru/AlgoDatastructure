package leetcode_30_days_challenge.week1;

import java.util.HashSet;
import java.util.Set;

public class SquaredDigitSum {
    /**
     * Write an algorithm to determine if a number is "happy".
     * <p>
     * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
     * <p>
     * Example:
     * <p>
     * Input: 19
     * Output: true
     * Explanation:
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     */
    public static void main(String[] args) {
        SquaredDigitSum digitSum = new SquaredDigitSum();

        System.out.println(digitSum.isHappy(19));


    }

    //https://mathlesstraveled.com/2018/04/26/iterating-squared-digit-sum/
    //Explanation above
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }
        //To avoid infinite loop
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNextNumber(n);
        }
        return n == 1;
    }

    private int getNextNumber(int n) {
        int sum = 0;
        int rem;
        while (n != 0) {
            rem = n % 10;
            sum += (rem * rem);
            n /= 10;
        }
        return sum;
    }

}
