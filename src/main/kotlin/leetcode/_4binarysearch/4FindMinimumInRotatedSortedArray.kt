package leetcode._4binarysearch

fun main() {

}

// https://www.youtube.com/watch?v=bkJeA7LnJww
// https://www.youtube.com/watch?v=nIVW4P8b1VA
// Time Complexity: O(log n) - The search space is halved in each iteration using binary search.
// Space Complexity: O(1) - Only a constant amount of extra space is used.
fun findMin(nums: IntArray): Int {
    var left = 0
    var right = nums.lastIndex
    //var right = nums.size - 1

    while (left < right) {
        val mid = left + (right - left) / 2

        if (nums[mid] > nums[right]) {
            // Minimum is in the right half
            left = mid + 1
        } else {
            // Minimum is in the left half (including mid)
            right = mid
        }
    }

    return nums[left]
}


fun findMin2(nums: IntArray): Int {
    return inbuiltMin(nums)
}

// O(n)
fun inbuiltMin(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    return nums.min() // minOrNull() ?: 0
}

// O(n)
fun min(nums: IntArray): Int {
    var min = Int.MAX_VALUE
    for (num in nums) {
        min = minOf(num, min)
    }
    return min
}
