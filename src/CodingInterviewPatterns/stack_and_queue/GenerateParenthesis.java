package CodingInterviewPatterns.stack_and_queue;

import java.util.ArrayList;
import java.util.List;


public class GenerateParenthesis {
    //https://www.youtube.com/watch?v=s9fokUqJ76A
    //https://www.youtube.com/watch?v=WW1rYrR3tTI
    //https://leetcode.com/problems/generate-parentheses/description/

    /**
     * The time complexity of the generateParenthesis function in the provided solution is O(4^n / sqrt(n)), where n is the number of pairs of parentheses.
     * <p>
     * The reason for this time complexity is as follows:
     * <p>
     * In each recursive call of the backtrack function, we have two choices: either add an opening parenthesis or add a closing parenthesis. Therefore, at each level of the recursion tree, we have two branches.
     * Since we need to generate all combinations of well-formed parentheses, the maximum depth of the recursion tree will be 2n, where n is the number of pairs of parentheses.
     * As a result, the total number of nodes in the recursion tree will be 2^(2n) = 4^n.
     * Additionally, at each node, we append a character to the current string, which takes O(1) time.
     * Therefore, the overall time complexity is O(4^n), considering all the nodes in the recursion tree.
     * In addition to the branching factor, the sqrt(n) factor in the time complexity comes from the string concatenation operation. In each recursive call, we concatenate a character to the current string, and the length of the string is bounded by 2n. The time complexity of string concatenation can be approximated as O(k^2), where k is the length of the resulting string. Since k ranges from 0 to 2n, the overall time complexity due to string concatenation is O((2n)^2) = O(4n^2). By ignoring constant factors, this simplifies to O(n^2). Therefore, the final time complexity is O(4^n / sqrt(n)).
     * <p>
     * It's worth noting that this time complexity is an upper bound and represents the worst-case scenario. In practice, the actual time taken may be significantly less, especially for small values of n.
     *
     * @param
     * @return
     */

    static class Solution {
        List<String> res;

        public List<String> generateParenthesis(int n) {
            //only add open parenthesis if open < n
            //only add closing parenthesis if closed < open;
            //base case valid if open ==closed && open==n

            res = new ArrayList<>();
            backtrack("", 0, 0, n);
            return res;
        }

        private void backtrack(String current, int openN, int closeN, int n) {
            //base case
            if (openN == closeN && openN == n) {
                res.add(current);
                return;
            }

            if (openN < n) {
                backtrack(current + "(", openN + 1, closeN, n);
            }

            if (closeN < openN) {
                backtrack(current + ")", openN, closeN + 1, n);
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        // Example 1
        int n1 = 3;
        List<String> result1 = s.generateParenthesis(n1);
        System.out.println(result1);  // Output: ["((()))","(()())","(())()","()(())","()()()"]

        // Example 2
        int n2 = 1;
        List<String> result2 = s.generateParenthesis(n2);
        System.out.println(result2);  // Output: ["()"]
    }


}
