package leetcode._6LinkedList

fun main() {

}

//https://www.youtube.com/watch?v=S5bfdUTrKLM
fun reorderList(head: ListNode?) {
    if (head?.next == null) return

    // 1. Find the end of the first half using slow/fast pointers.
    // Slow moves one step; fast moves two.
    // Example: 1 → 2 → 3 → 4 → 5, slow stops at 3.
    var slow = head
    var fast = head.next

    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }

    // 2. Split into two independent lists.
    // Example: 1 → 2 → 3 and 4 → 5.
    // Disconnecting prevents the old link from creating a cycle
    // when we merge the halves.
    var current = slow?.next
    slow?.next = null

    // 3. Reverse the second half: 4 → 5 becomes 5 → 4.
    // We need nodes from the back, but a singly linked list
    // only supports moving forward. Reversing gives that order.
    var previous: ListNode? = null

    while (current != null) {
        val next = current.next
        current.next = previous
        previous = current
        current = next
    }

    // 4. Alternate nodes from the first and reversed second halves.
    // 1 → 2 → 3 and 5 → 4 become 1 → 5 → 2 → 4 → 3.
    var first = head
    var second = previous

    while (second != null) {
        // Save the next nodes before changing their links.
        val firstNext = first?.next
        val secondNext = second.next

        first?.next = second
        second.next = firstNext

        first = firstNext
        second = secondNext
    }
}

// Time: O(n) — finding the middle, reversing, and merging
// each take linear time; O(n) + O(n) + O(n) = O(n).
// Extra space: O(1) — only a fixed number of pointers are used.
