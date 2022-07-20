package linkedList;

public class SearchInBinarySearchTree {
    //
    // https://leetcode.com/problems/search-in-a-binary-search-tree/
    //https://www.youtube.com/watch?v=b8_ZINq9yhA&t=336s
    //Definition for a binary tree node.
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
    //https://leetcode.com/problems/search-in-a-binary-search-tree/

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.val == val)
            return root;
        else if (root.val > val)
            return searchBST(root.left, val);
        else
            return searchBST(root.right, val);

    }

    public TreeNode searchBSTIterative(TreeNode root, int val) {
        if(root ==null){
            return null;
        }

        while(root!=null){
            if(root.val == val){
                return root;
            }else if(val <= root.val){
                root = root.left;
            }else {
                root = root.right;
            }
        }

        return null;

    }
}
