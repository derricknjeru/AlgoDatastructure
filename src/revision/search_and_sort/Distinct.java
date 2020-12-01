package revision.search_and_sort;

import java.util.Arrays;
import java.util.HashSet;

public class Distinct {
    public static void main(String[] args) {

    }

    /**
     * Write a function
     * <p>
     * class Solution { public int solution(int[] A); }
     * <p>
     * that, given an array A consisting of N integers, returns the number of distinct values in array A.
     * <p>
     * For example, given array A consisting of six elements such that:
     * <p>
     * A[0] = 2    A[1] = 1    A[2] = 1
     * A[3] = 2    A[4] = 3    A[5] = 1
     * the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.
     * <p>
     * Write an efficient algorithm for the following assumptions:
     * <p>
     * N is an integer within the range [0..100,000];
     * each element of array A is an integer within the range [−1,000,000..1,000,000].
     */
    // O(N) Time complexity
    public int solution(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        // write your code in Java SE 8
        HashSet<Integer> arrSet = new HashSet<>();
        for (int a : A) {
            arrSet.add(a);
        }

        return arrSet.size();
    }

    public int solution2(int[] A) {
        Arrays.sort(A);

        int n = A.length;

        // Traverse the sorted array
        int res = 0;
        for (int i = 0; i < n; i++) {

            // Move the index ahead while
            // there are duplicates
            while (i < n - 1 && A[i] == A[i + 1]) {
                i++;
            }
            res++;
        }
        return res;
    }
}
