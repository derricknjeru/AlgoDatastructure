package CodingInterviewPatterns.custom_data_structures;

import java.util.*;

public class InsertDeleteGetRandom {
    //https://leetcode.com/explore/learn/card/hash-table/187/conclusion-hash-table/1141/
    //https://www.youtube.com/watch?v=WtkwD7ikxfg

    static class RandomizedSet {
        private List<Integer> v;
        private Map<Integer, Integer> mymap;
        private Random random;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            v = new ArrayList<>();
            mymap = new HashMap<>();
            random = new Random();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (mymap.containsKey(val))
                return false;

            v.add(val);
            mymap.put(val, v.size() - 1);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!mymap.containsKey(val))
                return false;

            int index = mymap.get(val);
            int lastVal = v.get(v.size() - 1);

            v.set(index, lastVal);
            v.remove(v.size() - 1);

            mymap.put(lastVal, index);
            mymap.remove(val);

            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            int randomIndex = random.nextInt(v.size());
            return v.get(randomIndex);
        }
    }


    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();

        // Inserting elements
        System.out.println(randomizedSet.insert(1)); // Returns true
        System.out.println(randomizedSet.insert(2)); // Returns true
        System.out.println(randomizedSet.insert(3)); // Returns true

        // Trying to insert a duplicate element
        System.out.println(randomizedSet.insert(2)); // Returns false

        // Removing an element
        System.out.println(randomizedSet.remove(2)); // Returns true

        // Removing a non-existent element
        System.out.println(randomizedSet.remove(4)); // Returns false

        // Getting a random element
        int randomElement = randomizedSet.getRandom();
        System.out.println(randomElement); // Prints a random element from the set
    }


/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
