package leetcode._7Trees

fun main() {

}

// https://www.youtube.com/watch?v=gs2LMfuOR9k
fun lowestCommonAncestor(
    root: TreeNode?,
    p: TreeNode?,
    q: TreeNode?
): TreeNode? {
    if (root == null || p == null || q == null) return null

    var current = root

    while (current != null) {
        when {
            p.value < current.value && q.value < current.value ->
                current = current.left

            p.value > current.value && q.value > current.value ->
                current = current.right

            else -> return current
        }
    }

    return null
}


/*
 * Time: O(h), where h is the BST height
 *   O(log n) if balanced; O(n) in the worst case if skewed.
 * Space: O(1), since the search is iterative.
 */