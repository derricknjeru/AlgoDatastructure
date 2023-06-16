package CodingInterviewPatterns.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    // The idea is to have two conditions:
// One in which we will take the element into consideration,
// Second in which we won't take the element into consideration.
    //https://leetcode.com/problems/subsets/editorial/

    /**
     * Time complexity: O(N×2N)\mathcal{O}(N \times 2^N)O(N×2
     * N
     *  ) to generate all subsets
     * and then copy them into output list.
     *
     * Space complexity: O(N×2N)\mathcal{O}(N \times 2^N)O(N×2
     * N
     *  ) to keep all the subsets
     * of length NNN,
     * since each of NNN elements could be present or absent.
     */
    class Solution {

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            helper(ans, 0, nums, new ArrayList<>());
            return ans;
        }

        public void helper(List<List<Integer>> ans, int start, int[] nums, List<Integer> list) {
            if (start >= nums.length) {
                ans.add(new ArrayList<>(list));
            } else {
                // add the element and start the  recursive call
                list.add(nums[start]);
                helper(ans, start + 1, nums, list);
                // remove the element and do the backtracking call.
                list.remove(list.size() - 1);
                helper(ans, start + 1, nums, list);
            }
        }
    }

}
