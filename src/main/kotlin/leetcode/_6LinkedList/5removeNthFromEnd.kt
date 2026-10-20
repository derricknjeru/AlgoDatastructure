package leetcode._6LinkedList

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

// Time Complexity: O(L) — traverse the linked list once.
// Space Complexity: O(1) — use only a dummy node and two pointers.
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    // Dummy node handles removing the head node.
    val dummy = ListNode(0)
    dummy.next = head

    var fast: ListNode? = dummy
    var slow: ListNode? = dummy

    // Move fast n + 1 steps ahead.
    // This creates a gap of n nodes between slow and fast.
    repeat(n + 1) {
        fast = fast?.next
    }

    // Move both pointers until fast reaches the end.
    while (fast != null) {
        fast = fast.next
        slow = slow?.next
    }

    // slow is now just before the node to remove.
    slow?.next = slow.next?.next

    return dummy.next
}
// two pass
fun removeNthFromEnd2(head: ListNode?, n: Int): ListNode? {
    // Time Complexity: O(L) — traverse the list twice in the worst case.
    // Space Complexity: O(1) — use only a few variables.

    var length = 0
    var current = head

    // Pass 1: Count the nodes.
    while (current != null) {
        length++
        current = current.next
    }

    // Removing the head is a special case.
    if (n == length) return head?.next

    // Find the node just before the target.
    current = head
    repeat(length - n - 1) {
        current = current?.next
    }

    // Skip the target node.
    current?.next = current.next?.next

    return head
}