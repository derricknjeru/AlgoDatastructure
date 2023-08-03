package Grind75.Week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    //https://leetcode.com/problems/3sum/description/
    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> ans = new ArrayList<>();
            int n = nums.length;

            for (int index = 0; index < n - 2; index++) {
                if (index > 0 && nums[index] == nums[index - 1]) {
                    continue; // Avoid duplicates
                }

                int i = index + 1;
                int j = n - 1;

                while (i < j) {
                    int possibleSum = nums[index] + nums[i] + nums[j];
                    if (possibleSum == 0) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[index]);
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        ans.add(triplet);

                        i++;
                        j--;

                        // Skip duplicates
                        while (i < j && nums[i] == nums[i - 1]) {
                            i++;
                        }

                    } else if (possibleSum > 0) {
                        j--;
                    } else {
                        i++;
                    }
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = solution.threeSum(nums);
        System.out.println(result);
    }

}
