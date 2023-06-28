package CodingInterviewPatterns.linkedList;

public class DesignDoublyLinkedList {
    //https://www.geeksforgeeks.org/introduction-and-insertion-in-a-doubly-linked-list/
    //https://www.youtube.com/watch?v=nquQ_fYGGA4
    //TODO https://leetcode.com/explore/learn/card/linked-list/210/doubly-linked-list/1294/

    class ListNode {
        int val;
        ListNode prev;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode right;
    private ListNode left;

    public DesignDoublyLinkedList() {
        right = new ListNode(-1); //right dummy
        left = new ListNode(-1); //left dummy
        left.next = right;
        right.prev = left;
    }

    public int get(int index) {

       /* Node curr = head;

        for(int i = 0; i<=index; i++){
            curr = curr.next;
        }

        return curr.val;*/
        return 0;

    }


}
