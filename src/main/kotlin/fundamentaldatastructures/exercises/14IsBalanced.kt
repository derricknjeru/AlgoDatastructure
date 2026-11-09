package fundamentaldatastructures.exercises

fun isBalanced(s: String): String {
    val pairs = mapOf(
        ')' to '(',
        ']' to '[',
        '}' to '{'
    )

    val stack = ArrayDeque<Char>()

    for (character in s) {
        if (character in pairs.values) {
            stack.addLast(character)
        } else {
            if (
                stack.isEmpty() ||
                stack.removeLast() != pairs[character]
            ) {
                return "NO"
            }
        }
    }

    return if (stack.isEmpty()) "YES" else "NO"
}

fun main() {

}