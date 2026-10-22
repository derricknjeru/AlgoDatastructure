package leetcode._7Trees.notes

import java.util.ArrayDeque

/**
 * Introduction to binary trees in Kotlin.
 *
 * Run this file from IntelliJ IDEA.
 */
class TreeNode(
    var value: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

fun createTree(): TreeNode {
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left?.left = TreeNode(4)
    root.left?.right = TreeNode(5)
    return root
}

/**
 * Inserts at the first available child position in level order.
 * This is ordinary binary-tree insertion, not BST insertion.
 *
 * Time: O(n) worst case
 * Auxiliary space: O(n) worst case for the queue
 */
fun insert(root: TreeNode?, value: Int): TreeNode {
    val newNode = TreeNode(value)
    if (root == null) return newNode

    val queue = ArrayDeque<TreeNode>()
    queue.addLast(root)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()

        if (current.left == null) {
            current.left = newNode
            return root
        } else {
            queue.addLast(current.left!!)
        }

        if (current.right == null) {
            current.right = newNode
            return root
        } else {
            queue.addLast(current.right!!)
        }
    }

    return root
}

/**
 * Searches an ordinary binary tree.
 * Time: O(n) worst case
 * Recursive stack: O(h), where h is tree height
 */
fun search(root: TreeNode?, target: Int): Boolean {
    if (root == null) return false
    if (root.value == target) return true

    return search(root.left, target) ||
        search(root.right, target)
}

/**
 * DFS: Root -> Left -> Right
 * Time: O(n), recursive stack: O(h)
 */
fun preorder(root: TreeNode?) {
    if (root == null) return
    print("${root.value} ")
    preorder(root.left)
    preorder(root.right)
}

/**
 * DFS: Left -> Root -> Right
 * Time: O(n), recursive stack: O(h)
 */
fun inorder(root: TreeNode?) {
    if (root == null) return
    inorder(root.left)
    print("${root.value} ")
    inorder(root.right)
}

/**
 * DFS: Left -> Right -> Root
 * Time: O(n), recursive stack: O(h)
 */
fun postorder(root: TreeNode?) {
    if (root == null) return
    postorder(root.left)
    postorder(root.right)
    print("${root.value} ")
}

/**
 * BFS: level-order traversal, using a FIFO queue.
 * Time: O(n), auxiliary queue: O(w), worst case O(n)
 */
fun levelOrder(root: TreeNode?) {
    if (root == null) return

    val queue = ArrayDeque<TreeNode>()
    queue.addLast(root)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        print("${current.value} ")
        current.left?.let { queue.addLast(it) }
        current.right?.let { queue.addLast(it) }
    }
}

class NaryNode(
    var value: Int,
    val children: MutableList<NaryNode> = mutableListOf()
)

fun main() {
    val root = createTree()

    print("Preorder: ")
    preorder(root)
    println()

    print("Inorder: ")
    inorder(root)
    println()

    print("Postorder: ")
    postorder(root)
    println()

    print("Level order: ")
    levelOrder(root)
    println()

    println("Search 5: ${search(root, 5)}")
    println("Search 9: ${search(root, 9)}")

    insert(root, 6)
    print("After inserting 6 (level order): ")
    levelOrder(root)
    println()
}
