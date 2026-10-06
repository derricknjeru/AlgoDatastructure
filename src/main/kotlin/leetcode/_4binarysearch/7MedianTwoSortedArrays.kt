package leetcode._4binarysearch

fun main() {

}
// https://www.youtube.com/watch?v=fu2cD_6E8Hw
/**
 * **Complexity:** `O(log(min(m, n)))` time and `O(1)` space.
 * The algorithm partitions both arrays so that every value on the left is
 * less than or equal to every value on the right.
 * It then derives the median from the boundary values.
 */
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    // Always binary-search the smaller array
    if (nums1.size > nums2.size) {
        return findMedianSortedArrays(nums2, nums1)
    }

    val m = nums1.size
    val n = nums2.size

    var left = 0
    var right = m

    while (left <= right) {
        val partition1 = (left + right) / 2
        val partition2 = (m + n + 1) / 2 - partition1

        val maxLeft1 =
            if (partition1 == 0) Int.MIN_VALUE else nums1[partition1 - 1]
        val minRight1 =
            if (partition1 == m) Int.MAX_VALUE else nums1[partition1]

        val maxLeft2 =
            if (partition2 == 0) Int.MIN_VALUE else nums2[partition2 - 1]
        val minRight2 =
            if (partition2 == n) Int.MAX_VALUE else nums2[partition2]

        if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
            return if ((m + n) % 2 == 0) {
                (maxOf(maxLeft1, maxLeft2).toDouble() +
                        minOf(minRight1, minRight2).toDouble()) / 2.0
            } else {
                maxOf(maxLeft1, maxLeft2).toDouble()
            }
        }

        if (maxLeft1 > minRight2) {
            right = partition1 - 1
        } else {
            left = partition1 + 1
        }
    }

    throw IllegalArgumentException("Input arrays must be sorted")
}

//Important for below LeetCode: The problem specifically requires an O(log(m + n)) solution, so your merge solution is logically correct but does not satisfy the intended complexity requirement.

fun findMedianSortedArrays2(nums1: IntArray, nums2: IntArray): Double {
    val m = nums1.size
    val n = nums2.size

    val nums = IntArray(m + n)

    var i = 0
    var j = 0
    var k = 0

    // Merge the two sorted arrays
    while (i < m && j < n) {
        if (nums1[i] < nums2[j]) {
            nums[k++] = nums1[i++]
        } else {
            nums[k++] = nums2[j++]
        }
    }

    // Copy remaining elements from nums1
    while (i < m) {
        nums[k++] = nums1[i++]
    }

    // Copy remaining elements from nums2
    while (j < n) {
        nums[k++] = nums2[j++]
    }

    // Even number of elements
    if ((m + n) % 2 == 0) {
        val middle1 = nums[(m + n - 1) / 2]
        val middle2 = nums[(m + n) / 2]

        return (middle1 + middle2) / 2.0
    }

    // Odd number of elements
    return nums[(m + n - 1) / 2].toDouble()
}