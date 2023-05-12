package trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinarySearchTree {
    //https://www.youtube.com/watch?v=oSWTXtMglKE&ab_channel=HackerRank
    //https://www.youtube.com/watch?v=COZK7NATh4k&ab_channel=mycodeschool
    //https://www.youtube.com/watch?v=gcULXE7ViZw&ab_channel=mycodeschool
    //https://leetcode.com/problems/insert-into-a-binary-search-tree/
    //https://leetcode.com/problems/search-in-a-binary-search-tree/description/
    // https://leetcode.com/problems/delete-node-in-a-bst/submissions/949222122/
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }

    }

    public static Node insert(Node root, int val) {
        //BASE CASE
        //ROOT == NULL
        //RETURN THE NODE WITH THA VALUE VAL
        if (root == null) {
            return new Node(val);
        } else {
            //IF THE VALUE ROOT IS GREATER THEN THE VAL
            //GO TO LEFT
            //AND CALL INSERT METHOD (IF NULL ADD A NEW NODE WITH THE VALUE VAL)
            if (val <= root.data) {
                root.left = insert(root.left, val);
            } else {
                //IF THE VALUE OF ROOT IS SMALLER THEN THE VAL
                //GO TO RIGHT
                //AND CALL INSERT METHOD (IF NULL ADD A NEW NODE WITH THE VALUE VAL)
                root.right = insert(root.right, val);
            }
        }
        return root;
    }

    public static boolean contains(Node root, int value) {
        if (root == null) {
            return false;
        } else if (value == root.data) {
            return true;
        } else if (value < root.data) {
            return contains(root.left, value);
        } else {
            return contains(root.right, value);
        }
    }

    public static Node searchBST(Node root, int val) {
        if (root == null) {
            return null;
        } else if (val == root.data) {
            return root;
        } else if (val < root.data) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    public static Node delete(Node root, int val) {
        if (root == null) {
            return null;
        } else if (val < root.data) {
            root.left = delete(root.left, val);
        } else if (val > root.data) {
            root.right = delete(root.right, val);
        } else {
            //we found data
            //case 1: No children
            if (root.left == null && root.right == null) {
                root = null; //root is set to null i.e. root at that leaf
            }
            //case 2: 1 child, only left child is null
            else if (root.left == null) {
                root = root.right; //make the right node as the new root of the subtree
            }
            //case 2: 1 child, only right child is null
            else if (root.right == null) {
                root = root.left; //make the left node as the new root of the subtree
            }
            //case 3: 2 children
            else {
                Node min = findMin(root.right); // This function returns min value in the subtree
                root.data = min.data; //set the data we were trying to delete to the minimum value
                root.right = delete(root.right, min.data);// deleting the minimum value from the right subtree of the current node

            }
        }
        return root;
    }

    private static Node findMin(Node root) {
        while (root.left != null) root = root.left;
        return root;
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

    public static void printLevelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        if (root == null) return;
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                Node node = q.poll();
                System.out.print("\n" + node.data + " -> ");
                if (node.left != null) {
                    q.add(node.left);
                } else if (node.right != null) {
                    q.add(node.right);
                }
                size--;
            }
            System.out.println("------");
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

        printLevelOrder(root);
    }
}
