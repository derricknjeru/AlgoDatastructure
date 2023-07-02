package CodingInterviewPatterns.stack_and_queue.quickreview;

import java.io.*;
import java.util.*;

public class MovingAverageQuiz {
    //https://www.youtube.com/watch?v=E-kjYOZEBxY

    /**
     * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
     * <p>
     * For example,
     * MovingAverage m = new MovingAverage(3);
     * m.next(1) = 1
     * m.next(10) = (1 + 10) / 2
     * m.next(3) = (1 + 10 + 3) / 3
     * m.next(5) = (10 + 3 + 5) / 3
     */
    public static class MovingAverage {
        Queue<Integer> q;
        int maxSize = 0;
        double sum = 0.0;

        /*
         * @param size: An integer
         */
        public MovingAverage(int size) {
            // do intialization if necessary
            q = new LinkedList<>();
            maxSize = size;
        }

        /*
         * @param val: An integer
         * @return:
         */
        public double next(int val) {
            // write your code here
            if (q.size() >= maxSize) {
                sum -= q.poll();
            }
            q.add(val);
            sum += val;
            double average = sum / q.size();
            return average;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(read.readLine());
        MovingAverage obj = new MovingAverage(size);
        PrintWriter out = new PrintWriter(System.out);
        while (read.ready()) {
            int val = Integer.parseInt(read.readLine());
            double avg = obj.next(val);

            StringBuilder ans = new StringBuilder(String.format("%.5f", avg));
            while (ans.charAt(ans.length() - 2) != '.' && ans.charAt(ans.length() - 1) == '0') {
                ans.deleteCharAt(ans.length() - 1);
            }
            out.println(ans);
        }
        out.close();
    }
}

