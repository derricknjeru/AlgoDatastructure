package CodingInterviewPatterns.tries;

import java.util.*;

public class SearchSuggestionsSystem {

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

        public void searchWordDfs(TrieNode current, String word, List<String> ans) {
            if (current == null) return;

            if (ans.size() == 3) return;

            if (current.isWord) {
                ans.add(word);
            }

            for (char c : current.children.keySet()) {
                searchWordDfs(current.children.get(c), word + c, ans);
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


