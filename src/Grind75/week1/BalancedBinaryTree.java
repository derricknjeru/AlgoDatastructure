package Grind75.week1;

public class BalancedBinaryTree {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);


        boolean isBalanced = new Solution().isBalanced(root);
        System.out.println("Is the binary tree balanced? " + isBalanced);
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
    static class Solution {
        private int isBalanced = 1;

        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }

            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);

            if (Math.abs(leftHeight - rightHeight) > 1) {
                return false;
            }

            return isBalanced(root.left) && isBalanced(root.right);
        }

        private int getHeight(TreeNode node) {
            if (node == null) {
                return 0;
            }

            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
