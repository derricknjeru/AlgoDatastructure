package CodingInterviewPatterns.Heaps;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;

public class TaskScheduler {
    //https://www.youtube.com/watch?v=s8p8ukTyA2I&t=23s&ab_channel=NeetCode

    //Time complexity is O(n * m)
    class Task {
        int count;
        int cooldown;

        Task(int count, int cooldown) {
            this.count = count;
            this.cooldown = cooldown;
        }
    }

    class Solution {
        public int leastInterval(char[] tasks, int n) {
            if (n == 0)
                return tasks.length;

            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            Queue<Task> cooldownQueue = new LinkedList<>();
            int[] count = new int[26];

            for (char c : tasks)
                count[c - 'A']++;

            for (int val : count) {
                if (val > 0)
                    pq.add(val);
            }

            int time = 0;

            while (!pq.isEmpty() || !cooldownQueue.isEmpty()) {
                time++;

                if (!pq.isEmpty()) {
                    int val = pq.poll();
                    if (val > 1)
                        cooldownQueue.add(new Task(val - 1, time + n));
                }

                if (!cooldownQueue.isEmpty() && cooldownQueue.peek().cooldown == time)
                    pq.add(cooldownQueue.poll().count);
            }

            return time;
        }
    }
}
