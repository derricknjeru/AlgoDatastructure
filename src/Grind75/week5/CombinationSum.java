package Grind75.week5;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {

        Solution solution = new Solution();

        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = solution.combinationSum(candidates, target);

        System.out.println("Combinations that sum up to " + target + ":");
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }

    //https://www.youtube.com/watch?v=GBKI9VSKdGg&ab_channel=NeetCode

    /**
     * The time complexity of the corrected combinationSum function is exponential, specifically O(n^(target / min(candidates))),
     * where n is the length of the candidates array and target is the target value.
     * i.e. 2^target
     * This is because the function generates combinations recursively,
     * and the number of combinations grows exponentially with the target value and the minimum candidate value.
     * In practice, the actual number of combinations generated may be lower, but the worst-case time complexity provides an upper bound.
     */


    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<Integer> cur = new ArrayList<>();
            List<List<Integer>> ans = new ArrayList<>();
            combinationSum(candidates, target, 0, 0, cur, ans);
            return ans;
        }

        private void combinationSum(int[] candidates, int target,
                                    int currentTotal, int currentIndex,
                                    List<Integer> currentCombination, List<List<Integer>> result) {
            // Base case
            if (currentTotal > target || currentIndex >= candidates.length) {
                return;
            }

            if (currentTotal == target) {
                result.add(new ArrayList<>(currentCombination));
                return;
            } else {
                // Include the current candidate and explore possibilities
                currentCombination.add(candidates[currentIndex]);
                combinationSum(candidates, target, currentTotal + candidates[currentIndex],
                        currentIndex, currentCombination, result);

                // Exclude the current candidate and move to the next index
                currentCombination.remove(currentCombination.size() - 1); // Remove the last element
                combinationSum(candidates, target, currentTotal, currentIndex + 1,
                        currentCombination, result);
            }
        }
    }
}
