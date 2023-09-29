package CodingInterviewPatterns.Arrays;

import java.util.*;

public class FindAllDuplicatesInArray {

    static class Solution {
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> duplicates = new ArrayList<>();

            for (int num : nums) {
                // Get the absolute value of the number as an index
                int index = Math.abs(num) - 1;

                // If the value at the index is negative, it's a duplicate
                if (nums[index] < 0) {
                    duplicates.add(index + 1);
                } else {
                    // Mark the number's presence by negating its value
                    nums[index] = -nums[index];
                }
            }

            return duplicates;

        }
    }

    public static void main(String[] args) {
        Solution finder = new Solution();

        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> duplicates1 = finder.findDuplicates(nums1);
        System.out.println("Duplicates in nums1: " + duplicates1);

        int[] nums2 = {1, 1, 2};
        List<Integer> duplicates2 = finder.findDuplicates(nums2);
        System.out.println("Duplicates in nums2: " + duplicates2);

        int[] nums3 = {1};
        List<Integer> duplicates3 = finder.findDuplicates(nums3);
        System.out.println("Duplicates in nums3: " + duplicates3);
    }
}
