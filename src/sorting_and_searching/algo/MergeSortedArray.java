package sorting_and_searching.algo;

import java.util.Arrays;

public class MergeSortedArray {

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        MergeSortedArray ms = new MergeSortedArray();
        ms.merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));


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
