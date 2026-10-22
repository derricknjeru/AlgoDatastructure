package leetcode._7Trees

fun main() {

}

class Codec {
    fun serialize(root: TreeNode?): String {
        val result = StringBuilder()

        fun preorder(node: TreeNode?) {
            if (node == null) {
                result.append("#,")
                return
            }

            result.append(node.value).append(',')
            preorder(node.left)
            preorder(node.right)
        }

        preorder(root)
        return result.toString()
    }

    fun deserialize(data: String): TreeNode? {
        val values = data.split(',')
        var index = 0

        fun buildTree(): TreeNode? {
            val value = values[index++]
            if (value == "#") return null

            val node = TreeNode(value.toInt())
            node.left = buildTree()
            node.right = buildTree()
            return node
        }

        return buildTree()
    }
}

/*
 * Time: O(n) for both serialize and deserialize.
 * Auxiliary space:
 *   Serialize: O(h) recursion stack, excluding the O(n) output.
 *   Deserialize: O(n) for tokens plus O(h) recursion stack,
 *                excluding the O(n) reconstructed tree.
 * h is the tree height: O(log n) if balanced; O(n) if skewed.
 */