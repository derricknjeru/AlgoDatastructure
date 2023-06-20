package CodingInterviewPatterns.trees;

public class LowestCommonAncestorBT {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //https://www.youtube.com/watch?v=nMGph_Lt0ls
        //https://www.youtube.com/watch?v=KobQcxdaZKY
        // If the root is null or matches either p or q, it is the LCA
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        // Recursively search in the left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both left and right subtrees return a node, the current node is the LCA
        if (left != null && right != null) {
            return root;
        }

        // If only the left subtree returns a node, it is the LCA
        if (left != null) {
            return left;
        }

        // If only the right subtree returns a node, it is the LCA
        if (right != null) {
            return right;
        }

        return null; // No LCA found
    }

    public static void main(String[] args) {
        /*
         * Construct the following BST:
         *        6
         *      /   \
         *     2     8
         *    / \   / \
         *   0   4 7   9
         *      / \
         *     3   5
         */
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);

        LowestCommonAncestorBT lcaFinder = new LowestCommonAncestorBT();
        TreeNode lca = lcaFinder.lowestCommonAncestor(root, p, q);
        System.out.println("Lowest Common Ancestor: " + lca.val); // Output: 6
    }


}
