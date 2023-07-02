package CodingInterviewPatterns.stack_and_queue;

import java.util.Stack;

public class LargestRectangleInHistogram {
    //https://leetcode.com/problems/largest-rectangle-in-histogram/


    class Solution {
        public int largestRectangleArea(int[] heights) {
            int maxArea = 0;
            Stack<int[]> stack = new Stack<>(); // pair: (index, height)

            for (int i = 0; i < heights.length; i++) {
                int start = i;
                while (!stack.isEmpty() && stack.peek()[1] > heights[i]) {
                    int[] top = stack.pop();
                    int index = top[0];
                    int height = top[1];
                    maxArea = Math.max(maxArea, height * (i - index));
                    start = index;
                }
                stack.push(new int[]{start, heights[i]});
            }

            while (!stack.isEmpty()) {
                int[] top = stack.pop();
                int index = top[0];
                int height = top[1];
                maxArea = Math.max(maxArea, height * (heights.length - index));
            }

            return maxArea;
        }
    }
}
