package trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwoSumBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: root = [5,3,6,2,4,null,7], k = 9
     * Output: true
     * Example 2:
     * <p>
     * <p>
     * Input: root = [5,3,6,2,4,null,7], k = 28
     * Output: false
     * <p>
     * <p>
     * Constraints:
     * <p>
     * The number of nodes in the tree is in the range [1, 104].
     * -104 <= Node.val <= 104
     * root is guaranteed to be a valid binary search tree.
     * -105 <= k <= 105
     */

    List<Integer> sortedList;

    // TC: O(n), SC: O(n)
    public boolean findTarget(TreeNode root, int k) {
        sortedList = new ArrayList<>();

        inOrderTraversal(root);

        int i = 0;
        int j = sortedList.size() - 1;

        while (i < j) {
            int target = sortedList.get(i) + sortedList.get(j);

            if (target == k) {
                return true;
            }

            if (target >= k) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }

    private void inOrderTraversal(TreeNode root) {

        if (root == null) return;

        //traverse left tree
        inOrderTraversal(root.left);

        //visit root
        sortedList.add(root.val);

        //traverse right
        inOrderTraversal(root.right);
    }


    // TC: O(n), SC: O(n)
    public boolean findTarget2(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return preOrderTraversal(set,root,k);
    }

    private boolean preOrderTraversal( Set<Integer>set, TreeNode root, int k){

        if(root==null) return false;

        //visit root
        int target = k - root.val;
        if(set.contains(target)) return true;
        set.add(root.val);

        //traverse right
        return preOrderTraversal(set,root.right, k) ||  //traverse left tree
                preOrderTraversal(set,root.left,k);
    }
}
