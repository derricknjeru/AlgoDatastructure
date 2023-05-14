package revision_v2.linkedList;

public class RotateLinkedList {
    public static void main(String[] args) {

    }

    public ListNode rotateRight(ListNode head, int k) {
        //Base case
        if (head == null) {
            return head;
        }

        int len = 1; //will start at 1 to cater for the last node
        ListNode tail = head;

        //find the length by going through the list

        while (tail.next != null) {
            len++;
            tail = tail.next;
        }

        tail.next = head; //link the tail to the head.

        int rotatedHeadIndex = len - k % len;  // we use modules in a case where k
        //is greater than n

        if (rotatedHeadIndex == 0) {
            return head;  // There is no need to rotate
        }

        ListNode newTail = tail;

        while (rotatedHeadIndex-- > 0) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;

        //break the link of the tail

        newTail.next = null;

        return newHead;

    }
}
