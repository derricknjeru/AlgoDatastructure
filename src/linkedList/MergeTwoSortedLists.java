package linkedList;

public class MergeTwoSortedLists {
    /**
     * Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     * <p>
     * Example:
     * <p>
     * [1,2,4] Input
     * [1,3,4]Input
     * Expected [1,1,2,3,4,4]
     */
    ListNode head, head2;

    public static void main(String[] args) {
        MergeTwoSortedLists lists = new MergeTwoSortedLists();
        //List one
        lists.push(4);
        lists.push(2);
        lists.push(1);

        //List 2
        lists.push2(4);
        lists.push2(3);
        lists.push2(1);

        System.out.println("Created list 1::");
        lists.printList(lists.head);

        System.out.println("\nCreated list 2::");
        lists.printList(lists.head2);

        System.out.println("\nMerged list 1::");
        lists.printList(lists.mergeTwoLists(lists.head, lists.head2));


    }

    public void push(int d) {
        ListNode new_node = new ListNode(d);
        new_node.next = head;
        head = new_node;
    }

    public void push2(int d) {
        ListNode new_node = new ListNode(d);
        new_node.next = head2;
        head2 = new_node;
    }

    public void printList(ListNode head) {
        ListNode tempHead = head;
        while (tempHead != null) {
            System.out.print(" " + tempHead.val);
            tempHead = tempHead.next;
        }
    }

    //Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dummy.next = l1;
                l1 = l1.next;
            } else {
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
        }

        if (l1 != null) {
            dummy.next = l1;
        } else {
            dummy.next = l2;
        }


        return head.next;
    }

}
