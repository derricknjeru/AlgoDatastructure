package CodingInterviewPatterns.stack_and_queue.MonotonicStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElement {
    public static void main(String[] args) {
        //https://leetcode.com/problems/next-greater-element-i/description/
        //https://www.youtube.com/watch?v=68a1Dc_qVq4&t=1s&ab_channel=NeetCode
        /**
         * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
         *
         * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
         *
         * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
         *
         * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
         *
         *
         *
         * Example 1:
         *
         * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
         * Output: [-1,3,-1]
         * Explanation: The next greater element for each value of nums1 is as follows:
         * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
         * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
         * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
         * Example 2:
         *
         * Input: nums1 = [2,4], nums2 = [1,2,3,4]
         * Output: [3,-1]
         * Explanation: The next greater element for each value of nums1 is as follows:
         * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
         * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
         *
         *
         * Constraints:
         *
         * 1 <= nums1.length <= nums2.length <= 1000
         * 0 <= nums1[i], nums2[i] <= 104
         * All integers in nums1 and nums2 are unique.
         * All the integers of nums1 also appear in nums2.
         *
         *
         * Follow up: Could you find an O(nums1.length + nums2.length) solution?
         */

    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //https://www.youtube.com/watch?v=68a1Dc_qVq4
        //https://www.youtube.com/watch?v=mJWQjJpEMa4
        int len1 = nums1.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        int[] ans = new int[len1];
        Arrays.fill(ans, -1);
        //O(m)
        for (int i = 0; i < len1; i++) {
            map.put(nums1[i], i);
        }
        //Time complexity is O(m+n)
        for (int val : nums2) {
            while (!stack.isEmpty() && val > stack.peek()) {
                int smallerNo = stack.pop();
                ans[map.get(smallerNo)] = val;
            }
            if (map.containsKey(val)) {
                stack.push(val);
            }
        }
        return ans;


    }

    /**
     * Time complexity is O(m*n)
     * Space complexity is O(m)
     *
     * @param nums1
     * @param nums2
     * @return
     */

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        //https://www.youtube.com/watch?v=68a1Dc_qVq4
        //https://www.youtube.com/watch?v=mJWQjJpEMa4
        int len1 = nums1.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[len1];
        Arrays.fill(ans, -1);
        //O(m)
        for (int i = 0; i < len1; i++) {
            map.put(nums1[i], i);
        }
        //O(n*m) ---> in case where we have to go through the whole list looking for the value

        //Time complexity is O(m+n)

        for (int i = 0; i < nums2.length; i++) {
            if (!map.containsKey(nums2[i])) {
                continue;
            }

            for (int j = i + 1; j < nums2.length; j++) {
                if (nums2[j] > nums2[i]) {
                    ans[map.get(nums2[i])] = nums2[j];
                    break;
                }
            }
        }
        return ans;
    }


}
