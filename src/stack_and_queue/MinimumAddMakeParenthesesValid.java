package stack_and_queue;

public class MinimumAddMakeParenthesesValid {
    /**
     * A parentheses string is valid if and only if:
     * <p>
     * It is the empty string,
     * It can be written as AB (A concatenated with B), where A and B are valid strings, or
     * It can be written as (A), where A is a valid string.
     * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
     * <p>
     * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
     * Return the minimum number of moves required to make s valid.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "())"
     * Output: 1
     * Example 2:
     * <p>
     * Input: s = "((("
     * Output: 3
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 1000
     * s[i] is either '(' or ')'.
     */
    //https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
    //https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/solution/
    public int minAddToMakeValid(String s) {

        if (s == null || s.isEmpty()) return 0;

        int open = 0, closing = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                open++;
            else if (c == ')')
                if (open > 0)
                    open--;
                else
                    closing++;
        }
        return open + closing;
    }

    public int minAddToMakeValidReturnString(String s) {

        if (s == null || s.isEmpty()) return 0;

        int open = 0, closing = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                open++;
            else if (c == ')')
                if (open > 0)
                    open--;
                else
                    closing++;
        }

        int ans = open + closing;

        while (open-- > 0) {
            s = s + ')';
        }

        while (closing-- > 0) {
            s = '(' + s;
        }

        System.out.println(s);

        return ans;
    }

}
