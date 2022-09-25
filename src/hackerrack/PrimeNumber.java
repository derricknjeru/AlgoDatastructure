package hackerrack;

import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {

            int n = in.nextInt();
            printPrimeNumber(n);
        }
        in.close();
    }

    private static void printPrimeNumber(int n) {
        int temp;
        boolean isPrime = true;
        if (n == 1) {
            System.out.println("Not prime");
            return;
        }

        for (int i = 2; i <= n / 2; i++) {
            temp = n % i;
            if (temp == 0) {
                isPrime = false;
                break;
            }
        }
        //If isPrime is true then the number is prime else not
        if (isPrime)
            System.out.println("Prime");
        else
            System.out.println("Not prime");
    }
}
