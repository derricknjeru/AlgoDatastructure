package CodingInterviewPatterns.tries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesignSearchAutocompleteSystem {
    //https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/642-design-search-autocomplete-system.html
    //https://www.youtube.com/watch?v=Zm3-cGKKFLg&t=4s
    //https://happygirlzt.com/code/642.html
    static class AutocompleteSystem {
        class TrieNode implements Comparable<TrieNode> {
            TrieNode[] children;
            String sentence;
            int frequency;
            List<TrieNode> hot;

            public TrieNode() {
                children = new TrieNode[128];
                sentence = null;
                frequency = 0;
                hot = new ArrayList<>();
            }

            public int compareTo(TrieNode other) {
                if (this.frequency == other.frequency) {
                    return this.sentence.compareTo(other.sentence);
                }

                return other.frequency - this.frequency;
            }

            public void update(TrieNode node) {
                if (!this.hot.contains(node)) {
                    this.hot.add(node);
                }

                Collections.sort(hot);

                if (hot.size() > 3) {
                    hot.remove(hot.size() - 1);
                }
            }
        }

        TrieNode root;
        TrieNode current;
        StringBuilder currentInput;

        public AutocompleteSystem(String[] sentences, int[] frequencies) {
            root = new TrieNode();
            current = root;
            currentInput = new StringBuilder();

            for (int i = 0; i < sentences.length; i++) {
                addSentence(sentences[i], frequencies[i]);
            }
        }

        public void addSentence(String sentence, int frequency) {
            TrieNode node = root;

            List<TrieNode> visitedNodes = new ArrayList<>();
            for (char c : sentence.toCharArray()) {
                if (node.children[c] == null) {
                    node.children[c] = new TrieNode();
                }

                node = node.children[c];
                visitedNodes.add(node);
            }

            node.sentence = sentence;
            node.frequency += frequency;

            for (TrieNode visitedNode : visitedNodes) {
                visitedNode.update(node);
            }
        }

        public List<String> input(char c) {
            List<String> result = new ArrayList<>();
            if (c == '#') {
                addSentence(currentInput.toString(), 1);
                currentInput = new StringBuilder();
                current = root;
                return result;
            }

            currentInput.append(c);
            if (current != null) {
                current = current.children[c];
            }

            if (current == null) return result;
            for (TrieNode node : current.hot) {
                result.add(node.sentence);
            }

            return result;
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] frequencies = {5, 3, 2, 2};

        AutocompleteSystem system = new AutocompleteSystem(sentences, frequencies);

        System.out.println(system.input('i')); // ["i love you", "island", "i love leetcode"]
        System.out.println(system.input(' ')); // ["i love you", "i love leetcode"]
        System.out.println(system.input('a')); // []
        System.out.println(system.input('#')); // []
    }
}
