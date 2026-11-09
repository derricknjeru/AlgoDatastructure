package fundamentaldatastructures.exercises

fun main() {

}

fun minSteps(s: String, t: String): Int {
    val frequencies = IntArray(26)

    for (character in s) {
        frequencies[character - 'a']++
    }

    for (character in t) {
        frequencies[character - 'a']--
    }

    var replacements = 0

    for (difference in frequencies) {
        if (difference > 0) {
            replacements += difference
        }
    }



    return replacements
}