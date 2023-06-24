package CodingInterviewPatterns.Arrays;

public class TwoSumSortedBinarySearch {


    public int[] twoSum(int[] nums, int target) {
        /**
         * The time complexity of this solution is improved to O(n log n)
         * since each binary search operation takes O(log n) time,
         * and we perform it for each element in the array.
         */
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            int index = binarySearch(nums, complement, i + 1);

            if (index != -1) {
                return new int[]{i, index};
            }
        }

        return new int[0];
    }

    private int binarySearch(int[] nums, int target, int start) {
        int left = start;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        TwoSumSortedBinarySearch solution = new TwoSumSortedBinarySearch();
        int[] nums = {-2, 0, 3, 5, 9, 11};
        int target = 7;
        int[] result = solution.twoSum(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]);
    }


}
