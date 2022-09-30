package hackerrack;

import java.util.*;
import java.util.LinkedList;

class NodeT {
    NodeT left, right;
    int data;

    NodeT(int data) {
        this.data = data;
        left = right = null;
    }
}

class LevelOrderTraverse {

    static void levelOrder(NodeT root) {
        //Write your code here
        Queue<NodeT> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size(); //size of the queue at this point....e.g . first level one and second level 2
            //iterate through a current level
            for (int i = 0; i < size; i++) {
                //get current node
                NodeT currentNode = q.poll();
                if (currentNode != null) {
                    System.out.print(currentNode.data + " ");
                }

                if (currentNode != null && currentNode.left != null) {
                    q.add(currentNode.left);
                }

                if (currentNode != null && currentNode.right != null) {
                    q.add(currentNode.right);
                }
            }
        }
    }

    public static NodeT insert(NodeT root, int data) {
        if (root == null) {
            return new NodeT(data);
        } else {
            NodeT cur;
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

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        NodeT root = null;
        while (T-- > 0) {
            int data = sc.nextInt();
            root = insert(root, data);
        }
        levelOrder(root);
    }
}
