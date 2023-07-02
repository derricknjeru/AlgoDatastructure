package CodingInterviewPatterns.trees.quickReview;

public class LowestCommonAncestorBST {
    private static TreeNode root;

    // A binary tree node
    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
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

        LowestCommonAncestorBST lcaFinder = new LowestCommonAncestorBST();
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
     * Therefore, the time complexity of both approaches is O(h),
     * where h is the height of the BST. In the best case scenario of a balanced BST,
     * the time complexity is O(log(n)), and in the worst case scenario of a skewed BST,
     * the time complexity is O(n).
     * }
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //https://www.youtube.com/watch?v=gs2LMfuOR9k
        if (root == null) {
            return null;
        }

        while (root != null) {
            // If both p and q are less than the current node, LCA must be in the left subtree
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            }
            // If both p and q are greater than the current node, LCA must be in the right subtree
            else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            }
            // Otherwise, the current node is the LCA
            else {
                return root;
            }
        }

        return null;
    }

    /**
     * The time complexity of both the iterative and recursive approaches to finding the lowest common ancestor (LCA) in a binary search tree (BST) is O(h), where h is the height of the BST.
     * <p>
     * In both approaches, we traverse down the BST from the root to find the LCA node. The height of a balanced BST is log(n), where n is the number of nodes in the BST. In the worst case, when the BST is skewed (e.g., all nodes are in a straight line), the height becomes n.
     * <p>
     * In the recursive approach, we make recursive calls to search in either the left or right subtree based on the values of the current node, p, and q. The number of recursive calls depends on the height of the tree, so the time complexity is O(h).
     * <p>
     * Similarly, in the iterative approach, we use a while loop to traverse down the BST by updating the root pointer. The number of iterations depends on the height of the tree, so the time complexity is also O(h).
     * <p>
     * Therefore, the time complexity of both approaches is O(h), where h is the height of the BST. In the best case scenario of a balanced BST, the time complexity is O(log(n)), and in the worst case scenario of a skewed BST, the time complexity is O(n).
     *
     * @param root
     * @param p
     * @param q
     * @return
     */

    /*
     * In summary, the space complexity of the iterative approach is O(1),
     * and the space complexity of the recursive approach is O(h) or O(log(n)),
     *  depending on the height of the BST.
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        // If both p and q are less than the current node, LCA must be in the left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor2(root.left, p, q);
        }

        // If both p and q are greater than the current node, LCA must be in the right subtree
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor2(root.right, p, q);
        }

        // Otherwise, the current node is the LCA
        return root;
    }

}
