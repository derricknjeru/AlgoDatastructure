package arrays_and_strings;

import java.util.HashMap;

public class SubArraySumEqualsK {
    public static void main(String[] args) {

    }
    public int subarraySum(int[] nums, int k) {
        int count = 0, currSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); //pref sum and count

        for (int num : nums) {

            currSum += num;

            //because of index 1
            if (currSum == k) {
                count += 1;
            }

            if (map.containsKey(currSum - k)) {
                count += map.get(currSum - k);
            }

            map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        }
        return count;
    }
}
