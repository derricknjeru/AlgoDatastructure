package companies.Amazon;

import java.util.HashMap;

public class CountKAverageSubarrays {
    //https://www.geeksforgeeks.org/count-of-subarrays-with-average-k/


    // Function to count subarray having average
// exactly equal to K
    static int countKAverageSubarrays(int[] arr, int n,
                                      int k) {
        int result = 0, curSum = 0;

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
            // before, add it's frequency to
            // res
            if (mp.containsKey(curSum))
                result += mp.get(curSum);

            // Increment the frequency
            // of curSum
            mp.put(curSum, mp.getOrDefault(curSum, 0) + 1);
        }

        // Return result
        return result;
    }

    // Driver Code
    public static void main(String[] args) {

        // Given Input
        int K = 6;
        int[] arr = {12, 5, 3, 10, 4,
                8, 10, 12, -6, -1};
        int N = arr.length;

        // Function Call
        System.out.print(countKAverageSubarrays(arr, N, K));
    }
}

// This code is contributed by sanjoy_62

