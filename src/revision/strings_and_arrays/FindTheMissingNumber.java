package revision.strings_and_arrays;

import java.util.HashSet;

public class FindTheMissingNumber {
    public static void main(String[] args) {
        int[] arr = {9, 6, 2, 3, 5,8, 7, 0, 1};
        System.out.printf("The missing number is %d", missingNumber(arr));
    }

    /**
     * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
     * <p>
     * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [3,0,1]
     * Output: 2
     * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
     * Example 2:
     * <p>
     * Input: nums = [0,1]
     * Output: 2
     * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
     * Example 3:
     * <p>
     * Input: nums = [9,6,4,2,3,5,7,0,1]
     * Output: 8
     * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
     * Example 4:
     * <p>
     * Input: nums = [0]
     * Output: 1
     * Explanation: n = 1 since there is 1 number, so all numbers are in the range [0,1]. 1 is the missing number in the range since it does not appear in nums.
     */

    public static int missingNumber(int[] nums) {
//[9,6,4,2,3,5,7,0,1]
        int n = nums.length;

        //Gause formula==n(n+1)/2

        int totalSum = n * (n + 1) / 2;

        int currSum = 0;

        for (int a : nums) {
            currSum += a;
        }

        return totalSum - currSum;
    }

    public int missingNumber2(int[] nums) {
        //add this to set and compare if all the possible numbers are there

        HashSet<Integer> set = new HashSet<>();

        for (int a : nums) {
            set.add(a);
        }

        int n = nums.length;

        for (int i = 0; i <= n; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return -1;

    }
}
