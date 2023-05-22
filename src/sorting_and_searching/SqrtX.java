package sorting_and_searching;

public class SqrtX {
    /**
     * Given a non-negative integer x, compute and return the square root of x.
     * <p>
     * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
     * <p>
     * Note.md.md.md.md: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: x = 4
     * Output: 2
     * Example 2:
     * <p>
     * Input: x = 8
     * Output: 2
     * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
     *
     * @param x
     * @return
     */
    //Time complexity - binary search O(log n) and since we are only doing √n so time complexity is O(log √n);
    //space is constant time
    //https://leetcode.com/problems/sqrtx/
    public int mySqrt(int x) {

        int low = 0;

        int high = x;

        if (x < 2) {
            return x;
        }
        //https://www.youtube.com/watch?v=VYtEKhxKd1Q

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (mid == x / mid) {
                return mid;
            }

            if (x / mid > mid) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }


        }

        return low - 1;

    }
}
