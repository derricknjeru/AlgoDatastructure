package CodingInterviewPatterns.HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtrees {
    //https://leetcode.com/explore/learn/card/hash-table/185/hash_table_design_the_key/1127/
    //https://www.youtube.com/watch?v=RPbLhWp-hwg
    List<TreeNode> result;
    Map<String, Integer> map;

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        result = new ArrayList<>();

        map = new HashMap<>();

        postOrder(root);

        return result;

    }

    private String postOrder(TreeNode node) {

        //base case
        if (node == null) {
            return "&";
        }

        String res = node.val + " " + postOrder(node.left) + " " + postOrder(node.right);

        map.put(res, map.getOrDefault(res, 0) + 1);

        if (map.get(res) == 2)
            result.add(node);


        return res;
    }

}
