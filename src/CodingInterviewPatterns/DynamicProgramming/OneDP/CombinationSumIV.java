package CodingInterviewPatterns.DynamicProgramming.OneDP;

public class CombinationSumIV {
    //https://www.youtube.com/watch?v=1mtvm2ubHCY&list=RDCMUCBr_Fu6q9iHYQCh13jmpbrg&index=3&ab_channel=Errichto
    //https://www.youtube.com/watch?v=dw2nMCxG0ik&ab_channel=NeetCode

    public class Solution {
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];
            dp[0] = 1;

            for (int i = 1; i <= target; i++) {
                for (int num : nums) {
                    if (num <= i) {
                        dp[i] += dp[i - num];
                    }
                }
            }

            return dp[target];
        }
    }
}
