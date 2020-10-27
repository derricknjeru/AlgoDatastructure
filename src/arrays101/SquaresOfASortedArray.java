package arrays101;

import java.util.Arrays;

public class SquaresOfASortedArray {
    /**
     * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: [-4,-1,0,3,10]
     * Output: [0,1,9,16,100]
     * Example 2:
     * <p>
     * Input: [-7,-3,2,3,11]
     * Output: [4,9,9,49,121]
     * <p>
     * <p>
     * Note:
     * <p>
     * 1 <= A.length <= 10000
     * -10000 <= A[i] <= 10000
     * A is sorted in non-decreasing order.
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] A = {-4, -1, 0, 3, 10};
        System.out.println("The array results:: " + Arrays.toString(sortedSquares(A)));
    }

    /**
     * Complexity Analysis
     * <p>
     * Time Complexity: O(NlogN), where N is the length of A.
     * <p>
     * Space Complexity: O(N)O(N).
     *
     * @param A
     * @return
     */
    public static int[] sortedSquares(int[] A) {

        int size = A.length;

        int[] result = new int[size];

        for (int i = 0; i < size; i++)
            result[i] = A[i] * A[i];

        Arrays.sort(result);

        return result;

    }

    /**
     * Approach 2: Two Pointer
     * Intuition
     * <p>
     * Since the array A is sorted, loosely speaking it has some negative elements with squares in decreasing order, then some non-negative elements with squares in increasing order.
     * <p>
     * For example, with [-3, -2, -1, 4, 5, 6], we have the negative part [-3, -2, -1] with squares [9, 4, 1], and the positive part [4, 5, 6] with squares [16, 25, 36]. Our strategy is to iterate over the negative part in reverse, and the positive part in the forward direction.
     * <p>
     * Algorithm
     * <p>
     * We can use two pointers to read the positive and negative parts of the array - one pointer j in the positive direction, and another i in the negative direction.
     * <p>
     * Now that we are reading two increasing arrays (the squares of the elements), we can merge these arrays together using a two-pointer technique.
     */

    public int[] sortedSquares2(int[] A) {
        int N = A.length;
        int j = 0;
        while (j < N && A[j] < 0)
            j++;
        int i = j - 1;

        int[] ans = new int[N];
        int t = 0;

        while (i >= 0 && j < N) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[t++] = A[i] * A[i];
                i--;
            } else {
                ans[t++] = A[j] * A[j];
                j++;
            }
        }

        while (i >= 0) {
            ans[t++] = A[i] * A[i];
            i--;
        }
        while (j < N) {
            ans[t++] = A[j] * A[j];
            j++;
        }

        return ans;
    }


}
