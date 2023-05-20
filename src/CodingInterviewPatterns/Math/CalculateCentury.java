package CodingInterviewPatterns.Math;

public class CalculateCentury {
    public static void main(String[] args) {
        /**
         * Given a year, return the century it is in. The first century spans from the year 1 up to and including the year 100, the second - from the year 101 up to and including the year 200, etc.
         *
         * Example
         *
         * For year = 1905, the output should be
         * solution(year) = 20;
         * For year = 1700, the output should be
         * solution(year) = 17.
         * Input/Output
         *
         * [execution time limit] 3 seconds (java)
         *
         * [memory limit] 1 GB
         *
         * [input] integer year
         *
         * A positive integer, designating the year.
         *
         * Guaranteed constraints:
         * 1 ≤ year ≤ 2005.
         *
         * [output] integer
         *
         * The number of the century the year is in.
         *
         * [Java] Syntax Tips
         *
         * // Prints help message to the console
         * // Returns a string
         * //
         * // Globals declared here will cause a compilation error,
         * // declare variables inside the function instead!
         * String helloWorld(String name) {
         *     System.out.println("This prints to the console when you Run Tests");
         *     return "Hello, " + name;
         * }
         */

        int year = 1905;
        int century = solution(year);
        System.out.println("The year " + year + " is in the " + century + " century.");

        year = 1700;
        century = solution(year);
        System.out.println("The year " + year + " is in the " + century + " century.");
    }

    private static int solution(int year) {
        // Calculate the century by dividing the year by 100 and rounding up
        return (int) Math.ceil(year / 100.0);
    }
}
