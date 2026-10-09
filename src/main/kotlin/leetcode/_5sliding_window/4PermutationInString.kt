package leetcode._5sliding_window

fun main() {
// ```
//val sorted = input.toCharArray().sorted().joinToString("")
//
//```
    val s1 = "ab"
    println(s1[0].code)
    println()
}

// https://www.youtube.com/watch?v=UbyhOgBN834
/**
 * <a href="file:///Users/derrick/AlgoDatastructure/src/main/docs/images/permutation.png">
 * Permutation sliding-window illustration
 * </a>
 */
/**
 * `needed.contentEquals(window)` takes **O(26)** time in the worst case because
 * it compares the arrays element by element. Since the arrays always have a fixed size of 26,
 * that’s O(1) per comparison. Across all sliding windows, the comparisons contribute O(26 × n) = O(n) overall.
 */
// Time: O(26 * s2.length) = O(n), since the alphabet size is fixed.
// Space: O(26) = O(1)
class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false

        // Count each letter in s1 and in the first window of s2.
        val needed = IntArray(26)
        val window = IntArray(26)

        for (i in s1.indices) {
            needed[s1[i] - 'a']++
            window[s2[i] - 'a']++
        }

        for ( c in s1){
            window[c.code]--
        }

        // The first window is already a permutation of s1.
        if (needed.contentEquals(window)) return true

        // Move the window one character at a time.
        // 'right' is the index of the new character entering the window.
        for (right in s1.length until s2.length) {
            window[s2[right] - 'a']++ // Add the incoming character.
            window[s2[right - s1.length] - 'a']-- // Remove the outgoing character.

            // Matching counts mean this window is a permutation of s1.
            if (needed.contentEquals(window)) return true
        }

        return false
    }
}
