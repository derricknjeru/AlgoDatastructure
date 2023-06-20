package CodingInterviewPatterns.trees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {
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
    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null){
            return result;
        }

        result.add(root.val);

        preorderTraversal(root.left);

        preorderTraversal(root.right);

        return result;
    }

    //iterative //https://www.youtube.com/watch?v=elQcrJrfObg
}
