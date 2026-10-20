package leetcode._7Trees

/**
 * Introduction to trees. Run this file's main() from IntelliJ's gutter.
 *
 * A tree is a connected graph with no cycles. A rooted tree chooses one node as
 * its root and organizes the others into parent-child relationships.
 *
 *                 A          depth/level 0
 *               /   \
 *              B     C       depth/level 1
 *             / \     \
 *            D   E     F     depth/level 2
 *
 * Terminology (using the rooted tree above):
 * - Node: a value and references to its children, such as B.
 * - Edge: a connection between two nodes, such as A--B.
 * - Root: A; it has no parent. Every other node has exactly one parent.
 * - Parent/child: B is D's parent; D is B's child.
 * - Siblings: D and E have the same parent.
 * - Leaf/external node: D, E, F; no children.
 * - Internal node: A, B, C; at least one child.
 * - Ancestor/descendant: A and B are ancestors of E; E is their descendant.
 * - Subtree: B together with D and E is a tree rooted at B.
 * - Path: A -> B -> E; its length is two edges.
 * - Degree of a node in rooted-tree terminology: number of children.
 *   B has degree 2; tree degree is the maximum node degree (2 here).
 *   In graph theory, degree instead counts all incident edges, including the parent edge.
 * - Depth: edges from root to node; E has depth 2.
 * - Level: we use depth (root = 0); some books start levels at 1 instead.
 * - Height: longest downward path in edges to a leaf. B has height 1;
 *   a leaf has height 0, this tree has height 2, and an empty tree has height -1.
 * - Forest: a collection of disjoint trees; removing A leaves the B and C subtrees.
 *
 * Why non-linear? A node can branch to multiple children rather than having a
 * single next item as in a list. A traversal chooses an order for visiting nodes;
 * the tree itself does not impose one linear sequence.
 *
 * Why important? Trees model file systems, organization charts, UI hierarchies
 * and syntax. Search trees index ordered data; heaps support priority queues;
 * tries support prefix lookup; B/B+ trees support database and filesystem indexes.
 *
 * Types (arity describes the maximum number of children, not a required count):
 * - General tree: any number of children.
 * - Binary tree: at most 2 children, with distinct left and right positions.
 * - Ternary tree: at most 3 children.
 * - N-ary tree: at most N children; binary and ternary are special cases.
 * - Binary search tree (BST): a binary tree with an ordering rule (see insertBst).
 * - Heap: a complete binary tree with a heap-order rule, not the BST rule.
 * - AVL/red-black trees: BSTs that maintain balance while updating.
 *
 * Binary tree shapes (these describe shape, NOT value ordering):
 *
 * Full, not complete:     Complete, not full:     Balanced, not complete:
 *       1                       1                        1
 *      / \                     / \                        \
 *     2   3                   2   3                        2
 *        / \                 /
 *       4   5               4
 *
 * - Full/strict/proper: every node has either 0 or 2 children.
 * - Complete: all levels except possibly the last are full; the last fills
 *   left to right, without gaps.
 * - Height-balanced: at EVERY node, left and right subtree heights differ by
 *   at most 1. Our ordinary BST insertion does NOT maintain this property.
 * - Perfect: every internal node has 2 children and all leaves share a depth.
 *   Perfect implies full, complete and height-balanced; full alone does not.
 * - Skewed/degenerate: each internal node has one child, resembling a list.
 *
 * Properties for a nonempty rooted tree with n nodes and height h (in edges):
 * - There are n - 1 edges and exactly one simple path between any two nodes.
 * - A binary tree has at most 2^d nodes at depth d.
 * - h + 1 <= n <= 2^(h + 1) - 1 for a binary tree.
 * - Minimum binary-tree height is ceil(log2(n + 1)) - 1; maximum is n - 1.
 * - A perfect binary tree has 2^(h + 1) - 1 nodes and 2^h leaves.
 * - A full binary tree with i internal nodes has i + 1 leaves and 2i + 1 nodes.
 * - For an N-ary tree (N >= 2), at most N^d nodes occur at depth d.
 */

// Child references represent relationships, not contiguous memory locations.
// Attach only new, unshared descendants: cycles or shared children are not a tree.
class GeneralTreeNode<T>(
    val value: T,
    val children: MutableList<GeneralTreeNode<T>> = mutableListOf()
)

class TreeNode(
    val value: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

/** General-tree search has no ordering shortcut: O(n) worst-case time. */
fun <T> containsValue(root: GeneralTreeNode<T>?, target: T): Boolean {
    if (root == null) return false
    return root.value == target || root.children.any { containsValue(it, target) }
}

/**
 * BST only: all values in the left subtree are smaller; all on the right are larger.
 * Duplicate values are ignored, so this BST stores distinct integers.
 * Returns the root, which the caller must keep (especially when inserting into null).
 * O(h + 1) time and recursive space: O(log n) when balanced, O(n) when skewed.
 */
fun insertBst(root: TreeNode?, value: Int): TreeNode {
    if (root == null) return TreeNode(value)
    when {
        value < root.value -> root.left = insertBst(root.left, value)
        value > root.value -> root.right = insertBst(root.right, value)
    }
    return root
}

/** BST only: O(h + 1) time, O(1) extra space. Not valid for an arbitrary binary tree. */
fun searchBst(root: TreeNode?, target: Int): TreeNode? {
    var current = root
    while (current != null) {
        current = when {
            target == current.value -> return current
            target < current.value -> current.left
            else -> current.right
        }
    }
    return null
}

/**
 * Depth-first traversals work on ANY binary tree.
 * Each takes O(n) time, O(h + 1) call-stack space and O(n) output space.
 * Very deep trees can overflow the recursive stack; these are small teaching examples.
 */
fun preorder(root: TreeNode?): List<Int> {
    val values = mutableListOf<Int>()
    fun visit(node: TreeNode?) {
        if (node == null) return
        values.add(node.value)
        visit(node.left)
        visit(node.right)
    }
    visit(root)
    return values
}

// Left -> root -> right. Sorted only when the tree satisfies the BST rule.
fun inorder(root: TreeNode?): List<Int> {
    val values = mutableListOf<Int>()
    fun visit(node: TreeNode?) {
        if (node == null) return
        visit(node.left)
        values.add(node.value)
        visit(node.right)
    }
    visit(root)
    return values
}

// Left -> right -> root; useful when children must be processed before their parent.
fun postorder(root: TreeNode?): List<Int> {
    val values = mutableListOf<Int>()
    fun visit(node: TreeNode?) {
        if (node == null) return
        visit(node.left)
        visit(node.right)
        values.add(node.value)
    }
    visit(root)
    return values
}

/** Breadth-first (level-order): O(n) time, O(w) queue space for maximum width w. */
fun levelOrder(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()
    val values = mutableListOf<Int>()
    val queue = ArrayDeque<TreeNode>()
    queue.addLast(root)
    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        values.add(node.value)
        node.left?.let { queue.addLast(it) }
        node.right?.let { queue.addLast(it) }
    }
    return values
}

