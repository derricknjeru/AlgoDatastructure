package leetcode.stack

fun main() {

}
// https://www.youtube.com/watch?v=iu0082c4HDE
/**
 * Evaluates an expression in Reverse Polish Notation using an array as a stack.
 *
 * Time: O(n) - each token is processed once.
 * Space: O(n) - stack can hold up to n values.
 */
fun evalRPN(tokens: Array<String>): Int {
    // Use the tokens array size as the maximum stack size.
    val stack = IntArray(tokens.size)
    var top = 0

    for (token in tokens) {
        when (token) {
            "+" -> {
                // Combine the top two operands.
                stack[top - 2] = stack[top - 2] + stack[top - 1]
                top--
            }

            "-" -> {
                // Left operand is below the right operand.
                stack[top - 2] = stack[top - 2] - stack[top - 1]
                top--
            }

            "*" -> {
                // Combine the top two operands.
                stack[top - 2] = stack[top - 2] * stack[top - 1]
                top--
            }

            "/" -> {
                // Left operand is below the right operand.
                stack[top - 2] = stack[top - 2] / stack[top - 1]
                top--
            }

            else -> {
                // Push the number onto the stack.
                stack[top++] = token.toInt()
            }
        }
    }

    // The final result is the only value left on the stack.
    return stack[0]
}


/**
 * Evaluates an expression written in Reverse Polish Notation.
 *
 * Time: O(n) - each token is processed once.
 * Space: O(n) - stack can hold up to n values.
 */
fun evalRPN2(tokens: Array<String>): Int {
    val stack = ArrayDeque<Int>()

    for (token in tokens) {
        when (token) {
            // Order doesn't matter for addition.
            "+" -> stack.addLast(stack.removeLast() + stack.removeLast())

            "-" -> {
                val a = stack.removeLast() // right operand
                val b = stack.removeLast() // left operand
                stack.addLast(b - a)
            }

            // Order doesn't matter for multiplication.
            "*" -> stack.addLast(stack.removeLast() * stack.removeLast())

            "/" -> {
                val a = stack.removeLast() // right operand
                val b = stack.removeLast() // left operand
                stack.addLast(b / a)
            }

            // Token is an operand.
            else -> stack.addLast(token.toInt())
        }
    }

    return stack.removeLast()
}
