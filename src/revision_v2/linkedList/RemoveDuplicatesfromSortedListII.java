package revision_v2.linkedList;

public class RemoveDuplicatesfromSortedListII {
    ListNode head;

    public static void main(String[] args) {

        RemoveDuplicatesfromSortedListII listII = new RemoveDuplicatesfromSortedListII();

        listII.push(5);
        listII.push(4);
        listII.push(4);
        listII.push(3);
        listII.push(3);
        listII.push(2);
        listII.push(1);

        listII.printList();

    }

    private void printList() {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
    }

    private void push(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;
    }
    //https://www.geeksforgeeks.org/remove-occurrences-duplicates-sorted-linked-list/
    //https://www.youtube.com/watch?v=14PXbvtUbMo
    public ListNode deleteDuplicates(ListNode head) {
        // Create a dummy node that acts like a fake
        // head of list pointing to the original head
        ListNode dummy = new ListNode(-1);

        // Dummy node points to the original head
        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = head;

        while (current != null) {
            // Until the current and previous values
            // are same, keep updating current
            while (current.next != null &&
                    prev.next.val == current.next.val)
                current = current.next;

            // If current has unique value i.e current
            // is not updated, Move the prev pointer
            // to next node
            if (prev.next == current)
                prev = prev.next;

                // When current is updated to the last
                // duplicate value of that segment, make
                // prev the next of current*/
            else
                prev.next = current.next;

            current = current.next;
        }

        // Update original head to the next of dummy
        // node
        head = dummy.next;

        return head;

    }


}


