package CodingInterviewPatterns.DynamicProgramming.OneDP;

public class MaximumProductSubarray {
    //https://www.youtube.com/watch?v=lXVy6YWFcRM
    class Solution {
        //O(n) time complexity
        //O(1) space complexity
        public int maxProduct(int[] nums) {
            if (nums.length == 1) return nums[0];

            int res = nums[0];
            int max = 1;
            int min = 1;

            for (int n : nums) {
                int tmp = max * n;
                max = Math.max(n, Math.max(tmp, min * n));
                min = Math.min(n, Math.min(tmp, min * n));

                res = Math.max(res, max);
            }
            return res;
        }
    }

}
