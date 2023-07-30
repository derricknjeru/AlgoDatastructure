package Grind75.week2;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public static void main(String[] args) {
        Solution sb = new Solution();
        // Example 1
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Example 1: " + sb.containsDuplicate(nums1)); // Output: true

        // Example 2
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Example 2: " + sb.containsDuplicate(nums2)); // Output: false

        // Example 3
        int[] nums3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println("Example 3: " + sb.containsDuplicate(nums3)); // Output: true

    }

    static class Solution {
        public boolean containsDuplicate(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (!set.add(num)) return true;
            }
            return false;
        }
    }
}
