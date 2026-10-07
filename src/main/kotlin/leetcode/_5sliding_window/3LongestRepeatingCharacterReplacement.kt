package leetcode._5sliding_window

fun main() {

}
// https://www.youtube.com/watch?v=gqXU1UyA8pk
/**
 * The sliding window is valid when the number of characters that must be
 * replaced—`window length - most frequent character count`—is at most `k`.
 * **Time:** `O(n)` · **Space:** `O(1)`.
 */
fun characterReplacement(s: String, k: Int): Int {
    // Track each letter's frequency in the current window.
    val counts = IntArray(26)
    var left = 0
    var maxFrequency = 0
    var longest = 0

    for (right in s.indices) {
        val index = s[right] - 'A'
        counts[index]++

        // Keep the highest frequency seen; it lets us check how many
        // characters would need to be replaced to make the window uniform.
        maxFrequency = maxOf(maxFrequency, counts[index])

        // Shrink the window if more than k replacements would be needed.
        while ((right - left + 1) - maxFrequency > k) {
            counts[s[left] - 'A']--
            left++
        }

        // Record the largest valid window found so far.
        longest = maxOf(longest, right - left + 1)
    }

    return longest
}

/**
 * **Time:** `O(26n) = O(n)` — each `maxOrNull()` scans 26 counts, and each character enters
 * and leaves the window at most once.
 *
 * **Space:** `O(26) = O(1)` — the count array has a fixed size.
 */
fun characterReplacement2(s: String, k: Int): Int {
    val count = IntArray(26)
    var left = 0
    var res = 0

    for (right in s.indices) {
        count[s[right] - 'A']++

        // Replacing every other character makes the window uniform.
        val maxFreq = count.maxOrNull() ?: 0

        // Shrink until at most k characters need to be replaced.
        while ((right - left + 1) - maxFreq > k) {
            count[s[left] - 'A']--
            left++
        }

        // Keep the longest valid window seen so far.
        res = maxOf(res, right - left + 1)
    }

    return res
}
