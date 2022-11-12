package arrays_and_strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class LongestConsecutiveSequence {
    //https://www.geeksforgeeks.org/longest-consecutive-subsequence/
    //https://leetcode.com/problems/longest-consecutive-sequence/

    public int longestConsecutive(int[] nums) {
        /**
         * Complexity Analysis
         *
         * Time complexity : O(n)
         *
         * Although the time complexity appears to be quadratic due to the while loop nested within the for loop,
         * closer inspection reveals it to be linear.
         * Because the while loop is reached only when currentNum marks the beginning of a sequence
         * (i.e. currentNum-1 is not present in nums), the while loop can only run for nn iterations
         * throughout the entire runtime of the algorithm.
         * This means that despite looking like O(n⋅n) complexity, the nested loops actually run in O(n + n) = O(n) time.
         * All other computations occur in constant time, so the overall runtime is linear.
         *
         * Space complexity : O(n)
         *
         * In order to set up O(1) containment lookups,
         * we allocate linear space for a hash table to store the O(n) numbers in nums.
         * Other than that, the space complexity is identical to that of the brute force solution.
         */
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

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
                } else {
                    maxStreak = Math.max(maxStreak, currentStreak);
                    currentStreak = 1;
                }
            }
            prev = pq.poll();
        }

        return Math.max(maxStreak, currentStreak);

    }


}