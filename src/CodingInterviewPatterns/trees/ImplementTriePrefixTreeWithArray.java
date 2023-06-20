package CodingInterviewPatterns.trees;

public class ImplementTriePrefixTreeWithArray {
    public class TrieNode {

        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }


    TrieNode root;

    public ImplementTriePrefixTreeWithArray() {
        root = new TrieNode();
    }

    public void insert(String word) {
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

        current.isEnd = true;

    }

    public boolean search(String word) {

        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
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

        for (int i = 0; i < prefix.length(); i++) {

            char c = prefix.charAt(i);

            TrieNode node = current.children[c - 'a'];

            if (node == null) {
                return false;
            }

            current = node;
        }

        return true;
    }

}
