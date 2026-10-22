package leetcode._7Trees

fun main() {

}


/*
 * Time Complexity: O(M * N)
 * - N is the number of nodes in the root tree, and M is the number of nodes in subRoot.
 * - In the worst-case, isSameTree is called for every node in the root tree.
 *
 * Space Complexity: O(H)
 * - H is the height of the root tree due to the recursive call stack.
 * - This can be O(N) in the worst-case (skewed tree) and O(log N) in the best case (balanced tree).
 */
fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
    if (subRoot == null) return true
    if (root == null) return false
    // using helper function from is same tree question 6
    if (isSameTree(root, subRoot)) return true

    return isSubtree(root.left, subRoot) ||
            isSubtree(root.right, subRoot)
}