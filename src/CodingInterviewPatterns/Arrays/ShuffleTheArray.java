package CodingInterviewPatterns.Arrays;

public class ShuffleTheArray {
    //https://www.youtube.com/watch?v=IvIKD_EU8BY&ab_channel=NeetCodeIO
    //https://leetcode.com/problems/shuffle-the-array/editorial/

    class Solution {
        /**
         * Time complexity: O(n)
         * We iterate on nnn elements of the nums array, which takes us O(n) time.
         * Initializing the result array will take O(2n)time.
         * Thus, overall we take O(n+2n)=O(n) time.
         * Space complexity: O(1)
         * We are not using any additional space other than the output array.
         * @param nums
         * @param n
         * @return
         */
        public int[] shuffle(int[] nums, int n) {
            int[] result = new int[2 * n];
            int index = 0;
            for (int i = 0; i < n; i++) {
                result[index++] = nums[i];
                result[index++] = nums[i + n];
            }
            return result;
        }

    }
}
