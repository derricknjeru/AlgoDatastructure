package CodingInterviewPatterns.HashTable;

import java.util.*;

public class PairsOfIntegersSum {
    public static void main(String[] args) {
        /**
         Given an array of non-negative integers and a positive integer n.
         print out all the pairs of integers whose sum is n.
         {1,2,9,13}, n = 10 ans = 1,9
         {1,9,1,9} , n=10 ans = 1,9 | 1,9
         {1,9,1} , n = 10 ans = 1,9
         */
        int n = 10;
        int[] arr = {1, 2, 9, 12}; // 1,9
        for (int[] res : getPair(arr, n)) System.out.println(Arrays.toString(res));
        System.out.println("-----------------");

        printPairsWithSum(arr, n);
        System.out.println("-----------------");

        int[] arr2 = {1, 9, 1, 9}; //1,9 | 1,9
        for (int[] res : getPair(arr2, n)) System.out.println(Arrays.toString(res));
        System.out.println("-----------------");

        printPairsWithSum(arr2, n);
        System.out.println("-----------------");

        int[] arr3 = {1, 9, 1}; //4
        for (int[] res : getPair(arr3, n)) System.out.println(Arrays.toString(res));
        System.out.println("-----------------");

        printPairsWithSum(arr3, n);
        System.out.println("-----------------");

    }

    /**
     * Time and space complexity is O(n)
     *
     * @param arr
     * @param n
     * @return
     */
    private static List<int[]> getPair(int[] arr, int n) {
        List<int[]> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int a : arr) {
            int comp = n - a;
            if (set.contains(comp)) {
                res.add(new int[]{comp, a});
                set.remove(comp);
            } else {
                set.add(a);
            }
        }
        return res;
    }

    public static void printPairsWithSum(int[] nums, int targetSum) {
        HashMap<Integer, Integer> numCount = new HashMap<>();

        for (int num : nums) {
            int complement = targetSum - num;

            if (numCount.containsKey(complement) && numCount.get(complement) > 0) {
                System.out.println(complement + ", " + num);
                numCount.put(complement, numCount.get(complement) - 1);
            } else {
                numCount.put(num, numCount.getOrDefault(num, 0) + 1);
            }
        }
    }


}
