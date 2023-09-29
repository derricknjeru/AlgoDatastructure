package CodingInterviewPatterns.HashTable;

import java.util.*;

public class SingleNumber {

    //https://leetcode.com/problems/single-number/
    //https://www.geeksforgeeks.org/find-element-appears-array-every-element-appears-twice/

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
        /**
         * This is not an efficient approach but just another way to get the desired results. If we add each number once and multiply the sum by 2, we will get twice the sum of each element of the array. Then we will subtract the sum of the whole array from the twice_sum and get the required number (which appears once in the array).
         * Array [] : [a, a, b, b, c, c, d]
         * Mathematical Equation = 2*(a+b+c+d) – (a + a + b + b + c + c + d)
         *
         * In more simple words: 2*(sum_of_array_without_duplicates) – (sum_of_array)
         *
         * let arr[] = {7, 3, 5, 4, 5, 3, 4}
         * Required no = 2*(sum_of_array_without_duplicates) - (sum_of_array)
         *             = 2*(7 + 3 + 5 + 4) - (7 + 3 + 5 + 4 + 5 + 3 + 4)
         *             = 2*     19         -      31
         *             = 38 - 31
         *             = 7 (required answer)
         * As we know that set does not contain any duplicate element we will be using the set here.
         *
         * Below is the implementation of above approach:
         */
        Set<Integer> set = new HashSet<>();
        //2 2 1
        int sum = 0;
        for (int a : nums) {
            if (!set.add(a)) {
                sum = sum - a;
            } else {
                sum += a;
            }
        }

        return sum;
    }

    public int singleNumber4(int[] nums) {
        //For this, It will work only if elements appears at tleast twice.
        Set<Integer> set = new HashSet<>();
        for (int a : nums) {
            if (set.contains(a)) {
                set.remove(a);
            } else {
                set.add(a);
            }
        }
        Iterator<Integer> i = set.iterator();
        return i.next();
    }
}
