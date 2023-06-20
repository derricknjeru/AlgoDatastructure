package CodingInterviewPatterns.trees;

public class SortedArrayToBST {
    public static void main(String[] args) {
        //https://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/
        //https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/631/

        /**
         * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
         *
         * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
         *
         * Example:
         *
         * Given the sorted array: [-10,-3,0,5,9],
         *
         * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
         *
         *       0
         *      / \
         *    -3   9
         *    /   /
         *  -10  5
         */

    }

    public class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int d) {
            val = d;
            left = right = null;
        }
    }
    //https://www.youtube.com/watch?v=12omz-VAyRk
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return constructedBST(nums, 0, nums.length - 1);
    }

    private TreeNode constructedBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;

        TreeNode currentNode = new TreeNode(nums[mid]);

        currentNode.left = constructedBST(nums, left, mid - 1);
        currentNode.right = constructedBST(nums, mid + 1, right);

        return currentNode;
    }
}
