public class DeleteLinkedListRemoveNthFromEnd {
    ListNode head;

    public static void main(String[] args) {
        DeleteLinkedListRemoveNthFromEnd linkedList = new DeleteLinkedListRemoveNthFromEnd();

        linkedList.push(5);
        linkedList.push(4);
        linkedList.push(3);
        linkedList.push(2);
        linkedList.push(1);

        System.out.print("Created linked list\n");
        linkedList.printList();

        int n = 2;
        linkedList.removeNthFromEnd(linkedList.head, n);

        System.out.printf("\nAfter removing %d node from the linked list\n", n);
        linkedList.printList();

    }

    public void printList() {
        ListNode temp = head;
        while (temp != null) {
            System.out.printf("%d ", temp.val);
            temp = temp.next;
        }
    }

    // last-in first-out (LIFO) principle
    public void push(int val) {
        /* 1 & 2: Allocate the Node & Put in the data*/
        ListNode new_node = new ListNode(val);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;

    }

    /**
     * Definition for singly-linked list.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int d) {
            val = d;
        }
    }


    /**
     * Given a linked list, remove the n-th node from the end of list and return its head.
     * <p>
     * Example:
     * <p>
     * Given linked list: 1->2->3->4->5, and n = 2.
     * <p>
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     * Note:
     * <p>
     * Given n will always be valid.
     * <p>
     * Follow up:
     * <p>
     * Could you do this in one pass?
     * <p>
     * Hide Hint #1
     * Maintain two pointers and update one with a delay of n steps.
     * <p>
     * We notice that the problem could be simply reduced to another one :
     * Remove the (L - n + 1)th node from the beginning in the list , where L is the list length.
     * This problem is easy to solve once we found list length LL
     * .
     * <p>
     * Algorithm
     * <p>
     * First we will add an auxiliary "dummy" node, which points to the list head.
     * The "dummy" node is used to simplify some corner cases such as a list with only one node, or removing the head of the list.
     * On the first pass, we find the list length L. Then we set a pointer to the dummy node and start to move it through the list till it comes to the (L - n)th node.
     * We relink next pointer of the (L - n)(th node to the (L - n + 2)th node and we are done.
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {

        int length = 0;

        ListNode tempH = head;
        while (tempH != null) {
            length++;
            tempH = tempH.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        tempH = dummy;

        int position = (length - n) + 1; //Position to be deleted

        // Find previous node of the node to be deleted
        for (int i = 0; i < position - 1; i++) {
            if (tempH != null) {
                tempH = tempH.next;
            }
        }
        //Change next of the previous node to 2nd next;
        if (tempH != null) {
            tempH.next = tempH.next.next;
        }

        return dummy.next;
    }

}

