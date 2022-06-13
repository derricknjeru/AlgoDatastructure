package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
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
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        //Example ->https://www.youtube.com/watch?v=86g8jAQug04
        //ANS https://www.youtube.com/watch?v=XZnWETlZZ14
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

                    currentLevel.add(currentNode.val);

                    if (currentNode.left != null) {
                        q.add(currentNode.left);
                    }

                    if (currentNode.right != null) {
                        q.add(currentNode.right);
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

    static void levelOrder(Node root) {
        //Write your code here

    }
}
