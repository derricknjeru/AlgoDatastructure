package linkedList;

public class InsertOperations {
    private static Node head;
    static public int size;

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            insertAtHead(i);
        }
        System.out.println("Added at the head");
        System.out.println(printList(head));

        for (int i = 10; i <= 12; i++) {
            insertAtEnd(i);
        }
        System.out.println("Added at the end");
        System.out.println(printList(head));

        System.out.println(size);

    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    /**
     * Time Complexity: O(1), We have a pointer to the head, and we can directly attach a node and change the pointer. So the Time complexity of inserting a node at the head position is O(1) as it does a constant amount of work.
     * Auxiliary Space: O(1)
     *
     * @param data
     */
    private static void insertAtHead(int data) {
        Node new_node = new Node(data);
        new_node.next = head;
        head = new_node;
        size++;
    }

    /**
     * Time complexity: O(1), since prev_node is already given as argument in a method, no need to iterate over list to find prev_node
     * Auxiliary Space: O(1) since using constant space to modify pointers
     *
     * @param prev_node
     * @param new_data
     */
    public void insertAfter(Node prev_node, int new_data) {
        /* 1. Check if the given Node is null */
        if (prev_node == null) {
            System.out.println(
                    "The given previous node cannot be null");
            return;
        }

    /* 2. Allocate the Node &
    3. Put in the data*/
        Node new_node = new Node(new_data);

        /* 4. Make next of new Node as next of prev_node */
        new_node.next = prev_node.next;

        /* 5. make next of prev_node as new_node */
        prev_node.next = new_node;
    }

    /**
     * Time complexity: O(N), where N is the number of nodes in the linked list. Since there is a loop from head to end, the function does O(n) work.
     * This method can also be optimized to work in O(1) by keeping an extra pointer to the tail of the linked list/
     * Auxiliary Space: O(1)
     *
     * @param new_data
     */

    private static void insertAtEnd(int new_data) {
       /* 1. Allocate the Node &
       2. Put in the data
       3. Set next as null */
        Node new_node = new Node(new_data);

    /* 4. If the Linked List is empty, then make the
           new node as head */
        if (head == null) {
            head = new Node(new_data);
            size++;
            return;
        }

    /* 4. This new node is going to be the last node, so
         make next of it as null */
        new_node.next = null;

        /* 5. Else traverse till the last node */
        Node last = head;
        while (last.next != null) last = last.next;

        /* 6. Change the next of last node */
        last.next = new_node;
        size++;
        return;
    }

    public void addAtIndex(int position, int val) {
        int index = position - 1;
        if (index < 0 || index > size) return;

        Node prev = head;

        //moving to the prev node.
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node node = new Node(val);
        node.next = prev.next;
        prev.next = node;

        size++;
    }


    private static String printList(Node head) {
        Node cur = head;
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        while (cur != null) {
            sb.append(prefix);
            sb.append(cur.data);
            prefix = " -> ";
            cur = cur.next;
        }
        return sb.append(" -> null ").toString();
    }
}
