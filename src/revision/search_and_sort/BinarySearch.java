package revision.search_and_sort;

public class BinarySearch {
    public static void main(String[] args) {
        /**
         * Binary search
         *
         * In computer science, binary search, also known as half-interval search,
         * logarithmic search, or binary chop,
         * is a search algorithm that finds the position of a target value within a sorted array.
         * Binary search compares the target value to the middle element of the array.
         *
         */
    }

    public int search1(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        return binarySearch(nums, target, l, r);
    }


    private int binarySearch(int[] nums, int target, int l, int r) {
        if (l <= r) {
            int mid = l + (r - l) / 2;
            //base case
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                return binarySearch(nums, target, mid + 1, r);
            } else {
                return binarySearch(nums, target, l, mid - 1);
            }
        }

        return -1;

    }

    public int search(int[] arr, int x) {

        int defaultValue = -1;

        if (arr == null || arr.length == 0) {
            return defaultValue;
        }
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr[m] == x)
                return m;

            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return defaultValue;
    }
}
