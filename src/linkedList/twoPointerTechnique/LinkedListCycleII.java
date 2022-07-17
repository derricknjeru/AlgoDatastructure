package linkedList.twoPointerTechnique;

import linkedList.twoPointerTechnique.ListNode;

import java.util.*;

public class LinkedListCycleII {
    private static int[] res;
    //https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1214/

    /**
     * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * <p>
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
     * <p>
     * Do not modify the linked list.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: head = [3,2,0,-4], pos = 1
     * Output: tail connects to node index 1
     * Explanation: There is a cycle in the linked list, where tail connects to the second node.
     * Example 2:
     * <p>
     * <p>
     * Input: head = [1,2], pos = 0
     * Output: tail connects to node index 0
     * Explanation: There is a cycle in the linked list, where tail connects to the first node.
     * Example 3:
     * <p>
     * <p>
     * Input: head = [1], pos = -1
     * Output: no cycle
     * Explanation: There is no cycle in the linked list.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * The number of the nodes in the list is in the range [0, 104].
     * -105 <= Node.val <= 105
     * pos is -1 or a valid index in the linked-list.
     * <p>
     * <p>
     * Follow up: Can you solve it using O(1) (i.e. constant) memory?
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        //Check if it's cyclic

        if (head == null) {
            return null;
        }

        ListNode slow = head, fast = head;

        boolean isCyclic = false;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                isCyclic = true;
                break;
            }

        }

        if (isCyclic) {
            //current slow or fast is the intersection.
            ListNode start = head;

            while (start != slow) {

                slow = slow.next;
                start = start.next;
            }

            //could be start or slow

            return start;

        }

        return null;
    }

    public static void main(String[] args) {

    }
}
