package hackerrack_30_days_challenge;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        // Write Your Code Here
        System.out.printf("Array is sorted in %d swaps.\n", new Solution().bubbleSort(a));
        System.out.printf("First Element: %d", a[0]);
        System.out.printf("\nLast Element: %d", a[a.length - 1]);

    }

    public int bubbleSort(int arr[]) {
        // Track number of elements swapped during a single array traversal
        int numberOfSwaps = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    numberOfSwaps++;
                }

        return numberOfSwaps;
    }
}
