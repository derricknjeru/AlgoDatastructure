package leetcode._6LinkedList


/**
 * What is a linked list?
 * A linked list stores values in nodes. Each node holds a value and a reference to the next node:
 *
 * head → [1 | •] → [2 | •] → [3 | null]
 * An array keeps its items next to each other in memory. A linked list's nodes can be anywhere in memory, and the `next` references connect them.
 *
 * Operation                 Fixed-size array    ArrayList    Linked list
 * --------------------------------------------------------------------
 * Access by index           O(1)                O(1)         O(n)
 * Update by index           O(1)                O(1)         O(n)
 * Insert/remove at front    O(n)*               O(n)         O(1)
 * Append an element         O(n)*               O(1)**       O(1)***
 * Search                    O(n)                O(n)         O(n)
 *
 * *   Creates a new array to change its size.
 * **  Amortized O(1); O(n) when resizing is needed.
 * *** O(1) with a tail reference; otherwise O(n).
 *
 * Linked list assumes a singly linked list with a head reference.
 * Search assumes unsorted data.
 */

class ListNode(
    var `val`: Int, // we use backticks because val is a keyword; this could be var value : Int,
    var next: ListNode? = null
)

// ## 2. Basic operations on raw nodes
// Visit every node from head to the end
fun printLinkedList(head: ListNode?) {
    var curr = head
    while (curr != null) {
        print("${curr.`val`} --> ")
        curr = curr.next
    }
    println("null")
}

fun buildLinkedList(values: List<Int>): ListNode? {
    if (values.isEmpty()) return null
    val head = ListNode(values[0])
    var curr = head
    for (i in 1 until values.size) {
        curr.next = ListNode(values[i])
        curr = curr.next!!
    }
    return head
}

// Insert at the front: O(1)
fun addFirst(head: ListNode?, value: Int) = ListNode(value, head)

// Search: O(n)
fun contains(head: ListNode?, target: Int): Boolean {
    var current = head
    while (current != null) {
        if (current.`val` == target) return true
        current = current.next
    }
    return false
}

/**
 * - Time complexity: O(n) — in the worst case, you traverse all (n) nodes to reach the end.
 * - Space complexity: O(1) — only a constant number of references are used. The new node itself requires \(O(1)\) space.
 */
fun insertAtEnd(head: ListNode?, value: Int): ListNode {
    val newNode = ListNode(value)

    // If the list is empty, the new node becomes the head.
    if (head == null) return newNode

    var curr = head

    // Traverse until the last node.
    // Time: O(n)
    while (curr?.next != null) {
        curr = curr.next
    }

    // Attach the new node to the last node.
    curr?.next = newNode

    return head
}

/**
 * Complexity
 * - Time: O(1)
 * - Space: O(1)
 */
fun removeFirst(head: ListNode?): ListNode? {
    return head?.next
}

fun removeFirstValue(head: ListNode?): Pair<Int?, ListNode?> {
    if (head == null) return Pair(null, null)
    val value = head.`val`
    val newHead = head.next
    return Pair(value, newHead)
}

fun removeFirstValue2(head: ListNode?): Pair<Int?, ListNode?> {
    val value = head?.`val` ?: return Pair(null, null)
    return Pair(value, head.next)
}

fun deleteFirstOccurrence(head: ListNode?, value: Int): ListNode? {
    // If the list is empty, nothing to delete
    if (head == null) return null

    // If the target value is at the head node
    if (head.`val` == value) {
        return head.next
    }

    var current = head

    // Traverse the list looking for the node whose *next* node matches the value
    while (current?.next != null) {
        if (current.next?.`val` == value) {
            current.next = current.next?.next // Bypass the node to delete it
            break
        }
        current = current.next
    }

    return head
}

//Complexity: O(n) time and O(1) extra space.
//
fun removeAt(head: ListNode?, index: Int): ListNode? {
    if (head == null || index < 0) return head
    if (index == 0) return head.next

    var curr = head
    var i = 0
    while (curr != null && i < index - 1) {
        curr = curr.next
        i++
    }
    if (curr?.next == null) return head

    curr.next = curr.next?.next
    return head
}

fun main() {
    // build list
    val head = ListNode(1, ListNode(2, ListNode(3, ListNode(4))))
    printLinkedList(head)

    // build list with loop
    val head2 = buildLinkedList(listOf(2, 4, 6, 8, 9))
    printLinkedList(head2)

    // add first
    val head3 = addFirst(head2, 1)
    printLinkedList(head3)

    //contains
    val containsTwo = contains(head2, 2)
    println(containsTwo)

    // insert at the end
    val head4 = insertAtEnd(head2, 11)
    printLinkedList(head4)

    // remove first value
    val (value, currHead) = removeFirstValue(head4)
    printLinkedList(currHead)
    println(value)

    val removeFirst = removeFirst(head3)
    printLinkedList(removeFirst)

}
