package revision.search_and_sort;

import java.util.Arrays;

public class MergeSortedArray {
    /**
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     * <p>
     * Note:
     * <p>
     * The number of elements initialized in nums1 and nums2 are m and n respectively.
     * You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.
     * Example:
     * <p>
     * Input:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * <p>
     * Output: [1,2,2,3,5,6]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * -10^9 <= nums1[i], nums2[i] <= 10^9
     * nums1.length == m + n
     * nums2.length == n
     * Hide Hint #1
     * You can easily solve this problem if you simply think about two elements at a time rather than two arrays. We know that each of the individual arrays is sorted. What we don't know is how they will intertwine. Can we take a local decision and arrive at an optimal solution?
     * Hide Hint #2
     * If you simply consider one element each at a time from the two arrays and make a decision and proceed accordingly, you will arrive at the optimal solution.
     */

    public static void main(String[] args) {
        int[] num1 = {1, 2, 3, 0, 0, 0};
        int[] num2 = {2, 5, 6};
        merge(num1, 3, num2, 3);

    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, k = 0, j = 0;

        int[] A = new int[m];

        if (m >= 0) System.arraycopy(nums1, 0, A, 0, m);

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
        while (j < n) {
            nums1[k] = nums2[j];
            j++;
            k++;
        }
        while (i < m) {
            nums1[k] = A[i];
            i++;
            k++;
        }

        System.out.println(Arrays.toString(nums1));


    }

    //O(1) constant space
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        for(int i = m;i<m+n;i++) {
            nums1[i] = nums2[j];
            j++;
        }
        Arrays.sort(nums1);
    }
}

