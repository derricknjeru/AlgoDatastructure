package leetcode.stack

fun main() {

}

// https://www.youtube.com/watch?v=BjDczS5uQ-g
class Solution {
    fun calculate(s: String): Int {
        // TIME COMPLEXITY: O(N) where N is the length of string `s`.
        // We iterate through each character in the string exactly once.
        //
        // SPACE COMPLEXITY: O(N) worst-case.
        // In the worst case (deeply nested parentheses like "(((1+2)))"),
        // the stack will store up to N/2 entries. Space is O(1) if there are no parentheses.

        var result = 0
        var currentNumber = 0
        var sign = 1 // 1 for '+', -1 for '-'
        val stack = java.util.ArrayDeque<Int>() // O(N) space memory allocation

        for (char in s) {
            when {
                char.isDigit() -> {
                    // O(1) operation per digit
                    currentNumber = currentNumber * 10 + (char - '0')
                }
                char == '+' || char == '-' -> {
                    // O(1) operation
                    result += sign * currentNumber
                    currentNumber = 0
                    sign = if (char == '+') 1 else -1
                }
                char == '(' -> {
                    // O(1) push operations
                    // Pushes result and sign to pause current scope evaluation
                    stack.push(result)
                    stack.push(sign)

                    // Reset contexts for new sub-expression
                    result = 0
                    sign = 1
                }
                char == ')' -> {
                    // O(1) pop and evaluate operations
                    result += sign * currentNumber
                    currentNumber = 0

                    // Restore previous scope state from stack
                    val prevSign = stack.pop()
                    val prevResult = stack.pop()

                    // Combine outer scope with completed inner scope result
                    result = prevResult + (prevSign * result)
                }
            }
        }

        // O(1) final addition for trailing numbers
        result += sign * currentNumber
        return result
    }
}