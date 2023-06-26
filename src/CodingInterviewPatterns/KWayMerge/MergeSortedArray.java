package CodingInterviewPatterns.KWayMerge;

import java.util.Arrays;

public class MergeSortedArray {
    /**
     * The time complexity of the updated merge method is O(m + n), where m and n are the lengths of nums1 and nums2 respectively. This is because the method uses a single loop that iterates through nums1 and nums2 from the last index to the first index, performing comparisons and merging the elements.
     *
     * The space complexity is O(1), as the method does not use any additional space that scales with the input size. It only uses a constant amount of extra space for variables p1, p2, and lastIndex.
     *
     * Overall, the time complexity is linear with respect to the total number of elements in nums1 and nums2, and the space complexity is constant.
     * @param args
     */

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        MergeSortedArray ms = new MergeSortedArray();
        ms.merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));


    }

    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p1 = m - 1;
            int p2 = n - 1;
            int lastIndex = m + n - 1;

            while (p1 >= 0 && p2 >= 0) {
                if (nums1[p1] > nums2[p2]) {
                    nums1[lastIndex--] = nums1[p1--];
                } else {
                    nums1[lastIndex--] = nums2[p2--];
                }
            }

            // Copy any remaining elements from nums2 to nums1
            while (p2 >= 0) {
                nums1[lastIndex--] = nums2[p2--];
            }

            // No need to handle remaining elements in nums1
            // They are already in their correct positions
        }
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0, k = 0;

        int[] A = new int[m];

        for (int h = 0; h < m; h++) A[h] = nums1[h];

        while (i < m && j < n) {
            if (A[i] <= nums2[j]) {
                nums1[k] = A[i];
                i++;
            } else {
                nums1[k] = nums2[j];
                j++;
            }

            k++;
        }

        while (i < m) {
            nums1[k] = A[i];
            i++;
            k++;
        }
        while (j < n) {
            nums1[k] = nums2[j];
            j++;
            k++;
        }
    }

}
