package leetcode._7Trees

fun main() {

}

/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
// https://www.youtube.com/watch?v=K81C31ytOZE

// Keeps track of the maximum diameter found so far across all nodes.
private var maxDiameter = 0

/**
 * Calculates the diameter of a binary tree.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N), where N is the number of nodes in the binary tree.
 *   Every node is visited exactly once during the post-order DFS traversal.
 * - Space Complexity: O(H), where H is the height of the binary tree.
 *   This accounts for the recursive call stack space (O(N) in the worst-case skewed tree,
 *   O(log N) in the best-case balanced tree).
 */
fun diameterOfBinaryTree(root: TreeNode?): Int {
    maxDiameter = 0 // Reset in case the instance is reused
    calculateHeight(root)
    return maxDiameter
}

/**
 * Helper function that recursively computes the height of a node
 * while updating the global maximum diameter.
 */
private fun calculateHeight(node: TreeNode?): Int {
    // Base case: A null node has a height of 0
    if (node == null) return 0

    // Recursively find the height of the left and right subtrees
    val leftHeight = calculateHeight(node.left)
    val rightHeight = calculateHeight(node.right)

    // The path length passing through the current node is leftHeight + rightHeight.
    // Update the maximum diameter if this path is the longest found so far.
    maxDiameter = maxOf(maxDiameter, leftHeight + rightHeight)

    // Return the height of the current node (1 for the node itself + max subtree height)
    return 1 + maxOf(leftHeight, rightHeight)
}