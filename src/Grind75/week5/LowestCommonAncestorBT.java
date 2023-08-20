package Grind75.week5;

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

    static class Solution2 {

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

        Solution lcaFinder = new Solution();
        TreeNode lca = lcaFinder.lowestCommonAncestor(root, p, q);
        System.out.println("Lowest Common Ancestor: " + lca.val); // Output: 6
    }


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            return findLCA(root, p.val, q.val);
        }

        // This function returns pointer to LCA of two given
        // values n1 and n2. This function assumes that n1 and
        // n2 are present in Binary Tree
        TreeNode findLCA(TreeNode node, int n1, int n2) {
            // Base case
            if (node == null)
                return null;

            // If either n1 or n2 matches with root's key, report
            // the presence by returning root (Note that if a key is
            // ancestor of other, then the ancestor key becomes LCA
            if (node.val == n1 || node.val == n2)
                return node;

            // Look for keys in left and right subtrees
            TreeNode left_lca = findLCA(node.left, n1, n2);
            TreeNode right_lca = findLCA(node.right, n1, n2);

            // If both of the above calls return Non-NULL, then one key
            // is present in once subtree and other is present in other,
            // So this node is the LCA
            if (left_lca != null && right_lca != null)
                return node;

            // Otherwise check if left subtree or right subtree is LCA
            return (left_lca != null) ? left_lca : right_lca;
        }
    }


}
