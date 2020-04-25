package leetcode_30_days_challenge.week4;

public class BitwiseANDofNumbersRange {
    public static void main(String[] args) {

        BitwiseANDofNumbersRange range = new BitwiseANDofNumbersRange();
        System.out.println(range.rangeBitwiseAnd2(5, 7));

    }

    /**
     * Bitwise AND of Numbers Range
     * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
     * <p>
     * Example 1:
     * <p>
     * Input: [5,7]
     * Output: 4
     * Example 2:
     * <p>
     * Input: [0,1]
     * Output: 0
     */

    //https://www.youtube.com/watch?v=-MWSA_FgLQ8
    public int rangeBitwiseAnd(int m, int n) {

        int result = m;

        if (m == n) {
            return m; //if number are the same it will always return the number
        }
        for (int i = m + 1; i <= n; i++) {
            result = result & i;

            if (result == 0) {
                return 0; //Bitwise AND of any number is with zero is always zero
            }

            if (i == Integer.MAX_VALUE) {
                return result;
            }

        }

        return result;
    }

    public int rangeBitwiseAnd2(int m, int n) {
        int count = 0;

        while (m != n) {
            n >>= 1;
            m >>= 1;
            count++;

        }
        return m << count;
    }
}
