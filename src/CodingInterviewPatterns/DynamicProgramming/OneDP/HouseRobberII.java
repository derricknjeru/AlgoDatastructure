package CodingInterviewPatterns.DynamicProgramming.OneDP;

public class HouseRobberII {
    //https://leetcode.com/problems/house-robber-ii/submissions/973393759/
    //https://www.youtube.com/watch?v=rWAJCfYYOvM&ab_channel=NeetCode
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        if (length == 1) return nums[0];

        return Math.max(
                robHelper(nums, 0, length - 2),
                robHelper(nums, 1, length - 1)
        );
    }

    public int robHelper(int[] nums, int start, int end) {
        int prevMax = 0;
        int currMax = 0;
        for (int i = start; i <= end; i++) {
            int temp = Math.max(prevMax + nums[i], currMax);
            prevMax = currMax;
            currMax = temp;
        }
        return currMax;
    }
    /**
     * The time complexity of the provided code is O(N), where N is the length of the input array nums.
     * This is because the code iterates through the array once in the robHelper method,
     * and the rob method makes two calls to the robHelper method. The loop in the robHelper method contributes to the linear time complexity.
     *
     * The space complexity of the code is O(1) because it uses a constant amount of additional space.
     * It does not require any extra data structures that scale with the input size.
     * The variables used in the methods (prevMax, currMax, temp, start, end, etc.)
     * and the input array nums are the only variables used, and they do not depend on the input size.
     * Therefore, the space complexity is constant.
     */
}
