package Grind75.week2;

public class MaxSubArray {
    //https://www.youtube.com/watch?v=umt7t1_X8Rc
    //https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/

    /**
     * Time Complexity: O(N)
     * Auxiliary Space: O(1)
     */
    //Kadane’s Algorithm
    class Solution {
        public int maxSubArray(int[] nums) {
            int currentSum = 0;
            int maxSum = Integer.MIN_VALUE;

            for (int num : nums) {
                currentSum += num;

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }

                if (currentSum < 0) {
                    currentSum = 0;
                }
            }

            return maxSum;
        }

        //get index
        public int[] maxSubArray2(int[] nums) {
            int currentSum = 0;
            int maxSum = Integer.MIN_VALUE;
            int start = 0;  // Starting index of the subarray
            int end = 0;    // Ending index of the subarray
            int tempStart = 0;  // Temporary variable to track the starting index when updating maxSum

            for (int i = 0; i < nums.length; i++) {
                currentSum += nums[i];

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    start = tempStart;  // Update the starting index
                    end = i;            // Update the ending index
                }

                if (currentSum < 0) {
                    currentSum = 0;
                    tempStart = i + 1;  // Update the temporary starting index
                }
            }

            return new int[]{start, end};
        }

    }

    class Solution2 {
        public int maxSubArray(int[] nums) {
            int maxSum = nums[0]; // Initialize the maximum sum as the first element
            int currentSum = nums[0]; // Initialize the current sum as the first element

            for (int i = 1; i < nums.length; i++) {
                // Check if adding the current element to the current sum improves the sum
                currentSum = Math.max(nums[i], currentSum + nums[i]);

                // Update the maximum sum if the current sum is larger
                maxSum = Math.max(maxSum, currentSum);
            }

            return maxSum;
        }

    }

}
