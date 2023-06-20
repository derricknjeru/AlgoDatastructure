package CodingInterviewPatterns.trees;

public class BinaryTreeIsSymmetric {
    class TreeNode {
        int val;
        TreeNode right, left;

        TreeNode(int d) {
            val = d;
            right = left = null;
        }

    }

    //https://www.youtube.com/watch?v=K7LyJTWr2yA
    //https://leetcode.com/problems/symmetric-tree/

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helperIsSymetric(root.left, root.right);
    }

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
