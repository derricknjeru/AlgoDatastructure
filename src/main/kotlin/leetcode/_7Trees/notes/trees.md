# Introduction to Tree Data Structures in Kotlin

## 1. Definition

A **tree** is a non-linear data structure made of nodes connected by edges. It represents hierarchical relationships, such as folders, organization charts, syntax trees, and search structures.

```text
        1
       / \
      2   3
     / \
    4   5
```

Here, `1` is the root; `2` and `3` are its children; `4` and `5` are children of `2`.

## 2. Basic terminology

- **Node:** An element that stores data.
- **Root:** The topmost node; it has no parent.
- **Edge:** A connection between two nodes.
- **Parent / child:** Directly connected nodes, where one is above the other.
- **Siblings:** Nodes with the same parent.
- **Leaf:** A node with no children.
- **Internal node:** A node with at least one child.
- **Ancestor / descendant:** Nodes above / below another node along a path.
- **Subtree:** A node and all its descendants.
- **Depth:** Number of edges from the root to a node.
- **Height:** Number of edges on the longest downward path from a node to a leaf.
- **Level:** Often depth + 1; some resources start levels at 0.

Height conventions vary for empty trees. State your convention and stay consistent.

## 3. Why is a tree non-linear?

Arrays and linked lists arrange data in a sequence. Trees organize data hierarchically: one node may connect to multiple children, creating branches. A conventional tree is connected, acyclic, and has exactly one path between any two nodes.

## 4. Kotlin representation

A binary-tree node has a value and at most two child references:

```kotlin
class TreeNode(
    var value: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)
```

`TreeNode?` is nullable: a missing child is represented by `null`.

## 5. Create a tree

```kotlin
fun createTree(): TreeNode {
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left?.left = TreeNode(4)
    root.left?.right = TreeNode(5)
    return root
}
```

Result:

```text
        1
       / \
      2   3
     / \
    4   5
```

## 6. Importance and applications

- File systems: nested folders and files.
- Databases: indexes.
- Compilers: syntax and expression trees.
- AI: decision trees.
- User interfaces: nested components.
- Interviews: path, depth, ancestor, and hierarchy problems.

## 7. Types of tree data structures

- **Binary tree:** Each node has at most two children.
- **Ternary tree:** Each node has at most three children.
- **N-ary (generic) tree:** Nodes may have multiple children.
- **Binary Search Tree (BST):** Values follow an ordering rule. With distinct keys, left subtree values are smaller and right subtree values are larger.
- **Heap:** A complete binary tree with a heap-order property. In a min-heap, a parent is less than or equal to its children.
- **Balanced search tree:** Maintains a controlled height, e.g. AVL or red-black tree.

### Full, complete, and balanced binary trees

- **Full:** Every node has either zero or exactly two children.
- **Complete:** Every level is full except possibly the last; the last level fills from left to right.
- **Balanced:** Height is kept relatively small. In a height-balanced tree, subtree heights differ by at most a specified bound, commonly one.

These are shape classifications; one tree may satisfy more than one.

### N-ary node in Kotlin

```kotlin
class NaryNode(
    var value: Int,
    val children: MutableList<NaryNode> = mutableListOf()
)
```

## 8. Basic operations

### Insert into an ordinary binary tree

This inserts at the first available child position in level order. It is not BST insertion.

```kotlin
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
```

Time: `O(n)` worst case. Queue space: `O(n)` worst case.

### Search an ordinary binary tree

```kotlin
fun search(root: TreeNode?, target: Int): Boolean {
    if (root == null) return false
    if (root.value == target) return true

    return search(root.left, target) ||
           search(root.right, target)
}
```

Time: `O(n)` worst case. Recursive stack: `O(h)`, where `h` is tree height.

## 9. Tree traversal

Traversal means visiting nodes in a defined order.

### DFS: Depth-First Search

DFS explores a branch deeply before moving to another branch.

**Preorder: Root → Left → Right**

```kotlin
fun preorder(root: TreeNode?) {
    if (root == null) return
    print("${root.value} ")
    preorder(root.left)
    preorder(root.right)
}
```

For the example tree, output: `1 2 4 5 3`

**Inorder: Left → Root → Right**

```kotlin
fun inorder(root: TreeNode?) {
    if (root == null) return
    inorder(root.left)
    print("${root.value} ")
    inorder(root.right)
}
```

Output: `4 2 5 1 3`

**Postorder: Left → Right → Root**

```kotlin
fun postorder(root: TreeNode?) {
    if (root == null) return
    postorder(root.left)
    postorder(root.right)
    print("${root.value} ")
}
```

Output: `4 5 2 3 1`

Each DFS traversal takes `O(n)` time and `O(h)` recursive stack space.

### BFS: Breadth-First Search (level order)

BFS visits nodes level by level. It uses a FIFO queue.

```kotlin
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
```

Output: `1 2 3 4 5`

Time: `O(n)`. Auxiliary queue space: `O(w)`, where `w` is the maximum width; worst case `O(n)`.

### Traversal summary

| Traversal | Order | Typical use |
|---|---|---|
| Preorder | Root, Left, Right | Copying / serializing a tree |
| Inorder | Left, Root, Right | Sorted output from a BST |
| Postorder | Left, Right, Root | Subtree calculations / deletion |
| Level order | Level by level | Level-based problems / minimum depth |

Preorder, inorder, and postorder are traversal orders, not types of trees.

## 10. Tree properties

- A tree with `n` nodes has `n - 1` edges.
- Every node except the root has exactly one parent.
- A tree is connected and contains no cycles.
- There is exactly one path between any two nodes.
- Leaves have no children.

For a binary tree, with root depth 0 and height measured in edges:

- Maximum nodes at depth `d`: `2^d`.
- Maximum nodes in a tree of height `h`: `2^(h + 1) - 1`.
- Minimum possible height for `n` nodes: `ceil(log2(n + 1)) - 1`.

A perfect binary tree of height 2 has `2^(2 + 1) - 1 = 7` nodes.

## 11. Complexity summary

Let `n` be the node count, `h` the tree height, and `w` the maximum width.

| Operation | Ordinary binary tree |
|---|---:|
| Create one node | `O(1)` |
| Search | `O(n)` worst case |
| Level-order insertion | `O(n)` worst case |
| DFS traversal | `O(n)` |
| BFS traversal | `O(n)` |

BST search and insertion take `O(h)`; for a balanced BST, `h = O(log n)`, while a skewed BST can have `h = O(n)`.

## 12. Recommended LeetCode learning order

1. Create a binary tree and understand nullable child references.
2. Implement preorder, inorder, and postorder from memory.
3. Practise BFS using `ArrayDeque`.
4. Solve Maximum Depth of Binary Tree, Invert Binary Tree, and Same Tree.
5. Learn BST operations and Validate Binary Search Tree.
6. Progress to Diameter of Binary Tree, Balanced Binary Tree, Lowest Common Ancestor, and Binary Tree Level Order Traversal.

**Core idea:** DFS explores branches; BFS explores levels. Identify which pattern a problem needs, then decide what each recursive call must return.
