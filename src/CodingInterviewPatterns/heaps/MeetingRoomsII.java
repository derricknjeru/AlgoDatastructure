package CodingInterviewPatterns.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Definition of Interval:
 */

public class MeetingRoomsII {
    public static void main(String[] args) {

        int[][] intervals2 = {{0, 30}, {5, 10}, {15, 20}};

        int[][] intervals = {{7, 10}, {2, 4}};

        int[][] intervals3 = {{13, 15}, {1, 13}};

        System.out.println(minMeetingRooms(intervals));
    }

    /**
     * Given an array of meeting time intervals, where interval[i] = [start,end]
     * return the minimum number of conference rooms required
     * Input:
     * [[0,30],[5,10],[15,20]]
     * Output:
     * false
     * <p>
     * <p>
     * Input:
     * [[7,10],[2,4]]
     * <p>
     * Output:
     * true
     */
    //https://www.youtube.com/watch?v=uOTJITjfz9g
    public static int minMeetingRooms(int[][] intervals) {
        //sort array by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        //minheap to keep track of end time.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);

        //add first end time
        minHeap.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            //prev end is less than curr start
            if (minHeap.peek() <= curr[0]) {
                minHeap.poll(); //cos we don't wanna create any meeting
            }

            minHeap.add(curr[1]);

        }

        return minHeap.size();
    }
}
