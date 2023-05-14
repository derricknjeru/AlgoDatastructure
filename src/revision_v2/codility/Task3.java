package revision_v2.codility;

import java.util.HashMap;
import java.util.Map;

public class Task3 {
    public static void main(String[] args) {
        /**
         * An array A consisting of N Integers is given. We are looking for pairs of elements of the array that are equal but that occupy different positions in the array. More formally, a pair of indices (P.Q) is called identical if 0<=P<Q< N and A(P) = A(Q). The goal is to calculate the number of identical pairs of indices.
         *
         * For example, consider array A such that:
         *
         * A[0] = 3
         *
         * A[1] = 5
         *
         * A[2] = 6
         *
         * A[3] = 3
         *
         * A[4] = 3
         *
         * A[5] = 5
         *
         * There are four pairs of identical indices: (0, 3), (0, 4), (1, 5) and (3, 4). Note.md.md that pairs (2 2) and (5, 1) are not counted since their first indices are not smaller than their second.
         *
         * Write a function:
         *
         * class Solution { public int solution{ int[] A); }
         *
         * that, given an array A of N integers, returns the number of identical pairs of indices.
         *
         * If the number of identical pairs of indices is greater than 1,000,000,000, the function should return 1,000,000,000.
         *
         * For example, given:
         *
         * A[0] 3
         *
         * A[1] - 5
         *
         * A[2] = 6
         *
         * A[3] = 3
         *
         * A[4] - 3
         *
         * A[5] - 5
         *
         * the function should return 4, as explained above.
         *
         * Write an efficient algorithm for the following assumptions:
         *
         * • N is an integer within the range (0..100,000):
         *
         * • each element of array A is an integer within the range (-1,000,000,000.1,000,000,000).
         *
         * Copyright 2009-2020 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
         */

        int[] A = {3, 5, 6, 3, 3, 5}; //(0, 3), (0, 4), (1, 5) and (3, 4)

        int[] A2 = {3, 7, 6, 6, 3, 5}; // 0,4...,2,3....


        System.out.println(solution(A));
    }

    public static int solution(int[] A) {
        int ans = 0;
        int n = A.length;
        // for each index i and j
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)

                // finding the index with same
                // value but different index.
                if (A[i] == A[j])
                    ans++;
        return ans;
    }

    public static int solution2(int[] A) {
        //A method to return number of pairs with
        // equal values

        HashMap<Integer, Integer> hm = new HashMap<>();

        int n = A.length;

        // Finding frequency of each number.
        for (int i = 0; i < n; i++) {
            if (hm.containsKey(A[i]))
                hm.put(A[i], hm.get(A[i]) + 1);
            else
                hm.put(A[i], 1);
        }
        int ans = 0;

        // Calculating count of pairs with equal values
        for (Map.Entry<Integer, Integer> it : hm.entrySet()) {
            int count = it.getValue();
            ans += (count * (count - 1)) / 2;
        }
        return ans;
    }
}