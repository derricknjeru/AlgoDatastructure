package HashTable;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {

    }

    /**
     * Complexity Analysis
     * <p>
     * Time complexity: O(n) We traverse the list containing nnn elements only once. Each lookup in the table costs only O(1) time.
     * <p>
     * Space complexity: O(n). The extra space required depends on the number of items stored in the hash table, which stores at most nnn elements.
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

    /**
     * Complexity Analysis
     * <p>
     * Time complexity: O(n). We traverse the list containing nnn elements exactly twice. Since the hash table reduces the lookup time to O(1), the overall time complexity is O(n).
     * <p>
     * Space complexity: O(n). The extra space required depends on the number of items stored in the hash table, which stores exactly nnn elements.
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


}
