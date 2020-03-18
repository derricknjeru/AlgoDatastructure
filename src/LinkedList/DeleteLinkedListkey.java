package LinkedList;

public class DeleteLinkedListkey {
    ListNode head;


    public static void main(String[] args) {

        //4,5,1,9
        DeleteLinkedListkey linkedList = new DeleteLinkedListkey();

        //Inserting data.
        linkedList.head = new ListNode(4);
        ListNode second = new ListNode(5);
        ListNode third = new ListNode(1);
        ListNode fourth = new ListNode(9);

        // linking
        linkedList.head.next = second;
        second.next = third;
        third.next = fourth;


        System.out.println("\nCreated Linked list is:");
        printLinkedList(linkedList);

        linkedList.deleteNode(third); // Delete node at position 4

        System.out.println("\nLinked List after Deletion at position 4:");
        printLinkedList(linkedList);


    }

    private static void printLinkedList(DeleteLinkedListkey linkedList) {
        ListNode n = linkedList.head;
        while (n != null) {
            System.out.printf("%d ", n.data);
            n = n.next;
        }
    }

    static class ListNode {
        int data;
        ListNode next;

        ListNode(int d) {
            data = d;
            next = null;
        }
    }

    /**
     * Find previous node of the node to be deleted.
     * Change next of the previous node
     * Free memory of the node to be deleted
     */
    public void deleteNode(ListNode node) {


        //Store head node
        ListNode temp = head, prev = null;

        if (temp != null && temp.data == node.data) {
            head = temp.next;
            return;
        }

        // Search for the key to be deleted, keep track of the
        // previous node as we need to change temp.next

        while (temp != null && temp.data != node.data) {
            prev = temp;
            temp = temp.next;
        }

        //The key was not found
        if (temp == null) return;

        //Unlink the node from linked list
        prev.next = temp.next;

    }

}
