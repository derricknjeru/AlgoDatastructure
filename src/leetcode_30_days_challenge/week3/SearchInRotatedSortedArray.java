package leetcode_30_days_challenge.week3;

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = {
                4, 5, 6, 7, 0, 1, 2
        };
        int[] nums2 = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int[] nums3 = {5, 1, 3};

        SearchInRotatedSortedArray search = new SearchInRotatedSortedArray();
        System.out.println(search.search(nums3, 1));
    }
    //Algo

    /**
     *
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int lowerBound = 0;
        int upperBound = nums.length - 1;

        //The index of the first element of the sorted array before rotation
        int pivot = findPivot(nums, lowerBound, upperBound);
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


    int findPivot(int[] nums, int low, int high) {
        // base cases
        while (low < high) {
            int mid = low + (high - low) / 2; //we subs upper bound and lower bound to prevent integer overflows
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
