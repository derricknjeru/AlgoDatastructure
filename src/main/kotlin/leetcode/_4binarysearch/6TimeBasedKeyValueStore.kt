package leetcode._4binarysearch

import java.util.TreeMap

fun main() {

}

class TimeMap {
    /**
     * Because `set` operations are guaranteed to arrive in increasing timestamp order for each key, each list remains sorted.
     * - `set`: `O(1)`
     *
     * - `get`: `O(log n)`
     *
     * - Space: `O(n)`

     * The key idea is to find the **rightmost entry whose timestamp is less than or equal to the requested timestamp**.
     */
    private data class Entry(
        val timestamp: Int,
        val value: String
    )

    private val valuesByKey = HashMap<String, MutableList<Entry>>()

    fun set(key: String, value: String, timestamp: Int) {
        valuesByKey
            .getOrPut(key) { mutableListOf() }
            .add(Entry(timestamp, value))
    }

    fun get(key: String, timestamp: Int): String {
        val entries = valuesByKey[key] ?: return ""

        var left = 0
        var right = entries.lastIndex
        var answer = ""

        while (left <= right) {
            val middle = left + (right - left) / 2

            if (entries[middle].timestamp <= timestamp) {
                answer = entries[middle].value
                left = middle + 1
            } else {
                right = middle - 1
            }
        }

        return answer
    }
}

class TimeMap2 {
    /**
     * `TreeMap.floorEntry(timestamp)` directly performs the required predecessor lookup.
     * - `set`: `O(log n)`
     *
     * - `get`: `O(log n)`
     *
     * - Space: `O(total number of set operations)`
     */
    private val store = mutableMapOf<String, TreeMap<Int, String>>()

    fun set(key: String, value: String, timestamp: Int) {
        store
            .getOrPut(key) { TreeMap() }[timestamp] = value
    }

    fun get(key: String, timestamp: Int): String {
        val values = store[key] ?: return ""

        // Returns the entry with the greatest timestamp <= timestamp.
        return values.floorEntry(timestamp)?.value ?: ""
    }
}