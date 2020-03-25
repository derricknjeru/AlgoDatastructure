package trees;

import java.util.Scanner;

public class LowestCommonAncestor {
    // A binary tree node
    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }

        public int getHeight(Node root) {
            if (root == null || isLeaf(root)) {
                return 0;
            } else {
                //recursively get the height
                return 1 + Math.max(getHeight(root.left), getHeight(root.right));
            }
        }

        private boolean isLeaf(Node root) {
            return root.left == null && root.right == null;
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

        int height = root.getHeight(root);
        System.out.println("The height is::" + height);

        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        Node ans = lca(root, v1, v2);
        System.out.println("The lowest common Ancestor is::" + ans.data);

    }

    private static Node lca(Node root, int v1, int v2) {
        while (root != null) {
            // If both n1 and n2 are greater than root, then LCA lies in right
            if (root.data < v1 && root.data < v2) {
                root = root.right;

                // If both n1 and n2 are smaller than root, then LCA lies in left
            } else if (root.data > v1 && root.data > v2) {
                root = root.left;
            } else {
                break;
            }

        }
        return root;
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
}
