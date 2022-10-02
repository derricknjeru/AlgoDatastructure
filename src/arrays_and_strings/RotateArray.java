package arrays_and_strings;

public class RotateArray {
    public static void main(String[] args) {

    }

    //Approach 1: Using Extra Array
    public int[] solution(int[] A, int K) {
        // write your code in Java SE 8
        int[] nums = new int[A.length];

        int size = A.length;

        for (int i = 0; i < size; i++) {

            int rotatedIndex = (i + K) % size;

            nums[rotatedIndex] = A[i];

        }

        return nums;
    }

    //Approach 2: Using Using Reverse
    public void rotate(int[] nums, int k) {
        k %= nums.length;  // speed up the rotation
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1); //reverse k
        reverse(nums, k, nums.length - 1); //reverse n-k;

    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
