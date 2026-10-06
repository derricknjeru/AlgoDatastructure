package leetcode._3twopointers

fun main() {

}

// Time: O(n); space: O(1).
fun twoSum(numbers: IntArray, target: Int): IntArray {
    var left = 0
    //var right = numbers.size - 1
    var right = numbers.lastIndex

    while (left < right) {
        val sum = numbers[left] + numbers[right]

        when {
            sum == target -> return intArrayOf(left + 1, right + 1)
            sum < target -> left++
            else -> right--
        }
    }

    return intArrayOf() // The problem guarantees exactly one solution.
}