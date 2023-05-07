package arrays_and_strings;

public class RemoveDuplicateSortedArray {
    /**
     * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
     * <p>
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     * <p>
     * Example 1:
     * <p>
     * Given nums = [1,1,2],
     * <p>
     * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
     * <p>
     * It doesn't matter what you leave beyond the returned length.
     * Example 2:
     * <p>
     * Given nums = [0,0,1,1,1,2,2,3,3,4],
     * <p>
     * Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
     * <p>
     * It doesn't matter what values are set beyond the returned length.
     * Clarification:
     * <p>
     * Confused why the returned value is an integer but your answer is an array?
     * <p>
     * Note.md.md that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
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
     * Hide Hint #1
     * In this problem, the key point to focus on is the input array being sorted. As far as duplicate elements are concerned, what is their positioning in the array when the given array is sorted? Look at the image above for the answer. If we know the position of one of the elements, do we also know the positioning of all the duplicate elements?
     * <p>
     * Hide Hint #2
     * We need to modify the array in-place and the size of the final array would potentially be smaller than the size of the input array. So, we ought to use a two-pointer approach here. One, that would keep track of the current element in the original array and another one for just the unique elements.
     * Hide Hint #3
     * Essentially, once an element is encountered, you simply need to bypass its duplicates and move on to the next unique element.
     */

    public static void main(String[] args) {
        //int nums[] = {1, 1, 2};
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len = removeDuplicates(nums);

        System.out.printf("The length is %d ", len);

    }

    static int removeDuplicates(int[] nums) {
        int _length = 0;

        if (nums == null || nums.length == 0) {
            return _length;
        }

        int size = nums.length;

        for (int i = 0; i < size - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[_length] = nums[i];
                _length++;
            }

        }

        nums[_length] = nums[size - 1];


        return _length + 1; //we add one because what we were tracking was the index
    }


}
