package leetcode._6LinkedList

fun main() {

}

// Definition for a Node.
class Node(var `val`: Int) {
    var next: Node? = null
    var random: Node? = null
}
//https://www.youtube.com/watch?v=5Y2EiZST97Y

// Time Complexity: O(n) - Two passes over the linked list of length n
// Space Complexity: O(n) - HashMap used to store original-to-clone node mappings
fun copyRandomList(node: Node?): Node? {
    if (node == null) return null

    val map = HashMap<Node, Node>()

    // Step 1: Create all cloned nodes and store them in the hash map
    var curr: Node? = node
    while (curr != null) {
        map[curr] = Node(curr.`val`)
        curr = curr.next
    }

    // Step 2: Assign next and random pointers for the cloned nodes
    curr = node
    while (curr != null) {
        map[curr]?.next = map[curr.next]
        map[curr]?.random = map[curr.random]
        curr = curr.next
    }

    // Return the head of the cloned list
    return map[node]
}