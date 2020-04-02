package leetcode_30_days_challenge.week1;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * <p>
 * Note:
 * <p>
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * Example 1:
 * <p>
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [4,1,2,1,2]
 * Output: 4
 */

/**
 * 1. HashMap is non synchronized. It is not-thread safe and can’t be shared between many threads without proper synchronization code whereas Hashtable is synchronized. It is thread-safe and can be shared with many threads.
 * 2. HashMap allows one null key and multiple null values whereas Hashtable doesn’t allow any null key or value.
 * 3. HashMap is generally preferred over HashTable if thread synchronization is not needed
 * <p>
 * Why HashTable doesn’t allow null and HashMap does?
 * To successfully store and retrieve objects from a HashTable, the objects used as keys must implement the hashCode method and the equals method. Since null is not an object, it can’t implement these methods. HashMap is an advanced version and improvement on the Hashtable. HashMap was created later.
 */
public class SingleNumber {
    public static void main(String[] args) {
        SingleNumber sNumber = new SingleNumber();

        int[] nums = {2, 2, 1};
        int[] nums2 = {4, 1, 2, 1, 2};


        System.out.println(sNumber.singleNumber2(nums));

    }

    public int singleNumber(int[] nums) {

        Hashtable<Integer, Integer> occurrences = new Hashtable<>();

        for (int i : nums) {
            occurrences.put(i, occurrences.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> m : occurrences.entrySet()) {
            System.out.printf("%d occur %d time(s)\n", m.getKey(), m.getValue());
            if (m.getValue() == 1) {
                return m.getValue();
            }
        }

        return 0;
    }

    /**
     * Simpler because each integer appears twice
     *
     */
    public int singleNumber2(int[] nums) {

        Hashtable<Integer, Integer> occurrences = new Hashtable<>();
        int index = 0;
        for (int i : nums) {
            if (occurrences.containsKey(i)) {
                occurrences.remove(i);
            } else {
                occurrences.put(i, index++);
            }
        }

        return occurrences.keySet().stream().findFirst().get();
    }
    /**
     * Complexity Analysis of hashmap/hashtable
     *
     * Time complexity : O(n.1) = O(n). Time complexity of for loop is O(n). Time complexity of hash table(dictionary in python) operation pop is O(1).
     *
     * Space complexity : O(n). The space required by hashtable is equal to the number of elements in nums.
     */

}
