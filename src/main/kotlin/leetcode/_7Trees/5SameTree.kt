package leetcode._7Trees

fun main() {

}

// https://www.youtube.com/watch?v=vRbbcKXCxOw


/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

/**
 * Checks if two binary trees are identical in structure and node values.
 *
 * Time Complexity: O(N)
 * - Where N is the minimum number of nodes between the two trees.
 * - In the worst case (trees are identical or nearly identical), we visit every node once.
 *
 * Space Complexity: O(H)
 * - Where H is the height of the tree, representing the recursion stack depth.
 * - Best Case (Balanced Tree): O(log N)
 * - Worst Case (Skewed Tree): O(N)
 */
fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    // Base Case 1: Both nodes are null -> structurally identical at this leaf/branch
    if (p == null && q == null) return true

    // Base Case 2: One is null or values differ -> not identical
    if (p == null || q == null || p.value != q.value) return false

    // Recursive Step: Check both left and right subtrees
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
}
