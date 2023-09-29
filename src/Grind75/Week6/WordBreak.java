package Grind75.Week6;

import java.util.*;

public class WordBreak {
    //https://www.youtube.com/watch?v=hLQYQ4zj0qg&ab_channel=GeeksforGeeks
    //https://leetcode.com/problems/word-break/
    public static void main(String[] args) {
        Solution solution = new Solution();

        String s = "leetcode";
        List<String> wordDict = new ArrayList<>(Arrays.asList("leet", "code"));

        boolean canBreak = solution.wordBreak(s, wordDict);
        System.out.println("Can break: " + canBreak);
    }


    static class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordSet = new HashSet<>(wordDict);
            Map<String, Boolean> memo = new HashMap<>();
            return dfs(s, wordSet, memo);
        }

        // Time complexity: O(2^N), where N is the length of the input string.
        private boolean dfs(String s, Set<String> wordSet, Map<String, Boolean> memo) {
            // Base case: an empty string can always be segmented
            if (s.isEmpty()) {
                return true;
            }

            // Check if result is already memoized
            if (memo.containsKey(s)) {
                return memo.get(s);
            }

            // Explore different lengths of substrings
            for (int i = 1; i <= s.length(); i++) {
                String prefix = s.substring(0, i);

                // Check if the prefix is in the wordSet and the remaining part is segmentable
                if (wordSet.contains(prefix) && dfs(s.substring(i), wordSet, memo)) {
                    memo.put(s, true); // Memorize the result
                    return true;
                }
            }

            memo.put(s, false); // Memorize the result
            return false;
        }
    }

    static class Solution2 {
        static final int ALPHABET_SIZE = 26;

        public boolean wordBreak(String s, List<String> wordDict) {
            TrieNode root = new TrieNode();

            // Build the trie with words from wordDict
            for (String word : wordDict) {
                insert(root, word);
            }

            // Memoization to store results of subproblems
            HashMap<String, Boolean> memo = new HashMap<>();

            // Start the DFS to check if the string can be broken down
            return dfs(s, root, memo);
        }

        private boolean dfs(String str, TrieNode root, HashMap<String, Boolean> memo) {
            int size = str.length();

            // Base case: an empty string can be broken down
            if (size == 0) {
                return true;
            }

            // If result for this string is already computed, return it
            if (memo.containsKey(str)) {
                return memo.get(str);
            }

            // Try breaking down the string into prefixes
            for (int i = 1; i <= size; i++) {
                String prefix = str.substring(0, i);
                String suffix = str.substring(i);

                // Check if the prefix is in the dictionary and
                // recursively check the remaining suffix
                if (search(root, prefix) && dfs(suffix, root, memo)) {
                    memo.put(suffix, true);  // Memoize the result for the suffix
                    return true;
                }
            }

            memo.put(str, false);  // Memoize the result for the current string
            return false;
        }

        static void insert(TrieNode root, String key) {
            TrieNode pCrawl = root;

            for (int i = 0; i < key.length(); i++) {
                int index = key.charAt(i) - 'a';
                if (pCrawl.children[index] == null) {
                    pCrawl.children[index] = new TrieNode();
                }
                pCrawl = pCrawl.children[index];
            }

            // Mark last node as leaf
            pCrawl.isEndOfWord = true;
        }

        static boolean search(TrieNode root, String key) {
            TrieNode pCrawl = root;

            for (int i = 0; i < key.length(); i++) {
                int index = key.charAt(i) - 'a';
                if (pCrawl.children[index] == null) {
                    return false;
                }
                pCrawl = pCrawl.children[index];
            }
            return pCrawl != null && pCrawl.isEndOfWord;
        }

        static class TrieNode {
            TrieNode children[];
            boolean isEndOfWord;

            TrieNode() {
                children = new TrieNode[ALPHABET_SIZE];
                Arrays.fill(children, null);
                isEndOfWord = false;
            }
        }
    }

    class Solution3 {
        public boolean wordBreak(String s, List<String> wordDict) {
            int n = s.length();
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;

            for (int len = 1; len <= n; len++) {
                for (int i = 0; i < len; i++) {
                    if (dp[i] && wordDict.contains(s.substring(i, len))) {
                        dp[len] = true;
                        break;
                    }
                }
            }
            return dp[n];
        }

    }

}
