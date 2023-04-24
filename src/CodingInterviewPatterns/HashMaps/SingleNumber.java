package CodingInterviewPatterns.HashMaps;

import java.util.*;

public class SingleNumber {

    //https://leetcode.com/problems/single-number/

    /**
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     * <p>
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [2,2,1]
     * Output: 1
     * Example 2:
     * <p>
     * Input: nums = [4,1,2,1,2]
     * Output: 4
     * Example 3:
     * <p>
     * Input: nums = [1]
     * Output: 1
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 3 * 104
     * -3 * 104 <= nums[i] <= 3 * 104
     * Each element in the array appears twice except for one element which appears only once.
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {

        HashMap<Integer, Integer> occurrences = new HashMap<>();


        for (int i : nums) {
            occurrences.put(i, occurrences.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> m : occurrences.entrySet()) {
            System.out.printf("%d occur %d time(s)\n", m.getKey(), m.getValue());
            if (m.getValue() == 1) {
                return m.getKey();
            }


        }

        return 0;


    }

    //https://www.youtube.com/watch?v=qMPX1AOa83k
    //https://www.youtube.com/watch?v=krgK0UlfNYY best explanation

    public int singleNumber2(int[] nums) {
        int res = 0;
        for (int a : nums) {
            res = a ^ res;
        }
        return res;
    }

    public int singleNumber3(int[] nums) {
        //For this, It will work only if elements appears at tleast twice.
        Set<Integer> set = new HashSet<>();
        for(int a :nums){
            if(set.contains(a)){
                set.remove(a);
            }else{
                set.add(a);
            }
        }
        Iterator<Integer> i = set.iterator();
        return i.next();
    }
}
