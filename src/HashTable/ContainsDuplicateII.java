package HashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateII {
    //https://leetcode.com/problems/contains-duplicate-ii/
    //https://www.youtube.com/watch?v=SFMCxqSeM94&t=934s
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        Set<Integer> set = new HashSet<>();
        //time complexity = O(n)
        //space complexity O(min(n,k));
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) return true;

            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }

        return false;

    }
}
