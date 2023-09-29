package Grind75.Week6;

import java.util.*;

public class SubsetGenerator {
    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        generateUniqueSubsets(arr);
    }

    public static void generateUniqueSubsets(int[] arr) {
        //Arrays.sort(arr); // Sort the input array to handle duplicates
        List<Integer> subset = new ArrayList<>();
        generateUniqueSubsetsHelper(arr, subset, 0);
    }

    public static void generateUniqueSubsetsHelper(int[] arr, List<Integer> subset, int currentIndex) {
        printSubset(subset);

        for (int i = currentIndex; i < arr.length; i++) {
            if (i > currentIndex && arr[i] == arr[i - 1]) {
                continue; // Skip duplicate elements
            }

            subset.add(arr[i]);
            generateUniqueSubsetsHelper(arr, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }

    public static void printSubset(List<Integer> subset) {
        System.out.print("{ ");
        for (int i = 0; i < subset.size(); i++) {
            System.out.print(subset.get(i) + " ");
        }
        System.out.println("}");
    }
}

