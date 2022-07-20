package DesignQuestions;

import java.util.LinkedHashMap;

public class LRUCache {
    //https://www.youtube.com/watch?v=akFRa58Svug
    //https://www.youtube.com/watch?v=iEmActx7dYc&t=1257s
    LinkedHashMap<Integer, Integer> _map;
    int _capacity = 0;

    public LRUCache(int capacity) {
        _capacity = capacity;
        _map = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (_map.containsKey(key)) {
            int val = _map.get(key);
            _map.remove(key);
            _map.put(key, val);
            return val;
        }

        return -1;
    }

    public void put(int key, int value) {
        if (_map.containsKey(key)) {
            _map.remove(key);
        } else if (_map.size() == _capacity) {
            //remove the first key
            _map.remove(_map.keySet().iterator().next());
        }
        _map.put(key, value);

    }
}
