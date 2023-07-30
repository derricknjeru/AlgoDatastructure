package Grind75.week2;

public class ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Test Example 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        ListNode reversed1 = solution.reverseList(head1);
        Solution.printList(reversed1); // Output: 5 4 3 2 1

        // Test Example 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        ListNode reversed2 = solution.reverseList(head2);
        Solution.printList(reversed2); // Output: 2 1

        // Test Example 3
        ListNode head3 = null;
        ListNode reversed3 = solution.reverseList(head3);
        Solution.printList(reversed3); // Output: empty list []

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
        // Helper method to print the linked list for testing purposes
        public static void printList(ListNode head) {
            ListNode current = head;
            while (current != null) {
                System.out.print(current.val + " ");
                current = current.next;
            }
            System.out.println();
        }

        public ListNode reverseList(ListNode head) {
            ListNode current = head;
            ListNode prev = null;

            while (current != null) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            return prev;
        }
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
    static class Solution2 {

        // Helper method to print the linked list for testing purposes
        public static void printList(ListNode head) {
            ListNode current = head;
            while (current != null) {
                System.out.print(current.val + " ");
                current = current.next;
            }
            System.out.println();
        }

        public ListNode reverseList(ListNode head) {
            return helper(head);
        }

        private ListNode helper(ListNode head) {
            if (head == null || head.next == null) return head;

            ListNode reversedHead = helper(head.next);
            head.next.next = head;
            head.next = null;

            return reversedHead;
        }
    }

}


