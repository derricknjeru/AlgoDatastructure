package arrays_and_strings;

public class Atoi {
    public static void main(String[] args) {
        String s1 = "9000",s2="";
        char c =s1.charAt(0);

        int addedCur = c=='9' ? 0 : c-'0'+1;
        String s3=s1+addedCur;

        System.out.println(s3);
        
        String x = "4193 with words";//expected=4193
        String x1 = "words and 987";//expected=0
        String x2 = "-42";//expected=-42
        String x3 = "42";//expected=42
        String x4 = "91283472332";
        String x5 = "-91283472332"; //expected=-2147483648
        String x6 = "+1"; //expected=-2147483648
        String x7 = "9223372036854775808";
        String x8 = "18446744073709551617";

        System.out.println(new Atoi().myAtoi(x8));

    }

    /**
     * Implement atoi which converts a string to an integer.
     * <p>
     * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
     * <p>
     * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
     * <p>
     * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
     * <p>
     * If no valid conversion could be performed, a zero value is returned.
     * <p>
     * Note:
     * <p>
     * Only the space character ' ' is considered as whitespace character.
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
     * Example 1:
     * <p>
     * Input: "42"
     * Output: 42
     * Example 2:
     * <p>
     * Input: "   -42"
     * Output: -42
     * Explanation: The first non-whitespace character is '-', which is the minus sign.
     * Then take as many numerical digits as possible, which gets 42.
     * Example 3:
     * <p>
     * Input: "4193 with words"
     * Output: 4193
     * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
     * Example 4:
     * <p>
     * Input: "words and 987"
     * Output: 0
     * Explanation: The first non-whitespace character is 'w', which is not a numerical
     * digit or a +/- sign. Therefore no valid conversion could be performed.
     * Example 5:
     * <p>
     * Input: "-91283472332"
     * Output: -2147483648
     * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
     * Thefore INT_MIN (−231) is returned.
     */
    public int myAtoi(String str) {
        boolean isNegative = false;

        str = str.trim();

        if (str.isEmpty()) return 0;

        StringBuilder sb = new StringBuilder();
        if (str.charAt(0) == '-') {
            isNegative = true;
            sb.append(str);
            sb.deleteCharAt(0);
            str = sb.toString();

        } else if (str.charAt(0) == '+') {
            sb.append(str);
            sb.deleteCharAt(0);
            str = sb.toString();
        }

        double result = 0;

        for (int i = 0; i < str.length(); i++) {
            //If any character is not an integer then just return break
            if (!(str.charAt(i) - '0' >= 0 && str.charAt(i) - '0' <= 9))
                break;

            //else just keep updating the result
            result = result * 10 + str.charAt(i) - '0'; //horners rule

        }
        if (isNegative) {
            result = result * -1;
        }

        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        return (int) result;

    }
}
