package leetcode._7Trees

fun main() {

}


fun rightSideView(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    val result = mutableListOf<Int>()
    val queue = ArrayDeque<TreeNode>()
    queue.addLast(root)

    while (queue.isNotEmpty()) {
        val levelSize = queue.size

        repeat(levelSize) { index ->
            val node = queue.removeFirst()

            if (index == levelSize - 1) {
                result.add(node.value)
            }

            node.left?.let { queue.addLast(it) }
            node.right?.let { queue.addLast(it) }
        }
    }

    return result
}


/*
 * Time: O(n), where n is the number of nodes; each is processed once.
 * Auxiliary space: O(w), where w is the maximum tree width; O(n) worst case.
 * Output space: O(h), where h is the tree height.
 */


fun rightSideView2(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()

    fun dfs(node: TreeNode?, depth: Int) {
        if (node == null) return

        // The first node visited at each depth is visible from the right.
        if (depth == result.size) {
            result.add(node.value)
        }

        // Visit the right subtree before the left subtree.
        dfs(node.right, depth + 1)
        dfs(node.left, depth + 1)
    }

    dfs(root, 0)
    return result
}


/**
 * Time Complexity: O(n) — each node is visited exactly once.
 * Space Complexity: O(h) — recursion stack, where h is the tree height.
 * Worst case: O(n) for a skewed tree; O(log n) for a balanced tree.
 */


// Time Complexity: O(N), where N is the number of nodes in the binary tree (every node is visited once).
// Space Complexity: O(H), where H is the height of the tree, used by the call stack during recursion.
fun rightSideView3(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()
    dfs(root, 0, result)
    return result
}

private fun dfs(node: TreeNode?, depth: Int, result: MutableList<Int>) {
    if (node == null) return

    // If this is the first time we've reached this depth,
    // it means this node is visible from the right side.
    if (depth == result.size) {
        result.add(node.value)
    }

    // Traverse right first, then left
    dfs(node.right, depth + 1, result)
    dfs(node.left, depth + 1, result)
}
