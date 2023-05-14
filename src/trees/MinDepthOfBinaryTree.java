package trees;

import java.util.LinkedList;
import java.util.Queue;

public class MinDepthOfBinaryTree {


    //https://www.youtube.com/watch?v=6aChG_3jMAQ&ab_channel=NareshGupta
    //https://www.geeksforgeeks.org/find-minimum-depth-of-a-binary-tree/

    /**
     * This method is better because method 2 may end up with complete traversal of Binary Tree even when the topmost leaf is close to root.
     * A Better Solution is to do Level Order Traversal. While doing traversal, returns depth of the first encountered leaf node.
     * <p>
     * Below is the implementation of this solution.
     * <p>
     * <p>
     * Time Complexity: O(n), where n is the number of nodes in the given binary tree. This is due to the fact that we are visiting each node once.
     * Auxiliary Space: O(n), as we need to store the elements in a queue for level order traversal.
     *
     * @param root
     * @return
     */

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int depth = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                root = q.remove();
                // If this is the first leaf node
                // seen so far.
                // Then return its depth as answer
                if (root.left == null && root.right == null) {
                    return depth;
                }

                if (root.left != null) {
                    q.add(root.left);
                }

                if (root.right != null) {
                    q.add(root.right);
                }

            }

            depth++;

        }

        return 0;

    }

    /**
     * Time Complexity: O(n), as it traverses the tree only once.
     * Auxiliary Space: O(h), where h is the height of the tree, this space is due to the recursive call stack.
     *
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        // Corner case. Should never be hit unless the code is
        // called on root = NULL
        if (root == null) return 0;

        // Base case : Leaf Node. This accounts for height = 1.
        if (root.left == null && root.right == null) {
            return 1;
        }

        // If left subtree is NULL, recur for right subtree
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }
        // If right subtree is NULL, recur for left subtree
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}


