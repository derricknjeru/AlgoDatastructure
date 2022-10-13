package Two_pointer;

public class ContainerWithMostWater {

    //https://leetcode.com/problems/container-with-most-water/
    /**
     * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
     * <p>
     * Find two lines that together with the x-axis form a container, such that the container contains the most water.
     * <p>
     * Return the maximum amount of water a container can store.
     * <p>
     * Notice that you may not slant the container.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: height = [1,8,6,2,5,4,8,3,7]
     * Output: 49
     * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
     * Example 2:
     * <p>
     * Input: height = [1,1]
     * Output: 1
     * <p>
     * <p>
     * Constraints:
     * <p>
     * n == height.length
     * 2 <= n <= 105
     * 0 <= height[i] <= 104
     */

    //Time complexity is O(n)
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int width = right - left;

            int h = Math.min(height[left], height[right]);

            int currArea = width * h;

            maxArea = Math.max(currArea, maxArea);

            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
    //Time complexity O(n)

    public int maxAreaBruteForce(int[] height) {
        int maxArea = 0;

        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int currArea = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, currArea);
            }
        }

        return maxArea;
    }
}
