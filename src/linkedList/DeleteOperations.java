package linkedList;

public class DeleteOperations {
    private Node head;

    public static void main(String[] args) {
        DeleteOperations dl = new DeleteOperations();

        for (int i = 1; i <= 10; i++) {
            dl.insertAtHead(i);
        }

        dl.deleteNode(9);

        dl.printList();

    }

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }


    //Deletion at Head#
    private void deleteHead() {
        Node temp = head;
        if (temp == null) return;
        head = head.next;
    }

    /**
     * Time Complexity
     * In the worst case, you would have to traverse until the end of the list.
     * This means the time complexity will be O(n).
     *
     * @param data
     */

    //Delete by Value
    private void deleteAtValue(int data) {
        // Store head node
        Node temp = head, prev = null;

        // If head node itself holds the key to be deleted
        if (temp != null && temp.data == data) {
            head = temp.next; // Changed head
            return;
        }

        // Search for the key to be deleted, keep track of
        // the previous node as we need to change temp.next
        while (temp != null && temp.data != data) {
            prev = temp;
            temp = temp.next;
        }

        // If key was not present in linked list
        if (temp == null)
            return;

        // Unlink the node from linked list
        prev.next = temp.next;
    }

    /* Given a reference (pointer to pointer) to the head of a list
      and a position, deletes the node at the given position */
    void deleteNode(int position) {
        // If linked list is empty
        if (head == null)
            return;

        //store the head
        Node prev = head;

        // If head needs to be removed
        if (position == 0) {
            head = prev.next;
            return;
        }

        // Find previous node of the node to be deleted
        int index = position - 1;
        for (int i = 0; i < index; i++) {
            if (prev != null) {
                prev = prev.next;
            }
        }

        // // If position is more than number of nodes
        if (prev == null || prev.next == null) return;

        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        //Basically the node that will be temp next;

        prev.next = prev.next.next; // Unlink the deleted node from list
    }

    private void insertAtHead(int data) {
        Node new_node = new Node(data);
        new_node.next = head;
        head = new_node;
    }

    private void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
