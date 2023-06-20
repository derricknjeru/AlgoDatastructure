package CodingInterviewPatterns.trees;

public class ImplementTriePrefixTree2 {
    public class TrieNode {

        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }


    TrieNode root;

    public ImplementTriePrefixTree2() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            TrieNode node = current.children[c - 'a'];
            if (node == null) {
                node = new TrieNode();
                current.children[c - 'a'] = node;
            }

            current = node;

        }

        current.isEnd = true;

    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            TrieNode node = current.children[c - 'a'];
            if (node == null) {
                return false;
            }

            current = node;

        }

        return current.isEnd;

    }

    public boolean startsWith(String prefix) {

        TrieNode current = root;
        for (char c : prefix.toCharArray()) {

            TrieNode node = current.children[c - 'a'];

            if (node == null) {
                return false;
            }

            current = node;

        }

        return true;
    }
}
