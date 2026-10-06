package leetcode._5sliding_window

fun main() {

}
// https://www.youtube.com/watch?v=wiGpQwVHdE0
/**
 * The sliding window tracks a substring without repeats. When a character repeats,
 * move the window’s left edge past its previous occurrence.
 * **Time:** `O(n)` ·
 * **Space:** `O(min(n, character set size))`.
 *
 * ## Copilot said: `min(n, character set size)` means the set can hold at most the smaller of: - `n`: the length of the…
 *
 * `min(n, character set size)` means the set can hold at most the smaller of:
 *
 *
 *
 * - `n`: the length of the string
 *
 * - the number of possible distinct characters
 *
 *
 *
 * For example, if the string has 5 characters, the set can’t contain more than 5. If the input can contain only lowercase English letters, the set can’t contain more than 26.
 */

fun lengthOfLongestSubstring(s: String): Int {
    val lastSeen = mutableMapOf<Char, Int>()
    var left = 0
    var longest = 0

    for (right in s.indices) {
        val char = s[right]
        val previousIndex = lastSeen[char]

        if (previousIndex != null) {
            left = maxOf(left, previousIndex + 1)
        }

        lastSeen[char] = right
        longest = maxOf(longest, right - left + 1)
    }

    return longest
}

/**
 * **Time complexity:O(n)** — each character is added to and removed from the set at most once.
 * **Space complexity: O(min(n, character set size))** for the set.
 */
fun lengthOfLongestSubstring2(s: String): Int {
    val window = mutableSetOf<Char>()
    var left = 0
    var longest = 0

    for (right in s.indices) {
        while (s[right] in window) {
            window.remove(s[left])
            left++
        }

        window.add(s[right])
        longest = maxOf(longest, right - left + 1)
    }

    return longest
}


