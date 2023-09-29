package CodingInterviewPatterns.backtracking;

public class RegularExpressionMatching {
    //https://leetcode.com/problems/regular-expression-matching/description/
    //https://www.youtube.com/watch?v=HAA8mgxlov8&ab_channel=NeetCode
    static class Solution {
        public boolean isMatch(String s, String p) {
            // Create a memoization cache to store intermediate results
            Boolean[][] cache = new Boolean[s.length() + 1][p.length() + 1];
            // Start the matching process using DFS
            return dfs(s, p, 0, 0, cache);
        }

        private boolean dfs(String s, String p, int i, int j, Boolean[][] cache) {
            // Check if the result for the current (i, j) combination is already cached
            if (cache[i][j] != null)
                return cache[i][j];

            // If j has reached the end of the pattern, check if i has also reached the end of the input
            if (j >= p.length())
                return i >= s.length();

            // Check if the current characters in s and p match or if p contains a wildcard '.'
            boolean match = (i < s.length()) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

            boolean result;

            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // Handle zero-or-more repetition of the preceding character in p
                result = (dfs(s, p, i, j + 2, cache) || (match && dfs(s, p, i + 1, j, cache)));
            } else {
                // Handle direct character match
                result = match && dfs(s, p, i + 1, j + 1, cache);
            }

            // Cache the result for the current (i, j) combination
            cache[i][j] = result;

            return result;
            // Time complexity: O(s.length() * p.length()) as we can potentially explore all combinations of i and j.
            // Space complexity: O(s.length() * p.length()) for the memoization cache.
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "aa";
        String p1 = "a";
        System.out.println(solution.isMatch(s1, p1)); // Output: false

        String s2 = "aa";
        String p2 = "a*";
        System.out.println(solution.isMatch(s2, p2)); // Output: true

        String s3 = "ab";
        String p3 = ".*";
        System.out.println(solution.isMatch(s3, p3)); // Output: true

        String s4 = "mississippi";
        String p4 = "mis*is*p*.";
        System.out.println(solution.isMatch(s4, p4)); // Output: false
    }

}
