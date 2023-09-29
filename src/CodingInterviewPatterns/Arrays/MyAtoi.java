package CodingInterviewPatterns.Arrays;

public class MyAtoi {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        String str1 = "42";
        int result1 = solution.myAtoi(str1);
        System.out.println("Result 1: " + result1); // Expected output: 42

        String str2 = "   -42";
        int result2 = solution.myAtoi(str2);
        System.out.println("Result 2: " + result2); // Expected output: -42

        String str3 = "4193 with words";
        int result3 = solution.myAtoi(str3);
        System.out.println("Result 3: " + result3); // Expected output: 4193

        String str4 = "words and 987";
        int result4 = solution.myAtoi(str4);
        System.out.println("Result 4: " + result4); // Expected output: 0

        String str5 = "-91283472332";
        int result5 = solution.myAtoi(str5);
        System.out.println("Result 5: " + result5); // Expected output: -2147483648


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
     * Note.md.md.md.md:
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

    // Time complexity: O(N) where N is the length of the string, and we only traverse 1 time the string.
    // Space complexity: O(1) We have used only constant space to store the sign and the result. we do not use any significant extra space, all is associated to the existing data.
    static class Solution {
        public int myAtoi(String s) {
            int currentIndex = 0;
            int n = s.length();

            // Trim leading spaces
            while (currentIndex < n && s.charAt(currentIndex) == ' ') {
                currentIndex++;
            }

            // Determine the sign
            int sign = 1; // Default to positive
            if (currentIndex < n && (s.charAt(currentIndex) == '+' || s.charAt(currentIndex) == '-')) {
                sign = (s.charAt(currentIndex) == '-') ? -1 : 1;
                currentIndex++;
            }

            int result = 0;

            while (currentIndex < n && Character.isDigit(s.charAt(currentIndex))) {
                int digit = s.charAt(currentIndex) - '0';

                // Check for overflow
                if (result > (Integer.MAX_VALUE - digit) / 10) {
                    return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                result = result * 10 + digit;
                currentIndex++;
            }

            return sign * result;
        }
    }

    private static boolean isCharacterADigit(char character) {
        int possibleDigit = character - '0';
        return possibleDigit >= 0 && possibleDigit <= 9;
    }


    static class Solution2 {
        public int myAtoi(String s) {
            if (s == null || s.isEmpty() || s.length() > 200) return 0;

            int i = 0;
            int j = s.length();
            boolean isNegative = false;

            //remove white space at the beginning
            while (i < j && s.charAt(i) == ' ') {
                i++;
            }

            if (i < j && s.charAt(i) == '-') {
                i++;
                isNegative = true;
            } else if (i < j && s.charAt(i) == '+') {
                i++;
            }

            int result = 0;
            int maxLimit = Integer.MAX_VALUE / 10;

            while (i < j && isCharacterADigit(s.charAt(i))) {
                int digit = s.charAt(i) - '0';
                /*  This condition is used to handle any overflow case:
                1. First scenario: if result > maximumLimit, means that if we concatenate any value to the end of the result,
                   we are overflowing since the next value to maximumLimit is 214748365 and any value added after this 214748365"X"
                   overflows the number.

                2. Second scenario: in case the result is equal to maximumLimit (214748364), we should pay attention that the last digit
                   of Integer.MAX_VALUE (2147483647) is a 7, so any number above 7 concatenated to the end of the result will be above our limit
                   causing overflow, so that is why the condition "currentDigit>7".

                   Depending on the sign value, we just return the MIN or the MAX.
                */
                if (result > maxLimit || (result == maxLimit && digit > 7)) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result = result * 10 + digit;
                i++;
            }

            return isNegative ? result * -1 : result;

        }
    }


}
