package stack_and_queue;

public class StackImplementation {
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    Node top;

    public boolean isEmpty() {
        return top == null;
    }

    public int peek() {
        return top.data;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;

    }

    public int pop(){
        int data=top.data;
        top=top.next;

        return data;
    }
}
