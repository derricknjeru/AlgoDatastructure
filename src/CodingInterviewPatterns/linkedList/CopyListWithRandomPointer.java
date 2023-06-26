package CodingInterviewPatterns.linkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyListWithRandomPointer {
    //https://www.youtube.com/watch?v=5Y2EiZST97Y&ab_channel=NeetCode
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    static class Solution {
        /**
         * The time complexity of this solution is O(n) because it iterates over the original linked list twice.
         * The space complexity is also O(n) because it uses a hashmap to store the mappings between original and copied nodes.
         * @param head
         * @return
         */
        public Node copyRandomList(Node head) {
            Node cur = head;
            Map<Node, Node> map = new HashMap<>();

            while (cur != null) {
                map.put(cur, new Node(cur.val));
                cur = cur.next;
            }

            cur = head;

            //create links
            while (cur != null) {
                Node copy = map.get(cur);
                copy.next = map.get(cur.next);
                copy.random = map.get(cur.random);
                cur = cur.next;
            }

            return map.get(head);

        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        Node copy1 = solution.copyRandomList(node1);
        printLinkedList(copy1); // Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

        // Example 2
        Node node6 = new Node(1);
        Node node7 = new Node(2);
        node6.next = node7;
        node6.random = node7;
        node7.random = node7;

        Node copy2 = solution.copyRandomList(node6);
        printLinkedList(copy2); // Output: [[1,1],[2,1]]

        // Example 3
        Node node8 = new Node(3);
        Node node9 = new Node(3);
        Node node10 = new Node(3);
        node8.next = node9;
        node9.next = node10;
        node9.random = node8;

        Node copy3 = solution.copyRandomList(node8);
        printLinkedList(copy3); // Output: [[3,null],[3,0],[3,null]]
    }

    private static void printLinkedList(Node head) {
        Node current = head;
        List<List<Integer>> result = new ArrayList<>();
        while (current != null) {
            List<Integer> nodeData = new ArrayList<>();
            nodeData.add(current.val);
            if (current.random != null) {
                nodeData.add(getNodeIndex(head, current.random));
            } else {
                nodeData.add(null);
            }
            result.add(nodeData);
            current = current.next;
        }
        System.out.println(result);
    }

    private static Integer getNodeIndex(Node head, Node node) {
        int index = 0;
        Node current = head;
        while (current != node) {
            current = current.next;
            index++;
        }
        return index;
    }


}
