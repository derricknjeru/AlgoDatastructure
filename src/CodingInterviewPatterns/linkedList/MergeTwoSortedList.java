package CodingInterviewPatterns.linkedList;

import java.util.PriorityQueue;

public class MergeTwoSortedList {


    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * Add it to PriorityQueue which will sort the list
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        /**
         * The time complexity of the provided mergeKLists method is O(N log N),
         * where N is the total number of elements across all lists.
         *
         * Here's the breakdown of the time complexity:
         *
         * Inserting all values from the lists into the min-heap:
         * This step takes O(N log N) time. In the worst case, we need to insert all N elements into the min-heap,
         * and each insertion takes O(log N) time. Since we iterate through each element in all lists, the time complexity
         * is O(N log N).
         *
         * Constructing the merged list from the min-heap: This step also takes O(N log N) time.
         * We iterate until the min-heap is empty, and in each iteration, we remove the minimum element from the heap
         * and create a new node for it. The removal operation takes O(log N) time, and since we perform it N times,
         * the time complexity is O(N log N).
         *
         * Therefore, the overall time complexity is O(N log N),
         * where N is the total number of elements across all lists.
         */

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        //adding to PriorityQueue and sorting
        for (ListNode head : lists) {
            while (head != null) {
                minHeap.add(head.val);
                head = head.next;
            }
        }

        //creating a new list
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (!minHeap.isEmpty()) {
            head.next = new ListNode(minHeap.remove());
            head = head.next;
        }

        return dummy.next;
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
    class Solution {
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

}
