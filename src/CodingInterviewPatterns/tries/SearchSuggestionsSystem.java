package CodingInterviewPatterns.tries;

import java.util.*;

public class SearchSuggestionsSystem {
    //https://www.youtube.com/watch?v=PLNDfB0Vg9Y&ab_channel=FisherCoder
    //https://leetcode.com/problems/search-suggestions-system/editorial/
    //https://leetcode.com/problems/search-suggestions-system/description/
    //

    /**
     * Complexity Analysis
     * <p>
     * Time complexity : O(M) to build the trie where M is total number of characters in products For each prefix we find its representative node in O(len(prefix))O(\text{len(prefix)})O(len(prefix)) and dfs to find at most 3 words which is an O(1) operation. Thus the overall complexity is dominated by the time required to build the trie.
     * <p>
     * In Java there is an additional complexity of (m^2) due to Strings being immutable, here m is the length of searchWord.
     * Space complexity : O(26n)=O(n). Here n is the number of nodes in the trie. 26 is the alphabet size.
     * Space required for output is O(m) where m is the length of the search word.
     * <p>
     * 268. Search Suggestions System
     * Medium
     * 4.1K
     * 196
     * Companies
     * You are given an array of strings products and a string searchWord.
     * <p>
     * Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
     * <p>
     * Return a list of lists of the suggested products after each character of searchWord is typed.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
     * Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
     * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
     * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
     * After typing mou, mous and mouse the system suggests ["mouse","mousepad"].
     * Example 2:
     * <p>
     * Input: products = ["havana"], searchWord = "havana"
     * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
     * Explanation: The only word "havana" will be always suggested while typing the search word.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= products.length <= 1000
     * 1 <= products[i].length <= 3000
     * 1 <= sum(products[i].length) <= 2 * 104
     * All the strings of products are unique.
     * products[i] consists of lowercase English letters.
     * 1 <= searchWord.length <= 1000
     * searchWord consists of lowercase English letters.
     */


    class Solution {
        public List<List<String>> suggestedProducts(String[] products, String searchWord) {

            TrieNode root = new TrieNode();
            for (String product : products) {
                insertWord(root, product);
            }

            List<List<String>> res = new ArrayList<>();
            int n = searchWord.length();

            for (int i = 1; i <= n; i++) {
                String substring = searchWord.substring(0, i);
                res.add(searchPrefix(root, substring));
            }
            return res;
        }

        private void searchRecursively(TrieNode current, String word, List<String> ans) {
            if (ans.size() == 3) return;

            if (current.isWord) {
                ans.add(word);
            }

            // Run DFS on all possible paths.
            for (char c = 'a'; c <= 'z'; c++) {
                if (current.children[c - 'a'] != null) {
                    searchRecursively(current.children[c - 'a'], word + c, ans);
                }
            }
        }

        private List<String> searchPrefix(TrieNode root, String word) {
            TrieNode current = root;
            List<String> res = new ArrayList<>();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode node = current.children[c - 'a'];
                if (node == null) {
                    return res;
                }
                current = node;
            }

            searchRecursively(current, word, res);

            return res;
        }

        private void insertWord(TrieNode root, String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode node = current.children[c - 'a'];
                if (node == null) {
                    node = new TrieNode();
                    current.children[c - 'a'] = node;
                }
                current = node;
            }
            current.isWord = true;
        }

        class TrieNode {
            boolean isWord;
            TrieNode[] children;

            TrieNode() {
                isWord = false;
                children = new TrieNode[26];
            }
        }
    }

    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                current.children.putIfAbsent(c, new TrieNode());
                current = current.children.get(c);
            }
            current.isWord = true;
        }

        public TrieNode searchPrefix(String prefix) {
            TrieNode current = root;
            for (char c : prefix.toCharArray()) {
                TrieNode node = current.children.get(c);
                if (node == null) return null;
                current = node;
            }
            return current;
        }

        public void searchWordDfs(TrieNode current, String prefix, List<String> ans) {
            if (current == null) return;

            if (ans.size() == 3) return;

            if (current.isWord) {
                ans.add(prefix);
            }

            for (char c : current.children.keySet()) {
                searchWordDfs(current.children.get(c), prefix + c, ans);
            }
        }
    }

    static class TrieNode {
        boolean isWord;
        TreeMap<Character, TrieNode> children;

        TrieNode() {
            isWord = false;
            children = new TreeMap<>();
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie trie = new Trie();
        for (String product : products) {
            trie.insert(product);
        }

        List<List<String>> result = new ArrayList<>();
        String prefix = "";
        for (char c : searchWord.toCharArray()) {
            prefix += c;
            TrieNode current = trie.searchPrefix(prefix);
            List<String> ans = new ArrayList<>();
            trie.searchWordDfs(current, prefix, ans);
            result.add(ans);
        }

        return result;
    }

    public static void main(String[] args) {
        SearchSuggestionsSystem solution = new SearchSuggestionsSystem();
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";

        List<List<String>> suggestions = solution.suggestedProducts(products, searchWord);

        // Print the suggestions
        for (List<String> list : suggestions) {
            System.out.println(list);
        }
    }
}


