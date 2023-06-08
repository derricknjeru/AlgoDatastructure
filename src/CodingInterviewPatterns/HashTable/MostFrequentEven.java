package CodingInterviewPatterns.HashTable;

import java.util.HashMap;
import java.util.Map;

public class MostFrequentEven {
    //https://leetcode.com/problems/most-frequent-even-element/description/
    public int mostFrequentEven(int[] nums) {
        int maxCount = 0;
        int smallestEvenElement = -1;
        int[] occurrenceArray = new int[100001]; // Assuming maximum value in nums is 10^5

        for (int num : nums) {
            if (num % 2 == 0) {
                occurrenceArray[num]++;
                if (occurrenceArray[num] > maxCount || (occurrenceArray[num] == maxCount && num < smallestEvenElement)) {
                    maxCount = occurrenceArray[num];
                    smallestEvenElement = num;
                }
            }
        }

        return smallestEvenElement;
    }

    public int mostFrequentEven2(int[] nums) {
        Map<Integer, Integer> occurrenceMap = new HashMap<>();

        for (int num : nums) {
            if (num % 2 == 0) {
                occurrenceMap.put(num, occurrenceMap.getOrDefault(num, 0) + 1);
            }
        }

        int maxCount = 0;
        int smallestEvenElement = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : occurrenceMap.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();

            if (count > maxCount || (count == maxCount && num < smallestEvenElement)) {
                maxCount = count;
                smallestEvenElement = num;
            }
        }

        return maxCount > 0 ? smallestEvenElement : -1;
    }


    public int mostFrequentEven3(int[] nums) {
        Map<Integer, Integer> occurrenceMap = new HashMap<>();

        for (int num : nums) {
            if (num % 2 == 0) {
                occurrenceMap.put(num, occurrenceMap.getOrDefault(num, 0) + 1);
            }
        }

        int maxCount = 0;
        int smallestEvenElement = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : occurrenceMap.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();

            if (count > maxCount || (count == maxCount && num < smallestEvenElement)) {
                maxCount = count;
                smallestEvenElement = num;
            }
        }

        return maxCount > 0 ? smallestEvenElement : -1;
    }


}
