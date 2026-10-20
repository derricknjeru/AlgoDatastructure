package leetcode._6LinkedList

fun main() {

}


/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
// https://www.youtube.com/watch?v=G0_I-ZF0S38
fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var current = head

    while (current != null) {
        val nextTemp = current.next // Store the next node
        current.next = prev         // Reverse the current node's pointer
        prev = current              // Move prev one step forward
        current = nextTemp          // Move current one step forward
    }

    return prev // prev ends up at the new head of the reversed list
}

// Time Complexity:  O(n) - We traverse the linked list of length n exactly once.
// Space Complexity: O(1) - Only a few pointer variables are used, requiring constant extra space.