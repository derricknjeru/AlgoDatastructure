package leetcode.stack

fun main() {

}

// https://www.youtube.com/watch?v=OQJjh6AT00g
// https://www.youtube.com/watch?v=YFMaChUICCU
fun largestRectangleArea(heights: IntArray): Int {
    val stack = ArrayDeque<Int>() // Stores indices
    var maxArea = 0

    for (i in 0..heights.size) {
        // Use 0 as a sentinel after the last bar
        val currentHeight = if (i == heights.size) 0 else heights[i]

        while (stack.isNotEmpty() && currentHeight < heights[stack.last()]) {
            val height = heights[stack.removeLast()]

            // The current I is the first smaller bar on the right.
            // The new stack.last() is the first smaller bar on the left.
            val width = if (stack.isEmpty()) {
                i
            } else {
                i - stack.last() - 1
            }

            maxArea = maxOf(maxArea, height * width)
        }

        stack.addLast(i)
    }

    // Time: O(n) — each index is pushed and popped at most once
    // Space: O(n) — stack can contain up to n indices
    return maxArea
}

//
fun largestRectangleArea2(heights: IntArray): Int {
    var maxArea = 0
    val stack = ArrayDeque<Pair<Int, Int>>()

    heights.forEachIndexed { i, h ->
        var start = i
        while (stack.isNotEmpty() && stack.last().second > h) {
            val (index, height) = stack.removeLast()
            maxArea = maxOf(maxArea, height * (i - index))
            start = index
        }
        stack.addLast(start to h)
    }

    val n = heights.size
    for ((i, h) in stack) {
        maxArea = maxOf(maxArea, h * (n - i))
    }

    return maxArea
}

