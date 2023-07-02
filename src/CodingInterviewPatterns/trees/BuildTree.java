package CodingInterviewPatterns.trees;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BuildTree {
    //https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
    //https://www.youtube.com/watch?v=bw0o6v1lQYs
    //https://www.youtube.com/watch?v=ihj4IQGZ2zc

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
    class Solution {
        int preorderIndex;
        Map<Integer, Integer> inorderIndexMap;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            preorderIndex = 0;
            // build a hashmap to store value -> its index relations
            inorderIndexMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inorderIndexMap.put(inorder[i], i);
            }

            return arrayToTree(preorder, 0, preorder.length - 1);
        }

        private TreeNode arrayToTree(int[] preorder, int left, int right) {
            // if there are no elements to construct the tree
            if (left > right) return null;

            // select the preorder_index element as the root and increment it
            int rootValue = preorder[preorderIndex++];
            TreeNode root = new TreeNode(rootValue);

            // build left and right subtree
            // excluding inorderIndexMap[rootValue] element because it's the root
            root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
            root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
            return root;
        }
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
    class Solution2 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder.length == 0 || inorder.length == 0) return null;

            TreeNode root = new TreeNode(preorder[0]);
            int mid = 0;
            for (int i = 0; i < inorder.length; i++) {
                if (preorder[0] == inorder[i]) mid = i;
            }

            root.left =
                    buildTree(
                            Arrays.copyOfRange(preorder, 1, mid + 1),
                            Arrays.copyOfRange(inorder, 0, mid)
                    );
            root.right =
                    buildTree(
                            Arrays.copyOfRange(preorder, mid + 1, preorder.length),
                            Arrays.copyOfRange(inorder, mid + 1, inorder.length)
                    );

            return root;
        }
    }
}
