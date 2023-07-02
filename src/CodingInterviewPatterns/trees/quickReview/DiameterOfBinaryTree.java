package CodingInterviewPatterns.trees.quickReview;

public class DiameterOfBinaryTree {
    static TreeNode root;

    public static void main(String[] args) {
        Solution tree = new Solution();

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Function Call
        System.out.println(
                "The diameter of given binary tree is : "
                        + tree.diameterOfBinaryTree(root));
    }

    //https://www.youtube.com/watch?v=mWyhPJ1Ukvw&ab_channel=KnowledgeCenter
    //https://chat.openai.com/c/f6941e57-e22e-4585-a1a4-f8a26a0486e8


    //https://leetcode.com/problems/diameter-of-binary-tree/
    //https://leetcode.com/problems/maximum-depth-of-binary-tree/
    //https://leetcode.com/problems/validate-binary-search-tree/

    //Sometimes called width

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
    static class Solution {
        /**
         * In the given solution, the computeDepth method is used to calculate the depth (or height) of each subtree rooted at a particular node. The depth of a subtree is defined as the number of edges in the longest path from that node to a leaf node.
         * <p>
         * By returning Math.max(leftDepth, rightDepth) + 1, we are calculating the depth of the current node by taking the maximum depth between its left and right subtrees and adding 1 to account for the current node itself.
         * <p>
         * Since the diameter of a binary tree is defined as the longest path between any two nodes, it can be represented as the sum of the depths of the left and right subtrees rooted at a particular node. Therefore, at each node, we update the maxDiameter if the sum of the depths of its left and right subtrees is greater than the current maximum diameter.
         * <p>
         * By returning the maximum depth between the left and right subtrees plus 1, we ensure that the depth is correctly calculated for each node and propagated up the tree during the recursive traversal.
         */
        int maxDiameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            computeDepth(root); //This function calculate height.
            return maxDiameter;
        }

        private int computeDepth(TreeNode root) {
            if (root == null) return 0;

            //recursively compute depth on the left and right subtrees
            int leftDepth = computeDepth(root.left);
            int rightDepth = computeDepth(root.right);

            maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

            //this function is meant to return height. So keep returning height
            return 1 + Math.max(leftDepth, rightDepth);
        }

        /**
         * The time complexity of the given solution for finding the diameter of a binary tree is O(n), where n is the number of nodes in the tree.
         *
         * The reason for this time complexity is that we visit each node in the tree exactly once. During the recursive traversal of the tree, we compute the depth of each subtree rooted at a particular node. This computation involves visiting each node once, which contributes to a linear time complexity.
         *
         * Since we visit each node once and perform a constant amount of work at each node (updating the maxDiameter and calculating the depth), the overall time complexity of the solution is O(n).
         *
         * It's worth noting that the solution has an optimal time complexity since it visits each node only once. This is the most efficient approach for finding the diameter of a binary tree.
         */
    }
}
