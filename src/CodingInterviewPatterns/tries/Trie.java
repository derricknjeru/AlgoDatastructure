package CodingInterviewPatterns.tries;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/implement-trie-prefix-tree/
//https://www.youtube.com/watch?v=giiaIofn31A&ab_channel=MichaelMuinos

/**
 * Time complexity
 * Insert(): The time complexity is O(l), where l is the length of the word being inserted.
 * Search(): The time complexity is O(l), where l is the length of the word being searched.
 * Search prefix(): The time complexity is O(l), where l is the length of the prefix being searched.
 * Space complexity
 * Insert(): The space complexity is O(l) because, in the worst case, we will add l nodes in the trie.
 * Search(): The space complexity isO(1) because constant space is used while searching the trie.
 * Search prefix(): The space complexity is O(1) because constant space is used while searching the trie.
 */

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            TrieNode node = current.children.get(c);
            if (node == null) return null;
            current = node;
        }
        return current;
    }

    public boolean searchRecursively(String word) {
        TrieNode current = root;
        return searchRecursive(current, word, 0);
    }

    private boolean searchRecursive(TrieNode current, String word, int index) {

        if (index == word.length()) {
            return current.isEndOfWord;
        }

        char c = word.charAt(index);
        TrieNode node = current.children.get(c);

        if (node == null) {
            return false;
        }
        return searchRecursive(node, word, index + 1);
    }

    public void delete(String word) {
        deleteRecursive(root, word, 0);
    }

    private boolean deleteRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false; // Word doesn't exist in the Trie
            }
            current.isEndOfWord = false; // Mark as non-word
            return current.children.isEmpty(); // Return true if no more children exist
        }

        char ch = word.charAt(index);
        TrieNode nextNode = current.children.get(ch);
        if (nextNode == null) {
            return false; // Word doesn't exist in the Trie
        }

        boolean shouldDeleteCurrentNode = deleteRecursive(nextNode, word, index + 1);

        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.isEmpty();
        }

        return false;
    }
}