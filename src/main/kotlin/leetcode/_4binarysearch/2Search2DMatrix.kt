package leetcode._4binarysearch

import kotlin.math.ceil

fun main() {

}

// https://www.youtube.com/watch?v=HxnSEWzBeKI

fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    val rows = matrix.size
    val cols = matrix[0].size

    var left = 0
    var right = rows * cols - 1

    while (left <= right) {
        val mid = left + (right - left) / 2

        // Convert the 1D index back to row and column
        val row = mid / cols
        val col = mid % cols

        val value = matrix[row][col]

        if (value == target) {
            return true
        } else if (value < target) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }

    val x = intArrayOf(left, right)
    x.max()

    var hours = 0.0
    var hours2 = 0

    for (n in x) {
        hours += ceil((n / 2).toDouble())
        val s = 2
        hours2 += n / s + if (n % s == 0) 0 else 1
    }

    return false
}


/*
Time:  O(log(m * n))
Space: O(1)
*/