fun nodeCount(root: TreeNode?): Int =
    if (root == null) 0 else 1 + nodeCount(root.left) + nodeCount(root.right)

fun treeHeight(root: TreeNode?): Int =
    if (root == null) -1 else 1 + maxOf(treeHeight(root.left), treeHeight(root.right))

fun main() {
    // General tree creation and insertion: choose a parent explicitly; no sorting rule.
    val documents = GeneralTreeNode("Documents")
    val pictures = GeneralTreeNode("Pictures")
    val folders = GeneralTreeNode("Home", mutableListOf(documents, pictures))
    documents.children.add(GeneralTreeNode("notes.txt"))
    println("General tree: Home -> [Documents -> notes.txt, Pictures]")
    println("General search for notes.txt: ${containsValue(folders, "notes.txt")}")
    check(containsValue(folders, "notes.txt"))
    check(!containsValue(folders, "missing.txt"))
    check(!containsValue<String>(null, "Home"))

    // Binary tree creation by linking nodes; this tree is deliberately NOT a BST.
    val binary = TreeNode(10, left = TreeNode(20), right = TreeNode(5))
    println("Arbitrary binary tree inorder (not sorted): ${inorder(binary)}")
    check(inorder(binary) == listOf(20, 10, 5))

    // Create an empty BST, then insert values. Insertion order determines its shape.
    var root: TreeNode? = null
    for (value in listOf(8, 4, 12, 2, 6, 10, 14)) {
        root = insertBst(root, value)
    }
    println(
        """
        |
        |BST (also perfect, full, complete and height-balanced):
        |        8
        |      /   \
        |     4     12
        |    / \   /  \
        |   2   6 10  14
        """.trimMargin()
    )

    val pre = preorder(root)
    val sorted = inorder(root)
    val post = postorder(root)
    val breadth = levelOrder(root)
    println("Preorder   (root, left, right): $pre")
    println("Inorder    (left, root, right): $sorted")
    println("Postorder  (left, right, root): $post")
    println("Level-order (breadth-first):   $breadth")
    println("Search 6: ${searchBst(root, 6)?.value}; search 99: ${searchBst(root, 99)}")
    println("Nodes: ${nodeCount(root)}, edges: ${nodeCount(root) - 1}, height: ${treeHeight(root)}")

    check(pre == listOf(8, 4, 2, 6, 12, 10, 14))
    check(sorted == listOf(2, 4, 6, 8, 10, 12, 14))
    check(post == listOf(2, 6, 4, 10, 14, 12, 8))
    check(breadth == listOf(8, 4, 12, 2, 6, 10, 14))
    check(searchBst(root, 6)?.value == 6)
    check(searchBst(root, 99) == null)
    check(nodeCount(root) == 7 && treeHeight(root) == 2)

    root = insertBst(root, 6)
    check(inorder(root) == sorted) // Duplicate insertion leaves the tree unchanged.
    root = insertBst(root, 5)
    check(inorder(root) == listOf(2, 4, 5, 6, 8, 10, 12, 14))
    check(searchBst(root, 5)?.value == 5)
    println("After inserting 5: ${inorder(root)}")

    check(preorder(null).isEmpty() && inorder(null).isEmpty())
    check(postorder(null).isEmpty() && levelOrder(null).isEmpty())
    check(searchBst(null, 1) == null)
    check(nodeCount(null) == 0 && treeHeight(null) == -1)
    val single = insertBst(null, 1)
    check(nodeCount(single) == 1 && treeHeight(single) == 0)
    check(preorder(single) == listOf(1) && inorder(single) == listOf(1))
    check(postorder(single) == listOf(1) && levelOrder(single) == listOf(1))

    var skewed: TreeNode? = null
    for (value in 1..4) skewed = insertBst(skewed, value)
    check(treeHeight(skewed) == 3 && nodeCount(skewed) == 4)
    check(preorder(skewed) == listOf(1, 2, 3, 4))
    check(inorder(skewed) == listOf(1, 2, 3, 4))
    check(postorder(skewed) == listOf(4, 3, 2, 1))
    check(levelOrder(skewed) == listOf(1, 2, 3, 4))
    check(searchBst(skewed, 4)?.value == 4 && searchBst(skewed, 0) == null)
    println("Sorted insertions 1, 2, 3, 4 form a skewed BST: height ${treeHeight(skewed)}.")
    println("All tree example checks passed.")
}
