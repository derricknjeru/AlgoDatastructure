package HashTable;

import java.util.LinkedList;

public class DesignHashMap {
    //Why have we choosen SIZE as 769
    /*
    you can choose any arbitrary value!!
    usually, we try to select a large prime number!
    */
    //not only prime numbers. you can select any number. prime numbers give us less collision so that's a recommended practice.

    private LinkedList<Entry>[] map;
    private static int SIZE = 769; //largest prime number of 10^6

    int hash_key(int key){
        return key%SIZE;
    }

    public DesignHashMap() {
        //initialize linked list size
        map = new LinkedList[SIZE];
    }

    public void put(int key, int value) {
        int hash_key = hash_key(key);
        if(map[hash_key]==null){
            map[hash_key] = new LinkedList<Entry>();
            map[hash_key].add(new Entry(key,value));
        }else {
            for (Entry entry:map[hash_key]){
                if(entry.key==key){
                    entry.val = value;
                    return;
                }
            }

            map[hash_key].add(new Entry(key,value));
        }
    }

    public int get(int key) {
        int hashKey = hash_key(key);

        LinkedList<Entry> entries = map[hashKey];
        if(entries==null) return -1;

        for(Entry entry : entries){
            if(entry.key == key) return entry.val;
        }
        return -1;
    }

    public void remove(int key) {
        int hash_key =hash_key(key);

        Entry toRemove = null;

        if(map[hash_key]==null) return;
        else{
            for(Entry entry : map[hash_key]){
                if(entry.key == key){
                    toRemove = entry;
                }
            }
            if(toRemove == null) return;
            else map[hash_key].remove(toRemove);
        }

    }
}

class Entry {
    public int key;
    public int val;

    Entry(int key, int val){
        this.key = key;
        this.val = val;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

