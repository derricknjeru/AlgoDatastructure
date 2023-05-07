package arrays_and_strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Note.md.md:
 * <p>
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * <p>
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

public class IntersectionOfTwoArraysII {


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));

    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);  //O(nlogn)

        int i = 0, j = 0;

        List<Integer> list = new ArrayList<>();

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] result = new int[list.size()];
        int index = 0;
        for (int a : list) {
            result[index++] = a;
        }

        return result;

    }

    public static int[] intersect2(int[] nums1, int[] nums2) {
        //What if nums1's size is small compared to nums2's size?
        //Which algorithm is better?
        if (nums1.length < nums2.length) {
            return intersect(nums2, nums1);
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int a : nums1) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        List<Integer> list = new ArrayList<>();

        for (int a : nums2) {

            int count = map.getOrDefault(a, 0);

            if (count > 0) {
                list.add(a);
                map.put(a, count - 1);
            }

        }

        int[] result = new int[list.size()];
        int index = 0;

        for (int a : list) {
            result[index++] = a;
        }

        return result;
    }
}
