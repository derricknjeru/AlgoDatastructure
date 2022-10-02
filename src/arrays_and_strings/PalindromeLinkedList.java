package arrays_and_strings;

public class PalindromeLinkedList {
    ListNode head;

    public static void main(String[] args) {
        //Testing.
        PalindromeLinkedList list = new PalindromeLinkedList();
        //1->2->2->1
        /**list.push(1);
         list.push(2);
         list.push(2);
         list.push(1);**/

        //1->2
        list.push(2);
        list.push(1);

        list.printList(list.head);

        System.out.println("Is palindrome " + list.isPalindrome(list.head));


    }

    //Push method
    public void push(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;
    }

    public void printList(ListNode head) {
        ListNode tmpHead = head;
        while (tmpHead != null) {
            System.out.println(tmpHead.val);
            tmpHead = tmpHead.next;
        }
    }

    /**
     * Given a singly linked list, determine if it is a palindrome.
     * <p>
     * Example 1:
     * <p>
     * Input: 1->2
     * Output: false
     * Example 2:
     * <p>
     * Input: 1->2->2->1
     * Output: true
     * Follow up:
     * Could you do it in O(n) time and O(1) space?
     */
    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int valX) {
            val = valX;
            next = null;
        }

    }

    public boolean isPalindrome(ListNode head) {
        //1->2->2->1 even
        //1->2->3->1->2 odd

        ListNode sp = head, fp = head, mid = null;

        while (fp != null && fp.next != null) {
            sp = sp.next;
            fp = fp.next.next;
        }

        //fp !=null when the linked list is odd.
        if (fp != null) {
            mid = sp.next;
        } else {
            mid = sp;
        }

        //Reverse linked list from middle
        ListNode prev = null;
        ListNode next = null;
        //1->2->3->1->2
        while (mid != null) {
            next = mid.next; //save the current next
            mid.next = prev; //reverse(switch up the pointer) -->meaning we point to the prev
            prev = mid; //advance pointer
            mid = next; //advance pointer
        }

        //compare the two lists
        while (prev != null) {
            if (prev.val != head.val) {
                return false;
            }
            prev = prev.next;
            head = head.next;
        }


        return true;
    }
}


//https://www.youtube.com/watch?v=O0By4Zq0OFc
//https://leetcode.com/problems/reverse-linked-list/solution/
//https://leetcode.com/problems/palindrome-number/