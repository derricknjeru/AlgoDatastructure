package leetcode._6LinkedList

fun main() {
    val cache = LRUCache(2)

// Order below: least recently used → most recently used
    cache.put(1, 10)  // [1]
    cache.put(2, 20)  // [1, 2]
    println(cache.get(1)) // Returns 10; order becomes [2, 1]
    cache.put(3, 30)  // Evicts 2; order becomes [1, 3]
    println(cache.get(2))   // Returns -1
}

/*
LRU Cache using LinkedHashMap

Evicts the least recently used key when the cache exceeds its capacity.

1. Backing map:
   object : LinkedHashMap<Int, Int>(capacity, 0.75f, true)
   creates an anonymous subclass so we can override removeEldestEntry.

   Constructor arguments:
   - capacity: initial hash-table capacity, NOT a maximum entry limit.
   - 0.75f: load factor; controls when the hash table resizes.
   - true: enables access order instead of insertion order.

   Reading or updating a key moves it to the most recently used end.
   The first entry is therefore the least recently used.

2. Eviction:
   removeEldestEntry is called after inserting a new entry.
   Returning size > capacity automatically removes the eldest entry
   when the maximum number of entries is exceeded.

   The eldest parameter is the eviction candidate. We don't need to
   inspect it because our decision depends only on the cache size.

3. get(key):
   cache[key] returns the value and updates its recency if found.
   If absent, it returns null, and ?: -1 returns -1 instead.

4. put(key, value):
   cache[key] = value inserts or updates the entry and makes it
   most recently used. Updating an existing key does not increase size.

Example with capacity = 2:
Order: least recently used -> most recently used

put(1, 10) -> [1]
put(2, 20) -> [1, 2]
get(1)    -> returns 10; order becomes [2, 1]
put(3, 30) -> evicts 2; order becomes [1, 3]
get(2)    -> returns -1

Complexity:
- get: O(1) average time.
- put: O(1) amortized average time.
- Space: O(capacity).
*/
class LRUCache(capacity: Int) {
    private val cache = object : LinkedHashMap<Int, Int>(capacity, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean {
            return size > capacity
        }
    }

    fun get(key: Int): Int {
        return cache[key] ?: -1
    }

    fun put(key: Int, value: Int) {
        cache[key] = value
    }

}