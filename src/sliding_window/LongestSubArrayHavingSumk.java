package sliding_window;

import java.util.HashMap;

public class LongestSubArrayHavingSumk {
    //https://www.geeksforgeeks.org/longest-sub-array-sum-k/''
    //function to find the length of longest

    // Time Complexity: O(N), where N is the length of the given array.
    //Auxiliary Space: O(N), for storing the maxLength in the HashMap.
    static int lenOfLongSubarr(int[] arr, int n, int k) {
        // HashMap to store (sum, index) tuples
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, maxLen = 0;

        // traverse the given array
        for (int i = 0; i < n; i++) {

            // accumulate sum
            sum += arr[i];

            // when subarray starts from index '0'
            if (sum == k)
                maxLen = i + 1;

            // make an entry for 'sum' if it is
            // not present in 'map'
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }

            // check if 'sum-k' is present in 'map'
            // or not
            if (map.containsKey(sum - k)) {

                // update maxLength
                if (maxLen < (i - map.get(sum - k)))
                    maxLen = i - map.get(sum - k);
            }
        }

        return maxLen;
    }

    // Driver code
    public static void main(String args[]) {
        int[] arr = {10, 5, 2, 7, 1, 9};
        int n = arr.length;
        int k = 15;
        System.out.println("Length = " +
                lenOfLongSubarr(arr, n, k));
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
    public int subarraySum(int[] nums, int k) {
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
