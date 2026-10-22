package leetcode._7Trees

fun main() {


}

/**
 * The key insight is that inorder traversal of a Binary Search Tree (BST) visits nodes in ascending order.
 * Therefore, the (k)-th node visited is the (k)-th smallest element.
 */

private var count = 0
private var result = 0

fun kthSmallest(root: TreeNode?, k: Int): Int {
    inorder(root, k)
    return result
}

private fun inorder(node: TreeNode?, k: Int) {
    if (node == null) return

    // Visit left subtree first (smaller values)
    inorder(node.left, k)

    // Visit current node
    count++
    if (count == k) {
        result = node.value
        return
    }

    // Visit right subtree (larger values)
    inorder(node.right, k)
}
// Time Complexity: O(H + k) average when traversal stops early;
// O(N) worst case.
// Space Complexity: O(H) for the recursion stack.
// H = tree height, N = number of nodes.


fun kthSmallest2(root: TreeNode?, k: Int): Int {
    var count = 0
    var result = -1

    fun inorder(node: TreeNode?) {
        if (node == null || count >= k) return

        inorder(node.left)

        count++
        if (count == k) {
            result = node.value
            return
        }

        inorder(node.right)
    }

    inorder(root)
    return result
}

// iterative inorder travel
// https://www.youtube.com/watch?v=5LUXSvjmGCw
fun kthSmallest3(root: TreeNode?, k: Int): Int {
    val stack = ArrayDeque<TreeNode>()
    var current = root
    var remaining = k

    while (current != null || stack.isNotEmpty()) {
        while (current != null) {
            stack.addLast(current)
            current = current.left
        }

        val node = stack.removeLast()
        remaining--

        if (remaining == 0) return node.value

        current = node.right
    }

    error("k exceeds the number of nodes")
}


/*
 * Time: O(h + k), where h is the BST height.
 *   O(log n + k) if balanced; O(n) worst case.
 * Auxiliary space: O(h) for the stack.
 *   O(log n) if balanced; O(n) if skewed.
 */