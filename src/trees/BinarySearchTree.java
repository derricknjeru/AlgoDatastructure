package trees;

import java.util.Scanner;

public class BinarySearchTree {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }

    }

    public static Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        } else {
            if (value <= root.data) {
                if (root.left == null) {
                    root.left = new Node(value);
                } else {
                    insert(root.left, value);
                }

            } else {
                if (root.right == null) {
                    root.right = new Node(value);
                } else {
                    insert(root.right, value);
                }
            }
        }
        return root;
    }

    public static boolean contains(Node root, int value) {
        if (value == root.data) {
            return true;
        } else if (value < root.data) {
            if (root.left == null) {
                return false;
            } else {
                return contains(root.left, value);
            }
        } else {
            if (root.right == null) {
                return false;
            } else {
                return contains(root.right, value);
            }
        }
    }

    public static void printInorder(Node root) {
        if (root == null) return;

        if (root.left != null) {
            /* first recur on left child */
            printInorder(root.left);
        }
        //print my own data.
        System.out.print(root.data + " -> ");

        /* now recur on right child */
        if (root.right != null) {
            printInorder(root.right);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        System.out.println("Add " + t + " nodes");
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();

        printInorder(root);
    }
}
