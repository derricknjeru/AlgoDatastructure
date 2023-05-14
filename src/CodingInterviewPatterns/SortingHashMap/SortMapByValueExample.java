package CodingInterviewPatterns.SortingHashMap;

import java.util.*;

public class SortMapByValueExample {
    public static void main(String[] args) {
        usingList();
        usingListReverse();
        usingListString();
        usingListStringReverse();
    }

    private static void usingList() {
        // Time Complexity:
// Converting the HashMap to a List of Map.Entry objects: O(n)
// Sorting the list based on the value of each entry: O(n log n)
// Creating a new LinkedHashMap to store the sorted entries: O(n)
// Overall time complexity: O(n log n)

        // Create a HashMap
        Map<String, Integer> map = new HashMap<>();
        map.put("banana", 2);
        map.put("cherry", 3);
        map.put("apple", 1);


        // Convert the HashMap to a List of Map.Entry objects
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());


        // Sort the list based on the value of each entry no need to reverse
        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        //or
        list.sort((o1, o2) -> Integer.compare(o1.getValue(), o2.getValue()));

        //or because it is integer
        list.sort((o1, o2) -> o1.getValue() - (o2.getValue()));

        //or when no need to reverse
        list.sort(Map.Entry.comparingByValue());

        // Create a new LinkedHashMap to store the sorted entries
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        // Print the sorted map
        System.out.println("Sorted Map (by Value): " + sortedMap);
    }

    private static void usingListReverse() {
        // Time Complexity:
// Converting the HashMap to a List of Map.Entry objects: O(n)
// Sorting the list based on the value of each entry: O(n log n)
// Creating a new LinkedHashMap to store the sorted entries: O(n)
// Overall time complexity: O(n log n)

        // Create a HashMap
        Map<String, Integer> map = new HashMap<>();
        map.put("banana", 2);
        map.put("cherry", 3);
        map.put("apple", 1);

        // Convert the HashMap to a List of Map.Entry objects
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        //or to reverse
        list.sort((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

        //or to reverse
        list.sort((o1, o2) -> o2.getValue() - (o1.getValue())); //integer

        //or when no need to reverse
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));


        // Create a new LinkedHashMap to store the sorted entries
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        // Print the sorted map
        System.out.println("Sorted Map (by Value): " + sortedMap);
    }

    private static void usingListString() {
        // Time Complexity:
// Converting the HashMap to a List of Map.Entry objects: O(n)
// Sorting the list based on the value of each entry: O(n log n)
// Creating a new LinkedHashMap to store the sorted entries: O(n)
// Overall time complexity: O(n log n)

        // Create a HashMap
        Map<String, String> map = new HashMap<>();
        map.put("banana", "2");
        map.put("cherry", "3");
        map.put("apple", "4");

        // Convert the HashMap to a List of Map.Entry objects
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());

        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        //or
        list.sort(Map.Entry.comparingByValue());

        System.out.println("Sorted Map (by Value): " + list);
    }


    private static void usingListStringReverse() {
        // Time Complexity:
// Converting the HashMap to a List of Map.Entry objects: O(n)
// Sorting the list based on the value of each entry: O(n log n)
// Creating a new LinkedHashMap to store the sorted entries: O(n)
// Overall time complexity: O(n log n)

        // Create a HashMap
        Map<String, String> map = new HashMap<>();
        map.put("banana", "2");
        map.put("cherry", "3");
        map.put("apple", "4");

        // Convert the HashMap to a List of Map.Entry objects
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());

        //or to reverse
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println("Sorted Map (by Value): " + list);
    }


}





