package trees;


import java.util.*;

public class FindModeInBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

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
        int max = Integer.MIN_VALUE;
        TreeNode prev = null;
        //https://leetcode.com/problems/find-mode-in-binary-search-tree/solutions/2760664/java-0ms-beats-100-o-1-space-solution/?languageTags=java
        //https://www.youtube.com/watch?v=1FJDyBSfEbo
        //https://www.geeksforgeeks.org/frequent-element-array/
        //https://leetcode.com/discuss/interview-question/963428/Google-or-Phone-or-Most-frequent-element-in-a-BST


        public int[] findMode(TreeNode root) {
            List<Integer> modes = new ArrayList<>();

            inorderTraversal(root, modes); //will print node in a sorted way

            int[] res = new int[modes.size()];
            int index = 0;
            for (int a : modes) {
                res[index++] = a;
            }
            return res;
        }

        private void inorderTraversal(TreeNode root, List<Integer> modes) {
            if (root == null) return;

            inorderTraversal(root.left, modes);

            //print value
            if (prev != null) {
                if (prev.val == root.val) {
                    count++;
                } else {
                    count = 1;
                }
            }

            if (count > max) {
                max = count;
                modes.clear();
                modes.add(root.val);
            } else if (count == max) {
                modes.add(root.val);
            }

            prev = root;

            inorderTraversal(root.right, modes);

        }

        int maxCount = 1;

        public int[] findMode2(TreeNode root) {
            Map<Integer, Integer> map = new HashMap<>();
            findMode(root, map);

            int[] result = new int[map.size()];
            int index = 0;
            for (int key : map.keySet()) {
                if (map.get(key) == maxCount) {
                    result[index++] = key;
                }
            }
            return Arrays.copyOf(result, index);
        }

        private void findMode(TreeNode root, Map<Integer, Integer> map) {
            if (root == null) {
                return;
            } else if (map.containsKey(root.val)) {
                int count = map.get(root.val) + 1;
                map.put(root.val, count);
                maxCount = Math.max(maxCount, count);
            } else {
                map.put(root.val, 1);
            }

            findMode(root.left, map);
            findMode(root.right, map);
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
         *         printInorder(root.left)
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
         *         printInorder(root.right)
         *     }
         *
         *     preorder(root)
         *     return ans
         * };
         */
    }
}
