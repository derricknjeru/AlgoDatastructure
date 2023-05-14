package BigONotation;

import java.util.ArrayList;


public class ArrayListExample {
    /**
     * In this example, the time complexity of each operation is as follows:
     * <p>
     * Adding elements to the list using add(): O(1) amortized time complexity per operation, as the array that backs the ArrayList class automatically grows when necessary to accommodate new elements.
     * Inserting an element at a specific index using add(index, element): O(n) worst-case time complexity, as this operation requires shifting all the subsequent elements in the array by one index to make room for the new element.
     * Removing an element from the list using remove(element): O(n) worst-case time complexity, as this operation requires shifting all the subsequent elements in the array by one index to fill the gap left by the removed element.
     * Retrieving an element from the list using get(index): O(1) time complexity, as the element can be directly accessed using its index.
     * Checking whether the list contains a specific element using contains(element): O(n) worst-case time complexity, as the entire list needs to be searched to find the specified element.
     * Finding the index of a specific element using indexOf(element): O(n) worst-case time complexity, as the entire list needs to be searched to find the specified element.
     * Looping through all the elements in the list using a for-each loop: O(n) time complexity, as each element needs to be accessed once.
     *
     * @param args
     */
    public static void main(String[] args) {
        // create an ArrayList of Strings
        ArrayList<String> list = new ArrayList<String>();

        // add some elements to the list (O(1) amortized time)
        list.add("apple");
        list.add("banana");
        list.add("cherry");

        // print the list
        System.out.println("Original list: " + list);

        // insert an element at index 1 (O(n) worst-case time)
        list.add(1, "orange");

        // remove an element from the list (O(n) worst-case time)
        list.remove("banana");

        // get an element from the list by index (O(1) time)
        String element = list.get(2);

        // check if the list contains an element (O(n) worst-case time)
        boolean contains = list.contains("apple");

        // find the index of an element in the list (O(n) worst-case time)
        int index = list.indexOf("cherry");

        // loop through all the elements in the list (O(n) time)
        System.out.println("All elements in the list:");
        for (String item : list) {
            System.out.println(item);
        }

        // print the modified list and other information
        System.out.println("Modified list: " + list);
        System.out.println("Element at index 2: " + element);
        System.out.println("List contains \"apple\": " + contains);
        System.out.println("Index of \"cherry\": " + index);
    }
}

