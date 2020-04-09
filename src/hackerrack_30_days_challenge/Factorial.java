package hackerrack_30_days_challenge;

import java.io.IOException;
import java.util.Scanner;

public class Factorial {

    /**
     * @link https://www.geeksforgeeks.org/java-program-for-factorial-of-a-number/
     * Factorial of a non-negative integer, is multiplication of all integers smaller than or equal to n.
     * For example factorial of 6 is 6*5*4*3*2*1 which is 720.
     * <p>
     * n!=n*(n-1)*(n-2)*...*1
     */

    // Complete the factorial function(recursive)
    static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    // Complete the factorial iteratively
    static int factorial2(int n) {
        int res = 1, i;
        for (i = 2; i <= n; i++) res *= i;
        return res;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = factorial(n);
        int result2 = factorial(n);

        System.out.println(result);
        System.out.println(result2);

        scanner.close();
    }

}
