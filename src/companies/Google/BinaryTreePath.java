package companies.Google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class BinaryTreePath {
    public static List<String> findPathDirections(TreeNode root, TreeNode A, TreeNode B) {
        List<String> pathA = new ArrayList<>();
        List<String> pathB = new ArrayList<>();

        if (!findPath(root, A, pathA) || !findPath(root, B, pathB))
            return new ArrayList<>(); // Either node A or node B is not present in the tree

        Collections.reverse(pathA);
        Collections.reverse(pathB);

        int i = 0;
        while (i < pathA.size() && i < pathB.size() && pathA.get(i).equals(pathB.get(i))) {
            i++;
        }

        List<String> path = new ArrayList<>();

        for (int j = pathA.size() - 1; j >= i; j--) {
            path.add(pathA.get(j));
        }

        for (int j = i; j < pathB.size(); j++) {
            if (pathB.get(j).equals("LEFT")) {
                path.add("RIGHT");
            } else {
                path.add("LEFT");
            }
        }

        return path;
    }

    private static boolean findPath(TreeNode root, TreeNode target, List<String> path) {
        if (root == null)
            return false;

        if (root == target || findPath(root.left, target, path) || findPath(root.right, target, path)) {
            path.add(root == target ? "UP" : root.left == target ? "LEFT" : "RIGHT");
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        // Create the binary tree
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(13);
        root.left.right = new TreeNode(9);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(19);
        root.right.right.right = new TreeNode(12);

        // Test case 1
        TreeNode A = root.left.left;
        TreeNode B = root.right.left;
        List<String> path1 = findPathDirections(root, A, B);
        System.out.println(path1);  // Output: [UP, UP, RIGHT, LEFT]

        // Test case 2
        A = root.left.right;
        B = root.left.left.left;
        List<String> path2 = findPathDirections(root, A, B);
        System.out.println(path2);  // Output: []
    }
}



