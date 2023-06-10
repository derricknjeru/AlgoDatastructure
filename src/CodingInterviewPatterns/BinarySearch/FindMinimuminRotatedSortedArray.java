package CodingInterviewPatterns.BinarySearch;

public class FindMinimuminRotatedSortedArray {

    //https://www.youtube.com/watch?v=OXkLNPalfRs

    /**
     * The findMin method takes an array of integers nums as input and returns the minimum element in the array.
     * Two pointers, left and right, are initialized. left points to the start of the array, and right points to the end of the array.
     * The algorithm uses a binary search approach to find the minimum element.
     * Inside the while loop, the algorithm continues searching until the left pointer is less than the right pointer. This ensures that the search space is reduced to a single element, which will be the minimum element.
     * The middle element is calculated using int mid = left + (right - left) / 2. This formula avoids integer overflow when the array is large.
     * The algorithm checks if the middle element, nums[mid], is greater than the element at the right pointer, nums[right].
     * If nums[mid] is greater than nums[right], it means the pivot element lies in the right half of the array. Therefore, the left pointer is updated to mid + 1 to search in the right half.
     * If nums[mid] is not greater than nums[right], the pivot element lies in the left half of the array, or the middle element itself is the pivot. In this case, the right pointer is updated to mid to search in the left half.
     * The while loop continues until the left and right pointers converge on the minimum element.
     * Once the while loop terminates, the left pointer will be pointing to the minimum element.
     * Finally, the algorithm returns the element at index left as the minimum element.
     *
     * @param nums
     * @return
     */

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[right];
        //int[] nums = {4, 5, 6, 7, 0, 1, 2};

        // After binary search iterations:

// Iteration 1: mid = 3
// nums[mid] (7) > nums[right] (2), so left = mid + 1 = 4

// Iteration 2: mid = 5
// nums[mid] (1) <= nums[right] (2), so right = mid = 5

// Iteration 3: mid = 4
// nums[mid] (0) <= nums[right] (2), so right = mid = 4

// Final state: left = right = 4

// The minimum element is nums[left] = nums[4] = 0
    }


}
