package CodingInterviewPatterns.Intervals;

import java.util.ArrayList;
import java.util.List;

public class IntervalsGoogle {
//https://leetcode.com/discuss/interview-question/1718834/Google-Interview-question-2022

    /**
     * a) Given on-call rotation schedule for multiple people by: their name, start time and end time of the rotation:
     *
     * Abby 1 10
     * Ben 5 7
     * Carla 6 12
     * David 15, 17
     *
     * and given t = 9, return a list of names who are on call at time t.
     *
     * Expected output: [Abby, Carla]
     */
    static class Interval {
        String name;
        int startTime, endTime;

        Interval(String name, int startTime, int endTime) {
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static List<String> getOnCallNames(List<Interval> schedule, int t) {
        List<String> onCallNames = new ArrayList<>();

        for (Interval interval : schedule) {
            if (t >= interval.startTime && t <= interval.endTime) {
                onCallNames.add(interval.name);
            }
        }

        return onCallNames;
    }

    public static void main(String[] args) {
        List<Interval> schedule = new ArrayList<>();
        schedule.add(new Interval("Abby", 1, 10));
        schedule.add(new Interval("Ben", 5, 7));
        schedule.add(new Interval("Carla", 6, 12));
        schedule.add(new Interval("David", 15, 17));

        int t = 9;

        List<String> onCallNames = getOnCallNames(schedule, t);

        System.out.println("On call at time " + t + ":");
        System.out.println(onCallNames);
    }


}
