package sliding_window;

import java.util.HashMap;

public class SubArraySumEqualsK {
    public static void main(String[] args) {
        //https://leetcode.com/problems/subarray-sum-equals-k/
       // https://www.youtube.com/watch?v=HbbYPQc-Oo4&ab_channel=TECHDOSE


        int K = 6;
        int[] arr = { 12, 5, 3, 10, 4,
                8, 10, 12, -6, -1 };

        subarraySum(arr,K);

        System.out.println(subarraySum(arr,K));
    }

    // Time Complexity: O(N), where N is the length of the given array.
    //Auxiliary Space: O(N), for storing the count in the HashMap.
    public static int subarraySum(int[] nums, int k) {
        int count = 0, currSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); //pref sum and count

        for (int num : nums) {

            currSum += num;

            //because of index 1
            if (currSum == k) {
                count += 1;
            }

            if (map.containsKey(currSum - k)) {
                count += map.get(currSum - k);
            }

            map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        }
        return count;
    }

}
