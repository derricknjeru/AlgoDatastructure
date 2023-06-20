package CodingInterviewPatterns.Intervals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MeetingRooms {
    /**
     * Easy
     * Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei),
     * determine if a person could attend all meetings.
     * Example 1:
     * Input:
     * [[0,30],[5,10],[15,20]]
     * Output:
     * false
     * Example 2:
     * Input:
     * [[7,10],[2,4]]
     * <p>
     * Output:
     * true
     * Solution & Analysis
     * The idea here is to sort the meetings by starting time. Then, go through the meetings one by one and make sure that each meeting ends before the next one starts.
     * Time complexity : O(nlogn). The time complexity is dominated by sorting. Once the array has been sorted, only O(n) time is taken to go through the array and determine if there is any overlap.
     * Space complexity : O(1). Since no additional space is allocated.
     */
    //https://www.youtube.com/watch?v=PaJxqZVPhbg&ab_channel=NeetCode
    //https://aaronice.gitbook.io/lintcode/sweep-line/meeting-rooms
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

    public boolean canAttendMeetings(Interval[] intervals) {
        // Sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> a.start - b.start);

        // Check if there is any overlap between meetings
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end) {
                // There is an overlap, person cannot attend all meetings
                return false;
            }
        }

        // No overlap found, person can attend all meetings
        return true;
    }

    public boolean canAttendMeetings(List<Interval> intervals) {
        intervals.sort((a, b) -> a.start - b.start);

        for (int i = 0; i + 1 < intervals.size(); i++) {
            if (intervals.get(i).end > intervals.get(i + 1).start) {
                return false;
            }
        }
        return true;
    }

    public static boolean canAttendMeetings3(int[][] intervals) {
        // Sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Check if there is any overlap between meetings
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                // There is an overlap, person cannot attend all meetings
                return false;
            }
        }

        // No overlap found, person can attend all meetings
        return true;
    }

    public static void main(String[] args) {
        MeetingRooms meetingRooms = new MeetingRooms();

        // Example 1
        Interval[] intervals1 = {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
        boolean canAttend1 = meetingRooms.canAttendMeetings(intervals1);
        System.out.println("Can attend all meetings (Example 1): " + canAttend1);

        // Example 2
        Interval[] intervals2 = {new Interval(7, 10), new Interval(2, 4)};
        boolean canAttend2 = meetingRooms.canAttendMeetings(intervals2);
        System.out.println("Can attend all meetings (Example 2): " + canAttend2);
    }


}

