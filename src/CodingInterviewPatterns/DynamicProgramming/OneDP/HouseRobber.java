package CodingInterviewPatterns.DynamicProgramming.OneDP;

public class HouseRobber {
    //https://leetcode.com/problems/house-robber/description/
    //https://www.youtube.com/watch?v=73r3KWiEvyk&ab_channel=NeetCode

    /**
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     * <p>
     * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,2,3,1]
     * Output: 4
     * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
     * Total amount you can rob = 1 + 3 = 4.
     * Example 2:
     * <p>
     * Input: nums = [2,7,9,3,1]
     * Output: 12
     * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
     * Total amount you can rob = 2 + 9 + 1 = 12.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     */
    //Time complexity is O(n)
    //space complexity is O(1)
    public int rob2(int[] nums) {
        int rob1 = 0;
        int rob2 = 0;

        for (int n : nums) {
            int temp = Math.max(n + rob1, rob2);
            rob1 = rob2;
            rob2 = temp;
        }

        return rob2;
    }


    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;

        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[n];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];

    }

}
