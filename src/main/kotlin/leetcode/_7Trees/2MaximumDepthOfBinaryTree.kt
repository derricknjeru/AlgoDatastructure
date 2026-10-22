package leetcode._7Trees

fun main() {

}

// https://www.youtube.com/watch?v=hTM3phVI6YQ
fun maxDepth(root: TreeNode?): Int {
    /**
     * Time: `O(n)` — visits every node.
     * Auxiliary space: `O(h)` — recursion stack; `O(n)` for a skewed tree.
     */
    if (root == null) return 0

    val leftDepth = maxDepth(root.left)
    val rightDepth = maxDepth(root.right)

    return 1 + maxOf(leftDepth, rightDepth)
}

/**
 * Time: `O(n)`.
 * Auxiliary space: `O(w)`, where `w` is the maximum tree width; `O(n)` in the worst case.
 */
fun maxDepth2(root: TreeNode?): Int {
    if (root == null) return 0

    val queue = ArrayDeque<TreeNode>()
    queue.addLast(root)
    var depth = 0

    while (queue.isNotEmpty()) {
        val levelSize = queue.size

        repeat(levelSize) {
            val node = queue.removeFirst()

            node.left?.let { queue.addLast(it) }
            node.right?.let { queue.addLast(it) }
        }

        depth++
    }

    return depth
}

fun maxDepth3(root: TreeNode?): Int {
    /**
     *  Time: `O(n)` — every node is processed once.
     *
     * Auxiliary space: `O(h)` — pending nodes on the stack, where `h` is tree height; `O(n)` worst case.
     * A strictly single-child chain uses only `O(1)` stack space in this implementation.
     */
    if (root == null) return 0

    val stack = ArrayDeque<Pair<TreeNode, Int>>()
    stack.addLast(root to 1)

    var maxDepth = 0

    while (stack.isNotEmpty()) {
        val (node, depth) = stack.removeLast()
        maxDepth = maxOf(maxDepth, depth)

        node.right?.let { stack.addLast(it to depth + 1) }
        node.left?.let { stack.addLast(it to depth + 1) }
    }

    return maxDepth
}


