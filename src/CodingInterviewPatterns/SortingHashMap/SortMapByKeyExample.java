package CodingInterviewPatterns.SortingHashMap;

import java.util.*;

public class SortMapByKeyExample {
    /**
     * The time complexity of creating the HashMap and adding the key-value pairs is O(1) for each operation.
     * <p>
     * The time complexity of sorting the HashMap by key using a TreeMap is O(n log n), where n is the number of key-value pairs in the HashMap. This is because the TreeMap needs to perform a tree-based sort of the keys.
     * <p>
     * The time complexity of printing the sorted map is O(n), where n is the number of key-value pairs in the HashMap. This is because we need to iterate through the map to print each key-value pair.
     *
     * @param args
     */
    public static void main(String[] args) {

        usingTreeMap();
        usingTreeMapReverse();
        usingList();
        usingListReverse();
    }

    private static void usingTreeMap() {
        // Create a HashMap
        Map<String, Integer> map = new HashMap<>();  // O(1)
        map.put("banana", 2);  // O(1)
        map.put("cherry", 3);  // O(1)
        map.put("apple", 1);  // O(1)

        // Sort the HashMap by key
        Map<String, Integer> sortedMap = new TreeMap<>(map);  // O(n log n)

        // Print the sorted map
        System.out.println("Sorted Map (by Key): " + sortedMap);  // O(n)
    }

    private static void usingTreeMapReverse() {
        // Create a new hashmap
        HashMap<String, Integer> hashMap = new HashMap<>();

        // Add some key-value pairs to the hashmap
        hashMap.put("apple", 3);
        hashMap.put("banana", 2);
        hashMap.put("orange", 4);

        // Create a comparator to sort the keys in reverse order
        Comparator<String> reverseComparator = Collections.reverseOrder();

        // Sort the hashmap by key in descending order
        TreeMap<String, Integer> sortedMap = new TreeMap<>(reverseComparator);
        sortedMap.putAll(hashMap);

        // Print the sorted map
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private static void usingList() {

        // Create a HashMap
        Map<String, Integer> map = new HashMap<>();  // O(1)
        map.put("banana", 2);
        map.put("cherry", 3);
        map.put("apple", 1);

        // Convert the HashMap to a List of Map.Entry objects
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());  // O(n)

        // Sort the list based on the key of each entry
        list.sort((o1, o2) -> o1.getKey().compareTo(o2.getKey()));  // O(n log n)
        //or easiest
        list.sort(Map.Entry.comparingByKey());  // O(n log n)

        //We can print list at this point
        // Create a new LinkedHashMap to store the sorted entries. A LinkedHashMap allows us to do this, as it will maintain the order of insertion of each entry from the sorted list.
        Map<String, Integer> sortedMap = new LinkedHashMap<>();  // O(1)
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());  // O(n)
        }

        // Print the sorted map
        System.out.println("Sorted Map (by Key): " + sortedMap);  // O(n)
    }


    private static void usingListReverse() {

        // Create a HashMap
        Map<Integer, Integer> map = new HashMap<>();  // O(1)
        map.put(3, 2);  // O(1)
        map.put(1, 3);  // O(1)
        map.put(5, 1);  // O(1)

        // Convert the HashMap to a List of Map.Entry objects
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());  // O(n)

        // Sort the list based on the key of each entry
        list.sort((o1, o2) -> o2.getKey().compareTo(o1.getKey()));  // O(n log n)

        //or easiest
        list.sort((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));  // O(n log n)

        //or easiest
        list.sort(Map.Entry.comparingByKey(Collections.reverseOrder()));

        //We can print list at this point
        // Create a new LinkedHashMap to store the sorted entries. A LinkedHashMap allows us to do this, as it will maintain the order of insertion of each entry from the sorted list.
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();  // O(1)
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());  // O(n)
        }

        // Print the sorted map
        System.out.println("Sorted Map (by Key): " + sortedMap);  // O(n)
    }

}

