package CodingInterviewPatterns.trees;


public class InsertIntoBinarySearchTree {
    //https://leetcode.com/problems/insert-into-a-binary-search-tree/

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //BASE CASE
        //ROOT == NULL
        //RETURN THE NODE WITH THA VALULE VAL
        if(root == null){return new TreeNode(val);}

        //IF THE VALUE ROOT IS GREATER THEN THE VAL
        //GO TO LEFT
        //AND CALL INSERT METHOD (IF NULL ADD A NEW NODE WITH THE VALUE VAL)
        if(root.val >= val)
        {
            root.left = insertIntoBST(root.left, val);
        }
        //IF THE VALUE OF ROOT IS SMALLER THEN THE VAL
        //GO TO RIGHT
        //AND CALL INSERT METHOD (IF NULL ADD A NEW NODE WITH THE VALUE VAL)
        else if(root.val <= val)
        {
            root.right = insertIntoBST(root.right, val);
        }


        //RETURN THE ROOT
        return root;
    }
    //https://www.youtube.com/watch?v=nVJI8sUlwSs
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        } else {
            if (val <= root.val) {
                if (root.left == null) {
                    root.left = new TreeNode(val);
                } else {
                    insertIntoBST(root.left, val);
                }
            } else {
                if (root.right == null) {
                    root.right = new TreeNode(val);
                } else {
                    insertIntoBST(root.right, val);
                }
            }
        }

        return root;
    }
}
