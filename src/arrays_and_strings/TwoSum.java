package arrays_and_strings;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {

    }

    /**
     * Complexity Analysis
     * <p>
     * Time complexity: O(n^2)O(n
     * 2
     * ). For each element, we try to find its complement by looping through the rest of the array which takes O(n)O(n) time. Therefore, the time complexity is O(n^2)O(n
     * 2
     * ).
     * <p>
     * Space complexity: O(1)O(1). The space required does not depend on the size of the input array, so only constant space is used.
     *
     * @param nums
     * @param target
     * @return
     */

    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        // In case there is no solution, we'll just return null
        return null;
    }


    /**
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     * <p>
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * <p>
     * You can return the answer in any order.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
     * Example 2:
     * <p>
     * Input: nums = [3,2,4], target = 6
     * Output: [1,2]
     * Example 3:
     * <p>
     * Input: nums = [3,3], target = 6
     * Output: [0,1]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 2 <= nums.length <= 103
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * Only one valid answer exists.
     *
     * @param nums
     * @param target
     * @return
     */
    /**
     * @param nums
     * @param target
     * @return
     */

    /**
     * ime complexity: O(n) We traverse the list containing nn elements only once. Each lookup in the table costs only O(1) time.
     * <p>
     * Space complexity: O(n). The extra space required depends on the number of items stored in the hash table, which stores at most nn elements.
     *
     * @param nums
     * @param target
     * @return
     */

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }


    //two pass

    /**
     * Time complexity: O(n). We traverse the list containing nn elements exactly twice. Since the hash table reduces the lookup time to O(1)O(1), the overall time complexity is O(n)O(n).
     * <p>
     * Space complexity: O(n). The extra space required depends on the number of items stored in the hash table, which stores exactly nn elements.
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        // In case there is no solution, we'll just return null
        return null;
    }


    public int[] twoSum3(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int[] indexArr = new int[2];

        int index = 0;
        for (int a : nums) {

            int n = target - a;

            if (map.containsValue(n)) {
                indexArr[0] = getKey(map, n);
                indexArr[1] = index;
                return indexArr;
            }

            map.put(index++, a);

        }

        return indexArr;
    }

    private int getKey(HashMap<Integer, Integer> map, int n) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(n)) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
