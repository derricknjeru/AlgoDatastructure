package CodingInterviewPatterns.tries;

public class ImplementTrie {
    //https://leetcode.com/problems/implement-trie-prefix-tree/description/
    //https://www.youtube.com/watch?v=oobqoCJlHA0

    /**
     * The time complexity of the operations in the provided Trie implementation is as follows:
     * <p>
     * Insertion (insert):
     * <p>
     * When inserting a word of length L into the Trie, each character in the word is traversed once to find the appropriate location for insertion.
     * The insertion process takes O(L) time.
     * Search for a Word (search):
     * <p>
     * When searching for a complete word of length L in the Trie, each character in the word is traversed once to navigate through the Trie.
     * The search process takes O(L) time.
     * Search for Prefix (startsWith):
     * <p>
     * When searching for a prefix of length P in the Trie, each character in the prefix is traversed once to navigate through the Trie.
     * The search process takes O(P) time.
     * In each case, the time complexity is directly proportional to the length of the word or prefix being processed.
     * This makes Tries particularly efficient for tasks that involve words and strings,
     * where the search and insertion operations are based on the length of the input rather than the total number of words stored in the Trie.
     * <p>
     * The space complexity of the Trie is primarily determined by the number of nodes in the Trie,
     * which is influenced by the total number of words and the average length of the words. In the worst case,
     * where there are no shared prefixes among words,
     * the space complexity could be proportional to the total number of characters in all the words stored in the Trie
     */
    static class Trie {
        TrieNode root;

        public Trie() {
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
            current.isWord = true;
        }

        private TrieNode searchPrefix(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode node = current.children[c - 'a'];
                if (node == null) {
                    return node;
                }
                current = node;
            }
            return current;
        }

        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        System.out.println(trie.search("apple"));   // Output: true
        System.out.println(trie.search("app"));     // Output: true
        System.out.println(trie.search("banana"));  // Output: true
        System.out.println(trie.search("orange"));  // Output: false

        System.out.println(trie.startsWith("app")); // Output: true
        System.out.println(trie.startsWith("ban")); // Output: true
        System.out.println(trie.startsWith("or"));  // Output: false
    }

    static class TrieNode {
        TrieNode[] children;
        boolean isWord;

        TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
