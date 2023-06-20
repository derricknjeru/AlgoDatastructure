package CodingInterviewPatterns.trees;

public interface ConstructStringFromBinaryTree {
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
    //https://www.geeksforgeeks.org/binary-tree-string-brackets/
    //https://leetcode.com/problems/construct-string-from-binary-tree/

    class Solution {
        StringBuilder sb = new StringBuilder();
        String str;

        public String tree2str(TreeNode root) {
            if (root == null) return "";
            preOrder(root);
            return sb.toString();
        }

        private void preOrder(TreeNode root) {
            // bases case
            if (root == null)
                return;

            // push the root data as character
            sb.append(root.val + "");

            // if leaf node, then return
            if (root.left == null && root.right == null)
                return;

            // for left subtree
            sb.append("(");
            preOrder(root.left);
            sb.append(")");

            // only if right child is present to
            // avoid extra parenthesis
            if (root.right != null) {
                sb.append("(");
                preOrder(root.right);
                sb.append(")");
            }
        }

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}