package CodingInterviewPatterns.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    //https://www.youtube.com/watch?v=d4zLyf32e3I
    //https://leetcode.com/problems/binary-tree-right-side-view/description/
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

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = q.poll();
                if (currentNode.left != null) {
                    q.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    q.add(currentNode.right);
                }

                if (i == size - 1) res.add(currentNode.val);
            }

        }

        return res;
    }

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

    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursion(root, list, 0);
        return list;
    }

    public void recursion(TreeNode root, List<Integer> list, int depth) {
        if (root == null)
            return;

        if (depth == list.size())
            list.add(root.val);

        recursion(root.right, list, depth + 1);
        recursion(root.left, list, depth + 1);

    }


}
