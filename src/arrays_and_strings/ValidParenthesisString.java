package arrays_and_strings;

import java.util.Stack;

public class ValidParenthesisString {
    /**
     * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:
     * <p>
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
     * An empty string is also valid.
     * Example 1:
     * Input: "()"
     * Output: True
     * Example 2:
     * Input: "(*)"
     * Output: True
     * Example 3:
     * Input: "(*))"
     * Output: True
     * Note.md.md.md.md:
     * The string size will be in the range [1, 100].
     */
    public static void main(String[] args) {
        ValidParenthesisString validParent = new ValidParenthesisString();
        String s = "*))(*";
        System.out.println(validParent.checkValidString(s));
    }

    //Explanation
    //{@link https://www.youtube.com/watch?v=jNZu72XP-xQ}
    public boolean checkValidString(String s) {
        /** we store every openning blacked and asteric
         and pop it when ever we see closing blacket.
         */
        if (s.isEmpty()) {
            return true;
        }

        Stack<Integer> numLeft = new Stack<>();
        Stack<Integer> numAst = new Stack<>();


        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='('){
                numLeft.push(i);
            }else if(s.charAt(i)=='*'){
                numAst.push(i);
            }else if(s.charAt(i)==')' && !numLeft.isEmpty()){
                numLeft.pop();
            }else if(s.charAt(i)==')' && !numAst.isEmpty()){
                numAst.pop();
            }else{
                return false;
            }
        }


        while (!numLeft.isEmpty() && !numAst.isEmpty()) {
            if (numLeft.peek() > numAst.peek()) {
                return false;
            }
            numLeft.pop();
            numAst.pop();
        }

        return numLeft.isEmpty();


    }

    //Explanation https://leetcode.com/problems/valid-parenthesis-string/solution/
    public boolean checkValidString2(String s) {
        int low = 0;
        int high = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                low++;
                high++;
            } else if (s.charAt(i) == ')') {
                if (low > 0) {
                    low--;
                }
                high--;
            } else {
                if (low > 0) {
                    low--;
                }
                high++;
            }
            if (high < 0) {
                return false;
            }
        }
        return low == 0;

    }
}
