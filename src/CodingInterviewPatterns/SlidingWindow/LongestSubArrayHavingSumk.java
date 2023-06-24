package CodingInterviewPatterns.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayHavingSumk {
    //https://www.geeksforgeeks.org/longest-sub-array-sum-k/''

    //function to find the length of longest

    // Time Complexity: O(N), where N is the length of the given array.
    //Auxiliary Space: O(N), for storing the maxLength in the HashMap.

    public static int lenOfLongSubarr (int A[], int N, int K) {
        int maxLen = 0;
        int currSum = 0;
        //Complete the function
        Map<Integer, Integer> prefixSum = new HashMap<>();

        for(int i=0; i<N; i++){
            currSum += A[i];

            if (currSum == K) // when subarray starts from index '0'
                maxLen = i + 1;

            // check if 'sum-k' is present in 'map'
            // or not
            if (prefixSum.containsKey(currSum - K)) {
                // update maxLength
                maxLen = Math.max(maxLen, i - prefixSum.get(currSum - K));
            }
            // make an entry for 'sum' if it is
            // not present in 'map'
            if(!prefixSum.containsKey(currSum)){
                prefixSum.put(currSum, i);
            }

        }

        return maxLen;
    }

    // Driver code
    public static void main(String args[]) {
        int[] arr = {10, 5, 2, 7, 1, 9};
        int n = arr.length;
        int k = 15;

        int arr1[] = {3, 1, 2, 7, 4, 2, 1, 1, 5};
        int k1 = 8;
        System.out.println("Length = " +
                subarraySum(arr1, k1));
    }

    /**
     * This approach won’t work for negative numbers
     * The approach is to use the concept of the variable-size sliding window using 2 pointers
     * Initialize i, j, and sum = k. If the sum is less than k just increment j, if the sum is equal to k compute
     * the max and if the sum is greater than k subtract the ith element while the sum is less than k.
     * <p>
     * Time Complexity: O(N), where N is the length of the given array.
     * Auxiliary Space: O(1), as constant extra space is required.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        /**
         * Let's use an integer curr that tracks the sum of the current window.
         * Since the problem wants subarrays whose sum is less than or equal to k, we want to maintain curr <= k.
         * Let's look at an example where nums = [3, 1, 2, 7, 4, 2, 1, 1, 5] and k = 8.
         *
         * The window starts empty, but we can grow it to [3, 1, 2]
         * while maintaining the constraint. However, after adding the 7, the window's sum becomes too large.
         * We need to tighten the window until the sum is below 8 again, which doesn't happen until our window looks like [7]. When we try to add the next element, our window again becomes too large, and we need to remove the 7 which means we have [4]. We can now grow the window until it looks like [4, 2, 1, 1], but adding the next element makes the sum too large. We remove elements from the left until it fits the constraint again, which happens at [1, 1, 5]. The longest subarray we found was [4, 2, 1, 1] which means the answer is 4.
         */
        int left = 0;
        int curr = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            curr += nums[right];

            while (curr > k) {
                curr -= nums[left];
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
