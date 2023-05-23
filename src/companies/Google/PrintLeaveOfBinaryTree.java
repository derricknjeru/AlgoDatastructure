package companies.Google;

public class PrintLeaveOfBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);

        printLeaves(tree);

    }

    /**
     * Time Complexity: O(n), where n is the number of nodes in the binary tree.
     * Auxiliary Space: O(n)
     *
     * @param tree
     */

    private static void printLeaves(TreeNode tree) {
        // If node is null, return
        if (tree == null) {
            return;
        }
        // If node is leaf node, print its data
        if (tree.left == null && tree.right == null) {
            System.out.print(tree.val);
        }

        // If left child exists, check for leaf
        // recursively
        printLeaves(tree.left);

        // If right child exists, check for leaf
        // recursively
        printLeaves(tree.right);

    }
}
