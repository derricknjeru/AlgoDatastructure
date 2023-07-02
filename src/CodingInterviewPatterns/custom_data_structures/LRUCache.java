package CodingInterviewPatterns.custom_data_structures;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {
    /**
     * The time complexity of the get and put methods in the LRUCache class depends on the underlying operations of the LinkedHashMap and the size of the cache.
     * <p>
     * For the get method:
     * <p>
     * The LinkedHashMap provides constant-time complexity (O(1)) for the getOrDefault operation, as it uses a hash table for storing the entries.
     * For the put method:
     * <p>
     * The LinkedHashMap provides constant-time complexity (O(1)) for the put operation when the cache is not at its capacity.
     * When the cache is at its capacity and a new entry is added, the removeEldestEntry method is called, which has a time complexity of O(1) because it simply checks the size of the cache.
     * In summary, both the get and put methods of the LRUCache class have an average time complexity of O(1) due to the use of the LinkedHashMap, assuming that the cache does not exceed its capacity.
     */
    private final LinkedHashMap<Integer, Integer> cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

    public static void main(String[] args) {
        //NB: accessOrder – the ordering mode - true for access-order, false for insertion-order
        /**
         * using LinkedHashMap, the least recently used (LRU) item is evicted when the cache capacity is exceeded.
         * However, in the LinkedHashMap, the access order mode is enabled by setting the accessOrder parameter to true
         * in the constructor. This mode ensures that when an entry is accessed (via get or put),
         * it is moved to the end of the internal doubly linked list, signifying it as the most recently used item.
         *
         * In the given example, let's go through the steps to understand the order of item access:
         *
         * We create an LRUCache with a capacity of 2.
         * We add key-value pair (1, 1) to the cache.
         * We add key-value pair (2, 2) to the cache.
         * We call the get method with key 1. This access updates the order of the items in the LinkedHashMap, moving key 1 to the end, making it the most recently used item.
         * We add key-value pair (3, 3) to the cache. Since the capacity is exceeded at this point, the least recently used item, which is key 2, will be evicted.
         * When we call the get method with key 2, it returns -1 because key 2 was evicted and is no longer present in the cache.
         * We call the get method with key 1, and it returns 1 because key 1 was accessed most recently and is still present in the cache.
         * We call the get method with key 3, and it returns 3 because key 3 is the most recently used item.
         * To summarize, in this implementation, the "least recently used" item refers to the item that has been accessed the least recently among all the items in the cache. It is evicted when the cache capacity is exceeded.
         */
        // Create an LRUCache with capacity 2
        LRUCache lruCache = new LRUCache(2);

        // Add key-value pairs to the cache
        lruCache.put(1, 1);
        lruCache.put(2, 2);

        // Get the value for key 1
        System.out.println(lruCache.get(1)); // Output: 1

        // Add a new key-value pair, which evicts key 2
        lruCache.put(3, 3);

        // Get the value for key 2 (should return -1, as it was evicted)
        System.out.println(lruCache.get(2)); // Output: -1

        // Add a new key-value pair, which evicts key 1
        lruCache.put(4, 4);

        // Get the values for keys 1, 3, and 4
        System.out.println(lruCache.get(1)); // Output: -1
        System.out.println(lruCache.get(3)); // Output: 3
        System.out.println(lruCache.get(4)); // Output: 4
    }

}




