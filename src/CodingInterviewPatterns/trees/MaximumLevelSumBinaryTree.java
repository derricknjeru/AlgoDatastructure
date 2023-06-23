package CodingInterviewPatterns.trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumLevelSumBinaryTree {
    /**
     * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
     * <p>
     * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: root = [1,7,0,7,-8,null,null]
     * Output: 2
     * Explanation:
     * Level 1 sum = 1.
     * Level 2 sum = 7 + 0 = 7.
     * Level 3 sum = 7 + -8 = -1.
     * So we return the level with the maximum sum which is level 2.
     * Example 2:
     * <p>
     * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
     * Output: 2
     * <p>
     * <p>
     * Constraints:
     * <p>
     * The number of nodes in the tree is in the range [1, 104].
     * -105 <= Node.val <= 105
     * Accepted
     * 110,355
     * Submissions
     * 166,269
     */

    //https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
    public int maxLevelSum2(TreeNode root) {

        if (root == null) {
            return 0;
        }

        //Bfs
        Queue<TreeNode> pq = new LinkedList<>();
        pq.add(root);

        int level = 0;
        int ans = 1;
        int max = Integer.MIN_VALUE;

        while (!pq.isEmpty()) {

            int size = pq.size();
            int sum = 0;

            level += 1;

            for (int i = 0; i < size; i++) {
                TreeNode currNode = pq.poll();
                sum += currNode.val;

                if (currNode.left != null) {
                    pq.add(currNode.left);
                }

                if (currNode.right != null) {
                    pq.add(currNode.right);
                }

            }

            if (sum > max) {
                max = sum;
                ans = level;
            }

        }

        return ans;

    }

    public int maxLevelSum(TreeNode root) {

        if (root == null) {
            return 0;
        }

        //Bfs
        Queue<TreeNode> pq = new LinkedList<>();
        pq.add(root);

        int level = 0;
        HashMap<Integer, Integer> occurrences = new HashMap<>();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> occurrences.get(b) - occurrences.get(a));


        while (!pq.isEmpty()) {

            int size = pq.size();
            int sum = 0;

            level += 1;

            for (int i = 0; i < size; i++) {
                TreeNode currNode = pq.poll();
                assert currNode != null;
                sum += currNode.val;

                if (currNode.left != null) {
                    pq.add(currNode.left);
                }

                if (currNode.right != null) {
                    pq.add(currNode.right);
                }

            }

            occurrences.put(level, sum);

        }

        maxHeap.addAll(occurrences.keySet());

        return maxHeap.iterator().next();

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
