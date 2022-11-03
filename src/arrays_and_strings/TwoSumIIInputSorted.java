package arrays_and_strings;

public class TwoSumIIInputSorted {
    public static void main(String[] args) {

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
     * @param nums
     * @param target
     * @return
     */

    //https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
    public int[] twoSum(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (nums[l] + nums[r] != target) {
            if (nums[l] + nums[r] < target) l++;
            else r--;
        }

        return new int[]{l + 1, r + 1};
    }

    public int[] twoSum2(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        //Using two pointer

        while (i < j) {
            int possibleTarget = numbers[i] + numbers[j];

            if (possibleTarget == target) {
                return new int[]{i + 1, j + 1};
            }

            if (target >= possibleTarget) {
                i++;
            } else {
                j--;
            }
        }

        return null;
    }
 /*
    Binary Search Approach
            Logic
    We iterate through the entire array and initialise the high and low value
    We initialise the mid value if low index is lesser than the higher one or vice-versa
    Then we check whether array[i]+arr[mid] == target or not

            Complexity
    TC - O(nlogn)
    SC - O(1)
*/

        public int[] twoSum3(int[] numbers, int target) {
            for (int i = 0; i < numbers.length; ++i) {
                int low = i + 1;
                int high = numbers.length - 1;
                while (low <= high) {
                    int mid = (high - low) / 2 + low;
                    if (numbers[mid] +numbers[i] == target)
                        return new int[]{i + 1, mid + 1};
                    else if (numbers[mid] + numbers[i] > target)
                        high = mid - 1;
                    else
                        low = mid + 1;
                }
            }
            return new int[]{-1, -1};
        }

}