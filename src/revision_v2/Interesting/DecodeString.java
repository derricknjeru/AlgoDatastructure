package revision_v2.Interesting;

import java.util.Stack;

public class DecodeString {
    /**
     * Given an encoded string, return its decoded string.
     * <p>
     * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note.md.md.md.md that k is guaranteed to be a positive integer.
     * <p>
     * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
     * <p>
     * The test cases are generated so that the length of the output will never exceed 105.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "3[a]2[bc]"
     * Output: "aaabcbc"
     * Example 2:
     * <p>
     * Input: s = "3[a2[c]]"
     * Output: "accaccacc"
     * Example 3:
     * <p>
     * Input: s = "2[abc]3[cd]ef"
     * Output: "abcabccdcdcdef"
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 30
     * s consists of lowercase English letters, digits, and square brackets '[]'.
     * s is guaranteed to be a valid input.
     * All the integers in s are in the range [1, 300].
     */
    //https://www.youtube.com/watch?v=SF2W6VDs7bc&t=566s
    public String decodeString(String s) {
        Stack<String> sString = new Stack<>();
        Stack<Integer> sNo = new Stack<>();

        StringBuilder sb = new StringBuilder();
        //StringBuilder sbNo = new StringBuilder();
        int number=0;

        for (char c : s.toCharArray()) {
            if (c == '[') {
                //add values to stack and reset the no and word
                sString.push(sb.toString());
                sNo.push(number);
                sb = new StringBuilder();
                number = 0;
            } else if (c == ']') {
                String s2 = sString.pop();
                int n = sNo.pop();
                //repeat the current word and add it to the string
                String sf = s2 + sb.toString().repeat(n);
                sb = new StringBuilder();
                sb.append(sf);

            } else {
                if (Character.isDigit(c)) {
                    number=number*10 +c-'0';
                } else {
                    sb.append(c);
                }
            }

        }

        return sb.toString();

    }
}
