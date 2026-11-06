package fundamentaldatastructures.exercises


fun main() {

}

/*
 * Complete the 'matchingStrings' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. STRING_ARRAY stringList
 *  2. STRING_ARRAY queries
 */

fun matchingStrings(stringList: Array<String>, queries: Array<String>): Array<Int> {
    val occurrenceMap = mutableMapOf<String, Int>()
    for (str in stringList) {
        occurrenceMap[str] = occurrenceMap.getOrDefault(str, 0) + 1
    }
    val res = IntArray(queries.size)
    var index = 0
    for (q in queries) {
        res[index++] = occurrenceMap[q] ?: 0
    }
    return res.toTypedArray()
}

fun matchingStrings2(stringList: Array<String>, queries: Array<String>): Array<Int> {
    val occurrenceMap = stringList.groupingBy { it }.eachCount()
    val res = IntArray(queries.size)
    for (i in queries.indices) {
        res[i] = occurrenceMap[queries[i]] ?: 0
    }
    return res.toTypedArray()
}

fun matchingStrings3(stringList: Array<String>, queries: Array<String>): Array<Int> {
    val occurrenceMap = stringList.groupingBy { it }.eachCount()
    return Array(queries.size) { index ->
        occurrenceMap[queries[index]] ?: 0
    }
}

fun main(args: Array<String>) {
    val stringListCount = readln().trim().toInt()

    val stringList = Array<String>(stringListCount, { "" })
    for (i in 0 until stringListCount) {
        val stringListItem = readln()
        stringList[i] = stringListItem
    }

    val queriesCount = readln().trim().toInt()

    val queries = Array(queriesCount, { "" })
    for (i in 0 until queriesCount) {
        val queriesItem = readln()
        queries[i] = queriesItem
    }

    val res = matchingStrings(stringList, queries)

    println(res.joinToString("\n"))
}
