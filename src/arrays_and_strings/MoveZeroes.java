package arrays_and_strings;

public class MoveZeroes {
    public static void main(String[] args) {

    }

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
     */

    public void moveZeroes(int[] nums) {

        int index = 0;

        for (int a : nums) {
            if (a != 0) {
                nums[index++] = a;
            }

        }

        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
