package arrays_and_strings;

public class SearchInRotatedSortedArray {
    //https://leetcode.com/problems/search-in-rotated-sorted-array/

    public static void main(String[] args) {

    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        //find the pivot(Index of the smallest element)

        int left = 0;
        int right = nums.length - 1;

        int startIndex = findPivot(nums, left, right);

        //Determine which side the the target is
        //4,5,6,7,0,1,2

        if (target >= nums[startIndex] && target <= nums[right]) {
            left = startIndex;
        } else {
            right = startIndex;
        }

        //Do binary search on the sorted side
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > target) {
                right = mid - 1;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        return -1;

    }

    private int findPivot(int[] nums, int left, int right) {
        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }
        return left;
    }

}
