package CodingInterviewPatterns.trees;

import java.util.Scanner;
//https://www.codesdope.com/blog/article/binary-tree-in-java-traversals-finding-height-of-n/
class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class BinaryTreeHeight {

    /*
    class Node
        int data;
        Node left;
        Node right;
    */
    public static int height(Node root) {
        // Write your code here.
        if (root == null || isLeaf(root)) {
            // height will be 0 if the node is leaf or null
            return 0;
        } else {
            //height of a node will be 1+ greater among height of right subtree and height of left subtree
            return 1 + Math.max(height(root.left), height(root.right));
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

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }

    public static boolean isLeaf(Node a) {
        return (a.right == null && a.left == null);
    }


}
