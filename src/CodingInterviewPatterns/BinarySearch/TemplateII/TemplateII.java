package CodingInterviewPatterns.BinarySearch.TemplateII;

public class TemplateII {
    //https://leetcode.com/explore/learn/card/binary-search/126/template-ii/937/
    int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length - 1;
        while (left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Post-processing:
        // End Condition: left == right
        if (nums[left] == target) return left;
        return -1;
    }
}
