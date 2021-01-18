package revision.strings_and_arrays;

public class Atoi {
    public static void main(String[] args) {
        //String s = "-91283472332";
        String s = "";
        int i = myAtoi(s);
        System.out.println("The string::" + i);
        double result=0;


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
     * Note:
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
    public static int myAtoi(String s) {

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
}
