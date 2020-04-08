package leetcode_30_days_challenge.week2;

public class MiddleOfLinkedList {
    /**
     * Middle of the Linked List
     * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
     * <p>
     * If there are two middle nodes, return the second middle node.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: [1,2,3,4,5]
     * Output: Node 3 from this list (Serialization: [3,4,5])
     * The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
     * Note that we returned a ListNode object ans, such that:
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
     * Example 2:
     * <p>
     * Input: [1,2,3,4,5,6]
     * Output: Node 4 from this list (Serialization: [4,5,6])
     * Since the list has two middle nodes with values 3 and 4, we return the second one.
     * <p>
     * <p>
     * Note:
     * <p>
     * The number of nodes in the given list will be between 1 and 100.
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int d) {
            val = d;
        }
    }

    ListNode head;

    public static void main(String[] args) {
        MiddleOfLinkedList ml = new MiddleOfLinkedList();

        for (int i = 6; i > 0; i--) {
            ml.push(i);
        }

        ml.printLinkedList();

        ml.middleNode(ml.head);


    }

    //https://www.geeksforgeeks.org/write-a-c-function-to-print-the-middle-of-the-linked-list/
    public ListNode middleNode(ListNode head) {

        ListNode slowPointer = head;
        ListNode fastPointer = head;

        if (head == null) {
            return null;
        }
        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }
        System.out.println("\nThe middle element is [" +
                slowPointer.val + "] \n");
        return slowPointer;
    }

    public void push(int val) {
        //Create a new node
         /* 1 & 2: Allocate the Node &
                  Put in the data*/
        ListNode new_node = new ListNode(val);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;

    }

    public void printLinkedList() {
        ListNode tnode = head;

        while (tnode != null) {
            System.out.print(tnode.val + "->");
            tnode = tnode.next;
        }
    }

}
