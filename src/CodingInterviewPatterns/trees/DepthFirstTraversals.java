package CodingInterviewPatterns.trees;


// Java program for different tree traversals


import java.util.ArrayList;
import java.util.List;

public class DepthFirstTraversals {
    //https://stackoverflow.com/questions/9844193/what-is-the-time-and-space-complexity-of-a-breadth-first-and-depth-first-tree-tr
   //https://www.geeksforgeeks.org/dfs-traversal-of-a-tree-using-recursion/
    /* Class containing left and right child of current
       node and key value*/
    static class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // Root of Binary Tree
    Node root;

    DepthFirstTraversals() {
        root = null;
    }

    /**
     * DFS:
     * <p>
     * Time complexity is again O(|V|), you need to traverse all nodes.
     * Space complexity - depends on the implementation, a recursive implementation can have a O(h) space complexity [worst case], where h is the maximal depth of your tree.
     * Using an iterative solution with a stack is actually the same as BFS, just using a stack instead of a queue - so you get both O(|V|) time and space complexity.
     * <p>
     * (*) Note.md.md that the space complexity and time complexity is a bit different for a tree than for a general graphs becase you do not need to maintain a visited set for a tree, and |E| = O(|V|), so the |E| factor is actually redundant.
     *
     * @param node
     */

    /* Given a binary tree, print its nodes according to the
      "bottom-up" postorder traversal. */
    void printPostorder(Node node) {
        if (node == null)
            return;

        // first recur on left subtree
        if (root.left != null) {
            printPostorder(node.left);
        }
        // then recur on right subtree
        if (root.right != null) {
            printPostorder(node.right);
        }

        // now deal with the node
        System.out.print(node.key + " "); // print myself //root
    }

    /* Given a binary tree, print its nodes in inorder*/
    void printInorder(Node node) {
        if (node == null)
            return;

        /* first recur on left child */
        if (root.left != null) {
            printInorder(node.left);
        }
        /* then print the data of node */
        System.out.print(node.key + " "); // print myself //root

        /* now recur on right child */
        if (root.right != null) {
            printInorder(node.right);
        }
    }

    /* Given a binary tree, print its nodes in preorder*/
    void printPreorder(Node node) {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.key + " "); // print myself //root

        /* then recur on left sutree */
        if (root.left != null) {
            printPreorder(node.left);
        }
        /* now recur on right subtree */
        if (root.right != null) {
            printPreorder(node.right);
        }
    }

    // Wrappers over above recursive functions
    void printPostorder() {
        printPostorder(root);
    }

    void printInorder() {
        printInorder(root);
    }

    void printPreorder() {
        printPreorder(root);
    }

    // Driver method
    public static void main(String[] args) {
        DepthFirstTraversals tree = new DepthFirstTraversals();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("Preorder traversal of binary tree is ");
        tree.printPreorder();

        System.out.println("\nInorder traversal of binary tree is ");
        tree.printInorder();

        System.out.println("\nPostorder traversal of binary tree is ");
        tree.printPostorder();

        List<Integer> result = new ArrayList<>();
        result.stream().mapToInt(Integer::intValue).toArray();
    }
}

