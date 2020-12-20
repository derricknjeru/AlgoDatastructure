package revision.strings_and_arrays;




import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ReverseWordsInAString {
    public static void main(String[] args) {
        String s = "the sky is blue";
        //String s = "  hello world  ";
        //String s = "a good   example";
        //String s = "  Bob    Loves  Alice   ";
        //String s = "Alice does not even like bob";

        String reversed = new ReverseWordsInAString().reverseWords2(s);
        System.out.printf("reversed string %s\n ", reversed); //log
    }

    //{ @link https://leetcode.com/problems/reverse-words-in-a-string/}

    /**
     * Given an input string s, reverse the order of the words.
     * <p>
     * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
     * <p>
     * Return a string of the words in reverse order concatenated by a single space.
     * <p>
     * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "the sky is blue"
     * Output: "blue is sky the"
     * Example 2:
     * <p>
     * Input: s = "  hello world  "
     * Output: "world hello"
     * Explanation: Your reversed string should not contain leading or trailing spaces.
     * Example 3:
     * <p>
     * Input: s = "a good   example"
     * Output: "example good a"
     * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
     * Example 4:
     * <p>
     * Input: s = "  Bob    Loves  Alice   "
     * Output: "Alice Loves Bob"
     * Example 5:
     * <p>
     * Input: s = "Alice does not even like bob"
     * Output: "bob like even not does Alice"
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 104
     * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
     * There is at least one word in s.
     * <p>
     * <p>
     * Follow up:
     * <p>
     * Could you solve it in-place with O(1) extra space?
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = s.replaceAll("\\s+", " "); //remove white spaces.

        Stack<String> stringStack = new Stack<>();

        String[] arr = s.trim().split(" ");

        for (String str : arr) {
            stringStack.push(str);

        }
        StringBuilder sb = new StringBuilder();

        while (!stringStack.isEmpty()) {
            sb.append(stringStack.pop());
            sb.append(" ");
        }

        return sb.substring(0, sb.length() - 1);
    }

    String trimmedBetween(String str, int start, int end) {
        while (start < end && Character.isWhitespace(str.charAt(start))) {
            ++start;
        }

        while (start < end && Character.isWhitespace(str.charAt(end - 1))) {
            --end;
        }

        return str.substring(start, end);
    }


    //Solution with Space complexity 0(1)

    public String reverseWords2(String s) {
        s = s.replaceAll("\\s+", " "); //remove white spaces.
        String[] arr = s.trim().split(" ");

        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            String temp = arr[end];
            arr[end--] = arr[start];
            arr[start++] = temp;
        }

        System.out.println(Arrays.toString(arr));


        return String.join(" ", arr); //convert array to a string of words
    }



}
