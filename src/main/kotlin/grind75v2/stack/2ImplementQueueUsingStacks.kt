package grind75v2.stack

fun main() {

}

/**
 * push(1) → O(1)
 * push(2) → O(1)
 * push(3) → O(1)
 *
 * pop() → O(n)   ← moves elements
 * pop() → O(1)
 * pop() → O(1)
 */
class MyQueue {
    // Space Complexity: O(n) overall - where n is the total number of elements stored in the queue.

    private val inStack = ArrayDeque<Int>()
    private val outStack = ArrayDeque<Int>()

    // Time Complexity: O(1) - Pushing to the end of ArrayDeque is a constant time operation.
    fun push(x: Int) {
        inStack.addLast(x)
    }

    // Time Complexity: Amortized O(1), Worst-case O(n)
    // - Worst-case occurs when outStack is empty, requiring a transfer of all n elements from inStack.
    // - Amortized O(1) because each element is moved at most once from inStack to outStack across its entire lifecycle.
    fun pop(): Int {
        moveIfNeeded()
        return outStack.removeLast()
    }

    // Time Complexity: Amortized O(1), Worst-case O(n)
    // - Same logic as pop(): transfers elements only when outStack is empty.
    fun peek(): Int {
        moveIfNeeded()
        return outStack.last()
    }

    // Time Complexity: O(1) - Checking if ArrayDeques are empty takes constant time.
    fun empty(): Boolean {
        return inStack.isEmpty() && outStack.isEmpty()
    }

    // Helper method to maintain FIFO order by transferring elements
    // from inStack to outStack when outStack runs out of elements.
    private fun moveIfNeeded() {
        if (outStack.isEmpty()) {
            while (inStack.isNotEmpty()) {
                outStack.addLast(inStack.removeLast())
            }
        }
    }
}