package revision.strings_and_arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums = {3, 4, -1, 1};
        FirstMissingPositive x = new FirstMissingPositive();
        x.firstMissingPositive2(nums);
    }

    //This approach may take O(n) time on average, but it requires O(n) extra space.

    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        int size = nums.length;

        for (int a : nums) {
            if (a > 0) {
                set.add(a);
            }
        }

        for (int i = 1; i <= size; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return size + 1;

    }

    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;

        // To mark the occurrence of elements
        boolean[] present = new boolean[n + 1];


        // Mark the occurrences
        for (int i = 0; i < n; i++) {

            // Only mark the required elements
            // All non-positive elements and
            // the elements greater n + 1 will never
            // be the answer
            // For example, the array will be {1, 2, 3}
            // in the worst case and the result
            // will be 4 which is n + 1
            if (nums[i] > 0 && nums[i] <= n)
                present[nums[i]] = true;
        }

        System.out.println(Arrays.toString(present));

        // Find the first element which didn't
        // appear in the original array
        for (int i = 1; i <= n; i++)
            if (!present[i])
                return i;

        // If the original array was of the
        // type {1, 2, 3} in its sorted form
        return n + 1;

    }

    public int firstMissingPositive3(int[] nums) {
        //set any 0 or negative number as the size of the array + 1
        //[1,2,*-3*,4] => [1,2,*5*,4]
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }

        //if the absolute value is a valid position of this array, then
        //negate the value of the respective index
        // [1,2,5,4] =>  [-1,-2,5,-4]
        for (int i = 0; i < nums.length; i++) {
            int valueIdx = Math.abs(nums[i]) - 1;
            if (valueIdx + 1 > 0 && valueIdx + 1 <= nums.length) {
                nums[valueIdx] = -Math.abs(nums[valueIdx]);
            }
        }

        //traverse the array from left to right
        //if there's any positive number then
        //we found the missing positive number
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 1) {
                return i + 1;
            }
        }

        //if all elements of the array are correct
        //return the lenght of the array + 1
        return nums.length + 1;
    }
}
