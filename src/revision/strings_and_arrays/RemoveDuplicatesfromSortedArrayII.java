package revision.strings_and_arrays;

public class RemoveDuplicatesfromSortedArrayII {
    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};

    }

    /**
     * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
     * <p>
     * Do not allocate extra space for another array; you must do this by modifying the input array in-place with O(1) extra memory.
     * <p>
     * Clarification:
     * <p>
     * Confused why the returned value is an integer, but your answer is an array?
     * <p>
     * Note that the input array is passed in by reference, which means a modification to the input array will be known to the caller.
     * <p>
     * Internally you can think of this:
     * <p>
     * // nums is passed in by reference. (i.e., without making a copy)
     * int len = removeDuplicates(nums);
     * <p>
     * // any modification to nums in your function would be known by the caller.
     * // using the length returned by your function, it prints the first len elements.
     * for (int i = 0; i < len; i++) {
     * print(nums[i]);
     * }
     */

    //{@link https://www.youtube.com/watch?v=OTh-l-COvt4}
    public int removeDuplicates(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int _length = 0;

        for (int a : nums) {
            if (_length < 2 || a != nums[_length - 2]) {
                nums[_length++] = a;
            }

        }

        return _length;
    }
}
