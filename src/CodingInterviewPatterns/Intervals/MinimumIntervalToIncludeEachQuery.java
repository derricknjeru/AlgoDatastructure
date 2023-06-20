package CodingInterviewPatterns.Intervals;

import java.util.*;

public class MinimumIntervalToIncludeEachQuery {
    //https://www.youtube.com/watch?v=5hQ5WWW5awQ
    //https://leetcode.com/problems/minimum-interval-to-include-each-query/

    class Solution {
        //Time complexity is O(nlogn + qlogq) n length of intervals + q length of queries. It is mostly from sorting
        public int[] minInterval(int[][] intervals, int[] queries) {
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            Map<Integer, Integer> res = new HashMap<>();
            int i = 0;

            int[] sortedQueries = Arrays.copyOf(queries, queries.length); //since we need to keep order of query
            Arrays.sort(sortedQueries);

            for (int q : sortedQueries) {
                while (i < intervals.length && intervals[i][0] <= q) {
                    int l = intervals[i][0];
                    int r = intervals[i][1];
                    minHeap.offer(new int[]{r - l + 1, r});
                    i++;
                }

                while (!minHeap.isEmpty() && minHeap.peek()[1] < q) {
                    minHeap.poll();
                }

                res.put(q, !minHeap.isEmpty() ? minHeap.peek()[0] : -1);
            }

            int[] result = new int[queries.length];
            for (int j = 0; j < queries.length; j++) {
                result[j] = res.get(queries[j]);
            }

            return result;
        }
    }
}
