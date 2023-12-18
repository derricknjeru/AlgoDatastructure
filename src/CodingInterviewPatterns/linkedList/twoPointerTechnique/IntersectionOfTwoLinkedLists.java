package CodingInterviewPatterns.linkedList.twoPointerTechnique;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoLinkedLists {
    //https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1215/

    /**
     * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
     * <p>
     * For example, the following two linked lists begin to intersect at node c1:
     * <p>
     * <p>
     * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
     * <p>
     * Note.md.md.md.md that the linked lists must retain their original structure after the function returns.
     * <p>
     * Custom Judge:
     * <p>
     * The inputs to the judge are given as follows (your program is not given these inputs):
     * <p>
     * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
     * listA - The first linked list.
     * listB - The second linked list.
     * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
     * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
     * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
     * Output: Intersected at '8'
     * Explanation: The intersected node's value is 8 (README.md.md that this must not be 0 if the two lists intersect).
     * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
     * Example 2:
     * <p>
     * <p>
     * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * Output: Intersected at '2'
     * Explanation: The intersected node's value is 2 (README.md.md that this must not be 0 if the two lists intersect).
     * From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
     * Example 3:
     * <p>
     * <p>
     * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * Output: No intersection
     * Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
     * Explanation: The two lists do not intersect, so return null.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * The number of nodes of listA is in the m.
     * The number of nodes of listB is in the n.
     * 1 <= m, n <= 3 * 104
     * 1 <= Node.val <= 105
     * 0 <= skipA < m
     * 0 <= skipB < n
     * intersectVal is 0 if listA and listB do not intersect.
     * intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
     * <p>
     * <p>
     * Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?
     */

    //https://www.youtube.com/watch?v=IpBfg9d4dmQ
    // https://www.youtube.com/watch?v=D0X0BONOQhI
    //https://www.youtube.com/watch?v=CPXIkMWNn5Q -> No optimal

    //Time complexity is O(n+m) size of each list
    //Space complexity is O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null && headB == null) return null;

        ListNode a_pointer = headA;
        ListNode b_pointer = headB;

        while (a_pointer != b_pointer) {
            a_pointer = a_pointer == null ? headB : a_pointer.next;
            b_pointer = b_pointer == null ? headA : b_pointer.next;
        }

        return b_pointer;
    }

    //Time complexity is O(n+m) size of each list
    //Space complexity is O(n)

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        Set<ListNode> inter = new HashSet<>();

        ListNode temp1 = headA;

        while (temp1 != null) {
            inter.add(temp1);
            temp1 = temp1.next;
        }


        ListNode temp2 = headB;
        while (temp2 != null) {
            if (!inter.add(temp2)) return temp2;
            temp2 = temp2.next;
        }

        return null;
    }

    //https://www.youtube.com/watch?v=8qi8a2ph71o
    //Time complexity is O(n+m) size of each list
    //Space complexity is O(1)
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null && headB == null) return null;

        ListNode lA = headA;
        ListNode lB = headB;

        int len1 = 0, len2 = 0;

        while (lA != null) {
            lA = lA.next;
            len1++;
        }


        while (lB != null) {
            lB = lB.next;
            len2++;
        }

        int diff = Math.abs(len1 - len2);

        lA = headA;
        lB = headB;

        if (len1 > len2) {

            while (diff > 0) {
                lA = lA.next;
                diff--;
            }
        } else {

            while (diff > 0) {
                lB = lB.next;
                diff--;
            }
        }

        while (lA != lB) {

            lA = lA.next;

            lB = lB.next;

            if (lA == null || lB == null) return null;
        }

        return lA;
    }
}
