package leetcode_30_days_challenge.week4;

import java.util.HashMap;

public class SubArraySumEqualToK {
    /**
     * Subarray Sum Equals K
     * Solution
     * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
     * <p>
     * Example 1:
     * Input:nums = [1,1,1], k = 2
     * Output: 2
     * Note.md.md.md.md:
     * The length of the array is in range [1, 20,000].
     * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
     * Hide Hint #1
     * Will Brute force work here? Try to optimize it.
     * Hide Hint #2
     * Can we optimize it by using some extra space?
     * Hide Hint #3
     * What about storing sum frequencies in a hash table? Will it be useful?
     * Hide Hint #4
     * sum(i,j)=sum(0,j)-sum(0,i), where sum(i,j) represents the sum of all the elements from index i to j-1. Can we use this property to optimize it.
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;

        SubArraySumEqualToK k1 = new SubArraySumEqualToK();
        System.out.println(k1.subarraySum(nums, k));
    }

    //soln
    //https://leetcode.com/problems/subarray-sum-equals-k/solution/
    //https://www.youtube.com/watch?v=AmlVSNBHzJg
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
