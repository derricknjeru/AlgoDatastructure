package HashTable;

import java.util.*;

public class IntersectionOfTwoArrays {

    /**
     * Complexity Analysis
     * <p>
     * Time complexity : O(n+m), where n and m are arrays' lengths. O(n) time is used to convert nums1 into set, O(m) time is used to convert nums2, and contains/in operations are O(1) in the average case.
     * <p>
     * Space complexity : O(m+n) in the worst case when all elements in the arrays are different.
     *
     * @param nums1
     * @param nums2
     * @return
     */

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();

        for (int a : nums1) {
            set1.add(a);
        }

        Set<Integer> setInTer = new HashSet<>();

        for (int a : nums2) {
            if (set1.contains(a)) {
                setInTer.add(a);
            }
        }

        int[] intersection = new int[setInTer.size()];

        int index = 0;

        for (int a : setInTer) {
            intersection[index++] = a;
        }


        return intersection;
    }

    /**
     * * Time complexity : O(n+m) in the average case and O(n×m) in the worst case when load factor is high enough.
     * *
     * Space complexity : O(n+m) in the worst case when all elements in the arrays are different.
     * <p>
     * There are built-in intersection facilities, which provide O(n+m) time complexity in the average case and O(n×m) time complexity in the worst case.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    //Built in set union
    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int[] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }
}
