package CodingInterviewPatterns.linkedList.twoPointerTechnique;

import java.util.*;

public class LinkedListCycleII {
    private static int[] res;
    //https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1214/

    /**
     * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * <p>
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note.md.md.md.md that pos is not passed as a parameter.
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
     * @return
     */
    public class Solution {
        /**
         * Let nnn be the total number of nodes in the linked list.
         * <p>
         * Time complexity: O(n)O
         * The algorithm consists of two phases. In the first phase, we use two pointers (the "hare" and the "tortoise") to traverse the list. The slow pointer (tortoise) will go through the list only once until it meets the hare. Therefore, this phase runs in O(n)O(n)O(n) time.
         * <p>
         * In the second phase, we again have two pointers traversing the list at the same speed until they meet. The maximum distance to be covered in this phase will not be greater than the length of the list (recall that the hare just needs to get back to the entrance of the cycle). So, this phase also runs in O(n)O(n)O(n) time.
         * <p>
         * As a result, the total time complexity of the algorithm is O(n) + O(n) which simplifies to O(n).
         * <p>
         * Space complexity: O(1)
         * The space complexity is constant, O(1), because we are only using a fixed amount of space to store the slow and fast pointers. No additional space is used that scales with the input size. So the space complexity of the algorithm is O(1)O(1)O(1), which means it uses constant space.
         *
         * @param head
         * @return
         */
        public ListNode detectCycle(ListNode head) {
            // Initialize tortoise and hare pointers
            ListNode tortoise = head;
            ListNode hare = head;
            boolean cycleExist = false;

            // Move tortoise one step and hare two steps
            while (hare != null && hare.next != null) {
                tortoise = tortoise.next;
                hare = hare.next.next;

                // Check if the hare meets the tortoise
                if (tortoise == hare) {
                    cycleExist = true;
                    break;
                }
            }

            // Check if there is no cycle
            if (!cycleExist) {
                return null;
            }

            // Reset either tortoise or hare pointer to the head
            tortoise = head;

            // Move both pointers one step until they meet again
            while (tortoise != hare) {
                tortoise = tortoise.next;
                hare = hare.next;
            }

            // Return the node where the cycle begins
            return tortoise;
        }
    }


    public ListNode detectCycle2(ListNode head) {
        /**
         * Let nnn be the total number of nodes in the linked list.
         *
         * Time complexity: O(n)
         * We have to visit all nodes once.
         *
         * Space complexity: O(n)
         * We have to store all nodes in the hash set.
         */
        if (head == null) return null;

        ListNode temp = head;
        Set<ListNode> set = new HashSet<>();

        while (temp != null) {
            if (!set.add(temp)) return temp;
            temp = temp.next;
        }

        return null;
    }

    public static void main(String[] args) {

    }
}
