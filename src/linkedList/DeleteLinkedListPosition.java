package linkedList;

public class DeleteLinkedListPosition {
    Node head;

    public static void main(String[] args) {

        DeleteLinkedListPosition linkedListPosition = new DeleteLinkedListPosition();

        linkedListPosition.push(1);
        linkedListPosition.push(7);
        linkedListPosition.push(1);
        linkedListPosition.push(3);
        linkedListPosition.push(2);
        linkedListPosition.push(8);


        System.out.println("\nCreated Linked list is: ");
        linkedListPosition.printList();

        linkedListPosition.deleteNode(4);  // Delete node at position 4

        System.out.println("\nLinked List after Deletion at position 4: ");
        linkedListPosition.printList();


    }

    /* Given a reference (pointer to pointer) to the head of a list
     and a position, deletes the node at the given position */
    void deleteNode(int position) {
        // If linked list is empty
        if (head == null)
            return;

        //store the head
        Node temp = head;

        // If head needs to be removed
        if (position == 0) {
            head = temp.next;
            return;
        }

        // Find previous node of the node to be deleted
        for (int i = 0; i < position - 1; i++) {
            if (temp != null) {
                temp = temp.next;
            }
        }

        // // If position is more than number of ndoes
        if (temp == null || temp.next == null) return;

        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        //Basically the node that will be temp next;

        temp.next = temp.next.next; // Unlink the deleted node from list


    }


    public void printList() {
        Node n = head;
        while (n != null) {
            System.out.printf("%d ", n.data);
            n = n.next;
        }

    }

    public void push(int data) {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
        }
    }


}
