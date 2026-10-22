package leetcode._7Trees

fun main() {
}


private var count = 0

// Time Complexity: O(N) - We visit every node in the binary tree exactly once.
// Space Complexity: O(H) - Where H is the height of the tree, consumed by the recursive call stack.
//                          Worst case is O(N) for a skewed tree, and O(log N) for a balanced tree.
fun goodNodes(root: TreeNode?): Int {
    count = 0 // Reset in case the Solution instance is reused
    preOrder(root, Int.MIN_VALUE)
    return count
}

private fun preOrder(root: TreeNode?, max: Int) {
    if (root == null) return

    var currentMax = max
    if (root.value >= max) {
        count++
        currentMax = root.value
    }

    preOrder(root.left, currentMax)
    preOrder(root.right, currentMax)
}


fun goodNodes2(root: TreeNode?): Int {
    return countGoodNodes(root, Int.MIN_VALUE)
}

private fun countGoodNodes(node: TreeNode?, maxSoFar: Int): Int {
    if (node == null) return 0

    val isGood = if (node.value >= maxSoFar) 1 else 0
    val newMax = maxOf(maxSoFar, node.value)

    return isGood +
            countGoodNodes(node.left, newMax) +
            countGoodNodes(node.right, newMax)
}


/*
 * Time: O(n), where n is the number of nodes; each is visited once.
 * Auxiliary space: O(h) for the recursion stack, where h is the tree height.
 *   O(log n) if balanced; O(n) in the worst case if skewed.
 */