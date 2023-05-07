package revision_v2.Interesting;

import java.util.Stack;

public class BasicCalculatorII {
    /**
     * ///Solution
     * There are multiple variations of this problem like Basic Calculator and Basic Calculator III. This problem is relatively simpler to solve, as we don't have to take care of the parenthesis.
     *
     * The aim is to evaluate the given mathematical expression by applying the basic mathematical rules. The expressions are evaluated from left to right and the order of evaluation depends on the Operator Precedence. Let's understand how we could implement the problem using different approaches.
     *
     * Approach 1: Using Stack
     * Intuition
     *
     * We know that there could be 4 types of operations - addition (+), subtraction (-), multiplication (*) and division (/). Without parenthesis, we know that, multiplication (*) and (\) operations would always have higher precedence than addition (+) and subtraction (-) based on operator precedence rules.
     *
     * img
     *
     * If we look at the above examples, we can make the following observations -
     *
     * If the current operation is addition (+) or subtraction (-), then the expression is evaluated based on the precedence of the next operation.
     * In example 1, 4+3 is evaluated later because the next operation is multiplication (3*5) which has higher precedence. But, in example 2, 4+3 is evaluated first because the next operation is subtraction (3-5) which has equal precedence.
     *
     * If the current operator is multiplication (*) or division (/), then the expression is evaluated irrespective of the next operation. This is because in the given set of operations (+,-,*,/), the * and / operations have the highest precedence and therefore must be evaluated first.
     * In the above example 2 and 3, 4*3 is always evaluated first irrespective of the next operation.
     *
     * Using this intuition let's look at the algorithm to implement the problem.
     *
     * Algorithm
     *
     * Scan the input string s from left to right and evaluate the expressions based on the following rules
     *
     * If the current character is a digit 0-9 ( operand ), add it to the number currentNumber.
     * Otherwise, the current character must be an operation (+,-,*, /). Evaluate the expression based on the type of operation.
     * Addition (+) or Subtraction (-): We must evaluate the expression later based on the next operation. So, we must store the currentNumber to be used later. Let's push the currentNumber in the Stack.
     * Stack data structure follows Last In First Out (LIFO) principle. Hence, the last pushed number in the stack would be popped out first for evaluation. In addition, when we pop from the stack and evaluate this expression in the future, we need a way to determine if the operation was Addition (+) or Subtraction (-). To simplify our evaluation, we can push -currentNumber in a stack if the current operation is subtraction (-) and assume that the operation for all the values in the stack is addition (+). This works because (a - currentNumber) is equivalent to (a + (-currentNumber)).
     *
     * Multiplication (*) or Division (/): Pop the top values from the stack and evaluate the current expression. Push the evaluated value back to the stack.
     * Once the string is scanned, pop from the stack and add to the result.
     */

    /**
     * Given a string s which represents an expression, evaluate this expression and return its value.
     * <p>
     * The integer division should truncate toward zero.
     * <p>
     * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
     * <p>
     * Note.md.md: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "3+2*2"
     * Output: 7
     * Example 2:
     * <p>
     * Input: s = " 3/2 "
     * Output: 1
     * Example 3:
     * <p>
     * Input: s = " 3+5 / 2 "
     * Output: 5
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 3 * 105
     * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
     * s represents a valid expression.
     * All the integers in the expression are non-negative integers in the range [0, 231 - 1].
     * The answer is guaranteed to fit in a 32-bit integer.
     * Accepted
     * 451,176
     * Submissions
     * 1,077,316
     */
     //https://leetcode.com/problems/basic-calculator-ii/
    //https://www.youtube.com/watch?v=GR8Z1zaQzz0
    //https://leetcode.com/problems/basic-calculator-ii/solution/
    //https://www.youtube.com/watch?v=2EErQ25kKE8
    public int calculate2(String s) {

        if (s == null || s.isEmpty()) return 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber);
                } else if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
    //https://leetcode.com/problems/basic-calculator-ii/solution/
    public int calculate(String s) {

        if (s == null || s.isEmpty()) return 0;
        int length = s.length();
        int currentNumber = 0, lastNumber = 0, result = 0;
        char operation = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        result += lastNumber;
        return result;
    }


}
