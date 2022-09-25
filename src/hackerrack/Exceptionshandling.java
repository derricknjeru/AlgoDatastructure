package hackerrack;

import java.util.*;

//Write your code here
class Calculator2 {

    int power(int n, int p) {
        if (n >= 0 && p >= 0) {
            return (int) Math.pow(n, p);
        } else {
            throw new ArithmeticException("n and p should be non-negative");
        }
    }
}

class Exceptionshandling {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {

            int n = in.nextInt();
            int p = in.nextInt();
            Calculator2 myCalculator = new Calculator2();
            try {
                int ans = myCalculator.power(n, p);
                System.out.println(ans);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        in.close();
    }
}
