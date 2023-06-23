package CodingInterviewPatterns.FastAndSlowPointers;

public class DeleteMiddleOfTheLinkedList {
    //https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/
    //https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/editorial/

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        /**
         * Time complexity: O(n)
         * <p>
         * We stop the iteration when the pointer fast reaches the end, fast moves forward 2 nodes per step, so there are at most n/2n/2n/2 steps.
         * In each step, we move both fast and slow, which takes a constant amount of time.
         * Removing the middle node also takes constant time.
         * In summary, the overall time complexity is O(n)
         * Space complexity: O(1)
         * <p>
         * We only need two pointers, so the space complexity is O(1)
         */
        private class ListNode {
            int val;
            ListNode next;

            ListNode(int x) {
                val = x;
                next = null;
            }
        }

        public ListNode deleteMiddle(ListNode head) {
            // Edge case: return nullptr if there is only one node.
            if (head.next == null)
                return null;

            // Initialize two pointers, 'slow' and 'fast'.
            ListNode slow = head, fast = head.next.next;

            // Let 'fast' move forward by 2 nodes, 'slow' move forward by 1 node each step.
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            // When 'fast' reaches the end, remove the next node of 'slow' and return 'head'.
            slow.next = slow.next.next;
            return head;
        }
    }

    class Solution2 {
        /**
         * Let nnn be the length of the input linked list.
         * <p>
         * Time complexity: O(n)
         * <p>
         * We iterate over the linked list twice, the first time traversing the entire linked list and the second traversing half of it. Hence there are a total of O(n)O(n)O(n) steps.
         * In each step, we move a pointer forward by one node, which takes constant time.
         * Remove the middle node takes a constant amount of time.
         * In summary, the overall time complexity is O(n)
         * Space complexity: O(1)
         * <p>
         * We only need two pointers, thus the space complexity is O(1)
         */
        private class ListNode {
            int val;
            ListNode next;

            ListNode(int x) {
                val = x;
                next = null;
            }
        }

        public ListNode deleteMiddle(ListNode head) {
            // Edge case: return None if there is only one node
            if (head.next == null)
                return null;

            int count = 0;
            ListNode p1 = head, p2 = head;

            // First pass, count the number of nodes in the linked list using 'p1'.
            while (p1 != null) {
                count += 1;
                p1 = p1.next;
            }

            // Get the index of the node to be deleted.
            int middleIndex = count / 2;

            // Second pass, let 'p2' move toward the predecessor of the middle node.
            for (int i = 0; i < middleIndex - 1; ++i)
                p2 = p2.next;

            // Delete the middle node and return 'head'.
            p2.next = p2.next.next;
            return head;
        }
    }
}
