package arrays_and_strings;

public class ReverseInteger {
    public static void main(String[] args) {

        int x = -123;
        Solution r = new Solution();

        System.out.println(r.reverse(x));

    }


    /**
     * Given a 32-bit signed integer, reverse digits of an integer.
     * <p>
     * Example 1:
     * <p>
     * Input: 123
     * Output: 321
     * Example 2:
     * <p>
     * Input: -123
     * Output: -321
     * Example 3:
     * <p>
     * Input: 120
     * Output: 21
     * Note.md.md.md.md:
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     */
    /**
     * Complexity Analysis
     * <p>
     * Time Complexity: O(\log(x))O(log(x)). There are roughly \log_{10}(x)log
     * 10
     * ​
     * (x) digits in xx.
     * Space Complexity: O(1)O(1).
     */


    static class Solution {
        public int reverse(int x) {
            int res = 0;
            while (x != 0) {
                int rem = x % 10;
                x /= 10;

                if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && rem > 7)) return 0;
                if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && rem < -8)) return 0;

                res = res * 10 + rem;
            }

            return res;

        }

        public int reverse2(int x) {

            boolean isNegative = false;

            if (x < 0) {
                isNegative = true;
                x *= -1;
            }

            long reversed = 0;

            while (x > 0) {
                reversed = reversed * 10 + x % 10;
                x /= 10;
            }

            if (reversed > Integer.MAX_VALUE) {
                return 0;
            }

            return (int) (isNegative ? -reversed : reversed);

        }
    }

}
