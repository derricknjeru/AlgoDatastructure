package leetcode._7Trees

import kotlin.math.abs

fun main() {

}

/**
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
 */


fun isBalanced(root: TreeNode?): Boolean {
    return checkHeight(root) != -1
}

/**
 * Recursively checks the height of the binary tree.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N) where N is the number of nodes in the tree.
 *   Each node is visited exactly once in a post-order traversal fashion.
 * - Space Complexity: O(H) where H is the height of the tree, representing
 *   the recursion call stack depth. In the worst case (a skewed tree),
 *   it is O(N), and in the best case (a completely balanced tree),
 *   it is O(log N).
 */
private fun checkHeight(node: TreeNode?): Int {
    if (node == null) return 0

    val leftHeight = checkHeight(node.left)
    if (leftHeight == -1) return -1 // Left subtree is unbalanced

    val rightHeight = checkHeight(node.right)
    if (rightHeight == -1) return -1 // Right subtree is unbalanced

    // If the current node is unbalanced, return -1
    if (abs(leftHeight - rightHeight) > 1) return -1

    // Otherwise, return the height of the current node
    return maxOf(leftHeight, rightHeight) + 1
}
