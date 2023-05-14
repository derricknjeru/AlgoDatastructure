package revision_v2.linkedList;

public class ReverseLinkedList {
    ListNode head;

    public static void main(String[] args) {

        ReverseLinkedList linkedList = new ReverseLinkedList();

        linkedList.push(5);
        linkedList.push(4);
        linkedList.push(3);
        linkedList.push(2);
        linkedList.push(1);

        System.out.println("Created linked list:");
        linkedList.printList();


        linkedList.head = linkedList.reverseList(linkedList.head);

        System.out.println("\nLinked list after reversing:");
        linkedList.printList();


    }

    public void printList() {
        ListNode tempHead = head;
        while (tempHead != null) {
            System.out.print(" " + tempHead.data);
            tempHead = tempHead.next;
        }
    }

    public void push(int d) {
        ListNode new_node = new ListNode(d);
        new_node.next = head;
        head = new_node;
    }


    //You tube link @link https://www.youtube.com/watch?v=O0By4Zq0OFc


    /**
     * Assume that we have linked list 1 → 2 → 3 → Ø, we would like to change it to Ø ← 1 ← 2 ← 3.
     * <p>
     * While you are traversing the list, change the current node's next pointer to point to its previous element.
     * Since a node does not have reference to its previous node, you must store its previous element beforehand.
     * You also need another pointer to store the next node before changing the reference.
     * Do not forget to return the new head reference at the end!
     */

    public ListNode reverseList(ListNode head) {

        ListNode prev = null, next;
        ListNode curr = head;

        while (curr != null) {
            next = curr.next; //save next
            curr.next = prev; //reverse (switch up the pointer)
            prev = curr; //advance the pointer
            curr = next;//advance the pointer

        }
        return prev; //we are returning new head.
    }

    public class ListNode {
        int data;
        ListNode next;

        ListNode(int d) {
            data = d;
        }
    }

}
