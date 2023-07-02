package CodingInterviewPatterns.trees.quickReview;


public class SubtreeOfAnotherTree {
    //https://leetcode.com/problems/subtree-of-another-tree/editorial/
    //https://www.youtube.com/watch?v=E36O5SWp-LE
    //https://leetcode.com/problems/subtree-of-another-tree/

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

    /**
     * Complexity Analysis
     * Time complexity: O(MN), For every N node in the tree, we check if the tree rooted at node is identical to subRoot.
     * This check takes O(M) time, where M is the number of nodes in subRoot. Hence,
     * the overall time complexity is O(MN)
     * <p>
     * Space complexity: O(M+N)
     * <p>
     * There will be at most N recursive call to dfs ( or isSubtree).
     * Now, each of these calls will have MMM recursive calls to isIdentical.
     * Before calling isSametree, our call stack has at most O(N) elements and might increase to O(N+M)) during the call.
     * After calling isSametree, it will be back to at most O(N) since all elements made by isSametree are popped out.
     * Hence, the maximum number of elements in the call stack will be M+N
     *
     * @param root
     * @param subRoot
     * @return
     */

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        // If this node is Empty, then no tree is rooted at this Node
        // Hence, "tree rooted at node" cannot be equal to "tree rooted at subRoot"
        // "tree rooted at subRoot" will always be non-empty, as per constraints
        if (root == null) {
            return false;
        }

        // Check if the "tree rooted at root" is identical to "tree roooted at subRoot"
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // If not, check for "tree rooted at root.left" and "tree rooted at root.right"
        // If either of them returns true, return true
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;

        if (root == null || subRoot == null) return false;

        if (root.val != subRoot.val) return false;

        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
    }
}

