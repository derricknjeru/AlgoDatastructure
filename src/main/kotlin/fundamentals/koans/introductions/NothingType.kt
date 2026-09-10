package fundamentals.koans.introductions
import java.lang.IllegalArgumentException

fun main() {
    /**
     * Nothing type https://kotlinlang.org/docs/exceptions.html#the-nothing-type
     * Nothing type can be used as a return type for a function that always throws an exception.
     * When you call such a function, the compiler uses the information that the execution doesn't continue beyond the function.
     * Specify Nothing return type for the failWithWrongAge function. Note that without the Nothing type,
     * the checkAge function doesn't compile because the compiler assumes the age can be null.
     *
     * import java.lang.IllegalArgumentException
     *
     * fun failWithWrongAge(age: Int?) {
     *     throw IllegalArgumentException("Wrong age: $age")
     * }
     *
     * fun checkAge(age: Int?) {
     *     if (age == null || age !in 0..150) failWithWrongAge(age)
     *     println("Congrats! Next year you'll be ${age + 1}.")
     * }
     *
     * fun main() {
     *     checkAge(10)
     * }
     */
    checkAge(10)
}


fun failWithWrongAge(age: Int?):Nothing {
    throw IllegalArgumentException("Wrong age: $age")
}

fun checkAge(age: Int?) {
    if (age == null || age !in 0..150) failWithWrongAge(age)
    println("Congrats! Next year you'll be ${age + 1}.")
}
