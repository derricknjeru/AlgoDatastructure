package leetcode_30_days_challenge.week2;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 0, 1, 1, 0};
        System.out.println();
    }
    //https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/\
    //https://leetcode.com/articles/contiguous-array/
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }

}
