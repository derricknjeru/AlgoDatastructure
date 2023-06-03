package CodingInterviewPatterns.HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class RandomizedCollection {
    //https://chat.openai.com/c/e5335dab-f240-48c5-b8ed-75ad38aaa0cd
    private final List<Integer> v;
    private final Map<Integer, List<Integer>> mymap;
    private final Random random;

    public RandomizedCollection() {
        v = new ArrayList<>();
        mymap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean contains = mymap.containsKey(val);
        if (!contains) {
            mymap.put(val, new ArrayList<>());
        }
        mymap.get(val).add(v.size());
        v.add(val);
        return !contains;
    }

    public boolean remove(int val) {
        if (!mymap.containsKey(val)) {
            return false;
        }
        List<Integer> indices = mymap.get(val);

        int indexToRemove = indices.get(indices.size() - 1);
        int lastValue = v.get(v.size() - 1);

        // Update the value at the index to remove
        v.set(indexToRemove, lastValue);

        // Update the indices of the last value
        List<Integer> lastValueIndices = mymap.get(lastValue);
        lastValueIndices.set(lastValueIndices.indexOf(v.size() - 1), indexToRemove);
        // Remove the last value from the list
        v.remove(v.size() - 1);

        // Remove the index from the indices list
        indices.remove(indices.size() - 1);

        // Remove the value from the map if there are no more occurrences
        if (indices.isEmpty()) {
            mymap.remove(val);
        }


        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(v.size());
        return v.get(randomIndex);
    }

    public static void main(String[] args) {
        RandomizedCollection collection = new RandomizedCollection();

        // Insert elements
        System.out.println(collection.insert(1)); // true
        System.out.println(collection.insert(2)); // true
        System.out.println(collection.insert(2)); // true
        System.out.println(collection.insert(3)); // true

        // Try to insert a duplicate element
        System.out.println(collection.insert(2)); // true

        // Remove an element
        System.out.println(collection.remove(2)); // true

        // Get a random element
        System.out.println(collection.getRandom()); // Output could be 1, 2, or 3

        // Remove a non-existent element
        System.out.println(collection.remove(4)); // false

        // Get a random element
        System.out.println(collection.getRandom()); // Output could be 1, 2, or 3
    }

}
