package arrays_and_strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class IntersectionOfTwoArrays {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

    }

    public int[] intersection2(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int index = 0;
        for (int a : nums1) {
            map.put(index++, a);
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums2.length; i++) {
            if (map.containsValue(nums2[i])) {
                list.add(nums2[i]);
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
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
     * Note.md.md.md.md:
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


    public int[] intersection4(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int[] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;

    }

}
