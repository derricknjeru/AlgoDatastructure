package CodingInterviewPatterns.linkedList;

public class AddTwoNumbers {
    /**
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
     * <p>
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     * Example 2:
     * <p>
     * Input: l1 = [0], l2 = [0]
     * Output: [0]
     * Example 3:
     * <p>
     * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * Output: [8,9,9,9,0,0,0,1]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * The number of nodes in each linked list is in the range [1, 100].
     * 0 <= Node.val <= 9
     * It is guaranteed that the list represents a number that does not have leading zeros.
     */


    //* Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
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
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            //https://www.youtube.com/watch?v=wgFPrzTjm7s&ab_channel=NeetCode
            ListNode dummy = new ListNode(-1);
            ListNode curr = dummy;
            int sum = 0;
            int carry = 0;

            //either of the list has a digit
            while (l1 != null || l2 != null) {
                int x = l1 != null ? l1.val : 0;
                int y = l2 != null ? l2.val : 0;

                //create a digit
                sum = x + y + carry;
                int val = sum % 10;
                carry = sum / 10;

                curr.next = new ListNode(val);
                curr = curr.next;

                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;

            }

            if (carry > 0) {
                curr.next = new ListNode(carry);
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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode();
            ListNode cur = dummy;

            int carry = 0;
            while (l1 != null || l2 != null || carry != 0) {
                int v1 = (l1 != null) ? l1.val : 0;
                int v2 = (l2 != null) ? l2.val : 0;

                // new digit
                int val = v1 + v2 + carry;
                carry = val / 10;
                val = val % 10;
                cur.next = new ListNode(val);

                // update pointers
                cur = cur.next;
                l1 = (l1 != null) ? l1.next : null;
                l2 = (l2 != null) ? l2.next : null;
            }

            return dummy.next;
        }
    }
}
