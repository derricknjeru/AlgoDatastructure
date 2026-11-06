package fundamentaldatastructures.exercises

fun main() {
    val logs = arrayOf(
        "dig1 8 1 5 1",
        "let1 art can",
        "dig2 3 6",
        "let2 own kit dig",
        "let3 art zero"
    )

    println(reorderLogFiles(logs).joinToString("\n"))
}


fun reorderLogFiles(logs: Array<String>): Array<String> {
    return logs.sortedWith { first, second ->
        val firstParts = first.split(" ", limit = 2)
        val secondParts = second.split(" ", limit = 2)

        val firstIsDigit = firstParts[1][0].isDigit()
        val secondIsDigit = secondParts[1][0].isDigit()

        when {
            !firstIsDigit && !secondIsDigit -> {
                val contentComparison =
                    firstParts[1].compareTo(secondParts[1])

                if (contentComparison != 0) {
                    contentComparison
                } else {
                    firstParts[0].compareTo(secondParts[0])
                }
            }

            !firstIsDigit && secondIsDigit -> -1
            firstIsDigit && !secondIsDigit -> 1
            else -> 0
        }
    }.toTypedArray()
}