package CodingInterviewPatterns.Greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class HandOfStraights {
    //https://www.youtube.com/watch?v=amnrMCVd2YI
    //https://leetcode.com/problems/hand-of-straights/description/

    class Solution {
        public boolean isNStraightHand(int[] hand, int groupSize) {
            if (hand.length % groupSize != 0) {
                return false;
            }

            Map<Integer, Integer> count = new HashMap<>();
            for (int n : hand) {
                count.put(n, count.getOrDefault(n, 0) + 1);
            }

            PriorityQueue<Integer> minH = new PriorityQueue<>(count.keySet());
            while (!minH.isEmpty()) {
                int first = minH.peek();
                for (int i = first; i < first + groupSize; i++) {
                    if (!count.containsKey(i)) {
                        return false;
                    }
                    count.put(i, count.get(i) - 1);
                    if (count.get(i) == 0) {
                        if (i != minH.peek()) {
                            return false;
                        }
                        minH.poll();
                    }
                }
            }
            return true;
        }
    }

    class Solution2 {
        public boolean isNStraightHand(int[] hand, int groupSize) {
            if (hand.length % groupSize != 0) {
                return false;
            }

            TreeMap<Integer, Integer> count = new TreeMap<>();
            for (int n : hand) {
                count.put(n, count.getOrDefault(n, 0) + 1);
            }

            while (!count.isEmpty()) {
                int first = count.keySet().iterator().next();
                for (int i = first; i < first + groupSize; i++) {
                    if (!count.containsKey(i)) {
                        return false;
                    }
                    count.put(i, count.get(i) - 1);
                    if (count.get(i) == 0) {
                        int first1 = count.keySet().iterator().next();
                        if (i != first1) {
                            return false;
                        }
                        count.remove(i);
                    }
                }
            }
            return true;
        }
    }
}
