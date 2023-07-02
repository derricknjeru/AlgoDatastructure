package CodingInterviewPatterns.KWayMerge;

import java.util.PriorityQueue;

public class MergeKSortedLists {
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
         * //https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4807159151067136
         * The time complexity of the provided code, which uses an iterative approach to merge K sorted linked lists,
         * is O(N log K), where N is the total number of nodes across all the linked lists and K is the number of linked lists.
         * <p>
         * Here's a breakdown of the time complexity:
         * <p>
         * The outer while loop runs for log K iterations because the interval variable is doubled in each iteration
         * until it becomes greater than the size of the array. Since interval starts at 1 and doubles in each iteration,
         * it takes log K iterations for interval to become greater than or equal to K.
         * Therefore, the outer loop has a time complexity of O(log K).
         * <p>
         * The inner for loop runs for size / (2 * interval) iterations in each iteration of the outer loop.
         * It starts from index 0 and merges pairs of lists at the current interval. The number of iterations decreases
         * by a factor of 2 in each iteration of the outer loop because each iteration merges two lists and moves to the next pair of lists.
         * Therefore, the number of iterations in the inner loop can be approximated to size / (2 * interval).
         * <p>
         * The merge function merges two lists of sizes l1 and l2 in linear time, O(l1 + l2).
         * <p>
         * Combining the above complexities, the total time complexity is:
         * <p>
         * O(log K) * O(size / (2 * interval)) * O(l1 + l2)
         * <p>
         * Since the sizes of the lists can vary, the worst-case time complexity occurs when the sizes of the lists are balanced.
         * In this case, the total number of nodes across all the linked lists, N, is approximately size * log K.
         * Therefore, the time complexity can be simplified to:
         * <p>
         * O(log K) * O(N / (2 * interval)) * O(l1 + l2) = O(N log K)
         * <p>
         * So, the time complexity of this approach is O(N log K),
         * where N is the total number of nodes across all the linked lists and K is the number of linked lists.
         *
         * @param lists
         * @return
         */
        //The time complexity is O(nlogk), where k is the number of the lists and is the maximum length of a single
        public ListNode mergeKLists(ListNode[] lists) {
            int size = lists.length;
            int interval = 1;

            // Iterate until the interval is greater than the size of the array
            while (interval < size) {
                // Merge pairs of lists at the current interval
                for (int i = 0; i < size - interval; i += 2 * interval) {
                    // Merge the two lists at index i and i + interval
                    lists[i] = merge(lists[i], lists[i + interval]);
                }

                // Double the interval for the next iteration
                interval *= 2;
            }

            // Return the merged list
            return size > 0 ? lists[0] : null;
        }

        private ListNode merge(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode curr = dummy;

            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    curr.next = l1;
                    l1 = l1.next;
                } else {
                    curr.next = l2;
                    l2 = l2.next;
                }

                curr = curr.next;
            }

            if (l1 != null) {
                curr.next = l1;
            } else {
                curr.next = l2;
            }

            return dummy.next;
        }
    }

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
    class Solution2 {
        //n.k which is slower
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) return null;
            if (lists.length == 1) return lists[0];

            ListNode head = mergeList(lists[0], lists[1]);

            for (int i = 2; i < lists.length; i++) {
                head = mergeList(head, lists[i]);
            }

            return head;
        }

        private ListNode mergeList(ListNode l1, ListNode l2) {

            ListNode dummy = new ListNode(-1);
            ListNode curr = dummy;

            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    curr.next = l1;
                    l1 = l1.next;
                } else {
                    curr.next = l2;
                    l2 = l2.next;
                }

                curr = curr.next;
            }

            if (l1 != null) curr.next = l1;

            if (l2 != null) curr.next = l2;

            return dummy.next;

        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution3 {
        //The time complexity of the provided code is O(N log K),
        // where N is the total number of nodes across all the linked lists and K is the number of linked lists.

        /**
         * Here's a breakdown of the time complexity:
         * <p>
         * Iterating through all the linked lists and adding their nodes' values to the minHeap takes O(N) time, where N is the total number of nodes in all the linked lists.
         * <p>
         * Removing each element from the minHeap and creating a new ListNode takes O(log K) time, as removing an element from a heap takes logarithmic time with respect to the size of the heap.
         * <p>
         * Since there are N total nodes and each
         *
         * @param lists
         * @return
         */
        public ListNode mergeKLists(ListNode[] lists) {

            PriorityQueue<Integer> minHeap = new PriorityQueue();

            for (ListNode head : lists) {
                while (head != null) {
                    minHeap.add(head.val);
                    head = head.next;
                }
            }

            ListNode dummy = new ListNode(-1);
            ListNode head = dummy;

            while (!minHeap.isEmpty()) {
                head.next = new ListNode(minHeap.remove());
                head = head.next;
            }

            return dummy.next;
        }
    }
}
