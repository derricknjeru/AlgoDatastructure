package CodingInterviewPatterns.trees;

import java.util.*;

public class BinaryTreeInorderTraversal {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

//https://leetcode.com/problems/binary-tree-inorder-traversal/
//Soln https://leetcode.com/problems/binary-tree-inorder-traversal/


    /**
     * Given the root of a binary tree, return the inorder traversal of its nodes' values.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: root = [1,null,2,3]
     * Output: [1,3,2]
     * Example 2:
     * <p>
     * Input: root = []
     * Output: []
     * Example 3:
     * <p>
     * Input: root = [1]
     * Output: [1]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * The number of nodes in the tree is in the range [0, 100].
     * -100 <= Node.val <= 100
     * <p>
     * <p>
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     */
    //https://www.youtube.com/watch?v=ZM-sV9zQPEs
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        inorderTraversal(root, result);

        return result;
    }

    private void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, result);

        result.add(root.val);

        inorderTraversal(root.right, result);
    }

    //https://www.youtube.com/watch?v=nzmtCFNae9k
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return inorderTraversal2(root, result);
    }

    private List<Integer> inorderTraversal2(TreeNode root, List<Integer> result) {
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;

        }

        return result;
    }
}



