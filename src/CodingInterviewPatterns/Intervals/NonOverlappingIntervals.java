package CodingInterviewPatterns.Intervals;

import java.util.Arrays;

public class NonOverlappingIntervals {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int minIntervalsToRemove1 = solution.eraseOverlapIntervals(intervals1);
        System.out.println("Example 1: Minimum intervals to remove = " + minIntervalsToRemove1);
        // Output: Example 1: Minimum intervals to remove = 1
        // Explanation: The interval [1,3] can be removed to make the rest of the intervals non-overlapping.
        // The intervals after removing [1,3] are [[1,2],[2,3],[3,4]], which are non-overlapping.

        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        int minIntervalsToRemove2 = solution.eraseOverlapIntervals(intervals2);
        System.out.println("Example 2: Minimum intervals to remove = " + minIntervalsToRemove2);
        // Output: Example 2: Minimum intervals to remove = 2
        // Explanation: All three intervals [1,2] overlap with each other.
        // To make the intervals non-overlapping, we need to remove at least two intervals.

        int[][] intervals3 = {{1, 2}, {2, 3}};
        int minIntervalsToRemove3 = solution.eraseOverlapIntervals(intervals3);
        System.out.println("Example 3: Minimum intervals to remove = " + minIntervalsToRemove3);
        // Output: Example 3: Minimum intervals to remove = 0
        // Explanation: The given intervals [[1,2],[2,3]] are already non-overlapping.
        // No intervals need to be removed.
    }

    static class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // Sort intervals based on end time


            int prevEnd = intervals[0][1];
            int count = 0;

            for (int i = 1; i < intervals.length; i++) {
                int start = intervals[i][0];
                int end = intervals[i][1];

                if (start < prevEnd) { // Overlapping interval found
                    count++;
                    prevEnd = Math.min(prevEnd, end); // Update end time to the minimum of the current end time and the overlapping interval's end time
                } else {
                    prevEnd = end; // Update end time
                }
            }

            return count;
        }
    }

}
