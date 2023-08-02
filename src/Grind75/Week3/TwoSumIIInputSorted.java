package Grind75.Week3;

public class TwoSumIIInputSorted {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] numbers1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(numbers1, target1);
        System.out.println("Indices: [" + result1[0] + ", " + result1[1] + "]");

        int[] numbers2 = {2, 3, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(numbers2, target2);
        System.out.println("Indices: [" + result2[0] + ", " + result2[1] + "]");

        int[] numbers3 = {-1, 0};
        int target3 = -1;
        int[] result3 = solution.twoSum(numbers3, target3);
        System.out.println("Indices: [" + result3[0] + ", " + result3[1] + "]");
    }
    /**
     * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
     *
     * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
     *
     * The tests are generated such that there is exactly one solution. You may not use the same element twice.
     *
     * Your solution must use only constant extra space.
     *
     *
     *
     * Example 1:
     *
     * Input: numbers = [2,7,11,15], target = 9
     * Output: [1,2]
     * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
     * Example 2:
     *
     * Input: numbers = [2,3,4], target = 6
     * Output: [1,3]
     * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
     * Example 3:
     *
     * Input: numbers = [-1,0], target = -1
     * Output: [1,2]
     * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
     *
     *
     * Constraints:
     *
     * 2 <= numbers.length <= 3 * 104
     * -1000 <= numbers[i] <= 1000
     * numbers is sorted in non-decreasing order.
     * -1000 <= target <= 1000
     * The tests are generated such that there is exactly one solution.
     */

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @return
     */

    static class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int i = 0;
            int j = numbers.length - 1;

            while (i <= j) {
                int possibleTarget = numbers[i] + numbers[j];

                if (possibleTarget == target) return new int[]{i + 1, j + 1};

                if (possibleTarget > target) j--;
                else i++;

            }
            return new int[0];
        }
    }

    //https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
    //https://www.youtube.com/watch?v=cQ1Oz4ckceM
    public class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            int l = 0, r = nums.length - 1;

            while (nums[l] + nums[r] != target) {
                if (nums[l] + nums[r] < target) l++;
                else r--;
            }

            return new int[]{l + 1, r + 1};
        }
    }


}
