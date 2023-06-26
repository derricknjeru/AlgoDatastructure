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
    class Solution {
        /**
         * //https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4807159151067136
         *
         * @param lists
         * @return
         */
        //The time complexity is O(nlogk), where k is the number of the lists and is the maximum length of a single
        //,
        public ListNode mergeKLists(ListNode[] lists) {
            int size = lists.length;
            int interval = 1;

            while (interval < size) {
                for (int i = 0; i < size - interval; i += 2 * interval) {
                    lists[i] = merge(lists[i], lists[i + interval]);
                }

                interval *= 2;
            }

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
