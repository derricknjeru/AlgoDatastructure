package hackerrack;

import java.util.Scanner;

/**
 * Objective
 * Today, we're learning about the Array data structure. Check out the Tutorial tab for learning materials and an instructional video!
 * <p>
 * Task
 * Given an array, , of  integers, print 's elements in reverse order as a single line of space-separated numbers.
 * <p>
 * Input Format
 * <p>
 * The first line contains an integer,  (the size of our array).
 * The second line contains  space-separated integers describing array 's elements.
 * <p>
 * Constraints
 * <p>
 * , where  is the  integer in the array.
 * Output Format
 * <p>
 * Print the elements of array  in reverse order as a single line of space-separated numbers.
 * <p>
 * Sample Input
 * <p>
 * 4
 * 1 4 3 2
 * Sample Output
 * <p>
 * 2 3 4 1
 */
public class ReverseArray {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }

        scanner.close();
    }
}
