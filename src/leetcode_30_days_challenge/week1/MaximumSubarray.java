package leetcode_30_days_challenge.week1;

public class MaximumSubarray {
    /**
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     * <p>
     * Example:
     * <p>
     * Input: [-2,1,-3,4,-1,2,1,-5,4],
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     * Follow up:
     * <p>
     * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
     */
    public static void main(String[] args) {
        int []a = {-2, -3, 4, -1, -2, 1, 5, -3};
        int []nums = {-2, -3, 4, -1, -2, 1, 5, -3};

        int max_sum = maxSubArray(nums);
        System.out.println("Maximum contiguous sum is "
                + max_sum);
    }

    //https://www.youtube.com/watch?v=tinz1fiYv0c
    //https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
    //Given an array, the algorithm to find the maximum subarray sum is called Kadane’s Algorithm.
    public static int maxSubArray(int[] nums) {
        int max_so_far = nums[0];
        int curr_max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            curr_max = Math.max(nums[i], curr_max + nums[i]); //calculating the current maximum sum
            max_so_far = Math.max(max_so_far, curr_max); //storing largest so far
        }
        return max_so_far;

    }


}
