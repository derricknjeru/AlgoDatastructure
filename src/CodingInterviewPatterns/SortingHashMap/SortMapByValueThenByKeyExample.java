package CodingInterviewPatterns.SortingHashMap;

import java.util.*;

public class SortMapByValueThenByKeyExample {
    public static void main(String[] args) {
        usingList();
    }

    public static void usingList() {
        // Create a HashMap
        Map<String, Integer> map = new HashMap<>();
        map.put("cherry", 3);
        map.put("apple", 1);
        map.put("pear", 2);
        map.put("banana", 2);

        // Convert the HashMap to a List of Map.Entry objects
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        // Sort the List by value then by key using a custom Comparator
        list.sort((o1, o2) -> {
            int valueCompare = o1.getValue().compareTo(o2.getValue());
            if (valueCompare == 0) {
                return o1.getKey().compareTo(o2.getKey());
            }
            return valueCompare;
        });

        list.sort((a, b) -> a.getValue().equals(b.getValue()) ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());

        // Create a LinkedHashMap to preserve the order of insertion
        Map<String, Integer> sortedMap = new LinkedHashMap<>();

        // Add the sorted entries to the LinkedHashMap
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        // Print the sorted map
        System.out.println("Sorted Map (by Value then by Key): " + sortedMap);
    }
}

