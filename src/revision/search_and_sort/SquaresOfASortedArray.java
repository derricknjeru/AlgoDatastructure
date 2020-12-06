package revision.search_and_sort;

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
    //https://www.geeksforgeeks.org/sort-array-converting-elements-squares/

    /**
     * Efficient solution is based on the fact that the given array is already sorted. We do the following two steps.
     * <p>
     * Divide the array into two-part “Negative and positive “.
     * Use merge function to merge two sorted arrays into a single sorted array.
     * Below is the implementation of the above idea.
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares3(int[] nums) {
        int k = 0;

        int n = nums.length;
        // first dived array into part negative and positive
        for (int a : nums) {
            if (a >= 0)
                break;
            k++;
        }

        // Now do the same process that we learn
        // in merge sort to merge to two sorted array
        // here both two half are sorted and we traverse
        // first half in reverse meaner because
        // first half contain negative element
        int i = k - 1; //initial index of array 1
        int j = k; //initial index of array 2
        int ind = 0; // Initial index of temp array

        int[] temp = new int[n];

        while (i >= 0 && j < n) {


            int sq1 = nums[i] * nums[i];

            int sq2 = nums[j] * nums[j];

            if (sq1 > sq2) {
                temp[ind++] = sq2;
                j++;
            } else {
                temp[ind++] = sq1;
                i--;
            }

        }

        while (i >= 0) {
            int sq1 = nums[i] * nums[i];
            temp[ind++] = sq1;
            i--;
        }

        while (j < n) {
            int sq2 = nums[j] * nums[j];
            temp[ind++] = sq2;
            j++;
        }


        return temp;

    }


}
