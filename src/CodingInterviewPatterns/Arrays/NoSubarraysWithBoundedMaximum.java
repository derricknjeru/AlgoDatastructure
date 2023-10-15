package CodingInterviewPatterns.Arrays;

public class NoSubarraysWithBoundedMaximum {
    //https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/description/
    //https://interviewing.io/questions/number-of-subarrays-with-bounded-maximum
    //https://www.youtube.com/watch?v=cRB2f-PjTds&ab_channel=CodersCamp

    /**
     * 795. Number of Subarrays with Bounded Maximum
     * Solved
     * Medium
     * Topics
     * Companies
     * Given an integer array nums and two integers left and right, return the number of contiguous non-empty subarrays such that the value of the maximum array element in that subarray is in the range [left, right].
     * -
     * The test cases are generated so that the answer will fit in a 32-bit integer.
     * -
     * -
     * -
     * Example 1:
     * -
     * Input: nums = [2,1,4,3], left = 2, right = 3
     * Output: 3
     * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
     * Example 2:
     * -
     * Input: nums = [2,9,2,5,6], left = 2, right = 8
     * Output: 7
     * -
     * -
     * Constraints:
     * -
     * 1 <= nums.length <= 105
     * 0 <= nums[i] <= 109
     * 0 <= left <= right <= 109
     */

    public static void main(String[] args) {

    }

    class Solution {
        public int numSubarrayBoundedMax(int[] nums, int left, int right) {
            int start = -1, end = -1, result = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > right) {
                    start = end = i;
                    continue;
                }

                if (nums[i] >= left) {
                    end = i;
                }

                result += end - start;
            }

            return result;
        }
    }
}
