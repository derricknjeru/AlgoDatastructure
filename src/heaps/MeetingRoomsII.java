package heaps;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Definition of Interval:
 */
class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}


public class MeetingRoomsII {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        // Write your code here
      //  Collections.sort(intervals, Comparator.comparingInt(a -> a.start));
        Collections.sort(intervals, (a,b) -> a.start -a.end);

        for (int i = 0; i < intervals.size() - 1; i++) {
            if (intervals.get(i + 1).start > intervals.get(i).end) {
                return false;
            }
        }
        return true;
    }
}