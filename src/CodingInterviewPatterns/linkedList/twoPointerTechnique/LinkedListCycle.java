package CodingInterviewPatterns.linkedList.twoPointerTechnique;

import java.util.HashSet;
import java.util.Set;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

//https://www.youtube.com/watch?v=gBTe7lFR3vc&t=11s

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note.md.md.md.md that pos is not passed as a parameter.
 * <p>
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 * Example 3:
 * <p>
 * <p>
 * Input: head = [1], pos = -1
 * Output: false
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
 */

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            //You have to navigate first before checking
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;

        }

        return false;
    }

    public static boolean hasCycle2(ListNode head) {
        if (head == null) return false;

        Set<ListNode> set = new HashSet<>();
        ListNode node = head;

        while (node != null) {
            if (!set.add(node)) return true;
            node = node.next;
        }
        return false;
    }

}


//pwwkew
//f
//s

//p f1
//pw f2
//w s1
//_ s2/f2
//w f3
//wk f4
//wke f5
//wke s3
//kew
