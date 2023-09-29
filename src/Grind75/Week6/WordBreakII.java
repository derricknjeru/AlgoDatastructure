package Grind75.Week6;

import java.util.ArrayList;
import java.util.List;

public class WordBreakII {
    //https://www.youtube.com/watch?v=fNVs1J2KCyo&t=136s&ab_channel=Techdose
    //https://www.youtube.com/watch?v=9-grHHGUVls&ab_channel=KnowledgeCenter
    //https://leetcode.com/problems/word-break/description/
    public static void main(String[] args) {

    }

    class Solution {
        private List<String> validSentences = new ArrayList<>();

        private class TrieNode {
            char character;
            int wordCount;
            TrieNode[] children = new TrieNode[26];

            TrieNode(char character) {
                wordCount = 0;
                this.character = character;
            }
        }

        private TrieNode root;

        private void insertInTrie(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null)
                    current.children[index] = new TrieNode(c);
                current = current.children[index];
            }
            current.wordCount += 1;
        }

        private boolean searchInTrie(String s) {
            TrieNode current = root;
            for (char c : s.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null)
                    return false;
                current = current.children[index];
            }
            return current.wordCount > 0;
        }

        private void generateValidSentences(String input, String sentence, int position) {
            if (position == input.length()) {
                validSentences.add(sentence);
                return;
            }
            sentence += " ";
            for (int i = position; i < input.length(); ++i) {
                if (searchInTrie(input.substring(position, i + 1)))
                    generateValidSentences(input, sentence + input.substring(position, i + 1), i + 1);
            }
        }

        public List<String> wordBreak(String input, List<String> wordDictionary) {
            root = new TrieNode('/');
            for (String word : wordDictionary)
                insertInTrie(word);

            for (int i = 1; i <= input.length(); ++i) {
                if (searchInTrie(input.substring(0, i)))
                    generateValidSentences(input, input.substring(0, i), i);
            }
            return validSentences;
        }
    }
}
