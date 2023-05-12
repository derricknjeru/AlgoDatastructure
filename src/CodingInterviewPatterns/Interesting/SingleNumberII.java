package CodingInterviewPatterns.Interesting;

import java.util.Arrays;
import java.util.HashMap;

public class SingleNumberII {
    public static void main(String[] args) {
        //https://www.youtube.com/watch?v=cOFAmaMBVps
        //https://leetcode.com/problems/single-number-ii/

    }

    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;
        for (int element : nums) {
            // if element is repeated only once ones will contain that element
            ones = (ones ^ element) & ~(twos);
            twos = (twos ^ element) & ~(ones);
        }
        return ones;
    }

    public int singleNumber3(int[] nums) {
        //O(nlog n + n) sorting plus traversing.
        //log n <=32 n<=int_max
        // Base Case
        if (nums.length == 1)
            return nums[0];

        Arrays.sort(nums);

        // Check every 1st and 3rd numbers. If not equal, it is the first number
        for (int i = 0; i < nums.length / 3; i++) {
            if (nums[3 * i] != nums[(3 * i) + 1])
                return nums[3 * i];
        }

        // Return the very last number
        return nums[nums.length - 1];
    }

    public int singleNumber4(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int val = 0;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) == 1) {
                val = key;
                break;
            }
        }
        return val;
    }

}
