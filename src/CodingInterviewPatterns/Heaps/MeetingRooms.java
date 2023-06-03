package CodingInterviewPatterns.Heaps;

import java.util.Arrays;

public class MeetingRooms {
    public static void main(String[] args) {

        int[][] intervals2 = {{0, 30}, {5, 10}, {15, 20}};

        int[][] intervals = {{7, 10}, {2, 4}};

        int[][] intervals3 = {{13, 15}, {1, 13}};

        System.out.println(canAttendMeetings(intervals3));

    }
    /**
     * Given an array of meeting time intervals, where interval[i] = [start,end] determine if a person could attend all meetings.
     *
     * Input:
     * [[0,30],[5,10],[15,20]]
     * Output:
     *  false
     *
     *
     *  Input:
     *  [[7,10],[2,4]]
     *
     * Output:
     *  true
     */


    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */

    //https://www.youtube.com/watch?v=uOTJITjfz9g
    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < intervals.length - 1; i++) {
            int[] interval1 = intervals[i];
            int[] interval2 = intervals[i + 1];
            int start = interval2[0];
            int end = interval1[1];
            if (end > start) {
                return false;
            }
        }

        return true;
    }
}