package leetcode._6LinkedList

fun main() {


}


/**
 * Time: O(m + n). Extra space: O(1)—the existing nodes are reused.
 * If your `ListNode` property is named `value`, replace `` `val` `` with `value`.
 * https://www.youtube.com/watch?v=XIdigk956u0
 */
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    // dummy node trick; so that you don't have to worry about the edge case of inserting to empty list
    val dummy = ListNode(0)
    var tail = dummy

    /** We introduce first & second because
     *  **Kotlin function parameters are read-only (`val`)**, so you can't reassign `list1` or `list2`.
     *  Importantly, this **doesn't copy any nodes**. `first` and `second` are just references to the same nodes,
     *  so extra space is still O(1).
     */
    var first = list1
    var second = list2

    while (first != null && second != null) {
        if (first.`val` <= second.`val`) {
            tail.next = first
            first = first.next
        } else {
            tail.next = second
            second = second.next
        }
        tail = tail.next!!
    }

    // Attach whichever list still has nodes.
    tail.next = first ?: second

    return dummy.next
}
