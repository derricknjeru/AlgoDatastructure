package CodingInterviewPatterns.trees;

public class SymmetricTree {
    //https://www.youtube.com/watch?v=Mao9uzxwvmc

    /**
     * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     */

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //https://leetcode.com/problems/symmetric-tree/
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return helperIsSymetric(root.left, root.right);
    }

    //Time complexity is O(n)
    //Space complexity is recursive call stack O(h)

    private boolean helperIsSymetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val) return false;


        return helperIsSymetric(left.left, right.right) && helperIsSymetric(left.right, right.left);
    }
}
