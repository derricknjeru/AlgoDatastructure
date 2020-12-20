package arrays_and_strings;

public class ReverseInteger {
    public static void main(String[] args) {

        int x = 1534236469;
        ReverseInteger r = new ReverseInteger();

        System.out.println(r.reverse(x));

    }



    public int reverse3(int x) {
        String reversed = new StringBuilder().append(x).reverse().toString();
        try {
            return (x < 0) ? Integer.parseInt(reversed) * -1 : Integer.parseInt(reversed);
        } catch (NumberFormatException e) {
            return 0;
        }
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

    public int reverse2(int x) {
        if (x == 0) return 0;

        boolean isNegative = false;

        String sInt = String.valueOf(x);

        if (sInt.charAt(0) == '-') {
            isNegative = true;
            sInt = sInt.substring(1);
        }
        char[] arr = sInt.toCharArray();

        double result = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            result = result * 10 + arr[i] - '0';
        }

        if (isNegative) {
            result *= -1;
        }

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }


        return (int) result;


    }
}
