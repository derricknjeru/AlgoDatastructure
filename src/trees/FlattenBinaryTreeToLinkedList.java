package trees;

import java.util.ArrayDeque;
import java.util.Deque;

public class FlattenBinaryTreeToLinkedList {
    //https://www.youtube.com/watch?v=rKnD7rLT0lI

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    TreeNode prev = null;

    public void flatten(TreeNode root) {
        if(root ==null) return;

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;

    }

    public void flatten2(TreeNode root) {
        if(root ==null) return;

        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode curr_node = stack.pop();

            if(curr_node.right!=null){
                stack.push(curr_node.right) ;
            }

            if(curr_node.left != null){
                stack.push(curr_node.left);
            }

            if(!stack.isEmpty()){
                curr_node.right =stack.peek();
            }

            curr_node.left = null;
        }

    }


}
