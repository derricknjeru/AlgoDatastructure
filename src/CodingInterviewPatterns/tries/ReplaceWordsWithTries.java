package CodingInterviewPatterns.tries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceWordsWithTries {
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
     */
    //https://leetcode.com/problems/replace-words/
    //https://leetcode.com/problems/replace-words/solution/

    static class Solution {

        public String replaceWords(List<String> dictionary, String sentence) {
            //creating a trie
            TrieNode root = new TrieNode();
            for (String rt : dictionary) {
                insertWord(root, rt);
            }

            StringBuilder sb = new StringBuilder();
            String[] words = sentence.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (i > 0) {
                    sb.append(' ');
                }
                sb.append(searchRoot(root, words[i]));
            }
            return sb.toString();

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

        private String searchRoot(TrieNode root, String word) {
            TrieNode current = root;
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode node = current.children[c - 'a'];
                if (node == null || current.isWord) {
                    break;
                }
                prefix.append(c);
                current = node;
            }
            return current.isWord ? prefix.toString() : word;
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

    class TrieNode {
        boolean isWord;
        TrieNode[] children;

        TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        List<String> dictionary1 = Arrays.asList("cat", "bat", "rat");
        String sentence1 = "the cattle was rattled by the battery";
        String result1 = solution.replaceWords(dictionary1, sentence1);
        System.out.println(result1);  // Output: "the cat was rat by the bat"

        // Example 2
        List<String> dictionary2 = Arrays.asList("a", "b", "c");
        String sentence2 = "aadsfasf absbs bbab cadsfafs";
        String result2 = solution.replaceWords(dictionary2, sentence2);
        System.out.println(result2);  // Output: "a a b c"

        // Additional Test Case
        List<String> dictionary3 = Arrays.asList("a", "aa", "aaa", "aaaa");
        String sentence3 = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";
        String result3 = solution.replaceWords(dictionary3, sentence3);
        System.out.println(result3);  // Output: "a a a a a a a a bbb baba a"
    }

    public class ReplaceWordsWithTries2 {
        class TrieNode {
            boolean isWord;
            TrieNode[] children;

            TrieNode() {
                isWord = false;
                children = new TrieNode[26];
            }
        }

        private void insertWord(TrieNode root, String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isWord = true;
        }

        private String search(TrieNode root, String word) {
            TrieNode current = root;
            StringBuilder prefix = new StringBuilder();
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    break;
                }
                current = current.children[index];
                prefix.append(c);
                if (current.isWord) {
                    return prefix.toString();
                }
            }
            return word;
        }

        public String replaceWords(List<String> dictionary, String sentence) {
            TrieNode root = new TrieNode();
            for (String word : dictionary) {
                insertWord(root, word);
            }

            StringBuilder sb = new StringBuilder();
            String[] words = sentence.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                if (i > 0) {
                    sb.append(' ');
                }
                sb.append(search(root, words[i]));
            }

            return sb.toString();
        }


    }


}




