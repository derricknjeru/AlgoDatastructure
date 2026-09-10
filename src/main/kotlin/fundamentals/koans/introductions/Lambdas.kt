package fundamentals.koans.introductions

fun main() {
    /**
     * Kotlin supports functional programming. Learn about lambdas https://kotlinlang.org/docs/lambdas.html#lambda-expressions-and-anonymous-functions. in Kotlin.
     * Pass a lambda to the any function to check if the collection contains an even number.
     * The any function gets a predicate as an argument and returns true if at least one element satisfies the predicate.
     *
     * fun containsEven(collection: Collection<Int>): Boolean =
     *         collection.any { TODO() }
     */
}


fun containsEven(collection: Collection<Int>): Boolean =
    collection.any { it % 2 == 0 }

fun containsEven2(collection: Collection<Int>): Boolean =
    collection.any { y -> y % 2 == 0 }