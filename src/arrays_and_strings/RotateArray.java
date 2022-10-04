package arrays_and_strings;

public class RotateArray {
    public static void main(String[] args) {
        //https://leetcode.com/problems/rotate-array/
    }

    /**
     * An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is moved to the first place. For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).
     * <p>
     * The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.
     * <p>
     * Write a function:
     * <p>
     * class Solution { public int[] solution(int[] A, int K); }
     * <p>
     * that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.
     * <p>
     * For example, given
     * <p>
     * A = [3, 8, 9, 7, 6]
     * K = 3
     * the function should return [9, 7, 6, 3, 8]. Three rotations were made:
     * <p>
     * [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
     * [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
     * [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
     * For another example, given
     * <p>
     * A = [0, 0, 0]
     * K = 1
     * the function should return [0, 0, 0]
     * <p>
     * Given
     * <p>
     * A = [1, 2, 3, 4]
     * K = 4
     * the function should return [1, 2, 3, 4]
     * <p>
     * Assume that:
     * <p>
     * N and K are integers within the range [0..100];
     * each element of array A is an integer within the range [−1,000..1,000].
     * In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
     * <p>
     * Copyright 2009–2021 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
     *
     * @param A
     * @param K
     * @return
     */
    //using extra space
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

    //no extra space

    public void rotate(int[] nums, int k) {
        //[1,2,3,4,5,6,7], k = 3
        //7654321
        // 5674321

        //5671234


        int n = nums.length;

        k = k % n;

        //reverse the whole list
        reverse(nums, 0, n - 1);

        //reverse K numbers
        reverse(nums, 0, k - 1);

        //reverse n-k numbers
        reverse(nums, k, n - 1);

    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }


}
