package Grind75.week1;

import java.util.HashMap;

public class TwoSum {
    //https://leetcode.com/problems/two-sum/
    //The time complexity of the isValid method is O(n), where n is the length of the input string s.
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int n = nums.length;

            for (int i = 0; i < n; i++) {
                int comp = target - nums[i];
                if (map.containsKey(comp)) return new int[]{map.get(comp), i};
                map.put(nums[i], i);
            }
            return new int[2];
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        Solution solution = new Solution();
        int[] result = solution.twoSum(nums, target);

        System.out.println("Indices of the two numbers that add up to the target:");
        System.out.println(result[0] + " " + result[1]);
    }
}
