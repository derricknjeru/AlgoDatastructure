package CodingInterviewPatterns.BinarySearch;

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        //https://www.youtube.com/watch?v=q6IEA26hvXc&t=1s
        int m = nums1.length;
        int n = nums2.length;

        if (m > n) {
            return findMedianSortedArrays2(nums2, nums1);
        }

        int total = m + n;
        int halfLen = (total + 1) / 2;

        int left = 0;
        int right = m;

        while (left <= right) {
            int partitionX = (left + right) / 2;
            int partitionY = halfLen - partitionX;


            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];

            int minX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            int minY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxX <= minY && maxY <= minX) {
                if (total % 2 == 0) {
                    return (Math.max(maxX, maxY) + Math.min(minX, minY)) / 2.0;
                } else {
                    return Math.max(maxX, maxY);
                }
            } else if (maxX > minY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted!");
    }


    /*Brute-force solution (Linear)*/
    // Runtime: O(m+n)
    // Extra Space: O(m+n)

    /**
     * The time complexity of the provided implementation for finding the median of two sorted arrays, findMedianSortedArrays, can be analyzed as follows:
     * <p>
     * Constructing the merged array: The code merges the two sorted arrays nums1 and nums2 into a single sorted array nums. This step involves traversing both arrays and comparing the elements to determine the order. The time complexity of this step is O(m + n), where m is the length of nums1 and n is the length of nums2.
     * <p>
     * Calculating the median: After constructing the merged array nums, the code calculates the median. If the total number of elements in nums is even, it takes the average of the middle two elements. If the total number of elements is odd, it returns the middle element directly. These operations are performed in constant time, O(1).
     * <p>
     * Therefore, the overall time complexity of the implementation is O(m + n), where m and n are the lengths of the input arrays nums1 and nums2, respectively.
     * <p>
     * It's important to note that this time complexity assumes that the arrays nums1 and nums2 are already sorted. If the input arrays are not sorted, additional sorting steps may be required, which would affect the overall time complexity.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int totalLength = m + n;
        int[] merged = new int[totalLength];

        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merged[k] = nums1[i];
                i++;
            } else {
                merged[k] = nums2[j];
                j++;
            }
            k++;
        }

        while (i < m) {
            merged[k] = nums1[i];
            i++;
            k++;
        }

        while (j < n) {
            merged[k] = nums2[j];
            j++;
            k++;
        }

        if (totalLength % 2 == 0) {
            int mid1 = merged[totalLength / 2 - 1];
            int mid2 = merged[totalLength / 2];
            return (double) (mid1 + mid2) / 2;
        } else {
            return merged[totalLength / 2];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2)); // Output: 2.0

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println(findMedianSortedArrays(nums3, nums4)); // Output: 2.5
    }


}

