package CodingInterviewPatterns.trees;

import java.util.*;

public class BinarySearchTreeInsertion {


    static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }


    public static void preOrder(Node root) {

        if (root == null)
            return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

    /* Node is defined as :
    class Node
       int data;
       Node left;
       Node right;

       */
    //https://www.youtube.com/watch?v=nVJI8sUlwSs
    public static Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);
        } else {
            if (val <= root.data) {
                root.left = insert(root.left, val);
            } else {
                root.right = insert(root.right, val);
            }
        }
        return root;
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
        preOrder(root);
    }
}

