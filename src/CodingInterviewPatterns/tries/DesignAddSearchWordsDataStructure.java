package CodingInterviewPatterns.tries;

public class DesignAddSearchWordsDataStructure {
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
                    if (node != null && searchRecursive(node, word, index + 1)) return true;
                }
                return false;

            } else {

                Trie node = current.children[c - 'a'];

                if (current.children[c - 'a'] == null) {
                    return false;
                }

                current = node;
            }

            return searchRecursive(current, word, index + 1);

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
}
