package arrays_and_strings;

public class MaxConsecutiveOnes {

    /**
     * Given a binary array, find the maximum number of consecutive 1s in this array.
     * <p>
     * Example 1:
     * Input: [1,1,0,1,1,1]
     * Output: 3
     * Explanation: The first two digits or the last three digits are consecutive 1s.
     * The maximum number of consecutive 1s is 3.
     * Note.md.md:
     * <p>
     * The input array will only contain 0 and 1.
     * The length of input array is a positive integer and will not exceed 10,000
     *
     * @param arg
     */

    public static void main(String[] arg) {
        int[] nums = {1, 1, 0, 1, 1, 1};
        System.out.printf("Max number %d ", findMaxConsecutiveOnes(nums));
    }

    /**
     * Explanation { @link https://www.youtube.com/watch?v=PLa4tYQhqoU&ab_channel=NickWhite}
     *
     * @param nums
     * @return
     */
    static int findMaxConsecutiveOnes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int maxLength = 0;
        int currentMax = 0;
        for (int num : nums) {
            if (num == 1) {
                currentMax++;
            } else {
                currentMax = 0;
            }
            maxLength = Math.max(maxLength, currentMax);
        }

        return maxLength;
    }
}
