package HashTable;

import java.util.HashMap;

public class FourSumII {
    /**
     * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
     *
     * 0 <= i, j, k, l < n
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     *
     *
     * Example 1:
     *
     * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
     * Output: 2
     * Explanation:
     * The two tuples are:
     * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
     * Example 2:
     *
     * Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
     * Output: 1
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int result=0;

        for(int a : nums1){
            for(int b : nums2){
                map.put(a+b, map.getOrDefault(a+b, 0)+1);
            }
        }

        for(int c : nums3){
            for(int d : nums4){
                result += map.getOrDefault(-(c+d), 0);
            }
        }

        return result;

    }
}
