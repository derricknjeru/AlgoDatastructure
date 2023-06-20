package CodingInterviewPatterns.trees;


import java.util.*;

class TreeLevelOrderTraversal {
    //https://www.geeksforgeeks.org/level-order-tree-traversal/
    //https://www.hackerrank.com/challenges/tree-level-order-traversal/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
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
    /*

    class Node
        int data;
        Node left;
        Node right;
    */
    public static void levelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        StringBuilder sb = new StringBuilder();
        String prefix = "";

        while(!q.isEmpty()){
            Node currentNode = q.poll();
            sb.append(prefix);
            prefix = " ";
            sb.append(currentNode.data);

            System.out.print(currentNode.data+" ");


            if(currentNode.left!=null){
                q.add(currentNode.left);
            }

            if(currentNode.right!=null){
                q.add(currentNode.right);
            }
        }

        //System.out.print(sb.toString()); //Testing sb builder

    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
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
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        levelOrder(root);
    }
}
