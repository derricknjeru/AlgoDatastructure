package CodingInterviewPatterns.TwoPointers;

public class TrappingRainWater {
    //https://leetcode.com/problems/trapping-rain-water/description/
    //https://www.youtube.com/watch?v=ZI2z5pq0TqA
    //https://www.youtube.com/watch?v=C8UjlJZsHBw


    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();

        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int trappedWater1 = solution.trap(height1);
        System.out.println("Trapped Water 1: " + trappedWater1);

        int[] height2 = {4, 2, 0, 3, 2, 5};
        int trappedWater2 = solution.trap(height2);
        System.out.println("Trapped Water 2: " + trappedWater2);
    }


    //Two pointer programming
    //Time complexity O(n)
    //space complexity O(1)
    public int trap(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;

        int maxLeft = height[0];
        int maxRight = height[n - 1];
        int trappedWater = 0;
        int left = 1;   // Left pointer
        int right = n - 2;    // Right pointer
        while (left <= right) {
            if (maxLeft < maxRight) {
                if (height[left] >= maxLeft) maxLeft = height[left];
                else trappedWater += maxLeft - height[left];
                left += 1;
            } else {
                if (height[right] > maxRight) maxRight = height[right];
                else trappedWater += maxRight - height[right];
                right -= 1;
            }
        }
        return trappedWater;
    }

    //Dynamic programming
    //Time complexity O(n)
    //space complexity O(1)
    public int trap2(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];

        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;

    }

}
