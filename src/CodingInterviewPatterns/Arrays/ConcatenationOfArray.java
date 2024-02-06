package CodingInterviewPatterns.Arrays;

import java.util.Arrays;

public class ConcatenationOfArray {
    public static void main(String[] args) {
        int[] nums = {1,2,1};
        Solution sln = new Solution();
        System.out.println(Arrays.toString(sln.getConcatenation(nums)));// Output: [1,2,1,1,2,1]
    }

    static class Solution {
        public int[] getConcatenation(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n + n];

            for (int i = 0; i < n; i++) {
                ans[i] = nums[i];
                ans[i + n] = nums[i];
            }
            return ans;
        }
    }
}
