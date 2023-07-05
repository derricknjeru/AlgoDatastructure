package CodingInterviewPatterns.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    //https://www.youtube.com/watch?v=GBKI9VSKdGg&ab_channel=NeetCode

    /**
     * The time complexity of the corrected combinationSum function is exponential, specifically O(n^(target / min(candidates))),
     * where n is the length of the candidates array and target is the target value.
     * This is because the function generates combinations recursively,
     * and the number of combinations grows exponentially with the target value and the minimum candidate value.
     * In practice, the actual number of combinations generated may be lower, but the worst-case time complexity provides an upper bound.
     */
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<Integer> curCandidates = new ArrayList<>();
            List<List<Integer>> ans = new ArrayList<>();

            generateCombinations(candidates, 0, 0, target, curCandidates, ans);

            return ans;
        }

        private void generateCombinations(int[] candidates, int index, int total, int target,
                                          List<Integer> curCandidates, List<List<Integer>> ans) {
            if (total == target) {
                ans.add(new ArrayList<>(curCandidates));
            } else if (total > target || index >= candidates.length) {
                return;
            } else {
                curCandidates.add(candidates[index]);
                generateCombinations(candidates, index, total + candidates[index], target, curCandidates, ans);
                curCandidates.remove(curCandidates.size() - 1);

                generateCombinations(candidates, index + 1, total, target, curCandidates, ans);
            }
        }
    }
}
