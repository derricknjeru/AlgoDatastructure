package sorting_and_searching;

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = {
                4, 5, 6, 7, 0, 1, 2
        };
        int[] nums2 = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int[] nums3 = {5, 1, 3};

        SearchInRotatedSortedArray search = new SearchInRotatedSortedArray();
        System.out.println(search.search(nums2, 3));
    }
    //Algo

    /**
     *
     */
    public int search(int[] nums, int target) {
        //https://www.youtube.com/watch?v=0PiH6Beqif8
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int lowerBound = 0;
        int upperBound = nums.length - 1;

        //The index of the first element of the sorted array before rotation
        int pivot = findPivot(nums);
        System.out.println("start number index::" + pivot);

        if (target >= nums[pivot] && target <= nums[upperBound]) {
            lowerBound = pivot;
        } else {
            upperBound = pivot;
        }

        //do normal binary search
        while (lowerBound <= upperBound) {
            int mid = lowerBound + (upperBound - lowerBound) / 2; //we subs upper bound and lower bound to prevent integer overflows
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                lowerBound = mid + 1;
            } else {
                upperBound = mid - 1;
            }
        }

        return -1;
    }

    //https://www.youtube.com/watch?v=ggLGjf_XiNQ

    //Use when we don't have duplicates

    int findPivot(int[] nums) {

        int n = nums.length;
        // If the list has just one element then return that element.
        if (n == 1) {
            return 0;
        }

        // if the last element is greater than the first element then there is no
        // rotation.
        // e.g. 1 < 2 < 3 < 4 < 5 < 7. Already sorted array.
        // Hence the smallest element is first element. A[0]

        if (nums[0] < nums[n - 1]) {
            return 0;
        }

        // initializing left and right pointers.
        int start = 0, end = n - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // if the mid element is greater than its next element then mid+1 element is the
            // smallest
            // This point would be the point of change. From higher to lower value.

            if (nums[mid] > nums[mid + 1]) {
                return mid + 1;

            }

            // if the mid element is lesser than its previous element then mid element is
            // the smallest

            if (nums[mid - 1] > nums[mid]) {
                return mid;
            }

            // if the mid elements value is greater than the 0th element this means
            // the least value is still somewhere to the right as we are still dealing with
            // elements
            // greater than nums[0]
            if (nums[start] < nums[mid]) {

                start = mid + 1;

            } else {
                // if nums[0] is greater than the mid value then this means the smallest value
                // is somewhere to
                // the left
                end = mid - 1;
            }

        }

        return -1;


    }

}
