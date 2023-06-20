package CodingInterviewPatterns.Greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class DivideArrayInSetsOfKConsecutiveNumbers {
    //https://www.youtube.com/watch?v=amnrMCVd2YI
    //https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/description/
    class Solution {
        public boolean isPossibleDivide(int[] nums, int k) {
            if (nums.length % k != 0) {
                return false;
            }

            Map<Integer, Integer> count = new HashMap<>();
            for (int n : nums) {
                count.put(n, count.getOrDefault(n, 0) + 1);
            }

            PriorityQueue<Integer> minH = new PriorityQueue<>(count.keySet());
            while (!minH.isEmpty()) {
                int first = minH.peek();
                for (int i = first; i < first + k; i++) {
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
        public boolean isPossibleDivide(int[] nums, int k) {
            if (nums.length % k != 0) {
                return false;
            }

            Map<Integer, Integer> count = new TreeMap<>();
            for (int n : nums) {
                count.put(n, count.getOrDefault(n, 0) + 1);
            }

            while (!count.isEmpty()) {
                int first = count.keySet().iterator().next();
                for (int i = first; i < first + k; i++) {
                    if (!count.containsKey(i)) {
                        return false;
                    }
                    count.put(i, count.get(i) - 1);
                    if (count.get(i) == 0) {
                        count.remove(i);
                    }
                }
            }
            return true;
        }
    }
}
