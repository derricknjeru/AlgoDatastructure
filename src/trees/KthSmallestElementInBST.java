package trees;

import java.util.ArrayList;
import java.util.LinkedList;

public class KthSmallestElementInBST {
    //https://www.youtube.com/watch?v=5LUXSvjmGCw
    //https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/

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

    int count = 0;
    int res = -1;

    public int kthSmallest(TreeNode root, int k) {
        InOrdrTraversal(root, k);
        return res;
    }

    private void InOrdrTraversal(TreeNode root, int k) {
        if (root == null) return;

        InOrdrTraversal(root.left, k);
        count++;
        if (k == count) {
            res = root.val;
            return;
        }
        InOrdrTraversal(root.right, k);
    }


    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }

    public int kthSmallest2(TreeNode root, int k) {
        ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
        return nums.get(k - 1);
    }

    public int kthSmallest3(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }


}
