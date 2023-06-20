package CodingInterviewPatterns.Recursion;

public class SearchInBST {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
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
}
