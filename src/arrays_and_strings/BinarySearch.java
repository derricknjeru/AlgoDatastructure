package arrays_and_strings;

public class BinarySearch {
    //https://leetcode.com/problems/binary-search/
    public static void main(String[] ars) {
        int[] arr = {2, 3, 4, 10, 40};
        int x = 10;


        System.out.println(binarySearch(arr, x));

        int n = arr.length;
        int result = binarySearch(arr, 0, n - 1, x);

        System.out.println(result);
        /**
         * Important things
         * ---> while condition has to be equal to
         * ---> and r is length-1;
         */
    }


    // Java implementation of iterative Binary Search
    // Returns index of x if it is present in arr[l..
    // r], else return -1
    //Time complexity is O(log n);

    /**
     * Important things
     * ---> while condition has to be equal to
     * ---> and r is length-1;'
     * ---> Time complexity O(log n)
     */
    static int binarySearch(int[] arr, int x) {
        int l = 0;
        int h = arr.length - 1;

        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (arr[mid] == x) return mid;

            //ignore the left part of the array
            if (arr[mid] < x) l = mid + 1;

            //ignore the right part of the array
            if (arr[mid] > x) h = mid - 1;

        }
        return -1;
    }

    // Returns index of x if it is present in arr[l..
    // r], else return -1
    static int binarySearch(int[] arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr[mid] == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l + 1 < r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid;
            }
        }

        if (nums[l] == target) return l;
        if (nums[r] == target) return r;

        return -1;
    }


}
