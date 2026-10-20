package leetcode._6LinkedList

fun main() {

}


// Time Complexity: O(max(n, m)) - We iterate through both linked lists of lengths n and m once
// Space Complexity: O(max(n, m)) - A new linked list is created to store the result
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val dummyHead = ListNode(0)
    var curr = dummyHead
    var p = l1
    var q = l2
    var carry = 0

    // Loop until both lists are fully traversed and there's no carry left
    while (p != null || q != null || carry != 0) {
        val x = p?.`val` ?: 0
        val y = q?.`val` ?: 0

        val sum = x + y + carry
        carry = sum / 10 // Calculate new carry

        // Create a new node with the digit value (sum % 10) and attach it
        curr.next = ListNode(sum % 10)
        curr = curr.next!!

        // Move pointers forward
        if (p != null) p = p.next
        if (q != null) q = q.next
    }

    return dummyHead.next
}
