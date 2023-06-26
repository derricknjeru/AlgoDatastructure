package CodingInterviewPatterns.linkedList;


public class ReorderList {
    //https://www.youtube.com/watch?v=S5bfdUTrKLM&ab_channel=NeetCode

    public static void main(String[] args) {
        // Create a sample linked list
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // Create an instance of the Solution class
        Solution solution = new Solution();

        // Call the reorderList method
        solution.reorderList(head);

        // Print the reordered list
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    static class Solution {
        //The time complexity of the given code is O(n),
        // where n is the number of nodes in the linked list.
        public void reorderList(ListNode head) {
            if (head == null || head.next == null)
                return;

            // Find the middle node
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            // Reverse the second half
            ListNode second = reverseList(slow.next);
            //This effectively separates the first half of the linked list from the second half.
            slow.next = null;

            // Reorder the list by alternating nodes
            ListNode first = head;
            while (second != null) {
                ListNode temp1 = first.next;
                ListNode temp2 = second.next;
                first.next = second;
                second.next = temp1;
                first = temp1;
                second = temp2;
            }
        }

        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }
    }
}
