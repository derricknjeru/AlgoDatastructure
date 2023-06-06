package CodingInterviewPatterns.Arrays;

public class MaximumAscendingSubarraySum {
    public static void main(String[] args) {
        //https://leetcode.com/problems/maximum-ascending-subarray-sum/description/
        /**
         * Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.
         *
         * A subarray is defined as a contiguous sequence of numbers in an array.
         *
         * A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi  < numsi+1. Note that a subarray of size 1 is ascending.
         *
         *
         *
         * Example 1:
         *
         * Input: nums = [10,20,30,5,10,50]
         * Output: 65
         * Explanation: [5,10,50] is the ascending subarray with the maximum sum of 65.
         * Example 2:
         *
         * Input: nums = [10,20,30,40,50]
         * Output: 150
         * Explanation: [10,20,30,40,50] is the ascending subarray with the maximum sum of 150.
         * Example 3:
         *
         * Input: nums = [12,17,15,13,10,11,12]
         * Output: 33
         * Explanation: [10,11,12] is the ascending subarray with the maximum sum of 33.
         *
         *
         * Constraints:
         *
         * 1 <= nums.length <= 100
         * 1 <= nums[i] <= 100
         */

        int[] nums1 = {10, 20, 30, 5, 10, 50};
        int result1 = maxAscendingSum(nums1);
        System.out.println(result1);  // Output: 65

        int[] nums2 = {10, 20, 30, 40, 50};
        int result2 = maxAscendingSum(nums2);
        System.out.println(result2);  // Output: 150

        int[] nums3 = {12, 17, 15, 13, 10, 11, 12};
        int result3 = maxAscendingSum(nums3);
        System.out.println(result3);  // Output: 33

    }


    //Time complexity = O(n)
    //space complexity =
    public static int maxAscendingSum(int[] nums) {
        int sum = nums[0], maxSum = sum;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

}
