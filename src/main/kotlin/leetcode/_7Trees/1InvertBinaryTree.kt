package leetcode._7Trees

fun main() {

}


fun invertTree(root: TreeNode?): TreeNode? {
    // Base case: An empty subtree needs no inversion.
    if (root == null) return null

    // Swap the left and right subtrees.
    val temp = root.left
    root.left = root.right
    root.right = temp

    // Recursively invert both subtrees.
    invertTree(root.left)
    invertTree(root.right)

    return root
}


// Time Complexity: O(n) — visit every node once.
// Space Complexity: O(h) — recursion stack, where h is tree height.
// Worst case: O(n) for a skewed tree; O(log n) for a balanced tree.

/**
 * **Complexity:** `O(n)` time because every node is visited once; `O(h)`
 * auxiliary space for recursion, where `h` is the tree height—`O(log n)` for a balanced tree and `O(n)` for a skewed tree.
 */

/**
 * Definition for a binary tree node.
 * class TreeNode(var value: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */


fun invertTree2(root: TreeNode?): TreeNode? {
    // Base case: Empty tree.
    if (root == null) return null

    // Queue for level-order traversal (BFS).
    val queue = ArrayDeque<TreeNode>()
    queue.addLast(root)

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()

        // Swap the left and right children.
        val temp = node.left
        node.left = node.right
        node.right = temp

        // Add children to the queue.
        node.left?.let { queue.addLast(it) }
        node.right?.let { queue.addLast(it) }
    }

    return root
}


// Time Complexity: O(n) — visit every node once.
// Space Complexity: O(w) — queue holds up to the tree's maximum width.
// Worst case: O(n) for a broad, nearly complete binary tree.