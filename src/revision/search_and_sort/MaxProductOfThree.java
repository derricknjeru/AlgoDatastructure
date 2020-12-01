package revision.search_and_sort;

import java.util.Arrays;

public class MaxProductOfThree {
    public static void main(String[] args) {
    }
    //https://app.codility.com/programmers/lessons/6-sorting/max_product_of_three/

    /**
     * A non-empty array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).
     * <p>
     * For example, array A such that:
     * <p>
     * A[0] = -3
     * A[1] = 1
     * A[2] = 2
     * A[3] = -2
     * A[4] = 5
     * A[5] = 6
     * contains the following example triplets:
     * <p>
     * (0, 1, 2), product is −3 * 1 * 2 = −6
     * (1, 2, 4), product is 1 * 2 * 5 = 10
     * (2, 4, 5), product is 2 * 5 * 6 = 60
     * Your goal is to find the maximal product of any triplet.
     * <p>
     * Write a function:
     * <p>
     * class Solution { public int solution(int[] A); }
     * <p>
     * that, given a non-empty array A, returns the value of the maximal product of any triplet.
     * <p>
     * For example, given array A such that:
     * <p>
     * A[0] = -3
     * A[1] = 1
     * A[2] = 2
     * A[3] = -2
     * A[4] = 5
     * A[5] = 6
     * the function should return 60, as the product of triplet (2, 4, 5) is maximal.
     * <p>
     * Write an efficient algorithm for the following assumptions:
     * <p>
     * N is an integer within the range [3..100,000];
     * each element of array A is an integer within the range [−1,000..1,000].
     */

    public int solution(int[] A) {

        int N = A.length;
        Arrays.sort(A);

        /**
         * When we sort an array there are two possible options for the largest product
         * 1) The largest (the last) three elements
         * 2) Combination of two smallest and the largest elements
         * Logic of (1): Obvious
         * Logic of (2): A pair of negatives multiplied returns a positive, which in combination with
         * the largest positive element of the array can give the max outcome.
         * Therefore we return the max of options (1) and (2)
         */
        return Math.max(A[0] * A[1] * A[N - 1], A[N - 1] * A[N - 2] * A[N - 3]);
    }

    /*    The method get the max product of 3 consists in basically find the biggest 3 numbers from the array and the smallest 2 numbers from the array in just 1 iteration over the array. Here is the java code:*/
     //Without sorting.
    int solution2(int[] a) {
        /* the minimums initialized with max int to avoid cases with extreme max in array and false minims 0 minimums returned */

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        /* the same logic for maximum initializations but of course inverted to avoid extreme minimum values in array and false 0 maximums */

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

//the iteration over the array

        for (int j : a) {

//test if max1 is smaller than current array value

            if (j > max1) {
                //if a[i] is greater than the biggest max then a chain reaction is started,
                // max3 will be max2, max2 will be actual max1 and max1 will be a[i]
                max3 = max2;
                max2 = max1;
                max1 = j;
/* in case if current a[i] isn't bigger than max1 we test it to see maybe is bigger than second
 max. Then the same logic from above is applied for the max2 amd max3 */

            } else if (j > max2) {
                max3 = max2;
                max2 = j;
                /* finally if current array value isn't bigger than max1 and max2 maybe is greater than max3 */

            } else if (j > max3) {
                max3 = j;
            }

/* The logic from above with maximums is is applied here with minimums but of course inverted to
discover the 2 minimums from current array. */

            if (j < min1) {
                min2 = min1;
                min1 = j;
            } else if (j < min2) {
                min2 = j;
            }
        }
/* after we discovered the 3 greatest maximums and the 2 smallest minimums from the array
we do the 2 products of 3 from the greatest maximum and the 2 minimums . This is necessary
because mathematically the product of 2 negative values is a positive value, and because of
this the product of min1 * min2 * max1 can be greater than max1 * max2 * max3
 and the product built from the the 3 maximums. */

        int prod1 = min1 * min2 * max1;
        int prod2 = max1 * max2 * max3;

//here we just return the biggest product

        return Math.max(prod1, prod2);

    }
}

