package CodingInterviewPatterns.BinarySearch.TemplateIII;

public class SearchRange {
    //https://www.youtube.com/watch?v=4sQL7R5ySUU
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] res = new int[2];

            res[0] = modifiedBinarySearch(nums, target, true);
            res[1] = modifiedBinarySearch(nums, target, false);

            return res;
        }

        int modifiedBinarySearch(int[] nums, int target, boolean isFindingFirst) {
            int start = 0, end = nums.length-1;
            int ans = -1;

            while (start <= end) {
                int mid = start + (end-start) /2;

                if (nums[mid] == target) {
                    ans = mid;
                    if (isFindingFirst) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                } else if (nums[mid] > target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            return ans;
        }
    }
}
