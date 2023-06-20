package CodingInterviewPatterns.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        Solution solution = new Solution();
        int[][] mergedIntervals1 = solution.merge(intervals1);
        System.out.println(Arrays.deepToString(mergedIntervals1));
        // Output: [[1, 6], [8, 10], [15, 18]]

        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] mergedIntervals2 = solution.merge(intervals2);
        System.out.println(Arrays.deepToString(mergedIntervals2));
        // Output: [[1, 5]]
    }

    //https://www.youtube.com/watch?v=44H3cEC2fFM&ab_channel=NeetCode
    static class Solution {
        public int[][] merge(int[][] intervals) {
            List<int[]> mergedIntervals = new ArrayList<>();
            // Arrays.sort(intervals, (a, b)-> a[0] - b[0]); faster
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            //Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); faster

            for (int[] interval : intervals) {
                int lastIndex = mergedIntervals.size() - 1;
                if (mergedIntervals.isEmpty() || interval[0] > mergedIntervals.get(lastIndex)[1]) {
                    mergedIntervals.add(interval);
                } else {
                    //There is an overlap, update the mergedIntervals list
                    mergedIntervals.get(lastIndex)[1] =
                            Math.max(mergedIntervals.get(lastIndex)[1], interval[1]);
                }

            }

            return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);

        }
    }
}
