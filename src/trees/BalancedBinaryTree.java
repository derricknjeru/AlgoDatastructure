package trees;

public class BalancedBinaryTree {
    /**
     * Given a binary tree, determine if it is height-balanced.
     *
     * For this problem, a height-balanced binary tree is defined as:
     *
     * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
     */

    //https://leetcode.com/problems/balanced-binary-tree/
    //https://www.youtube.com/watch?v=OgdYyBT8iU8

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isBalanced2(TreeNode root) {
        if(root==null) return true;

        if(Math.abs(helper(root.left)-helper(root.right))>1) return false;

        return isBalanced(root.left) && isBalanced(root.right);

    }

    private int helper(TreeNode root){
        if(root==null){
            return 0;
        }

        int ld = helper(root.left);
        int rd = helper(root.right);

        return Math.max(ld, rd) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        return height(root)==-1 ? false: true;
    }

    private int height (TreeNode root){
        if(root==null){
            return 0;
        }

        int ld = height(root.left);
        int rd = height(root.right);

        int bf = Math.abs(ld-rd);

        if(bf>1 || ld==-1 || rd==-1)
            return -1;

        return Math.max(ld, rd) + 1;
    }
}
