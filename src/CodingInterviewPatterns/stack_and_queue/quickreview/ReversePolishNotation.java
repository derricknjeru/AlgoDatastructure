package CodingInterviewPatterns.stack_and_queue.quickreview;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ReversePolishNotation {
    /**
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * <p>
     * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
     * <p>
     * Note.md.md.md.md that division between two integers should truncate toward zero.
     * <p>
     * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: tokens = ["2","1","+","3","*"]
     * Output: 9
     * Explanation: ((2 + 1) * 3) = 9
     * Example 2:
     * <p>
     * Input: tokens = ["4","13","5","/","+"]
     * Output: 6
     * Explanation: (4 + (13 / 5)) = 6
     * Example 3:
     * <p>
     * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     * Output: 22
     * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= tokens.length <= 104
     * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
     *
     * @param tokens
     * @return
     */

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();

    }

    public int evalRPN2(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String cur : tokens) {

            if (cur.equals("+")) {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(first + second);
            } else if (cur.equals("-")) {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(first - second);
            } else if (cur.equals("*")) {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(first * second);
            } else if (cur.equals("/")) {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(first / second);
            } else {
                stack.push(Integer.valueOf(cur));
            }
        }


        return stack.pop();
    }
}
