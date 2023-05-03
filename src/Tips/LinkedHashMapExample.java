package Tips;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LinkedHashMapExample {
    //https://www.geeksforgeeks.org/linkedhashmap-class-in-java/

    /**
     * LinkedHashMap<String, String> map = new LinkedHashMap<>();
     * It is the same as HashMap with an additional feature that it maintains insertion order.
     * For example, when we run the code with a HashMap, we get a different order of elements.
     * <p>
     * Hash: All the input keys are converted into a hash which is a shorter form of the key so that the search and insertion are faster.
     * Key: Since this class extends HashMap, the data is stored in the form of a key-value pair. Therefore, this parameter is the key to the data.
     * Value: For every key, there is a value associated with it. This parameter stores the value of the keys. Due to generics, this value can be of any form.
     * Next: Since the LinkedHashMap stores the insertion order, this contains the address to the next node of the LinkedHashMap.
     * Previous: This parameter contains the address to the previous node of the LinkedHashMap.
     *
     * @param args
     */

    public static void main(String[] args) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();

        // Adding entries in Map
        // using put() method
        lhm.put("one", "practice.geeksforgeeks.org");
        lhm.put("two", "code.geeksforgeeks.org");
        lhm.put("four", "quiz.geeksforgeeks.org");

        // Printing all entries inside Map
        System.out.println(lhm);

        // Note.md: It prints the elements in same order
        // as they were inserted

        // Getting and printing value for a specific key
        System.out.println("Getting value for key 'one': " + lhm.get("one"));

        // Getting size of Map using size() method
        System.out.println("Size of the map: " + lhm.size());

        // Checking whether Map is empty or not
        System.out.println("Is map empty? " + lhm.isEmpty());

        // Using containsKey() method to check for a key
        System.out.println("Contains key 'two'? " + lhm.containsKey("two"));

        // Using containsKey() method to check for a value
        System.out.println("Contains value 'practice.geeks" + "forgeeks.org'? " + lhm.containsValue("practice" + ".geeksforgeeks.org"));

        // Removing entry using remove() method
        System.out.println("delete element 'one': " + lhm.remove("one"));

        // Printing mappings to the console
        System.out.println("Mappings of LinkedHashMap : " + lhm);

        // Updating the value with key 2
        lhm.put("four", "For");


        // Removing the mapping with Key 4
        lhm.remove("four");


    }
}
