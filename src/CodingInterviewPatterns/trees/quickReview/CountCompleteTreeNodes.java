package CodingInterviewPatterns.trees.quickReview;

public class CountCompleteTreeNodes {
    public static void main(String[] args) {
        //https://www.geeksforgeeks.org/count-number-of-nodes-in-a-complete-binary-tree/
        //https://leetcode.com/problems/count-complete-tree-nodes/description/
        //https://interviewing.io/questions/count-complete-tree-nodes
        //https://www.youtube.com/watch?v=CvrPf1-flAA&ab_channel=Techdose

        // Create a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Create an instance of the Solution class
        Solution solution = new Solution();

        // Calculate and print the total number of nodes in the tree
        int totalNodes = solution.countNodes(root);
        System.out.println("Total number of nodes in the binary tree: " + totalNodes);

    }

    static class Solution {
        /**
         * Time Complexity: O((log N)2)
         *-
         * Calculating the height of a tree with x nodes takes (log x) time.
         * Here, we are traversing through the height of the tree.
         * For each node, height calculation takes logarithmic time.
         * As the number of nodes is N, we are traversing log(N) nodes and calculating the height for each of them.
         * So the total complexity is (log N * log N) = (log N)2.
         */
        /**
         * Calculates the total number of nodes in a binary tree.
         *
         * @param root The root of the binary tree.
         * @return The total number of nodes in the tree.
         */
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int leftHeight = calculateHeight(root);
            int rightHeight = calculateRhHeight(root);

            if (leftHeight == rightHeight) {
                // The tree is a perfect binary tree, so use the formula to calculate nodes.
                return (1 << leftHeight) - 1; // Equivalent to 2^leftHeight - 1
            } else {
                // The tree is not perfect, so recursively count nodes in left and right subtrees.
                return 1 + countNodes(root.left) + countNodes(root.right);
            }
        }

        /**
         * Calculates the height (level) of a binary tree from the given node.
         *
         * @param node The root of the binary tree.
         * @return The height of the tree.
         */
        private int calculateHeight(TreeNode node) {
            int height = 0;
            while (node != null) {
                height++;
                node = node.left;
            }
            return height;
        }

        /**
         * Calculates the height (level) of a binary tree from the given node.
         *
         * @param node The root of the binary tree.
         * @return The height of the tree.
         */
        private int calculateRhHeight(TreeNode node) {
            int height = 0;
            while (node != null) {
                height++;
                node = node.right;
            }
            return height;
        }
    }

}
