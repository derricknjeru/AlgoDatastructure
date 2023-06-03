package trees;

import java.util.ArrayList;
import java.util.List;


public class FindLeavesOfBinaryTree {

    public static void main(String[] args) {
        // Create a binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Extract leaves in repeated succession
        BinaryTreeLeavesExtractor extractor = new BinaryTreeLeavesExtractor();
        List<List<Integer>> leaves = extractor.findLeaves(root);
        System.out.println(leaves);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class BinaryTreeLeavesExtractor {
        //https://chat.openai.com/c/508c74ab-9bce-42b7-bdee-6240c5e7f854
        public List<List<Integer>> findLeaves(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            extractLeaves(root, result);
            return result;
        }

        /**
         * The time complexity of the extractLeaves method in the given implementation is O(n), where n is the number of nodes in the binary tree.
         * <p>
         * Since the method traverses each node of the binary tree exactly once, the time complexity is directly proportional to the number of nodes in the tree.
         * In the worst case scenario, we need to visit every node in the tree to extract all the leaves. Therefore, the time complexity is linear with respect to the number of nodes.
         *
         * @param node
         * @param result
         * @return
         */

        private int extractLeaves(TreeNode node, List<List<Integer>> result) {
            if (node == null) {
                return -1;
            }

            int leftHeight = extractLeaves(node.left, result);
            int rightHeight = extractLeaves(node.right, result);
            int height = Math.max(leftHeight, rightHeight) + 1;

            System.out.println("Height-----" + height + "-----value" + node.val);

            if (height == result.size()) {
                result.add(new ArrayList<>());
            }
            result.get(height).add(node.val);

            return height;
        }
    }
   //We can use levels as well
    //https://www.youtube.com/watch?v=1T7vwABgiys
    private int extractLeaves(TreeNode node, List<List<Integer>> result) {
        if (node == null) {
            return 0;
        }

        int left = extractLeaves(node.left, result);
        int right = extractLeaves(node.right, result);
        int currentLevel = Math.max(left, right);

        if (result.size() <= currentLevel) {
            result.add(new ArrayList<>());
        }
        result.get(currentLevel).add(node.val);

        return currentLevel + 1;
    }
}
