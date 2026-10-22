package leetcode._7Trees

// https://www.youtube.com/watch?v=s6ATEkipzow


fun isValidBST(root: TreeNode?): Boolean {
    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE)
}

private fun validate(node: TreeNode?, lower: Long, upper: Long): Boolean {
    if (node == null) return true

    val value = node.value.toLong()
    val inRange = value in (lower + 1)..<upper //add 1 To exclude both bounds using a range: A valid BST requires both bounds to be **exclusive**
    return inRange && validate(node.left, lower, value) &&
            validate(node.right, value, upper)
}


/*
 * Time: O(n), where n is the number of nodes.
 * Auxiliary space: O(h) for the recursion stack, where h is the tree height.
 *   O(log n) if balanced; O(n) if skewed.
 */


fun isValidBST2(root: TreeNode?): Boolean {
    return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)
}

private fun isBST(
    root: TreeNode?,
    min: Long,
    max: Long
): Boolean {
    if (root == null) return true

    // The current node must be strictly within the valid range.
    if (root.value <= min || root.value >= max) return false

    // All nodes in the left subtree must be smaller than root.
    // All nodes in the right subtree must be greater than root.
    return isBST(root.left, min, root.value.toLong()) &&
            isBST(root.right, root.value.toLong(), max)
}


// Time Complexity: O(n) — each node is visited at most once.
// Space Complexity: O(h) — recursion stack, where h is the tree height.
// Worst case: O(n) for a skewed tree; O(log n) for a balanced tree.