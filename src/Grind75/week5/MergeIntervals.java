package Grind75.week5;

import java.util.ArrayList;
import java.util.Arrays;
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
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            List<int[]> res = new ArrayList<>();

            for (int[] interval : intervals) {
                int lastIndex = res.size() - 1;
                if (res.isEmpty() || res.get(lastIndex)[1] < interval[0]) {
                    // No overlap, add the interval as it is
                    res.add(interval);
                } else {
                    // Merge the overlapping intervals, We only need to care about tje end time
                    res.get(lastIndex)[1] = Math.max(res.get(lastIndex)[1], interval[1]);
                }
            }

            int[][] result = new int[res.size()][2];
            for (int i = 0; i < res.size(); i++) {
                result[i] = res.get(i);
            }

            return result;
        }
    }

    class Solution2 {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            List<int[]> res = new ArrayList<>();

            for (int[] interval : intervals) {
                int lastIndex = res.size() - 1;
                if (res.isEmpty() || res.get(lastIndex)[1] < interval[0]) {
                    // No overlap, add the interval as it is
                    res.add(interval);
                } else {
                    // Merge the overlapping intervals, We only need to care about tje end time
                    res.get(lastIndex)[1] = Math.max(res.get(lastIndex)[1], interval[1]);
                }
            }

            return res.toArray(new int[res.size()][2]);
        }
    }
}
