package CodingInterviewPatterns.HashTable.quickreview;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {

        //Example 1: Basic Usage of LinkedHashMap
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put(1, "Apple");
        linkedHashMap.put(2, "Banana");
        linkedHashMap.put(3, "Orange");

        System.out.println(linkedHashMap); //{1=Apple, 2=Banana, 3=Orange}
        System.out.println("-----------------------------------");

        //Example 2: Access Order Mode in LinkedHashMap

        LinkedHashMap<Integer, String> linkedHashMapA = new LinkedHashMap<>(16, 0.75f, true);

        linkedHashMapA.put(1, "Apple");
        linkedHashMapA.put(2, "Banana");
        linkedHashMapA.put(3, "Orange");

        // Accessing the value with key 2 moves it to the end of the map
        linkedHashMapA.get(2);

        System.out.println(linkedHashMapA); //{1=Apple, 3=Orange, 2=Banana}
        System.out.println("-----------------------------------");
        //Example 3: Custom removeEldestEntry Implementation

        LinkedHashMap<Integer, String> linkedHashMapC = new LinkedHashMap<Integer, String>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > 2; // Keep only the last two entries
            }
        };

        linkedHashMapC.put(1, "Apple");
        linkedHashMapC.put(2, "Banana");
        linkedHashMapC.put(3, "Orange");


        System.out.println(linkedHashMapC);  //{2=Banana, 3=Orange}

        linkedHashMapC.put(4, "Grapes");

        System.out.println(linkedHashMapC);   //{3=Orange, 4=Grapes}
        System.out.println("-----------------------------------");
        //Example 2: Evicting Least Recently Used (LRU) Entries

        LinkedHashMap<Integer, String> linkedHashMapLRU = new LinkedHashMap<Integer, String>(2, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > 2;
            }
        };

        linkedHashMapLRU.put(1, "Apple");
        linkedHashMapLRU.put(2, "Banana");

        System.out.println(linkedHashMapLRU);

        linkedHashMapLRU.put(3, "Orange");

        System.out.println(linkedHashMapLRU);

        linkedHashMapLRU.get(2); // Accessing key 2 moves it to the end

        linkedHashMapLRU.put(4, "Grapes");

        System.out.println(linkedHashMapLRU);
        /**
         * {1=Apple, 2=Banana}
         * {2=Banana, 3=Orange}
         * {3=banana, 4=Grapes}
         */

        //Example 1: Removing Entries Based on a Condition
        System.out.println("-----------------------------------");

        LinkedHashMap<Integer, String> linkedHashMapEC = new LinkedHashMap<Integer, String>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return eldest.getValue().startsWith("A");
            }
        };

        linkedHashMapEC.put(1, "Apple");
        linkedHashMapEC.put(2, "Banana");
        linkedHashMapEC.put(3, "Orange");

        System.out.println(linkedHashMapEC);

        linkedHashMapEC.put(4, "Avocado");

        System.out.println(linkedHashMapEC);
        System.out.println("-----------------------------------");
        //Example 2: Removing Entries Based on a Custom Condition

        int threshold = 2;

        LinkedHashMap<Integer, String> linkedHashMapK = new LinkedHashMap<Integer, String>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return eldest.getKey() > threshold;
            }
        };

        linkedHashMapK.put(1, "Apple");
        linkedHashMapK.put(2, "Banana");
        linkedHashMapK.put(3, "Orange");

        System.out.println(linkedHashMapK);

        linkedHashMapK.put(4, "Grapes");

        System.out.println(linkedHashMapK);

    }
}
