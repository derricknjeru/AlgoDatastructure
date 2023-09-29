package CodingInterviewPatterns.HashTable;

import java.util.HashMap;
import java.util.Map;

public class MostFrequentInteger {
    //https://interviewing.io/questions/most-frequent-element-in-an-array
    //https://interviewing.io/mocks/google-java-most-frequent-integer-and-pairs-of-integers-sum
    public static void main(String[] args) {
        /**
         * Given an array of integers, Find the most frequent integer.
         * Write a method that takes an array of integers and returns an integer.
         *
         * {1,2,3,3} => 3
         * {1,2,3} => 1, or(2,3)
         * {3,1,4,57,4} => 4
         */

        int[] arr = {1, 2, 3, 3}; // 3
        System.out.println(findMostFrequent(arr));

        int[] arr2 = {1, 2, 3,}; //1 or 2 or 3
        System.out.println(findMostFrequent(arr2));

        int[] arr3 = {3, 1, 4, 57, 4}; //4
        System.out.println(findMostFrequent(arr3));
    }

    private static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> occ = new HashMap<>();
        int mostFreq = arr[0];
        int maxCount = Integer.MIN_VALUE;
        for (int a : arr) {
            int count = occ.getOrDefault(a, 0) + 1;
            occ.put(a, count);
            if (maxCount < count) {
                mostFreq = a;
                maxCount = count;
            }
        }
        return mostFreq;
    }
}
