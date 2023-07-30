package CodingInterviewPatterns.BinarySearch.GeneralImplementation;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = new int[]{-7, -4, 3, 9, 9, 9, 12};
    }

    //All solutions below  have Time complexity: O(logn)
    //And space complexity is O(1)
    class Solution {
        //Approach 1: Find the Exact Value
        public int search(int[] nums, int target) {
            // Set the left and right boundaries
            int left = 0, right = nums.length - 1;

            // Under this condition
            while (left <= right) {
                // Get the middle index and the middle value.
                int mid = left + (right - left) / 2;

                // Case 1, return the middle index.
                if (nums[mid] == target) {
                    return mid;
                }
                // Case 2, discard the smaller half.
                else if (nums[mid] < target) {
                    left = mid + 1;
                }
                // Case 3, discard the larger half.
                else {
                    right = mid - 1;
                }
            }

            // If we finish the search without finding target, return -1.
            return -1;
        }
    }

    class Solution1 {
        //Approach 2: Find Upper bound
        //Explanation:
        //you have array int[] array = new int[]{-7, -4, 3, 9, 9, 9, 12};
        //Assume you want to insert 9 into the array.
        //if we look at the upper bound, we have to insert 9 to the right of all existing 9.
        //This will help a lot when we have duplicate values
        //Now we do binary search --We have left ==0 and right ==array.length (Note that the maximum insert position can be nums.size);
        //If nums[mid] < target, the insert position is on mid's right, so we let left = mid + 1 to discard the left half and mid.
        //If nums[mid] = target, the insert position is on mid's right, so we let left = mid + 1 to discard the left half and mid.
        //If nums[mid] > target, mid can also be the insert position. So we let right = mid to discard the right half while keeping mid.
        //Therefore, we merged the two conditions nums[mid] = target and nums[mid] < target and there are only two conditions in the if-else statement!
        //Once the loop stops, left stands for the insert position and left - 1 is the largest element that is not larger than target
        //We just need to check if nums[left - 1] equals target. Note this boundary condition where left = 0,
        // which means all elements in nums are larger than target, so there is no target in nums.
        public int search(int[] nums, int target) {
            // Set the left and right boundaries
            int left = 0, right = nums.length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (left > 0 && nums[left - 1] == target) {
                return left - 1;
            } else {
                return -1;
            }
        }
    }

    class Solution3 {
        //Approach 3: Find Lower bound
        //Different from the previous method, here we are looking for the leftmost insert position.
        //Explanation:
        //you have array int[] array = new int[]{-7, -4, 3, 9, 9, 9, 12};
        //Assume you want to insert 9 into the array.
        //if we look at the lower bound, we have to insert 9 to the left of all existing 9.
        //This will help a lot when we have duplicate values
        //Now we do binary search --We have left ==0 and right ==array.length (Note that the maximum insert position can be nums.size);
        //If nums[mid] = target, the insert position is on mid's left, so we let right = mid to discard the right half.
        //If nums[mid] > target, the insert position is on mid's left, so we let right = mid to discard the right half.
        //f nums[mid] < target, the insert position is on mid's right, so we let left = mid + 1 to discard the left half and mid.
        //Therefore, we merged the two conditions nums[mid] = target and nums[mid] > target and there are only two conditions in the if-else statement!
        //Once the loop stops, left stands for the insert position and nums[left] is the smallest element that is no less than target. We just need to check if nums[left] equals target. Note this boundary condition left = nums.size, which means all elements in nums are smaller than target, so there is no target in nums.

        public int search(int[] nums, int target) {
            // Set the left and right boundaries
            int left = 0, right = nums.length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            if (left < nums.length && nums[left] == target) {
                return left;
            } else {
                return -1;
            }
        }
    }

}
