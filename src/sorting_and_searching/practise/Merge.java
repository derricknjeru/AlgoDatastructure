package sorting_and_searching.practise;

public class Merge {
    public static void main(String[] args) {
        //https://www.youtube.com/watch?v=iMT7gTPpaqw&t=1s
        // https://www.youtube.com/watch?v=TzeBrDU-JaY  best
        // https://www.youtube.com/playlist?feature=edit_ok&list=PL2_aWCzGMAwKedT2KfDMB9YA5DgASZb3U
        int[] arr = {2, 4, 1, 90, 20, 6, 8, 5, 3, 7};

        Merge m = new Merge();

        m.mergeSort(arr, arr.length);

        printArray(arr);


    }

    /* A utility function to print array of size n */
    static void printArray(int[] arr) {
        int n = arr.length;
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }


    public void mergeSort(int[] arr, int n) {
        if (n < 2) return; // base condition. If the array has less than two element, do nothing.

        int m = n / 2;  // find the mid index.


        // create left and right subarrays
        // mid elements (from index 0 till mid-1) should be part of left sub-array
        // and (n-mid) elements (from mid to n-1) will be part of right sub-array

        // Find sizes of two subarrays to be merged
        int n1 = m;
        int n2 = n - m;

        int[] left = new int[n1];
        int[] right = new int[n2];

        // creating left subarray
        for (int i = 0; i < m; i++)
            left[i] = arr[i];

        // creating right subarray
        for (int j = 0; j < n - m; j++)
            right[j] = arr[m + j];


        mergeSort(left, m);  // sorting the left subarray
        mergeSort(right, n - m);  // sorting the right subarray
        merge(left, right, arr, n1, n2);   // Merging L and R into A as sorted list.


    }

    private void merge(int[] left, int[] right, int[] arr, int l_count, int r_count) {
        int i, j, k;

        // i - to mark the index of left aubarray (L)
        // j - to mark the index of right sub-raay (R)
        // k - to mark the index of merged subarray (A)
        i = 0;
        j = 0;
        k = 0;

        while (i < l_count && j < r_count) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < l_count) {
            arr[k] = left[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < r_count) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
}
