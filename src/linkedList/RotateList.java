package linkedList;

public class RotateList {
    //https://leetcode.com/explore/learn/card/linked-list/213/conclusion/1295/
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null) {
            return head;
        }

        int _length = 1;
        ListNode tail = head;

        while (tail != null && tail.next != null) {
            _length++;
            tail = tail.next;
        }

        k = k % _length; //Make sure it fits to the length of the list

        if (k == 0) return head;

        ListNode curr = head;
        ListNode newHead = head;

        for (int i = 0; i < _length - k - 1; i++) {
            curr = curr.next;
        }

        newHead = curr.next;
        curr.next = null;
        tail.next = head;

        return newHead;

    }
}
