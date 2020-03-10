public class ReverseInteger {
    public static void main(String[] args) {

        int x = -123;
        ReverseInteger r = new ReverseInteger();

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
     * Note:
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     */


    public int reverse(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x = x * -1;
        }
        long reversed = 0;
        while (x > 0) {
            reversed = (reversed * 10) + x % 10;
            x = x / 10;
        }

        if (reversed > Integer.MAX_VALUE) {
            return 0;
        }


        return (int) (isNegative ? -reversed : reversed);
    }
}
