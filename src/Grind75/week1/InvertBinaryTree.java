package Grind75.week1;

public class InvertBinaryTree {
    //https://www.youtube.com/watch?v=OnSn2XEQ4MY
    //https://leetcode.com/problems/invert-binary-tree/description/
    public static void main(String[] args) {
        // Create the binary tree
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        // Invert the binary tree
        Solution solution = new Solution();
        TreeNode invertedRoot = solution.invertTree(root);

        // Print the inverted binary tree
        printTree(invertedRoot);
    }

    public static void printTree(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
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
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;

            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;


            invertTree(root.left);
            invertTree(root.right);

            return root;
        }
    }

    static class Solution2 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;

            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);

            root.left = right;
            root.right = left;

            return root;
        }
    }
}
