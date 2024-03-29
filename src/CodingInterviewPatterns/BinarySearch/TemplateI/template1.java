package CodingInterviewPatterns.BinarySearch.TemplateI;

public class template1 {
    public static void main(String[] args) {
        //https://leetcode.com/explore/learn/card/binary-search/125/template-i/938/
    }

    int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // End Condition: left > right
        return -1;
    }
}
