package CodingInterviewPatterns.HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tip {
    public static void main(String[] args) {
        // Create a HashMap to store key-value pairs
        Map<String, Map.Entry<String, Integer>> hashMap = new HashMap<>();

        List<Map.Entry<String, Map.Entry<String, Integer>>> test = new ArrayList<>();




        // Create a key-value pair
        String key = "example";
        String key2 = "example";
        String innerKey = "inner";
        String innerKey2 = "inner";
        int value = 42;
        int value2 = 42;

        // Create a Map.Entry with the inner key and value
        Map.Entry<String, Integer> innerEntry = new HashMap.SimpleEntry<>(innerKey, value);

        // Store the key-value pair in the HashMap
        hashMap.put(key, innerEntry);
        hashMap.put(key2, Map.entry(innerKey2, value2));


        // Retrieve the value associated with the key
        Map.Entry<String, Integer> retrievedEntry = hashMap.get(key);

        // Check if the retrievedEntry has a value and add it to a variable
        int retrievedValue = 0; // Default value if retrievedEntry is null
        if (retrievedEntry != null) {
            retrievedValue = retrievedEntry.getValue();
        }
    }
}
