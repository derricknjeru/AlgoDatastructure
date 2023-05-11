package linkedList;

public class ReverseLinkedListII {
    //https://leetcode.com/problems/reverse-linked-list-ii/

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

    //Time complexity O(n)
    //Space complexity O(1)

    public ListNode reverseBetween(ListNode head, int left, int right) {
        //https://www.youtube.com/watch?v=RF_M9tX4Eag
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head;
        ListNode Lprev = dummy;
        //Reach nodes at position "left"
        for (int i = 0; i < left - 1; i++) {
            Lprev = curr;
            curr = curr.next;
        }

        //curr = "left" & Lprev is the node before left
        //Reverse from left to right
        ListNode next = null, prev = null;
        for (int i = 0; i < right - left + 1; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        //update pointers
        Lprev.next.next = curr; //cur is node after right
        Lprev.next = prev; //prev is node at position right

        return dummy.next;
    }
}
