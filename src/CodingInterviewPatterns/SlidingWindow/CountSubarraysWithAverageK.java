package CodingInterviewPatterns.SlidingWindow;

import java.util.HashMap;

public class CountSubarraysWithAverageK {
    //https://www.geeksforgeeks.org/count-of-subarrays-with-average-k/
    //https://leetcode.com/discuss/interview-question/1230801/count-number-of-subarrays-with-arithmetic-mean-k

    /**
     * Time Complexity: O(N)
     * Auxiliary Space: O(N)
     *
     * @param args
     */
    public static void main(String[] args) {
        // Given Input
        int K = 6;
        int[] arr = {12, 5, 3, 10, 4,
                8, 10, 12, -6, -1};
        int N = arr.length;

        int[] A = {1, 3, 4, 5, 6};
        int K2 = 3;
        // Answer: 2 # [3] and [1,3,4]

        // Function Call
        System.out.println(countKAverageSubarrays(arr, N, K));
        System.out.println("Test case2");
        System.out.println(countKAverageSubarrays(A, A.length, K2));
    }

    /**
     * If you write mean of some subarray let say (a + b + c + d) / 4 = k, where k is the mean, this can be written as (a + b + c + d) = 4k , it can be further written as
     * (a + b + c + d - 4k) = 0 => (a - k) + (b - k) + (c - k) + (d - k) = 0
     * The problem now transforms to find number of subarrays with sum 0, instead of take arr[i] you have to take (arr[i] - k).
     *
     * @param arr
     * @param n
     * @param k
     * @return
     */




    // Function to count subarray having average
// exactly equal to K
    static int countKAverageSubarrays(int[] arr, int n,
                                      int k) {
        int result = 1, curSum = 0;

        // Store the frequency of prefix
        // sum of the array arr[]
        HashMap<Integer,
                Integer> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {

            // Subtract k from each element,
            // then add it to curSum
            curSum += (arr[i] - k);

            // If curSum is 0 that means
            // sum[0...i] is 0 so increment
            // res
            if (curSum == 0)
                result++;

            // Check if curSum has occurred
            // before and if it has occurred
            // before, add its frequency to
            // res
            if (mp.containsKey(curSum)) result += mp.get(curSum);

            // Increment the frequency
            // of curSum
            mp.put(curSum, mp.getOrDefault(curSum, 0) + 1);
        }

        // Return result
        return result;
    }


}
