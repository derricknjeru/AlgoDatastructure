package leetcode_30_days_challenge.week2;

import java.util.Stack;

public class BackspaceStringCompare {
    /**
     * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
     * <p>
     * Example 1:
     * <p>
     * Input: S = "ab#c", T = "ad#c"
     * Output: true
     * Explanation: Both S and T become "ac".
     * Example 2:
     * <p>
     * Input: S = "ab##", T = "c#d#"
     * Output: true
     * Explanation: Both S and T become "".
     * Example 3:
     * <p>
     * Input: S = "a##c", T = "#a#c"
     * Output: true
     * Explanation: Both S and T become "c".
     * Example 4:
     * <p>
     * Input: S = "a#c", T = "b"
     * Output: false
     * Explanation: S becomes "c" while T becomes "b".
     * Note.md.md:
     * <p>
     * 1 <= S.length <= 200
     * 1 <= T.length <= 200
     * S and T only contain lowercase letters and '#' characters.
     * Follow up:
     * <p>
     * Can you solve it in O(N) time and O(1) space?
     */
    public static void main(String[] args) {
        BackspaceStringCompare stringCompare = new BackspaceStringCompare();
        //false
        String S = "ab#c";
        String T = "ad#c";
        //true
        String S1 = "ab##";
        String T1 = "c#d#";
        //true
        String S2 = "a##c";
        String T2 = "#a#c";

        //false
        String S3 = "a#c";
        String T3 = "b";

        System.out.println(stringCompare.backspaceCompare(S3, T3));


    }

    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stackS = new Stack<>();
        //String S = "ab#c";
        char[] chars = S.toCharArray();
        for (char c : chars) {
            if (c != '#') {
                stackS.push(c);
            } else if (!stackS.empty()) {
                stackS.pop();
            }
        }

        Stack<Character> stackT = new Stack<>();
        char[] chart = T.toCharArray();
        for (char c : chart) {
            if (c != '#') {
                stackT.push(c);
            } else if (!stackT.empty()) {
                stackT.pop();
            }
        }

        while (!stackS.empty()) {
            char c = stackS.pop();
            if (stackT.empty() || stackT.pop() != c) {
                return false;
            }
        }

        return stackS.isEmpty() && stackT.isEmpty();

        // convert stack to string
        /**StringBuilder Ssb = new StringBuilder(stackS.size());
         for (Character c : stackS) {
         Ssb.append(c);
         }
         // convert stack to string
         StringBuilder Tsb = new StringBuilder(stackT.size());
         for (Character c : stackT) {
         Tsb.append(c);
         }

         // print it
         System.out.println(Ssb.toString());

         System.out.println(Tsb.toString());

         return Ssb.toString().equalsIgnoreCase(Tsb.toString());*/
    }
}
