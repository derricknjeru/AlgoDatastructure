package leetcode.arrayandhashing

fun main() {

}

/**
 * LeetCode 242 - Valid Anagram
 *
 * TIME COMPLEXITY:  O(n)
 * - Length check runs in O(1) time.
 * - Loop iterates 'n' times (length of s and t). Array lookups and math operations take O(1) per step -> O(n).
 * - Final verification loop checks a fixed array of size 26 -> O(1).
 * - Total Time: O(n) + O(1) = O(n).
 *
 * SPACE COMPLEXITY: O(1)
 * - Uses a fixed-size IntArray of length 26 regardless of input size n.
 * - Memory allocation remains constant -> O(1).
 */

fun isAnagram(s: String, t: String): Boolean {
    // Early exit: Anagrams must have the exact same length
    if (s.length != t.length) return false

    // Fixed-size frequency array for 26 lowercase English letters ('a' through 'z')
    val counts = IntArray(26)

    // Single pass: Increment count for string 's' and decrement count for string 't'
    for (i in s.indices) {
        counts[s[i] - 'a']++  // Map character 'a'..'z' to index 0..25 and increment
        counts[t[i] - 'a']--  // Map character 'a'..'z' to index 0..25 and decrement
    }

    // Verify that all character counts netted out to zero
    /* for (count in counts) {
         if (count != 0) return false // Non-zero value indicates a character frequency mismatch
     }*/
    return counts.all { it == 0 }

    //return true
}


// Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
fun isAnagram2(s: String, t: String): Boolean {
    // If the lengths are different, s and t cannot be anagrams
    if (s.length != t.length) return false

    // Map to store character frequencies (Char -> Frequency Count)
    val map = mutableMapOf<Char, Int>()

    // Increment counts for every character in string 's'
    for (c in s) {
        map[c] = map.getOrDefault(c, 0) + 1
    }

    // Decrement counts for every character in string 't'
    for (c in t) {
        map[c] = map.getOrDefault(c, 0) - 1
    }

    // Check if all character frequencies netted out to zero
    return map.values.all { it == 0 }
}
