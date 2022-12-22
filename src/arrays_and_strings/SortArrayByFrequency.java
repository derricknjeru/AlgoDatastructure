package arrays_and_strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class SortArrayByFrequency {
    public static void main(String[] args) {
        //https://leetcode.com/problems/sort-array-by-increasing-frequency/description/

        /**
         * Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.
         *
         * Return the sorted array.
         *
         *
         *
         * Example 1:
         *
         * Input: nums = [1,1,2,2,2,3]
         * Output: [3,1,1,2,2,2]
         * Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
         * Example 2:
         *
         * Input: nums = [2,3,1,3,2]
         * Output: [1,3,3,2,2]
         * Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
         * Example 3:
         *
         * Input: nums = [-1,1,-6,4,5,-6,1,4,1]
         * Output: [5,-1,4,4,-6,-6,1,1,1]
         *
         *
         * Constraints:
         *
         * 1 <= nums.length <= 100
         * -100 <= nums[i] <= 100
         */
    }

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> occ = new HashMap<>();
        for (int num : nums) {
            occ.put(num, occ.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Objects.equals(occ.get(a), occ.get(b)) ? (b - a) : occ.get(a) - occ.get(b));
        pq.addAll(occ.keySet());

        int i = 0;
        while (!pq.isEmpty()) {
            int num = pq.poll();
            int freq = occ.get(num);
            for (int j = 0; j < freq; j++) nums[i++] = num;
        }
        return nums;

    }
}
