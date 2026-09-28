package leetcode.stack

fun main() {

}

//https://www.youtube.com/watch?v=cTBiBSnjO3c

// Time Complexity: O(N) - Each index is pushed onto the stack once and popped at most once.
// Space Complexity: O(N) - Stack holds at most N elements in the worst case (e.g., strictly decreasing input).
fun dailyTemperatures(temperatures: IntArray): IntArray {
    val result = IntArray(temperatures.size)
    // ArrayDeque is used as an efficient stack in Kotlin
    val stack = ArrayDeque<Int>()

    for (currDay in temperatures.indices) {
        val currTemp = temperatures[currDay]

        // Maintain a monotonically decreasing stack.
        // Pop indices whose temperature is strictly less than currTemp.
        while (stack.isNotEmpty() && temperatures[stack.last()] < currTemp) {
            val prevDay = stack.removeLast()
            result[prevDay] = currDay - prevDay
        }

        stack.addLast(currDay)
    }

    return result
}

