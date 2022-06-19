package trees;

import java.util.List;

public class replaceWordsWithTries {
    /**
     * In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word successor. For example, when the root "an" is followed by the successor word "other", we can form a new word "another".
     * <p>
     * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the successors in the sentence with the root forming it. If a successor can be replaced by more than one root, replace it with the root that has the shortest length.
     * <p>
     * Return the sentence after the replacement.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
     * Output: "the cat was rat by the bat"
     * Example 2:
     * <p>
     * Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
     * Output: "a a b c"
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= dictionary.length <= 1000
     * 1 <= dictionary[i].length <= 100
     * dictionary[i] consists of only lower-case letters.
     * 1 <= sentence.length <= 106
     * sentence consists of only lower-case letters and spaces.
     * The number of words in sentence is in the range [1, 1000]
     * The length of each word in sentence is in the range [1, 1000]
     * Every two consecutive words in sentence will be separated by exactly one space.
     * sentence does not have leading or trailing spaces.
     *
     * @param dictionary
     * @param sentence
     * @return
     */
    //https://leetcode.com/problems/replace-words/
    //https://leetcode.com/problems/replace-words/solution/
    public String replaceWords(List<String> dictionary, String sentence) {

        TrieNode trie = new TrieNode();

        for (String root : dictionary) {
            TrieNode cur = trie;
            for (char letter : root.toCharArray()) {
                TrieNode node = cur.children[letter - 'a'];
                if (node == null) {
                    node = new TrieNode();
                    cur.children[letter - 'a'] = node;
                }
                cur = node;
            }
            cur.wordValue = root;
            cur.isEnd = true;
        }

        String[] words = sentence.split("\\s+");

        StringBuilder ans = new StringBuilder();
        String prefix = "";

        for (String word : words) {

            TrieNode current = trie;

            for (char c : word.toCharArray()) {

                TrieNode node = current.children[c - 'a'];

                if (node == null || current.isEnd) {
                    break;
                }
                current = node;
            }

            ans.append(prefix);
            prefix = " ";
            ans.append(current.isEnd ? current.wordValue : word);

        }

        return ans.toString();
    }


}

class TrieNode {
    TrieNode[] children;
    boolean isEnd;
    String wordValue;

    TrieNode() {
        children = new TrieNode[26];
    }

}
