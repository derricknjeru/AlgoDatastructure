package CodingInterviewPatterns.Arrays;

public class FindPivotIndex {
    //https://leetcode.com/problems/find-pivot-index/editorial/
    //https://www.youtube.com/watch?v=u89i60lYx8U
    class Solution {
        //O(n)
        public int pivotIndex(int[] nums) {
            int sum = 0, leftsum = 0;
            for (int x: nums) sum += x;
            for (int i = 0; i < nums.length; ++i) {
                if (leftsum == sum - leftsum - nums[i]) return i;
                leftsum += nums[i];
            }
            return -1;
        }
    }
}
