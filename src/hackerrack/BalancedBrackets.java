package hackerrack;

import java.util.Stack;

public class BalancedBrackets {
    //https://www.hackerrank.com/challenges/balanced-brackets/problem
    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * <p>
     * An input string is valid if:
     * <p>
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Note that an empty string is also considered valid.
     * <p>
     * Example 1:
     * <p>
     * Input: "()"
     * Output: true
     * Example 2:
     * <p>
     * Input: "()[]{}"
     * Output: true
     * Example 3:
     * <p>
     * Input: "(]"
     * Output: false
     * Example 4:
     * <p>
     * Input: "([)]"
     * Output: false
     * Example 5:
     * <p>
     * Input: "{[]}"
     * Output: true
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public boolean isValid2(String s) {
        char[] arr = s.toCharArray();

        if (arr.length == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : arr) {
            switch (c) {
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;

                default:
                    stack.push(c);
                    break;

            }

        }

        return stack.isEmpty();

    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);

            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else {
                return false;
            }

        }

        return stack.isEmpty();

    }

    /**
     *   var isValid = function(s) {
     *     if(s.length<2) return false;
     *
     *     var stack =[];
     *
     *     var arr = s.split(''); //Array.from(s)
     *
     *     for(let c of arr){
     *         switch(c){
     *            case ')':
     *                 if(stack.length==0||stack.pop()!='('){
     *                   return false;
     *                 }
     *                 break;
     *            case '}':
     *                 if(stack.length==0||stack.pop()!='{'){
     *                   return false;
     *                 }
     *                 break;
     *
     *            case ']':
     *                 if(stack.length==0||stack.pop()!='['){
     *                   return false;
     *                 }
     *                 break;
     *
     *             default:
     *                 stack.push(c);
     *                 break;
     *         }
     *
     *     }
     *
     *     return stack.length==0? true : false;
     *
     * };
     */
}
