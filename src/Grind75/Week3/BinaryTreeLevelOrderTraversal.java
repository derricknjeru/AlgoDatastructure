package Grind75.Week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    //https://leetcode.com/problems/binary-tree-level-order-traversal/

    public static void main(String[] args) {

    }

    /**
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     * <p>
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its level order traversal as:
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     */


    // Definition for a binary tree node.

    class Solution {
        //Example ->https://www.youtube.com/watch?v=86g8jAQug04
        //ANS https://www.youtube.com/watch?v=XZnWETlZZ14
        /**
         * The given code is for a breadth-first search (BFS) traversal of a binary tree to find the right side view of the tree. The time complexity of this algorithm is O(N), where N is the number of nodes in the binary tree.
         *
         * In the worst case, the algorithm visits every node once, which gives us a linear time complexity. The while loop iterates until the queue is empty, and in each iteration, it processes all the nodes at the current level. The inner for loop iterates over the nodes at the current level, which takes O(size) time, where size is the number of nodes at the current level.
         *
         * In the binary tree, the number of nodes at each level can vary, but in the worst case scenario, the tree is a complete binary tree, and each level contains the maximum number of nodes possible. In such a case, the size of the queue will be maximum at each level, and the total number of iterations in the while loop will be proportional to the total number of nodes in the tree.
         *
         * Therefore, the time complexity of the algorithm is O(N), where N is the number of nodes in the binary tree.
         */

        /**
         * Bfs:
         * <p>
         * Time complexity is O(|V|), where |V| is the number of nodes. You need to traverse all nodes.
         * Space complexity is O(|V|) as well - since at worst case you need to hold all vertices in the queue.
         *
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder2(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);


            while (!q.isEmpty()) {
                int size = q.size(); //size of the queue at this point....e.g . first level one and second level 2

                System.out.println("The size is" + size);

                //store current level nodes in a list
                List<Integer> currentLevel = new ArrayList<>();

                //iterate through a current level
                for (int i = 0; i < size; i++) {
                    //get current node
                    TreeNode currentNode = q.poll();
                    if (currentNode != null) {
                        currentLevel.add(currentNode.val);

                        if (currentNode.left != null) {
                            q.add(currentNode.left);
                        }

                        if (currentNode.right != null) {
                            q.add(currentNode.right);
                        }
                    }

                }
                result.add(currentLevel);

            }

            return result;
        }

        public List<List<Integer>> levelOrder(TreeNode root) {

            List<List<Integer>> result = new ArrayList<>();

            if (root == null) {
                return result;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);

            while (!q.isEmpty()) {

                List<Integer> currentNodeList = new ArrayList<>();
                int size = q.size();

                while (size > 0) {

                    TreeNode currentNode = q.poll();
                    currentNodeList.add(currentNode.val);

                    if (currentNode.left != null) {
                        q.add(currentNode.left);
                    }

                    if (currentNode.right != null) {
                        q.add(currentNode.right);
                    }
                    size--;
                }

                result.add(currentNodeList);

            }

            return result;


        }


    }
}
