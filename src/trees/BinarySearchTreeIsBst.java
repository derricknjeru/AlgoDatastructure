package trees;

import java.util.Scanner;

public class BinarySearchTreeIsBst {
    //Soln and explanation.
    //https://www.youtube.com/watch?v=MILxfAbIhrE&t=564s

    //Root of the Binary Tree
    Node root;

    public boolean helper(Node node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.data;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!helper(node.right, val, upper)) return false;
        if (!helper(node.left, lower, val)) return false;
        return true;
    }

    public boolean isValidBST(Node root) {
        return helper(root, null, null);
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
        BinarySearchTreeIsBst isBst = new BinarySearchTreeIsBst();
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
