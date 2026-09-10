package fundamentals.koans.introductions

fun main() {
    /**
     * Imagine you have several overloads of 'foo()' in Java:
     *
     * public String foo(String name, int number, boolean toUpperCase) {
     *     return (toUpperCase ? name.toUpperCase() : name) + number;
     * }
     * public String foo(String name, int number) {
     *     return foo(name, number, false);
     * }
     * public String foo(String name, boolean toUpperCase) {
     *     return foo(name, 42, toUpperCase);
     * }
     * public String foo(String name) {
     *     return foo(name, 42);
     * }
     * You can replace all these Java overloads with one function in Kotlin.
     * Change the declaration of the foo function in a way that makes the code using foo compile.
     * Use default and named arguments.(https://kotlinlang.org/docs/functions.html#default-arguments)
     *
     * fun foo(name: String, number: Int, toUpperCase: Boolean) =
     *         (if (toUpperCase) name.uppercase() else name) + number
     *
     * fun useFoo() = listOf(
     *         foo("a"),
     *         foo("b", number = 1),
     *         foo("c", toUpperCase = true),
     *         foo(name = "d", number = 2, toUpperCase = true)
     * )
     *
     */

    println(useFoo()) // [a42, b1, C42, D2]

}

fun foo(name: String = "", number: Int = 42, toUpperCase: Boolean = false) =
    (if (toUpperCase) name.uppercase() else name) + number

fun useFoo() = listOf(
    foo("a"),
    foo("b", number = 1),
    foo("c", toUpperCase = true),
    foo(name = "d", number = 2, toUpperCase = true)
)