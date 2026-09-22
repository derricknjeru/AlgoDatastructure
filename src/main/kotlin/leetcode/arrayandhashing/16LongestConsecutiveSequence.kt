package leetcode.arrayandhashing

fun main() {

}


// Time: O(n)
// - Building the HashSet: O(n)
// - Each number is processed as part of a sequence at most once.
// - HashSet lookups are O(1) average.
//
// Space: O(n)
// - HashSet stores up to n numbers.

fun longestConsecutive(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    val set = nums.toHashSet()
    var longest = 0

    for (num in set) {

        // Only start counting if this is the beginning
        // of a consecutive sequence.
        if (num - 1 !in set) {

            var current = num
            var length = 1

            // Keep looking for the next consecutive number.
            while (current + 1 in set) {
                current++
                length++
            }

            longest = maxOf(longest, length)
        }
    }

    return longest
}