package CodingInterviewPatterns.Arrays;

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
     * @return
     */


    /**
     * Approach 3 &4: Using Extra Array
     * This approach requires creating an extra array of the same size as the input array,
     * resulting in O(n) extra space complexity.
     * <p>
     * Approach 1: Using Reversal
     * This approach does the rotation in-place without using any extra space other than a few variables,
     * resulting in O(1) extra space complexity.
     * <p>
     * Approach 2: Using Cyclic Replacements
     * This approach also performs the rotation in-place using O(1) extra space.
     * <p>
     * In terms of space complexity, Approach 1 and Approach 2 are more efficient than Approach 3 $ 4
     * since they do not require the creation of an extra array.
     * <p>
     * Therefore, if you are looking for the most efficient approach in terms of both time and space complexity,
     * you should choose either Approach 1 (Using Reversal) or Approach 2 (Using Cyclic Replacements).
     * Both approaches achieve the desired rotation in O(n) time complexity and O(1) space complexity.
     */

    class Solution {
        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }

        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k %= n; // To handle cases where k > n

            // Reverse the entire array
            reverse(nums, 0, n - 1);

            // Reverse the first k elements
            reverse(nums, 0, k - 1);

            // Reverse the remaining n-k elements
            reverse(nums, k, n - 1);
        }


    }

    class Solution2 {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k %= n; // To handle cases where k > n

            int count = 0; // Number of elements rotated so far
            int start = 0; // Start index for the cycle

            while (count < n) {
                int current = start;
                int temp = nums[start];

                do {
                    int nextIdx = (current + k) % n;
                    int temp2 = nums[nextIdx];
                    nums[nextIdx] = temp;
                    temp = temp2;
                    current = nextIdx;
                    count++;
                } while (current != start);

                start++;
            }
        }

    }

    //using extra space
    class Solution3 {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k %= n; // To handle cases where k > n

            // Create an extra array
            int[] extra = new int[n];

            // Copy the last k elements to extra array
            if (k >= 0) System.arraycopy(nums, n - k, extra, 0, k);

            // Copy the remaining elements to extra array
            if (n - k >= 0) System.arraycopy(nums, 0, extra, k, n - k);

            // Copy elements from extra array back to nums
            System.arraycopy(extra, 0, nums, 0, n);
        }

    }

    class Solution4 {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k %= n; // To handle cases where k > n

            // Create an extra array
            int[] extra = new int[n];

            // Copy the last k elements to extra array
            for (int i = 0; i < k; i++) {
                extra[i] = nums[n - k + i];
            }

            // Copy the remaining elements to extra array
            for (int i = k; i < n; i++) {
                extra[i] = nums[i - k];
            }

            // Copy elements from extra array back to nums
            for (int i = 0; i < n; i++) {
                nums[i] = extra[i];
            }
        }

    }


}
