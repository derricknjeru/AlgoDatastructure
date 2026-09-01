package adventofcode2024

import java.io.File

fun main() {
    val safe =
        getSafeData()
    println(safe)

    val safe2 =
        getSafeData2()
    println(safe2)
}

private fun getSafeData(): Int {
    val filePath =
        "src/main/kotlin/adventofcode2024/resources/day_2.txt"

    /*
     Time Complexity:
     To determine the time complexity of the function getSafeData, we evaluate
     the operations performed at each level of the function:

     File Reading (File(filePath).useLines):
     Reading the file line by line using useLines is O(L), where L is the number of lines in the file.

     Line Processing (lines.forEach):
     For each line:

     - line.split("\\s+".toRegex()): Splitting a line by whitespace is O(W), where W is the number of words (or numbers) in the line.
     - map { it.toInt() }: Mapping each string to an integer is O(W), as each word is converted to an integer.
     - numbers.zipWithNext().all { (a, b) -> b - a in 1..3 }:
       - The zipWithNext operation pairs adjacent elements, which is O(W).
       - The all operation iterates through these pairs to check the condition, which is also O(W).
       - Since this step is repeated for both increasing and decreasing checks, this becomes O(2W) = O(W).

     Overall processing for a single line is O(W + W + W) = O(W).

     Combining Line Costs:
     If there are L lines in the file, and each line has W words on average, the total time complexity is:
     O(L) × O(W) = O(L ⋅ W)

     Space Complexity:
     - Splitting the line (line.split) creates a list of strings, which has a space complexity of O(W).
     - Mapping to integers (map { it.toInt() }) creates a list of integers, also with a space complexity of O(W).
     - The zipWithNext operation creates a list of adjacent pairs, requiring additional O(W) space.
     - These lists are created per line and do not persist beyond processing that line.

     For L lines, at any given time, the maximum space required is O(W) for processing a single line.
     Therefore, the overall space complexity is O(W), where W is the maximum number of words (or numbers) per line.
    */
    var ret = 0
    File(filePath).useLines { lines ->
        lines.forEach { line ->
            val numbers = line.split("\\s+".toRegex()).map { it.toInt() }
            val isIncreasing = numbers.zipWithNext().all { (a, b) -> b - a in 1..3 }
            val isDecreasing = numbers.zipWithNext().all { (a, b) -> a - b in 1..3 }
            ret += when {
                isIncreasing || isDecreasing -> 1
                else -> 0
            }
        }
    }
    return ret
}

private fun getSafeData2(): Int {
    val filePath =
        "src/main/kotlin/adventofcode2024/resources/day_2.txt"

    /*
     Time Complexity:
     1. Outer Loop (File Reading):
        - The function processes each line of the file, so the outer loop is proportional to the number of lines L: O(L).

     2. Line Processing:
        - For each line:
          - `line.split("\\s+".toRegex())`: Splitting the line into words is O(W), where W is the number of words (or numbers) in the line.
          - `map { it.toInt() }`: Mapping each string to an integer is also O(W), as it processes each word in the list.
          - Inner Loop (`for` loop):
            - Iterates through the numbers in the line once, performing constant-time operations to compute the difference and update flags. This is O(W).

     Total Per Line:
     - Splitting + Mapping + Inner Loop = O(W) + O(W) + O(W) = O(W).

     Combining All Lines:
     - If there are L lines and each line has W numbers on average, the total complexity is:
       O(L) × O(W) = O(L ⋅ W).

     Space Complexity:
     1. Per Line Processing:
        - `line.split("\\s+".toRegex())` creates a list of strings, which takes O(W) space.
        - `map { it.toInt() }` creates a list of integers, which also takes O(W) space.
        - The `for` loop uses constant additional space for variables (`isIncreasing`, `isDecreasing`, `diff`).

     2. File Reading:
        - The file is read line by line using `useLines`, so there is no need to load the entire file into memory at once.

     Total Space:
     - At any given time, the maximum space used is proportional to the number of numbers in a single line:
       O(W).

     Final Complexity:
     - Time Complexity: O(L ⋅ W), where L is the number of lines and W is the average number of words (or numbers) per line.
     - Space Complexity: O(W), where W is the number of numbers in the largest line.
    */
    var ret = 0
    File(filePath).useLines { lines ->
        lines.forEach { line ->
            val numbers = line.split("\\s+".toRegex()).map { it.toInt() }
            var isIncreasing = true
            var isDecreasing = true
            for (i in 1 until numbers.size) {
                val diff = numbers[i] - numbers[i - 1]
                if (diff !in 1..3) isIncreasing = false
                if (-diff !in 1..3) isDecreasing = false
                if (!isIncreasing && !isDecreasing) break
            }
            if (isIncreasing || isDecreasing) ret++
        }
    }
    return ret
}

