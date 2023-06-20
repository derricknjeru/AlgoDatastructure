package CodingInterviewPatterns.trees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePostorderTraversal {


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

   //https://leetcode.com/problems/binary-tree-postorder-traversal/

    /**
     * Given the root of a binary tree, return the postorder traversal of its nodes' values.
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

    List<Integer> result = new ArrayList<>();

    //https://www.youtube.com/watch?v=ZM-sV9zQPEs
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root==null){
            return result;
        }

        postorderTraversal(root.left);

        postorderTraversal(root.right);

        result.add(root.val);

        return result;

    }

    //https://www.youtube.com/watch?v=xLQKdq0Ffjg&t=1s
    public List<Integer> postorderTraversalIterative(TreeNode root) {


    //TODO
        return result;
    }
}




