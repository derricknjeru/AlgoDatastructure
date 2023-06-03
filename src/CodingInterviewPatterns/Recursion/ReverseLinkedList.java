package CodingInterviewPatterns.Recursion;

public class ReverseLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
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

    public static void main(String[] args) {
        // Create an instance of ReverseLinkedList
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();

        // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = reverseLinkedList.new ListNode(1);
        head.next = reverseLinkedList.new ListNode(2);
        head.next.next = reverseLinkedList.new ListNode(3);
        head.next.next.next = reverseLinkedList.new ListNode(4);
        head.next.next.next.next = reverseLinkedList.new ListNode(5);

        // Print the original linked list
        System.out.println("Original Linked List:");
        printLinkedList(head);

        // Reverse the linked list
        ListNode reversedHead = reverseLinkedList.reverseList(head);

        // Print the reversed linked list
        System.out.println("Reversed Linked List:");
        printLinkedList(reversedHead);
    }

    // Helper method to print the linked list
    private static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

}
