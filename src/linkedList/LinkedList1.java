package linkedList;
//traversal
public class LinkedList1 {
    Node head;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    /* Linked list Node.  This inner class is made static so that
          main() can access it */
    public static void main(String[] args) {
        LinkedList1 llist = new LinkedList1();

        llist.head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);

        llist.head.next = second; // Link first node with the second node
        second.next = third;// Link first node with the second node

        // Link first node with the second node
        llist.printList();
    }

    /* This function prints contents of linked list starting from head */
    public void printList() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
    }
}