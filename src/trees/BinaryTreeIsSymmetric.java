package trees;

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
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }
}
