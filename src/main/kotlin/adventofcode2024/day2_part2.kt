package adventofcode2024

import java.io.File

fun main() {
    val safe = getSafeData()
    println(safe)

    val safe2 = getSafeData2()
    println(safe2)
}

private fun getSafeData(): Int {
    val filePath =
        "src/main/kotlin/adventofcode2024/resources/day_2.txt"

    /*
     Time Complexity: O(L ⋅ W^2)
     - O(L) for processing all lines in the file.
     - O(W^2) for checking the sequence safety, which includes W checks for increasing or decreasing
       and W removals (each removal triggers a safety check).

     Space Complexity: O(W)
     - O(W) for the numbers list created from each line.
    */
    var ret = 0
    File(filePath).useLines { lines ->
        lines.forEach { line ->
            val numbers = line.split("\\s+".toRegex()).map { it.toInt() }
            val isSafe = isSequenceSafe(numbers)
            ret += if (isSafe) 1 else 0
        }
    }
    return ret
}

private fun isSequenceSafe(numbers: List<Int>): Boolean {
    /*
     Time Complexity: O(W^2)
     - O(W) to check if the sequence is increasing or decreasing.
     - O(W ⋅ W) to iterate through all possible removals and check safety for each modified sequence.

     Space Complexity: O(W)
     - O(W) for the modified sequence created during each removal.
    */
    if (isIncreasing(numbers) || isDecreasing(numbers)) return true

    for (i in numbers.indices) {
        val modifiedNumbers = numbers.toMutableList().apply { removeAt(i) }
        if (isIncreasing(modifiedNumbers) || isDecreasing(modifiedNumbers)) return true
    }

    return false
}

private fun isIncreasing(numbers: List<Int>): Boolean {
    /*
     Time Complexity: O(W)
     - Iterates through the list once to check adjacent differences.

     Space Complexity: O(1)
     - No additional memory allocation beyond simple variables.
    */
    return numbers.zipWithNext().all { (a, b) -> b - a in 1..3 }
}

private fun isDecreasing(numbers: List<Int>): Boolean {
    /*
     Time Complexity: O(W)
     - Iterates through the list once to check adjacent differences.

     Space Complexity: O(1)
     - No additional memory allocation beyond simple variables.
    */
    return numbers.zipWithNext().all { (a, b) -> a - b in 1..3 }
}

private fun getSafeData2(): Int {
    val filePath =
        "src/main/kotlin/adventofcode2024/resources/day_2.txt"

    /*
     Time Complexity: O(L ⋅ W^2)
     - O(L) for processing all lines in the file.
     - O(W^2) for checking sequence safety, similar to getSafeData.

     Space Complexity: O(W)
     - O(W) for the numbers list created from each line.
    */
    var ret = 0
    File(filePath).useLines { lines ->
        lines.forEach { line ->
            val numbers = line.split("\\s+".toRegex()).map { it.toInt() }
            if (isSequenceSafe2(numbers)) ret++
        }
    }
    return ret
}

private fun isSequenceSafe2(numbers: List<Int>): Boolean {
    /*
     Time Complexity: O(W^2)
     - O(W) to check if the sequence is strictly safe.
     - O(W ⋅ W) to iterate through all possible removals and check strict safety for each modified sequence.

     Space Complexity: O(W)
     - O(W) for the modified sequence created during each removal.
    */
    if (isStrictlySafe(numbers)) return true

    for (i in numbers.indices) {
        val modifiedNumbers = numbers.toMutableList().apply { removeAt(i) }
        if (isStrictlySafe(modifiedNumbers)) return true
    }
    return false
}

private fun isStrictlySafe(numbers: List<Int>): Boolean {
    /*
     Time Complexity: O(W)
     - Iterates through the list once to check increasing and decreasing conditions.

     Space Complexity: O(1)
     - No additional memory allocation beyond simple variables.
    */
    var isIncreasing = true
    var isDecreasing = true

    for (i in 1 until numbers.size) {
        val diff = numbers[i] - numbers[i - 1]
        if (diff !in 1..3) isIncreasing = false
        if (-diff !in 1..3) isDecreasing = false
        if (!isIncreasing && !isDecreasing) return false
    }

    return true
}
