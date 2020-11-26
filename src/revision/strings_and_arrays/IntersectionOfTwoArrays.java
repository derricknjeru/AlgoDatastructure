package revision.strings_and_arrays;

import java.util.*;

public class IntersectionOfTwoArrays {
    public static void main(String[] args) {

    }

    /**
     * Given two arrays, write a function to compute their intersection.
     * <p>
     * Example 1:
     * <p>
     * Input: nums1 = [1,2,2,1], nums2 = [2,2]
     * Output: [2]
     * Example 2:
     * <p>
     * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * Output: [9,4]
     * Note:
     * <p>
     * Each element in the result must be unique.
     * The result can be in any order.
     */

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int a : nums1) {
            set1.add(a);
        }

        HashSet<Integer> intersection = new HashSet<>();
        for (int a : nums2) {
            if (set1.contains(a)) {
                intersection.add(a);
            }
        }
        int[] result = new int[intersection.size()];
        int index = 0;
        for (int a : intersection) {
            result[index++] = a;
        }
        return result;
    }
}
