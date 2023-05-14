package revision_v2.linkedList;

public class RemoveDuplicatesfromSortedList {
    public static void main(String[] args) {

    }

    /**
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     * <p>
     * Example 1:
     * <p>
     * Input: 1->1->2
     * Output: 1->2
     * Example 2:
     * <p>
     * Input: 1->1->2->3->3
     * Output: 1->2->3
     *
     * @param head
     * @return
     */


    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;

        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                //skip current node.next
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }

        }

        return head;

    }
}

// Definition for singly-linked list.
class ListNode {
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

