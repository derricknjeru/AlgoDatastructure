package CodingInterviewPatterns.BinarySearch.TemplateIII;

import java.util.*;

public class FindKClosestElements {
    //https://leetcode.com/problems/find-k-closest-elements/
    //https://www.techiedelight.com/find-k-closest-elements-to-given-value-array/

    static class Solution {

        /**
         * Finds the k the closest elements to x in a sorted array arr.
         *
         * @param arr The sorted array.
         * @param k   The number of the closest elements to find.
         * @param x   The target value.
         * @return The list of k the closest elements to x.
         */
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            // Find the index of the element in arr that is closest to x
            int closestIndex = findClosestIndex(arr, x);

            // Initialize the left and right pointers
            int left = closestIndex - 1;
            int right = closestIndex;

            // Iterate k times to adjust the pointers based on element differences
            for (int i = 0; i < k; i++) {
                if (left < 0 || right < arr.length &&
                        Math.abs(arr[left] - x) > Math.abs(arr[right] - x)) {
                    right++;
                } else {
                    left--;
                }
            }


            // Create a new list to store the closest elements
            List<Integer> res = new ArrayList<>();

            // Add the elements between left + 1 and right - 1 (exclusive) to the list
            for (int i = left + 1; i < right; i++) {
                res.add(arr[i]);
            }

            // Return the list of the closest elements
            return res;
        }

        /**
         * Finds the index of the element in arr that is closest to the target value.
         *
         * @param arr    The sorted array.
         * @param target The target value.
         * @return The index of the element closest to the target value.
         */
        private int findClosestIndex(int[] arr, int target) {
            int left = 0;
            int right = arr.length - 1;
            int closestIndex = -1;
            int minDiff = Integer.MAX_VALUE;

            // Perform binary search to find the closest index
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int currentDiff = Math.abs(arr[mid] - target);

                if (currentDiff < minDiff) {
                    minDiff = currentDiff;
                    closestIndex = mid;
                }

                if (arr[mid] == target) {
                    return mid;
                } else if (arr[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return closestIndex;
        }

        // Time Complexity:
        // - findClosestElements method: O(k)
        // - findClosestIndex method: O(log n)
        //   where n is the length of the array arr
    }

    class Solution2 {
        /**
         * The above solution to do a binary search to find the insertion point and then tries to find the k the closest elements.
         * However, we can combine the whole logic in a single binary search routine.
         * @param arr
         * @param k
         * @param x
         * @return
         */

        // Approach:
        // Using two pointers, we are going the 'start' and 'end' pointers towards each other,
        // until only k elements between 'start' and 'end'.

        public List<Integer> findClosestElements(int[] arr, int k, int x) {

            int start = 0;
            int end = arr.length - 1;
            // Between the 'start' and 'end' pointers, inclusive, contains all the k integers that is closest to x.
            while (end - start >= k) {
                // Move 'start' to the right if 'end' is closer to x, or move 'end' to the left if 'start' is closer to x.
                if (Math.abs(arr[start] - x) > Math.abs(arr[end] - x)) {
                    start++;
                } else {
                    end--;
                }
            }

            // Input all the k the closest integers into the result.
            List<Integer> result = new ArrayList<>(k);
            for (int i = start; i <= end; i++) {
                result.add(arr[i]);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> input = Arrays.asList(10, 12, 15, 17, 18, 20, 25);
        int target = 16, k = 4;
        int[] inputArray = new int[]{10, 12, 15, 18, 20, 25};

        List<Integer> res = solution.findClosestElements(inputArray, k, target);

        System.out.println(res);

    }
}
