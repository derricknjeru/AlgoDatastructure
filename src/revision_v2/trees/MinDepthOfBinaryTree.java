package revision_v2.trees;

public class MinDepthOfBinaryTree {


    //https://www.youtube.com/watch?v=JrrPcXix8zo
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        int minLeft = minDepth(root.left);

        int minRight = minDepth(root.right);

        //skewedd tree
        if (minLeft == 0 || minRight == 0) {
            return 1 + Math.max(minLeft, minRight);
        }

        return Math.min(minLeft, minRight) + 1;
    }
}


