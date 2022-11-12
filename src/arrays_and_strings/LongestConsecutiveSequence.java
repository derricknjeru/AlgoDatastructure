package arrays_and_strings;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LongestConsecutiveSequence {
    //https://www.geeksforgeeks.org/longest-consecutive-subsequence/
    //https://leetcode.com/problems/longest-consecutive-sequence/

    public int longestConsecutive2(int[] nums) {
        /**
         * Complexity Analysis
         *
         * Time complexity : O(nlgn).
         *
         * The main for loop does constant work nn times, so the algorithm's
         * time complexity is dominated by the invocation of sort,
         * which will run in O(nlgn) time for any sensible implementation.
         *
         * Space complexity : O(1) or O(n).
         *
         * For the implementations provided here,
         * the space complexity is constant because we sort the input array in place.
         * If we are not allowed to modify the input array, we must spend linear space to store a sorted copy.
         */
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    currentStreak += 1;
                } else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }

    public int longestConsecutive3(int[] nums) {
        /**
         * Time Complexity: O(N*log(N)), Time required to push and pop N elements is logN for each element.
         * Auxiliary Space: O(N), Space required by priority queue to store N elements.
         */
        if (nums.length == 0) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int a : nums) {
            pq.add(a);
        }

        int currentStreak = 1;

        int maxStreak = 1;

        int prev = pq.poll(); //It won't be null, we have checked

        while (!pq.isEmpty()) {
            if (pq.peek() != prev) {
                if (pq.peek() == prev + 1) {
                    currentStreak++;
                    prev = pq.poll();
                } else {
                    maxStreak = Math.max(maxStreak, currentStreak);
                    currentStreak = 1;
                    prev = pq.poll();
                }
            } else {
                prev = pq.poll();
            }
        }

        return Math.max(maxStreak, currentStreak);

    }


}
