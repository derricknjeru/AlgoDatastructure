package CodingInterviewPatterns.trees.quickReview;

public class BalancedBinaryTree {
    /**
     * Given a binary tree, determine if it is height-balanced.
     * <p>
     * For this problem, a height-balanced binary tree is defined as:
     * <p>
     * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
     */

    //https://leetcode.com/problems/balanced-binary-tree/
    //https://www.youtube.com/watch?v=QfJsau0ItOY
    //https://www.youtube.com/watch?v=OgdYyBT8iU8

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        private int isBalanced = 1;

        public boolean isBalanced(TreeNode root) {
            dfs(root);
            return isBalanced == 1;
        }

        private int dfs(TreeNode root) {
            if (root == null) return 0;

            int lh = dfs(root.left);
            int rh = dfs(root.right);

            int diff = Math.abs(lh - rh);

            if (diff > 1) {
                isBalanced = -1;
            }

            return 1 + Math.max(lh, rh);
        }

    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return height(root) != -1;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ld = height(root.left);
        int rd = height(root.right);

        int bf = Math.abs(ld - rd);

        if (bf > 1 || ld == -1 || rd == -1)
            return -1;

        return Math.max(ld, rd) + 1;
    }
}
