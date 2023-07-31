package Grind75.Week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
    //https://www.youtube.com/watch?v=A8NUOmlwOlM&ab_channel=NeetCode
    //https://www.youtube.com/watch?v=FuLfL_WhUHI&ab_channel=KnowledgeCenter
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        int[][] result1 = solution.insert(intervals1, newInterval1);
        System.out.println(Arrays.deepToString(result1)); // [[1, 5], [6, 9]]

        // Example 2
        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        int[][] result2 = solution.insert(intervals2, newInterval2);
        System.out.println(Arrays.deepToString(result2)); // [[1, 2], [3, 10], [12, 16]]
    }

    static class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            //The time complexity of the given code is O(n),
            // where n is the number of intervals in the input array.
            List<int[]> result = new ArrayList<>();

            // Add intervals before newInterval that end before it starts
            for (int[] interval : intervals) {
                if (interval[1] < newInterval[0]) {
                    // Current interval ends before newInterval starts, add it to the result
                    result.add(interval);
                } else if (interval[0] > newInterval[1]) {
                    // Current interval starts after newInterval ends, add newInterval and update it
                    result.add(newInterval);
                    newInterval = interval;
                } else {
                    // There is an overlap between the current interval and newInterval, merge them
                    newInterval[0] = Math.min(newInterval[0], interval[0]);
                    newInterval[1] = Math.max(newInterval[1], interval[1]);
                }
            }

            result.add(newInterval); // Add the merged newInterval

            return result.toArray(new int[result.size()][2]);
        }
    }


    class Solution2 {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> result = new ArrayList<>();
            int i = 0;

            // Add intervals before newInterval that end before it starts
            while (i < intervals.length && intervals[i][1] < newInterval[0]) {
                result.add(intervals[i]);
                i++;
            }

            // Merge intervals that overlap with newInterval
            while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
                i++;
            }

            result.add(newInterval); // Add the merged newInterval

            // Add remaining intervals
            while (i < intervals.length) {
                result.add(intervals[i]);
                i++;
            }

            return result.toArray(new int[result.size()][2]);
        }
    }


}


