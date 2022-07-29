package revision_v2.Interesting;

import java.util.Arrays;

public class BigIntegerAsArray {
    //https://leetcode.com/discuss/interview-question/1305134/Google-Reject
    public static void main(String[] args) {
        int x = 2345;

        int n = String.valueOf(x).length();
        int[] res = new int[n];

        while (x > 0) {
            int rem = x % 10;
            res[n - 1] = rem;
            x /= 10;
            n--;
        }

        System.out.println(Arrays.toString(res));

        System.out.println(Arrays.toString(plusOne(res)));
    }

    public static int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;

        }

        int[] array = new int[digits.length + 1];
        array[0] = 1;

        return array;
    }
}
