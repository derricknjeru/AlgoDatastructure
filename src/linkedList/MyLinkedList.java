package linkedList;

class MyLinkedList {

    public class Node {
        Node next;
        int val;

        Node(int value) {
            this.val = value;
        }
    }


    private int size;
    private Node head;

    public MyLinkedList() {
        head = new Node(0);
    }

    public int get(int index) {

        if (index < 0 || index >= size) return -1;

        Node curr = head;

        for (int i = 0; i <= index; i++) {
            curr = curr.next;
        }

        return curr.val;


    }

    public void addAtHead(int val) {
        addAtIndex(0, val);

    }


    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
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

    public void deleteAtIndex(int index) {

        if (index < 0 || index >= size) return;

        Node prev = head;

        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = prev.next.next;

        size--;
    }

}


/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 **/
