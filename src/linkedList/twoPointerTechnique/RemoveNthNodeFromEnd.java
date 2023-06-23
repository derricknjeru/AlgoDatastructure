package linkedList.twoPointerTechnique;

public class RemoveNthNodeFromEnd {
    ListNode head;

    public static void main(String[] args) {

        RemoveNthNodeFromEnd linkedList = new RemoveNthNodeFromEnd();

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
     * Note.md.md.md.md:
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


    /**
     * Complexity Analysis
     * <p>
     * Time complexity :O(L).
     * <p>
     * The algorithm makes two traversal of the list, first to calculate list length L and second to find the (L - n)th node(Previous node). There are 2L-n operations and time complexity is O(L).
     * <p>
     * Space complexity : O(1).
     * <p>
     * We only used constant extra space.
     */


    //Time complexity O(n)  we go through the list
    //Space complexity is O(1) no extra space
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //https://www.youtube.com/watch?v=Kka8VgyFZfc
        //https://www.youtube.com/watch?v=XVuQxVej6y8


        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        //Create a gap of n between the 2 pointers
        while (fast != null && n >= 0) {
            fast = fast.next;
            n--;
        }

        while (fast != null) { //fast.next because we want to stop when slow is at prev
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;

        return dummy.next;

    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {

        int length = 0;

        ListNode prev = head;
        while (prev != null) {
            length++;
            prev = prev.next;
        }

        /*When building a new list while doing linked list problems dummy heads are your best friend.
        They prevent you from having to do null checks on a list and you can immediately append to the .next value through a pointer to it with no fear of a null pointer exception.*/

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        prev = dummy;

        int position = (length - n) + 1; //Position to be deleted
        int index = position - 1;

        // Find previous node of the node to be deleted;
        // We use less than because we have a dummy value at the end
        for (int i = 0; i < index; i++) {
            if (prev != null) {
                prev = prev.next;
            }
        }

        if (prev == null || prev.next == null) return head;

        //Change next of the previous node to 2nd next;
        prev.next = prev.next.next;

        return dummy.next;
    }


}

