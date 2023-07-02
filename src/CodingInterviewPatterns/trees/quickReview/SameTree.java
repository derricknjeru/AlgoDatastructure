package CodingInterviewPatterns.trees.quickReview;

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
        private int isSame = 1;

        public boolean isSameTree(TreeNode p, TreeNode q) {
            return dfs(p, q);
        }

        private boolean dfs(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }

            if (p == null || q == null) {
                return false;
            }

            if (p.val != q.val) return false;

            boolean left = dfs(p.left, q.left);
            boolean right = dfs(p.right, q.right);

            return left && right;
        }
    }

    public static void main(String[] args) {

    }
}
