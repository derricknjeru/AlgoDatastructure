package revision.strings_and_arrays;

import java.util.Arrays;
import java.util.HashMap;

public class FindPairOfSum {
    public static void main(String[] args) {
        int[] arr = {0, -1, 2, -3, 1};
        //int[] arr  = {1, -2, 1, 0, 5};
        int sum = -2;
        //int sum = 0;

        find1(arr, sum);


    }

    /**
     * Given an array A[] and a number x, check for pair in A[] with sum as x
     * Last Updated: 28-10-2020
     * Write a program that, given an array A[] of n numbers and another number x, determines whether or not there exist two elements in S whose sum is exactly x.
     * Examples:
     * <p>
     * Input: arr[] = {0, -1, 2, -3, 1}
     * sum = -2
     * Output: -3, 1
     * If we calculate the sum of the output,
     * 1 + (-3) = -2
     * <p>
     * Input: arr[] = {1, -2, 1, 0, 5}
     * sum = 0
     * Output: -1
     * No valid pair exists.
     */
    //Time complexity is O(n)(linear time) and space complexity is linear space O(n).
    
    private static void find(int[] arr, int sum) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int index = 0;
        assert arr != null;

        for (int a : arr) {

            int value = sum - a;

            if (map.containsValue(value)) {
                System.out.printf("The pairs are %d and %d ", a, value);
            }
            map.put(index++, a);
        }


    }

    /**
     * Time complexity is O(nlogn) and space complexity is constant space O(1).
     * @param arr
     * @param sum
     */
    private static void find1(int[] arr, int sum) {
        Arrays.sort(arr);
        int i = 0;
        int j = arr.length - 1;
        // 0, -1, 2, -3, 1
        //-3, -1, 0, 1 ,2
        while (i < j) {
            if (arr[i] + arr[j] == sum) {
                System.out.printf("The pairs are %d and %d ", arr[i], arr[j]);
                return;
            } else if (arr[i] + arr[j] > sum) {
                j--;
            } else {
                i++;
            }
        }


    }

}
