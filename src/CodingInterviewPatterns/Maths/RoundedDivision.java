package CodingInterviewPatterns.Maths;

public class RoundedDivision {
    public static int roundedDivisionFloatingPoint(int dividend, int divisor) {
        /**
         * When dividing two integers, the result will be an integer,
         * and any fractional part will be truncated. In the case of 7 / 4,
         * the result is 1, not 2, because the fractional part 0.75 is discarded.
         */
        double result = (double) dividend / divisor;
        return (int) Math.ceil(result);
    }

    public static int roundedDivisionCeiling(int dividend, int divisor) {
        return (dividend + divisor - 1) / divisor;
    }

    public static void main(String[] args) {
        /**
         * When dividing two integers, the result will be an integer,
         * and any fractional part will be truncated. In the case of 7 / 4,
         * the result is 1, not 2, because the fractional part 0.75 is discarded.
         */
        int dividend = 7;
        int divisor = 4;

        int result1 = roundedDivisionFloatingPoint(dividend, divisor);
        System.out.println("Floating-Point Division: " + result1); // Output: 2

        int result2 = roundedDivisionCeiling(dividend, divisor);
        System.out.println("Ceiling Division: " + result2); // Output: 2
    }
}


