package revision.tips;

public class TrimMethods {
    public static void main(String[] args) {
        String s = "hello world";
        String s1 = "a good   example";
        String s2 = "  Bob    Loves  Alice   ";
        String s3 = "Alice does not even like bob";

        String[] arr = s.split("");


        System.out.printf("%s", trimAndRemoveWhite(s2));
        System.out.printf("\n%s", s2.trim());
    }

    private static String trimLeadingAndTrailing(String s) {
        String x = "";
        if (s.isBlank()) {
            return x;
        }
        int start = 0;
        int end = s.length() - 1;

        while (start < end && s.charAt(start) == ' ') {
            start++;
        }
        while (start < end && Character.isWhitespace(s.charAt(end))) {
            end--;
        }
        //We need to add 1 to end index
        return s.substring(start, end + 1);
    }

    private static String trimLeadingAndTrailingUsingRegularExpression(String s) {
        /**
         * The regular expressions just shown contain three parts each:
         */
        // 1. The shorthand character class to match any whitespace character (‹\s›)
        // 2. A quantifier to repeat the class one or more times (‹+›)
        // 3. An anchor to assert position at the beginning or end of the string. ‹\A› and ‹^› match at the beginning; ‹\Z› and ‹$› at the end.
        return s.replaceAll("^\\s+|\\s+$", "");
    }

    private static String removeAllWhiteSpaces(String s) {
        /**
         * The regular expressions just shown contain three parts each:
         */
        // 1. The shorthand character class to match any whitespace character (‹\s›)
        // 2. A quantifier to repeat the class one or more times (‹+›)
        // 3. An anchor to assert position at the beginning or end of the string. ‹\A› and ‹^› match at the beginning; ‹\Z› and ‹$› at the end.
        return s.replaceAll("\\s+", " ");


    }

    private static String trimAndRemoveWhite(String s) {
        return s.replaceAll("^\\s+|\\s+$", "").replaceAll("\\s+", " ");
    }


}
