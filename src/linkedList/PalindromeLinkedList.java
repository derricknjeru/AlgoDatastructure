package linkedList;

public class PalindromeLinkedList {
    public static void main(String[] args) {

    }

    /**
     * Given a singly linked list, determine if it is a palindrome.
     * <p>
     * Example 1:
     * <p>
     * Input: 1->2
     * Output: false
     * Example 2:
     * <p>
     * Input: 1->2->2->1
     * Output: true
     * Follow up:
     * Could you do it in O(n) time and O(1) space?
     */

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int _val) {
            val = _val;
            next = null;
        }

    }

    public boolean isPalindrome(ListNode head) {
        if(head==null) return true;

        ListNode midNode = getMidNode(head);
        ListNode revNode = reverseMidNode(midNode);

        while(revNode!=null){
            if(head.val!=revNode.val){
                return false;
            }
            head = head.next;
            revNode=revNode.next;
        }

        return true;

    }

    private ListNode getMidNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next !=null){
            slow = slow.next;
            fast=fast.next.next;
        }

        return slow;
    }

    private ListNode reverseMidNode(ListNode head){
        ListNode prev = null;
        ListNode curr= head;
        ListNode next =head;

        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;

    }


}
