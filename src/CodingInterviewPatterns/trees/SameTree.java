package CodingInterviewPatterns.trees;

public class SameTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
        /**
         * Definition for a binary tree node.
         * public class TreeNode {
         *     int val;
         *     TreeNode left;
         *     TreeNode right;
         *     TreeNode() {}
         *     TreeNode(int val) { this.val = val; }
         *     TreeNode(int val, TreeNode left, TreeNode right) {
         *         this.val = val;
         *         this.left = left;
         *         this.right = right;
         *     }
         * }
         */
    }

    //https://leetcode.com/problems/same-tree/solution/
    //https://leetcode.com/problems/same-tree/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {

    }
}
