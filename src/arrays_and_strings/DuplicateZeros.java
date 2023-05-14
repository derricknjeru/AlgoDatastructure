package arrays_and_strings;

public class DuplicateZeros {
    public static void main(String[] args) {
        //int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
        int[] arr = {8, 4, 5, 0, 0, 0, 0, 7}; //

        //DuplicateZeros zeros = new DuplicateZeros();
        // zeros.duplicateZeros(arr2);

        //Deque<Integer> stack = new ArrayDeque<>();


        int possibleDup = 0;
        int size = arr.length;

        for (int i = 0; i < size - possibleDup; i++) {
            if (arr[i] == 0) {

                if (i == size - possibleDup) {
                    // For this zero we just copy it without duplication.
                    arr[size - 1] = 0;
                    break;
                }

                possibleDup++;
            }

        }


        System.out.println(possibleDup);
    }

    /**
     * Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.
     * <p>
     * Note.md.md that elements beyond the length of the original array are not written.
     * <p>
     * Do the above modifications to the input array in place, do not return anything from your function.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: [1,0,2,3,0,4,5,0]
     * Output: null
     * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
     * Example 2:
     * <p>
     * Input: [1,2,3]
     * Output: null
     * Explanation: After calling your function, the input array is modified to: [1,2,3]
     * <p>
     * <p>
     * Note.md.md:
     * <p>
     * 1 <= arr.length <= 10000
     * 0 <= arr[i] <= 9
     * Hide Hint #1
     * This is a great introductory problem for understanding and working with the concept of in-place operations. The problem statement clearly states that we are to modify the array in-place. That does not mean we cannot use another array. We just don't have to return anything.
     * Hide Hint #2
     * A better way to solve this would be without using additional space. The only reason the problem statement allows you to make modifications in place is that it hints at avoiding any additional memory.
     * Hide Hint #3
     * The main problem with not using additional memory is that we might override elements due to the zero duplication requirement of the problem statement. How do we get around that?
     * Hide Hint #4
     * If we had enough space available, we would be able to accommodate all the elements properly. The new length would be the original length of the array plus the number of zeros. Can we use this information somehow to solve the problem?
     *
     * @param arr
     */
    public void duplicateZeros(int[] arr) {
        //Solution
        // https://www.youtube.com/watch?v=io3N5YTFbxM&ab_channel=RajdeepKaur
        //https://leetcode.com/problems/duplicate-zeros/solution/
        int possibleDups = 0;
        int length_ = arr.length - 1;

        // Find the number of zeros to be duplicated
        // Stopping when left points beyond the last element in the original array
        // which would be part of the modified array
        for (int left = 0; left <= length_ - possibleDups; left++) {

            // Count the zeros
            if (arr[left] == 0) {

                // Edge case: This zero can't be duplicated. We have no more space,
                // as left is pointing to the last element which could be included
                if (left == length_ - possibleDups) {
                    // For this zero we just copy it without duplication.
                    arr[length_] = 0;
                    length_ -= 1;
                    break;
                }
                possibleDups++;
            }
        }

        // Start backwards from the last element which would be part of new array.
        int last = length_ - possibleDups;

        // Copy zero twice, and non zero once.
        for (int i = last; i >= 0; i--) {
            if (arr[i] == 0) {
                arr[i + possibleDups] = 0;
                possibleDups--;
                arr[i + possibleDups] = 0;
            } else {
                arr[i + possibleDups] = arr[i];
            }
        }


    }

    //https://www.youtube.com/watch?v=io3N5YTFbxM&ab_channel=RajdeepKaur
    public void duplicateZeros2(int[] arr) {

        int zeros = 0;

        for (int a : arr) {
            if (a == 0) {
                zeros++;
            }
        }

        int i = arr.length - 1;
        int j = i + zeros;

        while (i != j) {
            insert(arr, i, j--);
            if (arr[i] == 0) {
                insert(arr, i, j--);
            }
            i--;

        }


    }

    private void insert(int[] arr, int i, int j) {
        if (j < arr.length) {
            arr[j] = arr[i];
        }
    }

}
