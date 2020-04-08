package leetcode_30_days_challenge.week1;

import java.util.HashSet;

public class CountingElements {

    /**
     * Given an integer array arr, count element x such that x + 1 is also in arr.
     * <p>
     * If there're duplicates in arr, count them seperately.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: arr = [1,2,3]
     * Output: 2
     * Explanation: 1 and 2 are counted cause 2 and 3 are in arr.
     * Example 2:
     * <p>
     * Input: arr = [1,1,3,3,5,5,7,7]
     * Output: 0
     * Explanation: No numbers are counted, cause there's no 2, 4, 6, or 8 in arr.
     * Example 3:
     * <p>
     * Input: arr = [1,3,2,3,5,0]
     * Output: 3
     * Explanation: 0, 1 and 2 are counted cause 1, 2 and 3 are in arr.
     * Example 4:
     * <p>
     * Input: arr = [1,1,2,2]
     * Output: 2
     * Explanation: Two 1s are counted cause 2 is in arr.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= arr.length <= 1000
     * 0 <= arr[i] <= 1000
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2};
        CountingElements e = new CountingElements();
        System.out.println(e.countElements(nums));

    }

    public int countElements(int[] arr) {
        int count = 0;
        //Use hashset to store all elements.
        HashSet<Integer> set = new HashSet<>();
        for (int a : arr) {
            set.add(a);
        }
        int sum;
        //Loop again to count all valid elements.
        for (int i = 0; i < arr.length && arr.length <= 1000; i++) {
            if (arr[i] >= 0 && arr[i] <= 1000) {
                sum = arr[i] + 1;
                if (set.contains(sum)) {
                    count += 1;
                }
            }
        }

        return count;
    }
}
