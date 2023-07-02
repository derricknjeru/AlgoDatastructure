package CodingInterviewPatterns.Heaps;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;

public class TaskScheduler {
    //https://www.youtube.com/watch?v=s8p8ukTyA2I&t=23s&ab_channel=NeetCode
    //https://www.youtube.com/watch?v=s8p8ukTyA2I

    /**
     * The code you provided is an implementation of the "leastInterval" method in the "Solution" class. It takes an array of tasks represented by characters and an integer "n" as parameters. The goal of this method is to determine the minimum time required to complete all the tasks, given that there must be at least "n" units of cooldown time between two consecutive occurrences of the same task.
     * <p>
     * The code uses a priority queue (pq) to store the frequency counts of each task in descending order. The priority queue is implemented using a binary heap, which provides efficient insertion and removal of elements with the highest priority.
     * <p>
     * The algorithm then proceeds with a simulation of task execution. It iterates until both the priority queue and the cooldown queue are empty. In each iteration, it increments the time by 1.
     * <p>
     * Within the loop, the algorithm checks if the priority queue is not empty. If so, it removes the task with the highest frequency count from the priority queue. If the count of the task is greater than 1, it creates a new task with a decremented count and a cooldown time (current time + n), and adds it to the cooldown queue.
     * <p>
     * Next, the algorithm checks if the cooldown queue is not empty and if the task at the front of the cooldown queue has reached its cooldown time. If both conditions are met, it removes the task from the cooldown queue and adds it back to the priority queue.
     * <p>
     * The algorithm continues this process until both queues are empty. Finally, it returns the total time taken for task execution.
     * <p>
     * Regarding the time complexity of this algorithm, it depends on the input size and the value of "n". Let's analyze it step by step:
     * <p>
     * Building the count array: This step has a time complexity of O(tasks.length) since it iterates through the "tasks" array once.
     * <p>
     * Building the priority queue: The algorithm adds the frequency counts to the priority queue, which has a maximum size of 26 (since there are 26 letters in the English alphabet). The time complexity for building the priority queue is O(1) since it does not depend on the input size.
     * <p>
     * Simulation loop: In each iteration, the algorithm performs constant time operations such as removing from the priority queue, adding to the cooldown queue, and checking the cooldown queue. The number of iterations depends on the total number of tasks and the number of cooldown periods. In the worst case, when there are no cooldown periods (n = 0), the loop will iterate tasks.length times. Therefore, the time complexity of the simulation loop is O(tasks.length).
     * <p>
     * Overall, the time complexity of the algorithm is O(tasks.length) because the dominant factor is the simulation loop, which iterates through the tasks array.
     */
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

    class Solution2 {
        public int leastInterval(char[] tasks, int n) {
            if (n == 0)
                return tasks.length;

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
            Queue<int[]> cooldownQueue = new LinkedList<>();
            int[] count = new int[26];

            for (char c : tasks)
                count[c - 'A']++;

            for (int val : count) {
                if (val > 0)
                    maxHeap.add(val);
            }

            int time = 0;

            while (!maxHeap.isEmpty() || !cooldownQueue.isEmpty()) {
                time++;

                if (!maxHeap.isEmpty()) {
                    int val = maxHeap.poll();
                    if (val > 1)
                        cooldownQueue.add(new int[]{val - 1, time + n});
                }

                if (!cooldownQueue.isEmpty() && cooldownQueue.peek()[1] == time)
                    maxHeap.add(cooldownQueue.poll()[0]);
            }

            return time;
        }
    }
}
