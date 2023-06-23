package CodingInterviewPatterns.Arrays;

import java.util.Arrays;
import java.util.Random;

public class ShuffleAnArray {
    //https://leetcode.com/problems/shuffle-an-array/description/
    //https://leetcode.com/problems/shuffle-an-array/editorial/
    //https://chat.openai.com/?model=text-davinci-002-render-sha
    //https://www.youtube.com/watch?v=Uch00ZGPdG8&ab_channel=codeExplainer

    /**
     * Complexity Analysis
     * <p>
     * Time complexity : O(n)
     * <p>
     * The Fisher-Yates algorithm runs in linear time, as generating a random
     * index and swapping two values can be done in constant time.
     * <p>
     * Space complexity : O(n)
     * <p>
     * Although we managed to avoid using linear space on the auxiliary array
     * from the brute force approach, we still need it for reset, so we're
     * stuck with linear space complexity.
     */
    static class Solution {
        //The Fisher-Yates algorithm
        private final int[] original;
        private final int[] shuffled;
        private final Random rand;

        public Solution(int[] nums) {
            /**
             * By using the clone method, we create separate copies of the original array.
             * This way, any modifications made to the shuffled array
             * (during shuffling) do not affect the original array, and vice versa.
             * Each array remains independent and maintains its own state throughout the shuffling process.
             */
            original = nums.clone();
            shuffled = nums.clone();
            rand = new Random();
        }

        public int[] reset() {
            return original;
        }

        public int[] shuffle() {
            int n = shuffled.length;
            for (int i = n - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                swap(shuffled, i, j);
            }
            return shuffled;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution solution = new Solution(nums);

        System.out.println("Original array: " + Arrays.toString(solution.reset()));
        System.out.println("Shuffled array: " + Arrays.toString(solution.shuffle()));
        System.out.println("Reset array: " + Arrays.toString(solution.reset()));
        System.out.println("Shuffled array: " + Arrays.toString(solution.shuffle()));
        System.out.println("Shuffled array: " + Arrays.toString(solution.shuffle()));
        System.out.println("Shuffled array: " + Arrays.toString(solution.shuffle()));
        /**
         * Original array: [1, 2, 3]
         * Shuffled array: [2, 1, 3]
         * Reset array: [1, 2, 3]
         * Shuffled array: [3, 1, 2]
         */
    }


}
