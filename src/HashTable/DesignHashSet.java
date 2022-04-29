package HashTable;

import java.util.LinkedList;
import java.util.List;
//Source link https://www.youtube.com/watch?v=U79BoHTcCYw
public class DesignHashSet {
    private int numBuckets = 15000;
    LinkedList[] buckets;

    private int hash_function(int key) {
        return key % numBuckets;
    }

    public DesignHashSet() {
        buckets = new LinkedList[numBuckets];
    }

    public void add(int key) {
        int i = hash_function(key);
        //create a new list
        if (buckets[i] == null) {
            buckets[i] = new LinkedList<>();
        }

        if (buckets[i].indexOf(key) == -1) {
            buckets[i].add(key);
        }
    }

    public void remove(int key) {
        int i = hash_function(key);
        //create a new list
        if (buckets[i] == null) return;

        if (buckets[i].indexOf(key) != -1) {
            buckets[i].remove(buckets[i].indexOf(key));
        }
    }

    public boolean contains(int key) {
        int i = hash_function(key);
        //create a new list
        if (buckets[i] == null || buckets[i].indexOf(key) == -1) {
            return false;
        }
        return true;
    }
}
