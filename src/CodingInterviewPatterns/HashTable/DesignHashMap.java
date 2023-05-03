package CodingInterviewPatterns.HashTable;

import java.util.LinkedList;

//https://leetcode.com/explore/learn/card/hash-table/182/practical-applications/1140/
//https://www.youtube.com/watch?v=ISir207RuKQ&ab_channel=AlgorithmsMadeEasy

class DesignHashMap {
    //Why have we chosen SIZE as 769
    /*
    you can choose any arbitrary value!!
    usually, we try to select a large prime number!
    */
    //not only prime numbers. you can select any number. prime numbers give us less collision so that's a recommended practice.

    private static final int SIZE = 769;
    private final LinkedList<Entry>[] map;

    public DesignHashMap() {
        map = new LinkedList[SIZE];
    }

    public void put(int key, int value) {
        int hashKey = key % SIZE;
        if (map[hashKey] != null) {
            //can someone dynamically update collections?
            for (int i = 0; i < map[hashKey].size(); i++) {
                if (map[hashKey].get(i).key == key) {
                    map[hashKey].get(i).value = value;
                    return;
                }
            }
            map[hashKey].add(new Entry(key, value));
        } else {
            map[hashKey] = new LinkedList<>();
            map[hashKey].add(new Entry(key, value));
        }
    }

    public int get(int key) {

        int hashKey = key % SIZE;

        if (map[hashKey] == null) return -1;

        for (Entry entry : map[hashKey]) {
            if (entry.key == key) return entry.value;
        }

        return -1;

    }

    public void remove(int key) {
        int hashKey = key % SIZE;

        if (map[hashKey] == null) return;

        Entry toRemove = null;

        for (Entry entry : map[hashKey]) {
            if (entry.key == key) {
                toRemove = entry;
            }
        }

        if (toRemove != null) map[hashKey].remove(toRemove);
    }

    static class Entry {
        int key, value;

        public Entry(int key, int value) {
            this.value = value;
            this.key = key;
        }
    }

    //16
    //
}
