package arrays_and_strings;


import java.util.*;

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
     * Note.md.md that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
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

    public String reverseWords3(String s) {

        //trim whitespaces
        String x = s.replaceAll("^\\s+|\\s+$", "").replaceAll("\\s+", " ");

        //split
        String[] arr = split(x);

        int i = 0;
        int j = arr.length - 1; //last index

        while (i < j) {
            String tempWord = arr[i];
            arr[i++] = arr[j];
            arr[j--] = tempWord;

        }

        return String.join(" ", arr);


    }

    private String[] split(String s) {

        List<String> list = new ArrayList<>();

        char[] arr = s.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (char c : arr) {
            if (c == ' ') {
                list.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }

        list.add(sb.toString());

        return list.toArray(new String[0]);
    }

    public String reverseWords4(String s) {

        //trim whitespaces
        String x = s.replaceAll("\\s+", " ");

        //split
        String[] arr = x.trim().split(" ");

        Stack<String> stack = new Stack<>();

        for (String w : arr) {
            stack.push(w);
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            sb.append(" ");
        }

        return sb.substring(0, sb.length() - 1);


    }

    private int getKey(HashMap<Integer, Integer> map, int n) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(n)) {
                return entry.getKey();
            }
        }
        return -1;
    }


    public String reverseWords5(String s) {
        s = s.replaceAll("^\\s+|\\s+$", ""); //trim

        s = s.replaceAll("\\s+", " "); //remove whitespaces

        String[] arr = split(s); //split

        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            String temp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = temp;

        }

        return String.join(" ", arr);

    }


}
