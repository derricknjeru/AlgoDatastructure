package CodingInterviewPatterns.trees.quickReview;

public class CountGoodNodesInBinaryTree {
    //https://www.youtube.com/watch?v=7cp5imvDzl4
    //https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/
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


    int count = 0;

    public int goodNodes(TreeNode root) {
        //Time complexity O(n) n is the number of nodes
        /**
         * The time complexity of the given code is O(N), where N is the number of nodes in the binary tree.
         *
         * In the goodNodes method, the code calls the preOrder method, passing the root of the binary tree and the initial maximum value, which is Integer.MIN_VALUE. The preOrder method then traverses the tree in a pre-order manner, visiting each node once.
         *
         * During the traversal, the code compares the value of each node with the current maximum value. If the node's value is greater than or equal to the maximum value, it increments the count variable and updates the maximum value. This comparison and increment operation take constant time.
         *
         * Since each node is visited exactly once, the time complexity is proportional to the number of nodes in the binary tree, resulting in a time complexity of O(N), where N is the number of nodes in the tree.
         */
        //space - O(h) ---height of the tree. The recursive call stack, This can be O(n) if the tree is skewed
        return helper(root, Integer.MIN_VALUE);
    }

    private int helper(TreeNode root, int max) {
        if (root == null) return 0;

        if (root.val >= max) {
            count++;
        }
        max = Math.max(root.val, max);

        helper(root.left, max);
        helper(root.right, max);

        return count;
    }

    public int goodNodes2(TreeNode root) {
        return helper2(root, Integer.MIN_VALUE);
    }

    private int helper2(TreeNode root, int max) {
        if (root == null) return 0;

        int res = root.val >= max ? 1 : 0;

        res += helper2(root.left, Math.max(root.val, max));
        res += helper2(root.right, Math.max(root.val, max));

        return res;
    }

}
