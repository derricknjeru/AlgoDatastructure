package CodingInterviewPatterns.HashTable;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    //https://leetcode.com/problems/contains-duplicate/
    public boolean containsDuplicate(int[] nums) {

        Set<Integer> set = new HashSet<>(nums.length);
        for (int a : nums) {
            if (set.contains(a)) {
                return true;
            }

            set.add(a);
        }

        return false;

    }
}
