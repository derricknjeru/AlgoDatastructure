package CodingInterviewPatterns.trees.quickReview;


import java.util.ArrayList;
import java.util.LinkedList;

public class KthSmallestElementInBST {
    //https://www.youtube.com/watch?v=5LUXSvjmGCw
    //https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/

    /**
     * In the kthSmallest method, the code calls the inOrderTraversal method,
     * passing the root of the binary tree and the value of k.
     * The inOrderTraversal method performs an in-order traversal of the binary tree and stops when the count reaches the value of k.
     *
     * During the in-order traversal, the code visits nodes in ascending order of their values.
     * It increments the count variable for each visited node and checks if the count is equal to k. If the count is equal to k, it updates the smallest variable with the value of the current node and stops further traversal.
     *
     * Since the code stops the traversal when the count reaches k,
     * it visits only k nodes in the worst case. Therefore, the time complexity of the code is O(k), where k is the input parameter.
     *
     * The space complexity remains O(H), where H is the height of the binary tree,
     * as explained in the previous response.
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
