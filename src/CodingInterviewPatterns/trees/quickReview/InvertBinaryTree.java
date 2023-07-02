package CodingInterviewPatterns.trees.quickReview;

public class InvertBinaryTree {
    TreeNode root;

    class TreeNode {
        int data;
        TreeNode left, right;

        public TreeNode(int item) {
            data = item;
            left = right = null;
        }
    }
    //https://www.youtube.com/watch?v=OnSn2XEQ4MY&t=3s

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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    private TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;

        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);

        root.left = right;
        root.right = left;

        return root;
    }


}
