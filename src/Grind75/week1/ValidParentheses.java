package Grind75.week1;

import java.util.Stack;

public class ValidParentheses {
    //https://leetcode.com/problems/valid-parentheses/
    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";

        Solution solution = new Solution();

        System.out.println("Is s1 valid? " + solution.isValid(s1)); //true
        System.out.println("Is s2 valid? " + solution.isValid(s2)); //true
        System.out.println("Is s3 valid? " + solution.isValid(s3)); //false
    }

    //The time complexity of the isValid method is O(n), where n is the length of the input string s.
    static class Solution {
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
    }

}
