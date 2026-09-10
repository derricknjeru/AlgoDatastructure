package fundamentals.koans.introductions

fun main() {
    /**
     * Named arguments
     * Make the function joinOptions() return the list in a JSON format (for example, [a, b, c]) by specifying only two arguments.
     * Default and named arguments(https://kotlinlang.org/docs/functions.html#named-arguments) help to minimize the number of overloads and improve the readability of the function invocation.
     * The library function joinToString is declared with default values for parameters:
     *
     * fun joinToString(
     *     separator: String = ", ",
     *     prefix: String = "",
     *     postfix: String = "",
     *     /* ... */
     * ): String
     * It can be called on a collection of Strings.
     *
     * fun joinOptions(options: Collection<String>) =
     *         options.joinToString(TODO())
     */

    val options  = listOf("a", "b", "c")
    println(joinOptions(options = options)) // [a, b, c]

}

fun joinOptions(options: Collection<String>) =
    options.joinToString(prefix = "[", postfix = "]")