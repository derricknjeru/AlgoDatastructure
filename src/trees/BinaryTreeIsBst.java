package trees;

import java.util.Scanner;

public class BinaryTreeIsBst {
    //Soln and explanation.
    //https://www.youtube.com/watch?v=Z_-h_mpDmeg&t=2s&ab_channel=KevinNaughtonJr.
    //https://www.youtube.com/watch?v=MILxfAbIhrE&t=564s


    //Root of the Binary Tree
    Node root;

    //https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
    private boolean isBST(Node root, Node l, Node r) {
        // Base condition
        if (root == null)
            return true;

        // if left node exist then check it has
        // correct data or not i.e. left node's data
        // should be less than root's data
        if (l != null && root.data <= l.data)
            return false;

        // if right node exist then check it has
        // correct data or not i.e. right node's data
        // should be greater than root's data
        if (r != null && root.data >= r.data)
            return false;

        // check recursively for every node.
        return isBST(root.left, l, root) &&
                isBST(root.right, root, r);
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
     *     return validBST(root,null,null)
     * };
     *
     * function validBST(root, l, r){
     *     if(root == null){
     *        return true;
     *     }
     *
     *     if(l!=null && l.val >= root.val){
     *        return false;
     *     }
     *
     *     if(r!=null && r.val <=root.val){
     *        return false;
     *     }
     *
     *     return validBST(root.left,l,root) &&  validBST(root.right,root,r);
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
