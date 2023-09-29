package Grind75.Week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitArrayToEqualSumSubArrays {
    public static void main(String[] args) {
        System.out.println(split(List.of(1, 2, 3)));
        //Testing list of
        List.of(1, 2, 1);
        int[] arr = {1, 3, 5};
        List.of(Arrays.asList(arr));
    }

    private static List<List<Integer>> split(List<Integer> list) {
        int totalSum = 0;
        for (int a : list) totalSum += a;

        if (totalSum % 2 != 0) return new ArrayList<>();
        int targetSum = totalSum / 2;
        int runningSum = 0;

        for (int i = 0; i < list.size(); i++) {
            runningSum += list.get(i);
            if (runningSum == targetSum) {
                return List.of(list.subList(0, i + 1), list.subList(i + 1, list.size()));
            }
        }
        return null;
    }
}
