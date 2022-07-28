package SearchingAndSorting;

public class ValidPerfectSquare {
    /**
     * Given a positive integer num, write a function which returns True if num is a perfect square else False.
     * <p>
     * Follow up: Do not use any built-in library function such as sqrt.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: num = 16
     * Output: true
     * Example 2:
     * <p>
     * Input: num = 14
     * Output: false
     */

    public boolean isPerfectSquare(int num) {

        if (num == 1) {
            return true;
        }

        int low = 1;
        int high = num;

        while (low <= high) {

            long mid = low + (high - low) / 2;

            if (mid * mid == num) {
                return true;
            }

            if (mid * mid > num) {
                high = (int) mid - 1;
            } else {
                low = (int) mid + 1;
            }

        }

        return false;
    }
}
