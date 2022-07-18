package leetcode_30_days_challenge.week4;

import java.util.HashMap;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * <p>
 * The cache is initialized with a positive capacity.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 */

//https://leetcode.com/problems/lru-cache/

class Node {
    Node next;
    Node prev;
    int key;
    int val;

    public Node(int key, int val)  // Although saving key in node might be redundant here but this will ensure we would follow O(1) runtime when removing it from Map.
    {
        this.key = key;
        this.val = val;
    }

    public Node() {
    }
}

class LRUCache {

    Node head, tail;
    HashMap<Integer, Node> lru;
    int capacity;

    public LRUCache(int capacity) {
        head = new Node();
        tail = new Node();
        head.prev = null;
        tail.next = null;
        head.next = tail;   //  This is important because it would avoid using null check and would maintain seamless transition when swapping the nodes back and forth.
        tail.prev = head;
        this.lru = new HashMap();
        this.capacity = capacity;
    }

    private void addNode(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    public int get(int key) {
        Node n = lru.getOrDefault(key, null);
        if (n == null) return -1;
        deleteNode(n);
        addNode(n);
        return n.val;
    }

    public void put(int key, int value) {
        Node n = lru.getOrDefault(key, null);

        if (lru.size() == capacity) {
            if (n == null) {
                Node temp = tail.prev;
                deleteNode(temp);
                lru.remove(temp.key);
                n = new Node(key, value);
            } else {
                n.val = value;
                deleteNode(n);
            }
        } else {
            if (n == null) {
                n = new Node(key, value);
            } else {
                n.val = value;
                deleteNode(n);
            }
        }
        addNode(n);
        lru.put(key, n);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */