package leetcode.stack

fun main() {

}
// https://www.youtube.com/watch?v=qkLl7nAwDPo
class MinStack {

    // Stores all values in the stack.
    private val stack = ArrayDeque<Int>()

    // Stores the minimum value at each stack position.
    private val minStack = ArrayDeque<Int>()

    fun push(value: Int) {
        stack.addLast(value)

        // The current minimum is either the new value
        // or the previous minimum.
        val currentMin = minOf(
            value,
            minStack.lastOrNull() ?: value
        )

        minStack.addLast(currentMin)
    }

    fun pop() {
        // Remove the value and its corresponding minimum.
        stack.removeLast()
        minStack.removeLast()
    }

    fun top(): Int {
        return stack.last()
    }

    fun getMin(): Int {
        // The top of minStack is always the current minimum.
        return minStack.last()
    }

    /*
     * Time Complexity:
     * push()    -> O(1)
     * pop()     -> O(1)
     * top()     -> O(1)
     * getMin()  -> O(1)
     *
     * Space Complexity:
     * O(n), where n is the number of elements in the stack.
     *
     * We use two stacks, each containing up to n elements.
     */
}
