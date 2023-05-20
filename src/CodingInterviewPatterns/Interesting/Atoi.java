package CodingInterviewPatterns.Interesting;

public class Atoi {
    public static void main(String[] args) {
        //String s = "-91283472332";
        String s = "";
        int i = myAtoi(s);
        System.out.println("The string::" + i);
        double result = 0;


        /**
         * Learning
         *
         *
         *  result = result * 10 + str.charAt(i) - '0'; //horners rule to append integers
         */



    }




    /**
     * { https://leetcode.com/problems/string-to-integer-atoi/ }
     * Implement atoi which converts a string to an integer.
     * <p>
     * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
     * <p>
     * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
     * <p>
     * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
     * <p>
     * If no valid conversion could be performed, a zero value is returned.
     * <p>
     * Note.md.md:
     * <p>
     * Only the space character ' ' is considered a whitespace character.
     * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, 231 − 1 or −231 is returned.
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: str = "42"
     * Output: 42
     * Example 2:
     * <p>
     * Input: str = "   -42"
     * Output: -42
     * Explanation: The first non-whitespace character is '-', which is the minus sign. Then take as many numerical digits as possible, which gets 42.
     * Example 3:
     * <p>
     * Input: str = "4193 with words"
     * Output: 4193
     * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
     * Example 4:
     * <p>
     * Input: str = "words and 987"
     * Output: 0
     * Explanation: The first non-whitespace character is 'w', which is not a numerical digit or a +/- sign. Therefore no valid conversion could be performed.
     * Example 5:
     * <p>
     * Input: str = "-91283472332"
     * Output: -2147483648
     * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer. Thefore INT_MIN (−231) is returned.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 0 <= s.length <= 200
     * s consists of English letters (lower-case and upper-case), digits, ' ', '+', '-' and '.'.
     */

    // Time complexity: O(N) where N is the length of the string and we only traverse 1 time the string.
    // Space complexity: O(1) We have used only constant space to store the sign and the result. we do not use any significant extra space, all is associated to the existing data.
    public static int myAtoi(String s) {
        //  Main constraints related to the length.
        if (s == null || s.isEmpty() || s.length() > 200) return 0;

        int length = s.length();
        int signDriver = 1;
        int currentIndex = 0;
        int result = 0;

        /*  Integer.MAX_VALUE = 2147483647(10 digits)
            Integer.MAX_VALUE / 10 = 214748364(9 digits) */
        int maximumLimit = Integer.MAX_VALUE / 10;

        //  Ignore every single leading space at the beginning of the string.
        while (currentIndex < length && s.charAt(currentIndex) == ' ')
            currentIndex++;

        //  Check if the next char is '-' or '+' to determine the sign of the result. Default is positive.
        if (currentIndex < length) {
            if (s.charAt(currentIndex) == '-') {
                signDriver *= -1;
                currentIndex++;
            } else if (s.charAt(currentIndex) == '+') {
                currentIndex++;
            }
        }

        /*  Once we traverse the string ignoring spaces and finding the corresponding sign,
            we continue traversing the string only if the following characters represents a digit between 0 to 9. */
        while (currentIndex < length && isCharacterADigit(s.charAt(currentIndex))) {
            //  Converting the current char to integer using ASCII.
            int currentDigit = s.charAt(currentIndex) - '0';

            /*  This condition is used to handle any overflow case:
                1. First scenario: if result > maximumLimit, means that if we concatenate any value to the end of the result,
                   we are overflowing since the next value to maximumLimit is 214748365 and any value added after this 214748365"X"
                   overflows the number.

                2. Second scenario: in case the result is equal to maximumLimit (214748364), we should pay attention that the last digit
                   of Integer.MAX_VALUE (2147483647) is a 7, so any number above 7 concatenated to the end of the result will be above our limit
                   causing overflow, so that is why the condition "currentDigit>7".

                   Depending in the sign value, we just return the MIN or the MAX.
            */
            if (result > maximumLimit || (result == maximumLimit && currentDigit > 7))
                return signDriver == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            // Operation to concatenate the next digit at the end of the result.
            result = result * 10 + currentDigit;
            // Move forward to the next character.
            currentIndex++;
        }
        // We should return the value multiplied by the corresponding sign.
        return signDriver * result;
    }

    private static boolean isCharacterADigit(char character) {
        int possibleDigit = character - '0';
        return possibleDigit >= 0 && possibleDigit <= 9;
    }

    public static int myAtoi3(String s) {

        boolean isNegative = false;
        s = s.trim();

        if (s.equals("")) {
            return 0;
        }
        if ((s.length() >= 200 || Character.isLetter(s.charAt(0)))) {
            return 0;
        }


        if (s.charAt(0) == '-') {
            isNegative = true;
            s = s.substring(1);
        } else if (s.charAt(0) == '+') {
            s = s.substring(1);
        }

        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                break;
            }
        }
        s = sb.toString();

        double x = 0;
        try {
            x = Double.parseDouble(s);
        } catch (NumberFormatException ex) {
            return 0;
            //throw new NumberFormatException("Invalid number");
        }

        if (isNegative) {
            x = x * -1;
        }
        if (x < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (x > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int) x;
        }


    }


    public int myAtoi2(String s) {
        if (s == null || s.isEmpty() || s.length() >= 200) return 0;

        s = s.trim();

        boolean isNegative = false;

        //we use s.isEmpty() because we just trimmed the string

        if (!s.isEmpty() && s.charAt(0) == '-') {
            isNegative = true;
            s = s.substring(1);
        } else {
            if (!s.isEmpty() && s.charAt(0) == '+')
                s = s.substring(1);
        }


        int index = 0;

        /*  Integer.MAX_VALUE = 2147483647(10 digits)
            Integer.MAX_VALUE / 10 = 214748364(9 digits) */
        int maximumLimit = Integer.MAX_VALUE / 10;
        int result = 0;

        int len = s.length();

        while (index < len && Character.isDigit(s.charAt(index))) {

            int currentDigit = s.charAt(index++) - '0';

            if (result > maximumLimit || result == maximumLimit && currentDigit > 7) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            result = result * 10 + currentDigit;

        }

        return isNegative ? result * -1 : result;


    }


}
