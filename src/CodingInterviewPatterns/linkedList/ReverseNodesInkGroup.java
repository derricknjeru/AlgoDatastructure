package CodingInterviewPatterns.linkedList;

public class ReverseNodesInkGroup {
    //https://www.youtube.com/watch?v=1UOPsfP85V4&ab_channel=NeetCode

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode groupPrev = dummy;

            while (groupPrev.next != null) {
                ListNode kth = getKth(groupPrev, k);
                if (kth == null) {
                    break;
                }
                ListNode groupNext = kth.next;

                // Reverse group
                ListNode prev = kth.next;
                ListNode curr = groupPrev.next;
                while (curr != groupNext) {
                    ListNode tmp = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = tmp;
                }

                ListNode tmp = groupPrev.next;
                groupPrev.next = kth;
                groupPrev = tmp;
            }

            return dummy.next;
        }

        private ListNode getKth(ListNode curr, int k) {
            while (curr != null && k > 0) {
                curr = curr.next;
                k--;
            }
            return curr;
        }

    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution2 {
        public ListNode reverseKGroup(ListNode head, int k) {
            // Base case: if the remaining nodes are less than k, return the head
            if (head == null || getKthNode(head, k) == null) {
                return head;
            }

            ListNode prev = null;
            ListNode curr = head;
            int count = 0;

            // Reverse the current group of k nodes
            while (curr != null && count < k) {
                ListNode nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
                count++;
            }

            // Recursively reverse the next group of k nodes
            head.next = reverseKGroup(curr, k);

            // Return the head of the reversed list
            return prev;
        }

        // Helper function to get the k-th node from a given node
        private  ListNode getKthNode(ListNode node, int k) {
            int count = 1;
            while (node != null && count < k) {
                node = node.next;
                count++;
            }
            return node;
        }
    }
}
