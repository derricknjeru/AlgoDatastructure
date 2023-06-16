package trees;

public class PathSum {
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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        // Check if the current node is a leaf node
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        // Recursively check the left and right child nodes
        boolean leftHasPath = hasPathSum(root.left, targetSum - root.val);
        boolean rightHasPath = hasPathSum(root.right, targetSum - root.val);

        // Return true if either of the child nodes has a path with the target sum
        return leftHasPath || rightHasPath;
    }

}
