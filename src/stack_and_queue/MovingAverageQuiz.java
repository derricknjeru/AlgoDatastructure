package stack_and_queue;

import java.io.*;
import java.util.*;

public class MovingAverageQuiz {
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

