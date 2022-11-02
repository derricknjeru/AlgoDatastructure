package arrays_and_strings;

import java.util.ArrayList;
import java.util.List;

public class BinaryPrefixDivisibleBy5 {
    //https://leetcode.com/problems/binary-prefix-divisible-by-5/

    /**
     * You are given a binary array nums (0-indexed).
     * <p>
     * We define xi as the number whose binary representation is the subarray nums[0..i] (from most-significant-bit to least-significant-bit).
     * <p>
     * For example, if nums = [1,0,1], then x0 = 1, x1 = 2, and x2 = 5.
     * Return an array of booleans answer where answer[i] is true if xi is divisible by 5.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [0,1,1]
     * Output: [true,false,false]
     * Explanation: The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.
     * Only the first number is divisible by 5, so answer[0] is true.
     * Example 2:
     * <p>
     * Input: nums = [1,1,1]
     * Output: [false,false,false]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 105
     * nums[i] is either 0 or 1.
     *
     * @param nums
     * @return
     */
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> res = new ArrayList<>();

        int num = nums[0];
        if (num % 5 == 0) res.add(true);
        else res.add(false);

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == 1) {
                num = num * 2 + 1;
            } else num = num * 2;

            if (num % 5 == 0) res.add(true);
            else res.add(false);

            num %= 5; //to avoid integer overflow
        }

        return res;

    }

    //Hint If X is the first i digits of the array as a binary number, then 2X + A[i] is the first i+1 digits.
    public List<Boolean> prefixesDivBy52(int[] nums) {
        List<Boolean> result = new ArrayList<>();
        int curr = 0;
        for (int num : nums) {
            curr = (2 * curr + num) % 5;
            result.add(curr == 0);
        }
        return result;
    }
}


