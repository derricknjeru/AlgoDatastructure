package leetcode_30_days_challenge.week1;

import java.util.Arrays;

public class MoveZeroes {
    /**
     * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * <p>
     * Example:
     * <p>
     * Input: [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * Note.md.md.md.md:
     * <p>
     * You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.
     * Hide Hint #1
     * In-place means we should not be allocating any space for extra array. But we are allowed to modify the existing array. However, as a first step, try coming up with a solution that makes use of additional space. For this problem as well, first apply the idea discussed using an additional array and the in-place solution will pop up eventually.
     * Hide Hint #2
     * A two-pointer approach could be helpful here. The idea would be to have one pointer for iterating the array and another pointer that just works on the non-zero elements of the array.
     */

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        MoveZeroes z = new MoveZeroes();
        z.moveZeroes(nums);
    }
    //https://www.youtube.com/watch?time_continue=6&v=1PEncepEIoE&feature=emb_logo
    public void moveZeroes(int[] nums) {
        //index to keep track of non zeroes.
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }

        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }

        System.out.println(Arrays.toString(nums));

    }
}
