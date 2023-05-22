package CodingInterviewPatterns.Interesting;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindTheKthLargestIntegerInTheArray {
    /**
     * Constraints:
     *
     * 1 <= k <= nums.length <= 104
     * 1 <= nums[i].length <= 100
     * nums[i] consists of only digits.
     * nums[i] will not have any leading zeros.
     */
    //https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array

    /**
     * You are given an array of strings nums and an integer k. Each string in nums represents an integer without leading zeros.
     * <p>
     * Return the string that represents the kth largest integer in nums.
     * <p>
     * Note.md.md.md.md: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first largest integer, "2" is the second-largest integer, and "1" is the third-largest integer.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = ["3","6","7","10"], k = 4
     * Output: "3"
     * Explanation:
     * The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
     * The 4th largest integer in nums is "3".
     * Example 2:
     * <p>
     * Input: nums = ["2","21","12","1"], k = 3
     * Output: "2"
     * Explanation:
     * The numbers in nums sorted in non-decreasing order are ["1","2","12","21"].
     * The 3rd largest integer in nums is "2".
     * Example 3:
     * <p>
     * Input: nums = ["0","0"], k = 2
     * Output: "0"
     * Explanation:
     * The numbers in nums sorted in non-decreasing order are ["0","0"].
     * The 2nd largest integer in nums is "0".
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= k <= nums.length <= 104
     * 1 <= nums[i].length <= 100
     * nums[i] consists of only digits.
     * nums[i] will not have any leading zeros.
     */

    public String kthLargestNumber(String[] nums, int k) {
        /**
         * The time complexity of this approach is O(nlogk),
         * where n is the size of the array and k is the number of largest elements we need to keep track of in the heap.
         * We iterate over n elements and each heap operation takes logk time.
         */
        //a.compareTo(b) we are doing so that the smallest can always be at the top
        //If the length of the strings are equal, we just see which one is lexicographically larger else we compare the length and sort.
        // PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
        PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> a.length() == b.length() ? a.compareTo(b) : Integer.compare(a.length(), b.length()));

        for (String a : nums) {
            minHeap.add(a);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }

        return minHeap.poll();

    }
}
