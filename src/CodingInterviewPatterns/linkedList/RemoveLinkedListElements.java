package CodingInterviewPatterns.linkedList;

public class RemoveLinkedListElements {
    //https://www.youtube.com/watch?v=5lNkvTd7PM4
    // Definition for singly-linked list.

    /**
     * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: head = [1,2,6,3,4,5,6], val = 6
     * Output: [1,2,3,4,5]
     * Example 2:
     *
     * Input: head = [], val = 1
     * Output: []
     * Example 3:
     *
     * Input: head = [7,7,7,7], val = 7
     * Output: []
     *
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [0, 104].
     * 1 <= Node.val <= 50
     * 0 <= val <= 50
     */
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeElements(ListNode head, int val) {
        //https://www.youtube.com/watch?v=JI71sxtHTng
        //https://www.youtube.com/watch?v=5lNkvTd7PM4
        ListNode dummy = new ListNode(0);
        dummy.next=head;

        ListNode prev=dummy;
        ListNode curr=head;

        while(curr!=null){
            if(curr.val == val){
                prev.next=curr.next;
            }else{
                prev = curr;
            }

            curr=curr.next;
        }

        return dummy.next;
    }
}
