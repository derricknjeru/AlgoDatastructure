package linkedList;

public class SwapNodesInPairs {
    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int _val) {
            val = _val;
            next = null;
        }

    }

    //Time complexity = O(n)
    //Space complexity = O(n) -> because space is need in order to maintain the stack in case of a recursion
    public ListNode swapPairsRecursive(ListNode head) {
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        swap(dummy);
        return dummy.next;
    }

    void swap(ListNode node) {
        if (node == null) return;

        ListNode first = node.next;
        ListNode second = null;
        if (first != null) {
            second = first.next;
        }

        if (second != null) {
            ListNode secondNext = second.next;
            second.next = first;
            node.next = second;
            first.next = secondNext;

            swap(first);
        }

    }

    //Time complexity = O(n)
    //Space complexity = O(1)
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy;

        while (node != null) {

            ListNode first = node.next;
            ListNode second = null;
            if (first != null) {
                second = first.next;
            }

            if (second != null) {
                ListNode secondNext = second.next;
                second.next = first;
                node.next = second;
                first.next = secondNext;
                node = first;
            } else {
                break;
            }
        }

        return dummy.next;
    }
}
