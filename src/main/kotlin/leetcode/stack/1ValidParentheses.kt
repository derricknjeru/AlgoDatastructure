package leetcode.stack

fun main() {

}


// Time Complexity: O(n) - We iterate through the string of length n once.
//                  Map lookups, stack pushes, and pops are all O(1) operations.
// Space Complexity: O(n) - In the worst case (e.g., "((((("), the stack holds n/2
//                   or up to n elements. The hash map takes O(1) auxiliary space
//                   since its size is fixed at 3 key-value pairs.
fun isValid(s: String): Boolean {
    val stack = ArrayDeque<Char>()
    val pairs = mapOf(
        ')' to '(',
        '}' to '{',
        ']' to '[',
    )
    for (c in s) {
        if (c in pairs.values) {
            stack.addLast(c)
        } else {
            if (stack.isEmpty() || stack.removeLast() != pairs[c]) return false
        }
    }
    return stack.isEmpty()
}


fun isValid3(s: String): Boolean {
    val stack = ArrayDeque<Char>()

    for (char in s) {
        when (char) {
            '(', '{', '[' -> stack.addLast(char)

            ')' -> {
                if (stack.isEmpty() || stack.last() != '(') {
                    return false
                }
                stack.removeLast()
            }

            '}' -> {
                if (stack.isEmpty() || stack.last() != '{') {
                    return false
                }
                stack.removeLast()
            }

            ']' -> {
                if (stack.isEmpty() || stack.last() != '[') {
                    return false
                }
                stack.removeLast()
            }

            else -> return false
        }
    }

    return stack.isEmpty()
}

