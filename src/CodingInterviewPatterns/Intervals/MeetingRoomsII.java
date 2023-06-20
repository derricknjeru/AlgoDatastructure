package CodingInterviewPatterns.Intervals;

import java.util.*;

public class MeetingRoomsII {
//https://www.youtube.com/watch?v=FdzJmTCVyJU

    /**
     * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
     * return the minimum number of conference rooms required.
     * <p>
     * Example 1:
     * <p>
     * Input: intervals = [[0,30],[5,10],[15,20]]
     * Output: 2
     * Example 2:
     * <p>
     * Input: intervals = [[7,10],[2,4]]
     * Output: 1
     * Constraints:
     * <p>
     * 1 <= intervals.length <= 104
     * 0 <= starti < endi <= 106
     */
    public static class Solution {
        public static class Interval {
            int start;
            int end;

            Interval() {
                start = 0;
                end = 0;
            }

            Interval(int s, int e) {
                start = s;
                end = e;
            }
        }

        public int minMeetingRooms(List<Interval> intervals) {
            intervals.sort((a, b) -> a.start - b.start); // Sort intervals based on start times

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            minHeap.offer(intervals.get(0).end); // Add the first meeting's end time to the priority queue

            for (int i = 1; i < intervals.size(); i++) {
                Interval currInterval = intervals.get(i);

                if (currInterval.start >= minHeap.peek()) {
                    minHeap.poll(); // Reuse the existing room
                }

                minHeap.offer(currInterval.end); // Add new meeting end time to the priority queue
            }

            return minHeap.size();
        }

        public static void main(String[] args) {
            List<Interval> intervals1 = new ArrayList<>();
            intervals1.add(new Interval(0, 30));
            intervals1.add(new Interval(5, 10));
            intervals1.add(new Interval(15, 20));

            Solution solution = new Solution();
            System.out.println("Minimum rooms required: " + solution.minMeetingRooms(intervals1)); // Output: 2

            List<Interval> intervals2 = new ArrayList<>();
            intervals2.add(new Interval(7, 10));
            intervals2.add(new Interval(2, 4));
            System.out.println("Minimum rooms required: " + solution.minMeetingRooms(intervals2)); // Output: 1
        }
    }

}
