package leetcode.stack


/**
 * STACK DATA STRUCTURE IN KOTLIN
 *
 * A Stack is a linear data structure that follows LIFO:
 *
 * LIFO = Last In, First Out
 *
 * Think of a stack of plates:
 * - You add a plate to the top.
 * - You remove the top plate first.
 *
 * Main stack operations:
 *
 * push()    -> Add an element to the top.
 * pop()     -> Remove the top element.
 * peek()    -> View the top element without removing it.
 * isEmpty() -> Check whether the stack is empty.
 *
 * Time complexity:
 * - Push:    O(1)
 * - Pop:     O(1)
 * - Peek:    O(1)
 * - isEmpty: O(1)
 *
 *
 * KOTLIN STACK USING ARRAYDEQUE
 *
 * Kotlin's ArrayDeque can be used as a stack.
 *
 * We use the end of the deque as the top of the stack:
 *
 * addLast()       -> Push
 * removeLast()    -> Pop
 * last()          -> Peek
 * isEmpty()       -> Check if empty
 *
 *
 * Example:
 *
 * val stack = ArrayDeque<Int>()
 *
 * stack.addLast(10)
 * stack.addLast(20)
 * stack.addLast(30)
 *
 * println(stack.last())          // 30
 * println(stack.removeLast())   // 30
 * println(stack.removeLast())   // 20
 *
 *
 * ============================================================
 * LEETCODE 20: VALID PARENTHESES
 * ============================================================
 *
 * Problem:
 *
 * Given a string containing only the characters:
 *
 *     '(', ')', '{', '}', '[' and ']'
 *
 * Determine if the input string is valid.
 *
 * A string is valid if:
 *
 * 1. Every opening bracket has a matching closing bracket.
 * 2. Brackets close in the correct order.
 * 3. Every closing bracket matches the most recent unmatched
 *    opening bracket.
 *
 * Examples:
 *
 *     "()"      -> true
 *     "()[]{}"  -> true
 *     "(]"      -> false
 *     "([)]"    -> false
 *     "{[]}"    -> true
 *
 *
 * APPROACH: USE A STACK
 *
 * 1. If the current character is an opening bracket:
 *      Push it onto the stack.
 *
 * 2. If the current character is a closing bracket:
 *      - If the stack is empty, return false.
 *      - Pop the top opening bracket.
 *      - Check if it matches the closing bracket.
 *      - If it does not match, return false.
 *
 * 3. After processing all characters:
 *      The stack must be empty.
 *
 * Why must the stack be empty at the end?
 *
 * Because an empty stack means every opening bracket has been
 * matched and closed.
 *
 *
 * IMPORTANT LINE:
 *
 *     stack.removeLast() != pairs[ch]
 *
 * Suppose the current character is ']'.
 *
 *     pairs[']'] == '['
 *
 * The top of the stack must therefore be '['.
 *
 * If the top is something else, the brackets are invalid.
 *
 *
 * DRY RUN: "([{}])"
 *
 * Character    Action          Stack
 * ---------    ------          -----
 *     (        Push            (
 *     [        Push            ( [
 *     {        Push            ( [ {
 *     }        Pop {           ( [
 *     ]        Pop [           (
 *     )        Pop (           Empty
 *
 * Final result: true
 *
 *
 * DRY RUN: "([)]"
 *
 * Character    Action          Stack
 * ---------    ------          -----
 *     (        Push            (
 *     [        Push            ( [
 *     )        Expected '('    Top is '[' -> false
 *
 * The closing ')' does not match the most recent opening '['.
 *
 *
 * COMPLEXITY
 *
 * Let n be the length of the string.
 *
 * Time Complexity: O(n)
 * - Each character is processed once.
 * - Each bracket is pushed and popped at most once.
 *
 * Space Complexity: O(n)
 * - In the worst case, all characters are opening brackets.
 */

fun main() {
    // The name deque is short for "double ended queue" and is usually pronounced "deck".
    //val stack = ArrayDeque<Int>()
    val stack = Stack<Int>()

    // Push elements
    stack.push(10)
    stack.push(20)
    stack.push(30)


    for (s in stack) {
        println("Derrick $s")
    }

    // [10, 20, 30]
    println(stack)

    // Peek: view the top
    println(stack.peek()) // 30

    // Pop: remove the top
    println(stack.pop()) // 30
    println(stack.pop()) // 20

    println(stack) // [10]

    // Check if empty
    println(stack.isEmpty()) // false

    for(char in "Derrick"){
        when(char) {
            'd','e', 'r', -> {

            }
        }
    }
}

/**
 * > Task :grind75v2.stack.StackKt.main()
 * Derrick 10
 * Derrick 20
 * Derrick 30
 * [10, 20, 30]
 * 30
 * 30
 * 20
 * [10]
 * false
 */

/**
 * A generic, LIFO (Last-In, First-Out) Stack data structure implementation.
 *
 * Implements [Iterable] to allow iteration from the top of the stack to the bottom
 * without mutating or popping its elements.
 *
 * @param T The type of elements held in this stack.
 */
class Stack<T> : Iterable<T> {

    // Internal backing storage using ArrayDeque for O(1) push and pop operations
    private val storage = ArrayDeque<T>()

    /**
     * The number of elements currently in the stack.
     */
    val size: Int
        get() = storage.size

    /**
     * Checks whether the stack is empty.
     *
     * @return `true` if the stack contains no elements, `false` otherwise.
     */
    fun isEmpty(): Boolean = storage.isEmpty()

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param element The item to be added.
     */
    fun push(element: T) {
        storage.addLast(element)
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return The popped element, or `null` if the stack is empty.
     */
    fun pop(): T? = storage.removeLastOrNull()

    /**
     * Retrieves, but does not remove, the element at the top of the stack.
     *
     * @return The top element, or `null` if the stack is empty.
     */
    fun peek(): T? = storage.lastOrNull()

    override fun toString(): String = storage.toString()

    override fun iterator(): Iterator<T> = storage.iterator()
}

