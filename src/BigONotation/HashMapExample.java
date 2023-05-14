package BigONotation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class HashMapExample {
    /**
     * In this example, the time complexity of each operation is as follows:
     * <p>
     * Adding a key-value pair to the map using put(key, value): O(1) amortized time complexity per operation, as long as the size of the map doesn't exceed its capacity. If the size of the map does exceed its capacity, the map needs to be resized, which takes O(n) time, where n is the number of key-value pairs in the map.
     * Retrieving the value associated with a key using get(key): O(1) expected time complexity per operation, as long as there are no collisions (i.e., multiple keys that map to the same hash code). In the worst case, where all the keys in the map hash to the same code, the time complexity is O(n), where n is the number of key-value pairs in the map.
     * Removing a key-value pair from the map using remove(key): O(1) expected time complexity per operation, as long as there are no collisions. In the worst case, where all the keys in the map hash to the same code, the time complexity is O(n), where n is the number of key-value pairs in the map.
     * Checking whether the map contains a specific key using containsKey(key): O(1) expected time complexity per operation, as long as there are no collisions. In the worst case, where all the keys in the map hash to the same code, the time complexity is O(n), where n is the number of key-value pairs in the map.
     * Checking whether the map contains a specific value using containsValue(value): O(n) time complexity, as all the values in the map need to be searched to find the specified value.
     * Looping through all the keys in the map using keySet() and a for-each loop: O(n
     *
     * @param args
     */
    public static void main(String[] args) {
        // create a HashMap with keys of type String and values of type Integer
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        // add some key-value pairs to the map (O(1) amortized time)
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);

        // print the map
        System.out.println("Original map: " + map);

        // get the value associated with a key (O(1) expected time, O(n) worst-case time)
        Integer value = map.get("banana");

        // remove a key-value pair from the map (O(1) expected time, O(n) worst-case time)
        map.remove("cherry");

        // check if the map contains a key (O(1) expected time, O(n) worst-case time)
        boolean containsKey = map.containsKey("apple");

        // check if the map contains a value (O(n) time)
        boolean containsValue = map.containsValue(2);

        // loop through all the keys in the map (O(n) time)
        System.out.println("All keys in the map:");
        for (String key : map.keySet()) {
            System.out.println(key);
        }

        // loop through all the entries in the map (O(n) time)
        System.out.println("All entries in the map:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // sort the map by key (O(n log n) time)
        Map<String, Integer> sortedMap = new TreeMap<String, Integer>(map);
        System.out.println("Sorted map: " + sortedMap);

        // print the modified map and other information
        System.out.println("Modified map: " + map);
        System.out.println("Value associated with \"banana\": " + value);
        System.out.println("Map contains key \"apple\": " + containsKey);
        System.out.println("Map contains value 2: " + containsValue);
    }
}

