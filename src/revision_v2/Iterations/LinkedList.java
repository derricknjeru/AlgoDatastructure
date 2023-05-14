package revision_v2.Iterations;

public class LinkedList {
    Node head;

    class Node {
        int data;
        Node next;

        Node(int val) {
            data = val;
            next = null;
        }

    }

    public static void main(String args[]) {
        LinkedList list = new LinkedList();
        for (int i = 1; i <= 10; i++) {
            list.push(i);
        }

        list.println();

    }

    public void push(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
    }

    public void println() {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }


}
