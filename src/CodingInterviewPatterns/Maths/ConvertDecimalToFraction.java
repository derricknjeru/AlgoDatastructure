package CodingInterviewPatterns.Maths;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ConvertDecimalToFraction {

    public static void main(String[] args) {
        double[] nums = {0.625, 2.40, 0.5, 0.75, 0.6, 0.33, 3.00};
        for (double num : nums) {
            System.out.println("num: " + num);
            System.out.println("convertDecimalToFraction: " + convertDecimalToFraction(num));
            System.out.println("=====================================");
        }
    }


    private static String convertDecimalToFraction(double num) {
        int denominator = 1;

        // multiply the number by 10 until it becomes an integer
        while (num % 1 != 0) {
            num *= 10;
            denominator *= 10;
        }

        System.out.println(denominator);

        int numerator = (int) num;

        int gcd = gcd(numerator, denominator);

        if (gcd == 1) {
            return Integer.toString(numerator);
        }

        return numerator / gcd + "/" + denominator / gcd;
    }

    private static int gcd(int numerator, int denominator) {
        if (denominator == 0) {
            return numerator;
        }
        System.out.println("numerator: " + numerator + ", denominator: " + denominator);

        // Euclidean algorithm: Explaination: https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/the-euclidean-algorithm
        return gcd(denominator, numerator % denominator);
    }

    public static String convertToFraction(double decimal) {
        BigDecimal bd = BigDecimal.valueOf(decimal);
        BigInteger numerator = bd.unscaledValue();
        BigInteger denominator = BigDecimal.TEN.pow(bd.scale()).toBigInteger();
        BigInteger gcd = numerator.gcd(denominator);
        BigInteger simplifiedNumerator = numerator.divide(gcd);
        BigInteger simplifiedDenominator = denominator.divide(gcd);

        return simplifiedNumerator + "/" + simplifiedDenominator;
    }
}
