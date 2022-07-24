package trees;


import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {


    /**
     * Given the root of a binary tree, return its maximum depth.
     * <p>
     * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: root = [3,9,20,null,null,15,7]
     * Output: 3
     * Example 2:
     * <p>
     * Input: root = [1,null,2]
     * Output: 2
     * Example 3:
     * <p>
     * Input: root = []
     * Output: 0
     * Example 4:
     * <p>
     * Input: root = [0]
     * Output: 1
     * <p>
     * <p>
     * Constraints:
     * <p>
     * The number of nodes in the tree is in the range [0, 104].
     * -100 <= Node.val <= 100
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int d) {
            val = d;
            left = right = null;
        }
    }
   //https://leetcode.com/explore/learn/card/recursion-i/256/complexity-analysis/2375/
    //https://www.youtube.com/watch?v=hTM3phVI6YQ

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ld = maxDepth(root.left);
        int rd = maxDepth(root.right);

        return Math.max(ld, rd) + 1;

    }

    private boolean isLeaf(TreeNode a) {
        return (a.right == null && a.left == null);
    }


    public int maxDepth2(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);


        int level = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                TreeNode currentNode = q.poll();

                if (currentNode.left != null) {
                    q.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    q.add(currentNode.right);
                }
            }

            level++;
        }

        return level;

    }
}