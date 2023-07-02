package CodingInterviewPatterns.BinarySearch.TemplateII;

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
     * @param
     * @return
     */

    class Solution {
        /**
         * he time complexity of the provided implementation is O(log n), where n is the length of the input array nums. This is because the algorithm uses a binary search approach to find the minimum element.
         *
         * In each iteration of the while loop, the search space is halved, as we update either the start or end index based on a comparison with the middle element. This halving of the search space results in a logarithmic time complexity.
         *
         * Therefore, the time complexity of the algorithm is logarithmic, O(log n), indicating that the algorithm's runtime increases proportionally to the logarithm of the input size. This is an efficient approach for finding the minimum element in a rotated sorted array.
         * @param nums
         * @return
         */
        public int findMin(int[] nums) {
            int start = 0;
            int end = nums.length - 1;

            // Check if the array is already sorted
            if (nums[start] <= nums[end]) {
                return nums[start];
            }

            while (start < end) {
                int mid = start + (end - start) / 2;

                // If the mid-element is greater than the end element,
                // the minimum element is in the right subarray.
                if (nums[mid] > nums[end]) {
                    start = mid + 1;
                }
                // If the mid-element is less than or equal to the end element,
                // the minimum element is in the left subarray.
                else {
                    end = mid;
                }
            }
            //System.out.println(start);

            return nums[start];
        }
    }


}
