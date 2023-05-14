package trees;

import java.util.Scanner;

public class BinaryTreeIsBst {
    //Soln and explanation.
    //https://www.youtube.com/watch?v=kR5AxWHa9nc
    //https://leetcode.com/problems/validate-binary-search-tree/description/


    //Root of the Binary Tree
    Node root;

    //https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/

    /**
     * Time Complexity: O(N), Where N is the number of nodes in the tree
     * Auxiliary Space: O(1), if Function Call Stack size is not considered, otherwise O(H) where H is the height of the tree
     *
     * @param root
     * @return
     */
    private boolean isBST(Node root, Integer min, Integer max) {
        // Base condition
        if (root == null) return true;

        // if left node exist then check it has
        // correct data or not i.e. left node's data
        // should be less than root's data
        if (min != null && root.data <= min) return false;

        // if right node exist then check it has
        // correct data or not i.e. right node's data
        // should be greater than root's data
        if (max != null && root.data >= max) return false;

        // check recursively for every node.
        return isBST(root.left, min, root.data) && //lower bound does not change
                isBST(root.right, root.data, max); //upper bound will not change(r) and data will be current root
    }

    public boolean isValidBST(Node root) {
        return isBST(root, null, null);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        BinaryTreeIsBst isBst = new BinaryTreeIsBst();
        if (isBst.isValidBST(root)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }


    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    //Javacript

    /**
     * var isValidBST = function(root) {
     * return validBST(root,null,null)
     * };
     * <p>
     * function validBST(root, l, r){
     * if(root == null){
     * return true;
     * }
     * <p>
     * if(l!=null && l.val >= root.val){
     * return false;
     * }
     * <p>
     * if(r!=null && r.val <=root.val){
     * return false;
     * }
     * <p>
     * return validBST(root.left,l,root) &&  validBST(root.right,root,r);
     * }
     */

    /* Class containing left and right child of current
        node and key value*/
    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }
}
