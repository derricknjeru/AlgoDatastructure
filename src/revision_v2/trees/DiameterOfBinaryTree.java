package revision_v2.trees;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int d) {
        val = d;
        left = right = null;
    }
}

public class DiameterOfBinaryTree {
    TreeNode root;

    public static void main(String[] args) {
        DiameterOfBinaryTree tree = new DiameterOfBinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);

        // Function Call
        System.out.println(
                "The diameter of given binary tree is : "
                        + tree.diameterOfBinaryTree(tree.root));
    }

    //https://www.youtube.com/watch?v=mWyhPJ1Ukvw&ab_channel=KnowledgeCenter


    //https://leetcode.com/problems/diameter-of-binary-tree/
    //https://leetcode.com/problems/maximum-depth-of-binary-tree/
    //https://leetcode.com/problems/validate-binary-search-tree/

    //Sometimes called width
    public int diameterOfBinaryTree(TreeNode root) {
        //base case
        if (root == null) {
            return 0;
        }

        /**Diameter through the root**/
        //lheight
        int lh = height(root.left);
        //rheight
        int rh = height(root.right);

        //Diameter through root
        int diameterTR = lh + rh + 1;

        /** Diameter is not through the root**/
        //left diameter
        int ld = diameterOfBinaryTree(root.left);

        //right diameter
        int rd = diameterOfBinaryTree(root.right);

        return Math.max(diameterTR, Math.max(ld, rd));


    }

    private int height(TreeNode node) {
        // base case tree is empty
        if (node == null)
            return 0;

        /** calculate height of each sub tree**/
        int lh = height(node.left);

        int rh = height(node.right);

        return Math.max(lh, rh) + 1;

    }
}
