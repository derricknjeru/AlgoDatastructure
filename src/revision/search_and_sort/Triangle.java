package revision.search_and_sort;

import java.util.Arrays;

public class Triangle {
    public static void main(String[] args) {

    }

    /**
     * An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
     * <p>
     * A[P] + A[Q] > A[R],
     * A[Q] + A[R] > A[P],
     * A[R] + A[P] > A[Q].
     * For example, consider array A such that:
     * <p>
     * A[0] = 10    A[1] = 2    A[2] = 5
     * A[3] = 1     A[4] = 8    A[5] = 20
     * Triplet (0, 2, 4) is triangular.
     * <p>
     * Write a function:
     * <p>
     * class Solution { public int solution(int[] A); }
     * <p>
     * that, given an array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.
     * <p>
     * For example, given array A such that:
     * <p>
     * A[0] = 10    A[1] = 2    A[2] = 5
     * A[3] = 1     A[4] = 8    A[5] = 20
     * the function should return 1, as explained above. Given array A such that:
     * <p>
     * A[0] = 10    A[1] = 50    A[2] = 5
     * A[3] = 1
     * the function should return 0.
     * <p>
     * Write an efficient algorithm for the following assumptions:
     * <p>
     * N is an integer within the range [0..100,000];
     * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
     */

    /**
     * Execution:
     * By sorting the array, we have guaranteed that P+R > Q and Q+R > P (because R is always the biggest).
     * Now what remains, is the proof that P+Q > R, that can be found out by traversing the array.
     * The chance to find such a combination is with three adjacent values as they provide the highest P and Q.
     * @param A
     * @return
     */
    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length <3) return 0;

        Arrays.sort(A);

        for (int i = 0; i < A.length - 2; i++) {
            int p = A[i];
            int q = A[i + 1];
            int r = A[i + 2];
            //p+q>r
            //overflow can be avoided using A[P] > A[R] - A[Q] instead of A[P] + A[Q] > A[R].
            if (p > r - q) return 1;

        }

        return 0;

    }

}
