package leetcode._6LinkedList

fun main() {

}

/**
 *
 * It’s **Floyd’s cycle detection algorithm**, also called the **tortoise and hare algorithm**.
 *
 * - **Tortoise (`slow`)** moves one node per iteration.
 * - **Hare (`fast`)** moves two nodes per iteration.
 *
 * If there’s a cycle, they eventually meet. Otherwise, `fast` reaches the end.
 *
 * **Time:** O(n) · **Space:** O(1).
 *
 *
 *
 * The slow pointer moves one node at a time, and the fast pointer moves two.
 * If they meet, the list has a cycle; if the fast pointer reaches the end,
 * it doesn’t. `===` checks that both variables refer to the same node.
 *
 * **Time:** O(n) · **Space:** O(1)
 */
fun hasCycle(head: ListNode?): Boolean {
    var slow = head
    var fast = head

    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next

        if (slow === fast) return true
    }
    return false
}

/**
 * `add()` returns `false` when the node is already in the set.
 * Store the **node itself**, not its value—different nodes can have equal values.
 * LeetCode’s `ListNode` uses reference-based equality.

 * **Time:** O(n) expected · **Space:** O(n).
 * A `HashMap` would also work, but a set is simpler because there’s no associated value to store.
 */
fun hasCycle2(head: ListNode?): Boolean {
    val visited = HashSet<ListNode>()
    var current = head

    while (current != null) {
        if (!visited.add(current)) return true
        current = current.next
    }

    return false
}

