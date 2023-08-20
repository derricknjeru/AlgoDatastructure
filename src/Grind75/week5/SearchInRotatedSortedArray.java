package Grind75.week5;

public class SearchInRotatedSortedArray {

    class Solution {
        /**
         * O(log n) + O(log m)
         * <p>
         * However, since m can be equal to n (in the worst case scenario),
         * we can simplify the time complexity analysis to:
         * <p>
         * O(log n + m)
         * <p>
         * In this case, the time complexity is O(log n + n) = O(log n) in the worst case scenario,
         * where n represents the number of elements in the input array nums.
         * <p>
         * Therefore, the overall time complexity of the search method is O(log n) in the worst case.
         *
         * @param nums
         * @param target
         * @return
         */
        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;

            int pivotIndex = findPivot(nums, left, right);

            if (target >= nums[pivotIndex] && target <= nums[right]) {
                left = pivotIndex;
            } else {
                right = pivotIndex;
            }

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) return mid;

                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
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

    public static int search(int[] nums, int target) {
        //https://www.youtube.com/watch?v=oTfPJKGEHcc
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {  // Check if the left half is sorted
                if (nums[left] <= target && target < nums[mid]) {  // Target is in the left half
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {  // The right half must be sorted
                if (nums[mid] < target && target <= nums[right]) {  // Target is in the right half
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;  // Target not found

    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println(search(nums1, target1));  // Output: 4

        // Example 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println(search(nums2, target2));  // Output: -1

        // Example 3
        int[] nums3 = {1};
        int target3 = 0;
        System.out.println(search(nums3, target3));  // Output: -1
    }
}

