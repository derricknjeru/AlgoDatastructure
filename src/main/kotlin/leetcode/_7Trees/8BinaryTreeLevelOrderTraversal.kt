package leetcode._7Trees

fun main() {

}

// https://www.youtube.com/watch?v=6ZnyEApgFYg
fun levelOrderList(root: TreeNode?): List<List<Int>> {
    if (root == null) return emptyList()

    val result = mutableListOf<List<Int>>()
    val queue = ArrayDeque<TreeNode>()
    queue.addLast(root)

    while (queue.isNotEmpty()) {
        val levelSize = queue.size
        val level = mutableListOf<Int>()

        repeat(levelSize) {
            val node = queue.removeFirst()
            level.add(node.value)

            node.left?.let { queue.addLast(it) }
            node.right?.let { queue.addLast(it) }
        }

        result.add(level)
    }

    return result
}



/*
 * Time: O(n), where n is the number of nodes; each is processed once.
 * Auxiliary space: O(w), where w is the maximum tree width; O(n) worst case.
 * Output space: O(n).
 */