package Grind75.week1;

public class LinkedListCycle {
    //This solution has a time complexity of O(n) and uses O(1) space, satisfying the constraints of the problem.

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    static public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }

            ListNode slow = head;
            ListNode fast = head.next;

            while (slow != fast) {
                if (fast == null || fast.next == null) {
                    return false;
                }

                slow = slow.next;
                fast = fast.next.next;
            }

            return true;
        }
    }

    public static void main(String[] args) {
        // Creating a linked list with a cycle
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1; // Creating a cycle

        Solution solution = new Solution();
        boolean hasCycle = solution.hasCycle(head);
        System.out.println("Has Cycle: " + hasCycle); // Output: Has Cycle: true

        // Creating a linked list without a cycle
        head = new ListNode(1);
        node1 = new ListNode(2);
        head.next = node1;

        hasCycle = solution.hasCycle(head);
        System.out.println("Has Cycle: " + hasCycle); // Output: Has Cycle: false
    }
}
