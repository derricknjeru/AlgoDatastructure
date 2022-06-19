package stack_and_queue;

public class QueueImplementationWithLinkedList {

    //https://leetcode.com/explore/learn/card/queue-stack/228/first-in-first-out-data-structure/1396/
    //https://www.youtube.com/watch?v=RN1wzY_tnYU
    //enQueue and deQueue have to have time complexity of O(1);
    private class Node {
        int data;
        Node next;

        private Node(int data) {
            this.data = data;
        }
    }

    Node head; //remove from the head;
    Node tail; //add things

    public boolean isEmpty() {
        return head == null;
    }

    public void display(){
        if(head==null && tail==null){
            System.out.println("queue is empty");
        }else {
            Node curr=head;
            while (curr!=null) {
                System.out.println(curr.data);
                curr=curr.next;
            }
        }
    }

    public void enQueue(int data) {
        Node newNode = new Node(data);
        if (head == null && tail == null) {
            head = tail = newNode;
        } else {
            //Always insert from the rear head(tail)
            tail.next = newNode; //next of tail is the new node;
            tail = newNode; //now move the value of tail to the newNode;
        }
    }

    public void deQueue(){
       if(head==null && tail==null){
           System.out.println("queue is empty");
       }else {
           head=head.next;
       }
    }

    public int peek(){
        if(head==null && tail==null){
            System.out.println("queue is empty");
            return -1;
        }else {
            return head.data;
        }
    }
}
