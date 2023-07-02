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

            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            //Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

            List<int[]> res = new ArrayList<>();

            for (int[] interval : intervals) {
                if (res.isEmpty() || res.get(res.size() - 1)[1] < interval[0]) {
                    res.add(interval);
                } else {
                    //There is an overlap
                    int lastIndex = res.size() - 1;
                    res.get(lastIndex)[0] = Math.min(res.get(lastIndex)[0], interval[0]);
                    res.get(lastIndex)[1] = Math.max(res.get(lastIndex)[1], interval[1]);
                }
            }

            return res.toArray(new int[res.size()][2]);

        }
    }
}
