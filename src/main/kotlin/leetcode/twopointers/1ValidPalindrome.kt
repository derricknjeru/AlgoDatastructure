package leetcode.twopointers

fun main() {

}

// https://www.youtube.com/watch?v=jJXJ16kPFWg
/**
 * Time & Space Complexity
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
fun isPalindrome(s: String): Boolean {
    var left = 0
    var right = s.length - 1

    while (left < right) {
        // Skip non-alphanumeric characters from the left
        while (left < right && !s[left].isLetterOrDigit()) {
            left++
        }

        // Skip non-alphanumeric characters from the right
        while (left < right && !s[right].isLetterOrDigit()) {
            right--
        }

        // Compare characters ignoring case
        if (!s[left].equals(s[right], ignoreCase = true)) {
            return false
        }

        left++
        right--
    }

    return true
}

fun isPalindrome2(s: String): Boolean {
    var left = 0
    var right = s.lastIndex

    while (left < right) {
        while (left < right && !s[left].isLetterOrDigit()) left++
        while (left < right && !s[right].isLetterOrDigit()) right--

        if (s[left].lowercaseChar() != s[right].lowercaseChar()) {
            return false
        }

        left++
        right--
    }

    return true
}


fun isPalindrome3(s: String): Boolean {
    val s = s.lowercase()
    var i = 0
    var j = s.lastIndex
    //var j = s.length - 1

    while (i < j) {
        while (i < j && !s[i].isLetterOrDigit()) i++
        while (i < j && !s[j].isLetterOrDigit()) j--
        if (s[i++] != s[j--]) return false
    }
    return true
}


fun isPalindrome4(s: String): Boolean {
    val filtered = s.filter { it.isLetterOrDigit() }.lowercase()

    //val x: IntArray = intArrayOf(1, 3)
    val map = mutableMapOf<Int, MutableSet<Char>>()
    return filtered == filtered.reversed()
}


