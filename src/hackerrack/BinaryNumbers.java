package hackerrack;

import java.util.Scanner;

public class BinaryNumbers {
    /**
     * Objective
     * Today, we're working with binary numbers. Check out the Tutorial tab for learning materials and an instructional video!
     * <p>
     * Task
     * Given a base- integer, , convert it to binary (base-). Then find and print the base- integer denoting the maximum number of consecutive 's in 's binary representation.
     * <p>
     * Input Format
     * <p>
     * A single integer, .
     * <p>
     * Constraints
     * <p>
     * Output Format
     * <p>
     * Print a single base- integer denoting the maximum number of consecutive 's in the binary representation of .
     * <p>
     * Sample Input 1
     * <p>
     * 5
     * Sample Output 1
     * <p>
     * 1
     * Sample Input 2
     * <p>
     * 13
     * Sample Output 2
     * <p>
     * 2
     * Explanation
     * <p>
     * Sample Case 1:
     * The binary representation of  is , so the maximum number of consecutive 1's is .
     * <p>
     * Sample Case 2:
     * The binary representation of  is , so the maximum number of consecutive 1's is .
     */

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        printConsecutive1s(n);

        scanner.close();
    }

    private static int printConsecutive1s(int n) {

        // Initialize result
        int count = 0;

        // Count the number of iterations to
        // reach x = 0.
        while (n != 0) {
            // This operation reduces length
            // of every sequence of 1s by one.
            n = (n & (n << 1));

            count++;
        }

        System.out.print(count);

        return count;
    }
}
