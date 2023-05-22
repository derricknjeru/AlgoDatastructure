package linkedList;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Time complexity : O(n), where n is the number of nodes in the Linked List.
     * <p>
     * Similar to the above approaches. Finding the middle is O(n),
     * reversing a list in place is O(n), and then comparing the 2 resulting Linked Lists is also O(n).
     * <p>
     * Space complexity : O(1).
     * <p>
     * We are changing the next pointers for half of the nodes.
     * This was all memory that had already been allocated,
     * so we are not using any extra memory and therefore it is O(1)
     * <p>
     * I have seen some people on the discussion forum saying it has to be O(n) because we're creating a new list. This is incorrect,
     * because we are changing each of the pointers one-by-one, in-place.
     * We are not needing to allocate more than O(1) extra memory to do this work, and there is O(1) stack frames going on the stack.
     * It is the same as reversing the values in an Array in place (using the two-pointer technique).
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;

        ListNode midNode = getMidNode(head);
        ListNode revNode = reverseMidNode(midNode);

        while (revNode != null) {
            if (head.val != revNode.val) {
                return false;
            }
            head = head.next;
            revNode = revNode.next;
        }

        return true;

    }

    private ListNode getMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode reverseMidNode(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = head;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;

    }

    /**
     * Time complexity : O(n), where n is the number of nodes in the Linked List.
     * <p>
     * In the first part, we're copying a Linked List into an Array List.
     * Iterating down a Linked List in sequential order is O(n),
     * and each of the n writes to the ArrayList is O(1). Therefore, the overall cost is O(n).
     * <p>
     * In the second part, we're using the two pointer technique to check whether or not the Array List is a palindrome.
     * Each of the n values in the Array list is accessed once, and a total of O(n/2)
     * comparisons are done. Therefore, the overall cost is O(n).
     * The Python trick (reverse and list comparison as a one liner) is also O(n).
     * <p>
     * This gives O(2n)=O(n) because we always drop the constants.
     * <p>
     * Space complexity : O(n), where n is the number of nodes in the Linked List.
     * <p>
     * We are making an Array List and adding nnn values to it.
     *
     * @param head
     * @return
     */


    public boolean isPalindrome2(ListNode head) {
        List<Integer> vals = new ArrayList<>();

        // Convert LinkedList into ArrayList.
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // Use two-pointer technique to check for palindrome.
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            // Note.md.md that we must use ! .equals instead of !=
            // because we are comparing Integer, not int.
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }


}
