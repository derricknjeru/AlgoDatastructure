package CodingInterviewPatterns.trees.quickReview;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//https://leetcode.com/problems/binary-search-tree-iterator/
//https://www.youtube.com/watch?v=C8iHdhXjKC4
public class BinarySearchTreeIterator {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    class BSTIterator {
        List<Integer> valList = new ArrayList<>();
        int index = 0;

        public BSTIterator(TreeNode root) {
            inOrderTraversal(root);
        }

        private void inOrderTraversal(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrderTraversal(root.left);
            valList.add(root.val);
            inOrderTraversal(root.right);
        }

        public int next() {
            return valList.get(index++);
        }

        public boolean hasNext() {
            return index < valList.size();
        }
    }

    class BSTIterator2 {
        Deque<TreeNode> stack = new ArrayDeque<>();
        public BSTIterator2(TreeNode root) {
            partialInorder(root);
        }

        private void partialInorder (TreeNode node){
            while(node!=null){
                stack.push(node);
                node = node.left;
            }
        }

        public int next() {
            TreeNode top = stack.pop();
            partialInorder(top.right);
            return top.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
}
