package linkedList;

public class OddEvenLinkedList {
    public class ListNode {
        int data;
        ListNode next;

        ListNode(int d) {
            data = d;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next; //move one step

            even.next = odd.next;
            even = even.next; //move one step

        }

        //old is head
        odd.next = evenHead;

        return head;
    }
}
