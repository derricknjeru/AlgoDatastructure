package HashTable;

import java.util.*;

public class InsertDeleteGetRandom {
    //https://leetcode.com/explore/learn/card/hash-table/187/conclusion-hash-table/1141/

    class RandomizedSet {

        Set<Integer> set;
        Random rand;

        public RandomizedSet() {
            set=new HashSet<>();
            rand = new Random();
        }

        public boolean insert(int val) {
            return set.add(val);
        }

        public boolean remove(int val) {
            return set.remove(val);
        }

        public int getRandom() {
            List<Integer> list = new ArrayList<>(set);
            //could also be  list.addAll(set);
            if(list.size()==1){
                return list.get(0);
            }

            //Collections.shuffle(list, new Random(list.size()));

            int int_random = rand.nextInt(list.size());

            int value = list.get(int_random);

            return value;
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
