package CodingInterviewPatterns.Recursion;

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
    /**
     * The time complexity of the swap function is O(n), where n is the number of nodes in the linked list. This is because in each recursive call, we process two nodes (swapping them) and then make a recursive call for the remaining nodes. Since each node is processed once, the total number of recursive calls will be equal to the number of nodes in the linked list.
     *
     * The space complexity of the swap function is O(n) as well, due to the recursive nature of the algorithm. In each recursive call, memory is consumed for the function call stack. The maximum depth of the function call stack is equal to the number of nodes in the linked list. Therefore, the space complexity is proportional to the number of nodes in the list.
     *
     * It's worth noting that if the linked list is very long, the recursive approach used in this implementation may lead to stack overflow error. In such cases, an iterative approach can be more efficient and would have a constant space complexity of O(1).
     */

    /**
     * In this swap function, we use recursion to swap adjacent nodes in the linked list.
     * Here's a breakdown of the steps:
     * <p>
     * Base case: If the list is empty or has only one node, we return the current head since there are no nodes to swap.
     * <p>
     * Swap the first two nodes:
     * <p>
     * Assign newHead to be the second node (head.next) because after swapping, it will become the new head of the list.
     * Recursively call swap to swap the remaining nodes starting from the third node (head.next.next).
     * Make the first node the second node in the swapped list:
     * <p>
     * Set newHead. Next to head to make the first node (head) the second node in the swapped list.
     * Return the new head of the swapped list (newHead).
     *
     * @param head
     * @return
     */

    public static ListNode swapPairs(ListNode head) {
        // Base case: if the list is empty or has only one node
        if (head == null || head.next == null) {
            return head;
        }

        // Swap the first two nodes
        ListNode newHead = head.next;  // The new head of the swapped list will be the second node
        head.next = swapPairs(head.next.next);  // Recursively swap the remaining nodes starting from the third node
        newHead.next = head;  // Make the first node the second node in the swapped list

        // Return the new head of the swapped list
        return newHead;
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
