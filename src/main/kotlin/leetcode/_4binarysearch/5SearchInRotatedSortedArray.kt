package leetcode._4binarysearch

fun main() {
}


/**
 * // https://www.youtube.com/watch?v=j7at6HbkX48
 *
 * Finds the rotation pivot with binary search, then binary-searches the sorted part
 * that could contain the target.
 *
 * Time: O(log n) — two binary searches, O(log n) + O(log n).
 * Space: O(1).
 */
fun search(nums: IntArray, target: Int): Int {
    if (nums.isEmpty()) return -1

    var left = 0
    var right = nums.lastIndex

    // O(log n): index of the smallest element (rotation point)
    val pivotIndex = findPivot(nums, left, right)

    // O(1): choose the sorted part that could contain the target
    if (target >= nums[pivotIndex] && target <= nums[right]) {
        left = pivotIndex
    } else {
        right = pivotIndex
    }

    // O(log n): standard binary search on the chosen part
    while (left <= right) {
        val mid = left + (right - left) / 2
        when {
            nums[mid] == target -> return mid
            nums[mid] > target -> right = mid - 1
            else -> left = mid + 1
        }
    }
    return -1
}

// Time: O(log n), Space: O(1)
private fun findPivot(nums: IntArray, start: Int, end: Int): Int {
    var l = start
    var r = end
    while (l < r) {
        val mid = l + (r - l) / 2
        if (nums[mid] > nums[r]) {
            l = mid + 1
        } else {
            r = mid
        }
    }
    return l
}
