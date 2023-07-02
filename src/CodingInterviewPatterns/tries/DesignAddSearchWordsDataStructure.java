package CodingInterviewPatterns.tries;

import java.util.HashMap;
import java.util.Map;

public class DesignAddSearchWordsDataStructure {
    //https://www.youtube.com/watch?v=9OcBPa_GQ7w&t=901s
    //https://www.youtube.com/watch?v=4OyefqK-LDA&t=942s

    /**
     * Taking into account the updated time complexity analysis, here's the overall time and space complexity for the functions in the WordDictionary class:
     * <p>
     * addWord(String word):
     * <p>
     * Time Complexity: O(M), where M is the length of the word.
     * Space Complexity: O(M), for storing the characters of the word in the trie.
     * search(String word):
     * <p>
     * Time Complexity: O(K * N * L), where K is the number of children, N is the number of nodes in the trie, and L is the average length of the words.
     * Space Complexity: O(1), as no additional space is used.
     * searchRecursive(Trie current, String word, int index):
     * <p>
     * Time Complexity: O(K * N), where K is the number of children and N is the number of nodes in the trie.
     * Space Complexity: O(L), for the recursive call stack, where L is the length of the word being searched.
     * Overall:
     * <p>
     * Time Complexity: The dominant factor is the search operation, which has a worst-case time complexity of O(K * N * L).
     * Space Complexity: The space required to store the trie is proportional to the number of nodes and the length of the words added to the trie. Therefore, the space complexity is O(N * L), where N is the number of nodes and L is the average length of the words.
     * It's worth noting that these complexities assume a balanced trie structure. In the worst case, where the trie is highly unbalanced, the time and space complexity can increase.
     */
    class WordDictionary {
        private Trie root;

        public WordDictionary() {
            root = new Trie();

        }

        public void addWord(String word) {
            Trie current = root;
            for (char c : word.toCharArray()) {

                Trie node = current.children[c - 'a'];
                if (node == null) {
                    node = new Trie();
                    current.children[c - 'a'] = node;
                }
                current = node;
            }

            current.isEnd = true;
        }

        public boolean search(String word) {
            Trie current = root;
            return searchRecursive(current, word, 0); //dfs
        }

        private boolean searchRecursive(Trie current, String word, int index) {
            if (index == word.length()) {
                return current.isEnd;
            }

            char c = word.charAt(index);

            if (c == '.') {
                for (Trie node : current.children) {
                    if (node != null && searchRecursive(node, word, index + 1))
                        return true;
                }
                return false;

            } else {
                Trie node = current.children[c - 'a'];

                if (current.children[c - 'a'] == null) {
                    return false;
                }
                current = node;
                return searchRecursive(current, word, index + 1);
            }


        }


    }

    public class Trie {
        public Trie[] children;
        public boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */

    class WordDictionary2 {
        TrieNode root;

        public WordDictionary2() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                current.children.putIfAbsent(c, new TrieNode());
                //TrieNode node = current.children.get(c);
                current = current.children.get(c);
            }
            current.isWord = true;
        }

        public boolean search(String word) {
            TrieNode current = root;
            return searchRecursiverly(root, word, 0);
        }

        private boolean searchRecursiverly(TrieNode current, String word, int index) {
            if (index == word.length()) {
                return current.isWord;
            }

            char c = word.charAt(index);

            if (c == '.') {
                for (TrieNode node : current.children.values()) {
                    if (node == null) return false;
                    if (searchRecursiverly(node, word, index + 1)) return true;
                }
                return false;

            } else {
                TrieNode node = current.children.get(c);
                if (node == null) return false;
                return searchRecursiverly(node, word, index + 1);
            }

        }
    }

    class TrieNode {
        boolean isWord;
        Map<Character, TrieNode> children;

        TrieNode() {
            isWord = false;
            children = new HashMap<>();
        }
    }


    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */


}
