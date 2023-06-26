package CodingInterviewPatterns.linkedList;

public class SwapNodesInPairs {
    public static void main(String[] args) {
        // Create the linked list: 1 -> 2 -> 3 -> 4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        // Print the original list
        System.out.print("Original List: ");
        printList(head);

        // Swap adjacent nodes
        ListNode swappedHead = swapPairs(head);

        // Print the modified list
        System.out.print("Modified List: ");
        printList(swappedHead);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        //https://www.youtube.com/watch?v=o811TZLAWOo
        ListNode dummy = new ListNode(0); // Create a dummy node
        dummy.next = head; // Connect the dummy node to the head
        ListNode prev = dummy; // Initialize prev pointer to the dummy node
        ListNode curr = head; // Initialize curr pointer to the head

        while (curr != null && curr.next != null) {
            // Save pointers
            ListNode nextPair = curr.next.next;
            ListNode second = curr.next;

            // Reverse this pair
            second.next = curr;
            curr.next = nextPair;
            prev.next = second;

            // Update pointers
            prev = curr;
            curr = nextPair;
        }

        return dummy.next; // Return the modified list (head of the modified list)
    }


    //https://chat.openai.com/c/2c5d5fa7-e4f1-4e64-8983-847859eb428a
    //recursive
    public static ListNode swapPairs2(ListNode head) {

        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Nodes to be swapped
        ListNode first = head;
        ListNode second = head.next;

        // Swap the nodes
        first.next = swapPairs(second.next);
        second.next = first;

        // Return the new head
        return second;
    }

    /**
     * Explanation.
     * Base case check:
     * <p>
     * We check if the linked list is empty or contains only a single node.
     * If the condition head == null || head.next == null is true, it means there are no or only one node in the list.
     * In such cases, there is no need to perform any swapping, so we return the head as it is.
     * This serves as the base case for the recursive function.
     * Swapping the first two nodes:
     * <p>
     * If the base case is not met, we proceed to swap the first two nodes in the list.
     * We create two pointers, first and second, to keep track of the nodes to be swapped.
     * first is initially assigned to the head node, and second is assigned to the next node.
     * To perform the swap, we update the next pointers:
     * first.next is set to the result of recursively calling swapPairs on the sublist starting from the node after second.
     * second.next is updated to point to first, effectively swapping the positions of first and second.
     * Attaching the returned head of the sub-list:
     * <p>
     * After swapping the first two nodes and recursively swapping the rest of the list, we need to attach the returned head of the sub-list to the two nodes swapped in the previous step.
     * Since the head of the sub-list is the node that was initially in the second position, we return second as the new head of the modified list.
     * Return the new head:
     * <p>
     * The function returns second as the new head of the modified linked list, representing the swapped pairs.
     *
     * @param head
     */

    // Helper method to print the linked list
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

}
