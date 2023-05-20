package trees;

import java.util.HashSet;
import java.util.Set;

public class FindPairBST {
    /**
     * The time complexity of the modified code remains the same as before, which is O(n). Here's the breakdown of the time complexity:
     *
     * In-order Traversal: The hasPair method performs an in-order traversal of the BST. In the worst case, it visits all the nodes in the BST once, resulting in a time complexity of O(n), where n is the number of nodes in the BST.
     *
     * Set Operations: During the traversal, set operations such as elements.contains(complement) and elements.add(node.val) are performed. The average case time complexity for these operations in a HashSet is O(1).
     *
     * Therefore, the overall time complexity of the algorithm is O(n), where n is the number of nodes in the BST.
     *
     * It's worth noting that the time complexity assumes a balanced BST. In the worst case scenario of an unbalanced BST, the time complexity can reach O(n^2) if the tree is skewed and the in-order traversal takes O(n) time for each node. However, for a balanced BST, the time complexity remains O(n).
     *
     * I apologize for any confusion caused by the previous responses. This revised analysis accurately represents the time complexity of the code.
     */
    /**
     * The space complexity of the modified code is O(n), where n is the number of nodes in the BST. Here's the breakdown of the space complexity:
     *
     * Recursive Stack Space: During the in-order traversal performed by the hasPair method, recursive calls are made. Each recursive call consumes space on the stack. The maximum depth of the recursion is the height of the BST, which can be O(n) in the worst case for an unbalanced BST. However, for a balanced BST, the height is logarithmic, approximately O(log n) where n is the number of nodes.
     *
     * HashSet Space: The elements set is used to store the elements during the traversal. The space required by the HashSet is proportional to the number of distinct elements in the BST. In the worst case, when all the elements in the BST are distinct, the space complexity can be O(n).
     *
     * Pair Array: The pair array of size 2 is used to store the pair with the given sum. The space complexity for this array is constant, O(1).
     *
     * Therefore, the overall space complexity is O(n), considering the worst-case scenario of an unbalanced BST with distinct elements. In the case of a balanced BST or when the elements are not all distinct, the space complexity may be lower.
     *
     * Please note that the space complexity does not include the space required to store the input BST itself, as it is assumed to be given and not part of the space used by the algorithm.
     * @param root
     * @param target
     * @return
     */

    // Find a pair with the given sum in a BST
// Time complexity: O(n)
    public static int[] findPair(TreeNode root, int target) {
        // Step 1: Perform in-order traversal and store elements in a set
        Set<Integer> elements = new HashSet<>();
        int[] pair = new int[2];
        if (hasPair(root, elements, target, pair)) {
            return pair;
        }

        // Step 3: No pair found
        return null;
    }

    private static boolean hasPair(TreeNode node, Set<Integer> elements, int target, int[] pair) {
        if (node == null) {
            return false;
        }

        if (hasPair(node.left, elements, target, pair)) {
            return true;
        }

        int complement = target - node.val;
        if (elements.contains(complement)) {
            pair[0] = node.val;
            pair[1] = complement;
            return true;
        }

        elements.add(node.val);

        return hasPair(node.right, elements, target, pair);
    }

}
