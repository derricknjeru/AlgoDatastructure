package leetcode.arrayandhashing

fun main() {

}


/*
 * Time Complexity: O(N * K)
 * - N is the number of strings in the input array.
 * - K is the maximum length of a string.
 * - Counting character frequencies takes linear time O(K) per string, bypassing sorting.
 *
 * Space Complexity: O(N * K)
 * - Space required to store the grouped strings and map entries.
 */
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    // Map to store grouped anagrams: Key = character frequency signature, Value = list of anagrams
    val map = HashMap<String, MutableList<String>>()

    for (str in strs) {
        // Fixed-size array to count occurrences of each lowercase letter ('a' through 'z')
        val count = IntArray(26)
        for (char in str) {
            count[char - 'a']++
        }

        // Convert frequency counts into a delimiter-separated key string
        // e.g., "aba" becomes "#2#1#0#0..." to uniquely represent letter counts
        val key = count.joinToString("#")

        // Group original string under its character count signature
        map.getOrPut(key) { mutableListOf() }.add(str)
    }

    // Return all grouped lists
    return map.values.toList()
}


/*
 * Time Complexity: O(N * K log K)
 * - N is the number of strings in the input array.
 * - K is the maximum length of a string.
 * - Sorting each string takes O(K log K) time, done for all N strings.
 *
 * Space Complexity: O(N * K)
 * - Space required to store the grouped strings inside the hash map.
 */
fun groupAnagrams2(strs: Array<String>): List<List<String>> {
    // Map to store grouped anagrams: Key = sorted string, Value = list of original anagrams
    val map = HashMap<String, MutableList<String>>()

    for (str in strs) {
        // Sort characters of the current string to form a normalized canonical key
        // e.g., "eat", "tea", "ate" all become "aet"
        val key = str.sort2()
        // If the key doesn't exist in map, create a new empty list; then add the original string
        /**
         * Equivalent
         * if (!groups.containsKey(key)) {
         *     groups[key] = mutableListOf()
         * }
         *
         * groups[key]!!.add(str)
         */
        map.getOrPut(key) { mutableListOf() }.add(str)
    }

    // Return all grouped lists as a List<List<String>>
    return map.values.toList()
}


private fun String.sort(): String {
    val count = IntArray(26)
    for (c in this) {
        count[c - 'a']++
    }
    return count.joinToString("#")
}

private fun String.sort2(): String {
    val chars = this.toCharArray()
    chars.sort()
    return String(chars)
}

private fun String.sort3(): String {
    return this.toCharArray().apply {
        sort()
    }.concatToString()
}

private fun String.sort4(): String =
    toCharArray().sorted().joinToString("")