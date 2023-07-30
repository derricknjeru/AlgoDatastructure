package Grind75.week2;

public class MiddleOfTheLinkedList {
    public static void main(String[] args) {
        Solution sln = new Solution();
        // Create the linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        ListNode middle1 = sln.middleNode(head1);
        System.out.println("Middle node of the first linked list: " + middle1.val);

        // Create the linked list: 1 -> 2 -> 3 -> 4 -> 5 -> 6
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next = new ListNode(6);

        ListNode middle2 = sln.middleNode(head2);
        System.out.println("Middle node of the second linked list: " + middle2.val);

    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    static class Solution {
        public ListNode middleNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }
}
