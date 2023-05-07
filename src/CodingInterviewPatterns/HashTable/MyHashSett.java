package CodingInterviewPatterns.HashTable;

import java.util.LinkedList;

//Source link https://www.youtube.com/watch?v=U79BoHTcCYw
//https://leetcode.com/explore/learn/card/hash-table/182/practical-applications/1139/
class MyHashSett {

    private static final int size = 15000; // we want lf of 0.667
    private LinkedList<Integer>[] set;


    public MyHashSett() {
        set = new LinkedList[size];

    }

    public void add(int key) {
        int hashKey = key % size;

        if(set[hashKey]==null){
            set[hashKey]=new LinkedList<>();
        }

        if(!set[hashKey].contains(key)) set[hashKey].add(key);

    }

    public void remove(int key) {
        int hashKey = key % size;

        if(set[hashKey] ==null) return;

        if(set[hashKey].contains(key)){
            set[hashKey].remove(set[hashKey].indexOf(key));
        }

    }

    public boolean contains(int key) {
        int hashKey = key % size;

        if(set[hashKey] != null && set[hashKey].contains(key)) return true;

        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
