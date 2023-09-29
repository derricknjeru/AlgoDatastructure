package CodingInterviewPatterns.trees;

import java.util.ArrayList;
import java.util.List;


public class FindLeavesOfBinaryTree {
    //https://interviewing.io/questions/find-leaves-of-binary-tree

    public static void main(String[] args) {
        // Create a binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        Solution sln = new Solution();

        List<List<Integer>> leavesInSuccession = sln.findLeaves(root);

        System.out.println("Leaves in succession:");
        for (List<Integer> level : leavesInSuccession) {
            System.out.println(level);
        }

    }

    static class Solution {
        List<List<Integer>> findLeaves(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            /**
             * Time/Space Complexity
             *-
             * Time Complexity: O(n). Our algorithm visits each node exactly once, and does a constant amount of work per node.
             * We can say that our time complexity is O(n), where n is the number of nodes in the tree.
             * Space Complexity:
             * O(n). Our algorithm makes a copy of all of the values of the tree as it assembles its answer, which is O(n).
             * It also does a depth-first search to process the input, which takes O(h) space, where h is the height of the tree.
             * If the tree is a balanced binary tree, then h is the base-2 logarithm of n, or lg(n).
             * If the tree is not a balanced binary tree the worst-case space complexity O(h) == O(n),
             * as the height of a tree with all nodes on one side could equal the size of the tree as a whole.
             */
            //this is an post order traversal
            extractLeavesDFS(root, result);

            return result;
        }

        private int extractLeavesDFS(TreeNode node, List<List<Integer>> result) {
            if (node == null) {
                /**
                 * Nodes with no children and proper leaves will always have a height of zero and a sublist index of zero.
                 * .e -1 + 1 = 0
                 */
                return -1; // Return -1 for null nodes, coz we want to start at index 0
            }

            int leftDepth = extractLeavesDFS(node.left, result);
            int rightDepth = extractLeavesDFS(node.right, result);

            // We get max height since we are calculating height from the children
            /**
             * The height of all other nodes will be derived from the heights passed upwards from their children.
             * If their children have different heights, the greater of the two heights "wins" and the parent node’s height
             * will equal the winner's height plus one. That will also be the sublist index the node value ends up in.
             */
            int currentDepth = Math.max(leftDepth, rightDepth) + 1;

            // Ensure the result list has enough levels to store leaves at the current depth.
            while (result.size() <= currentDepth) {
                result.add(new ArrayList<>());
            }

            // Add the current node's value to the appropriate level in the result.
            result.get(currentDepth).add(node.val);

            return currentDepth;
        }

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }


}
