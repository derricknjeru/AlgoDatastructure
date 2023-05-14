package revision_v2.trees;

import java.util.ArrayList;
import java.util.List;

public class FindModeInBinarySearchTree {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-mode-in-binary-search-tree/


        //converting int to array
        //https://stackoverflow.com/questions/41520654/an-efficient-way-to-convert-listinteger-to-int-array-without-iteration/41528117
    }


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        int count = 1;
        int max = 0;
        Integer prev = null;

        public int[] findMode(TreeNode root) {
            List<Integer> modes = new ArrayList<>();

            traverse(root, modes);

            int[] result = new int[modes.size()];

            for (int i = 0; i < modes.size(); i++) {
                result[i] = modes.get(i);
            }

            return result;

        }

        private void traverse(TreeNode root, List<Integer> mode) {

            if (root == null) return;

            traverse(root.left, mode);

            if (prev != null) {
                if (prev == root.val) {
                    count++;
                } else {
                    count = 1;
                }

            }

            if (count > max) {
                mode.clear();
                mode.add(root.val);
                max = count;
            } else if (count == max) {
                mode.add(root.val);
            }


            prev = root.val;

            traverse(root.right, mode);

        }
    }
    //Javascript

    /**
     * var findMode = function(root) {
     *     let ans = []
     *     let count = 0, _max = 0, prev = 0
     *
     *     const preorder = (root) => {
     *         if(!root) return
     *
     *         preorder(root.left)
     *
     *         count = prev == root.val ? ++count : 1
     *
     *         if(count > _max) {
     *             ans = [root.val]
     *             _max = count
     *         }
     *
     *         else if(count === _max)
     *             ans.push(root.val)
     *
     *         prev = root.val
     *
     *         preorder(root.right)
     *     }
     *
     *     preorder(root)
     *     return ans
     * };
     */
}
