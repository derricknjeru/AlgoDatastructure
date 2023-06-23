package CodingInterviewPatterns.graph;

import java.util.*;


public class AlienDictionary {
    //https://www.youtube.com/watch?v=6kTZYvNNyps
    //https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/ --> Kahn’s algorithm
    //https://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/


    /**
     * There is a new alien language which uses the latin alphabet.
     *  However, the order among letters are unknown to you.
     *
     *  You receive a list of words from the dictionary, where words are sorted lexicographically
     *  by the rules of this new language.
     *
     *  Derive the order of letters in this language.
     *
     *  For example,
     *
     *  Given the following words in dictionary,
     *    [
     *      "wrt",
     *      "wrf",
     *      "er",
     *      "ett",
     *      "rftt"
     *    ]
     *
     *  The correct order is: "wertf".
     *
     *
     *  Input:
     *  [
     *  "z",
     *  "x",
     *  "z"
     *  ]
     *
     *  Output: ""
     *
     *  Explanation: The order is invalid, so return "".
     *
     *
     *  Note.md.md.md.md:
     *      You may assume all letters are in lowercase.
     *      If the order is invalid, return an empty string.
     *      There may be multiple valid order of letters, return any one of them is fine.
     */

    /**
     * Soln. Build a CodingInterviewPatterns.graph, then run topological sorting
     */


    /**
     * Certainly! Building the graph involves analyzing the relationships between characters in the given words to determine the order of letters in the alien language. Here's how it works:
     *
     * Initialize an empty graph represented by a Map<Character, Set<Character>>. This map will store the characters as keys and their corresponding neighbors as values.
     *
     * Iterate through the given words, comparing adjacent words pair by pair.
     *
     * For each pair of adjacent words, find the first characters that differ. This indicates an ordering relationship between the characters.
     *
     * Add an edge from the differing character in the first word to the differing character in the second word.
     *
     * If the differing character in the first word is not already a key in the graph, add it with an empty set as its value.
     *
     * Add the differing character in the second word to the set of neighbors of the differing character in the first word.
     *
     * Repeat steps 3-6 for all pairs of adjacent words.
     *
     * By the end of this process, the graph will represent the relationships between the characters in the alien language. Each character will be a key in the map, and its corresponding value will be a set containing all the characters that come after it in the language.
     *
     * For example, let's consider the input words: ["wrt", "wrf", "er", "ett", "rftt"].
     *
     * Comparing "wrt" and "wrf", we find that 't' comes before 'f'. So we add an edge from 't' to 'f' in the graph.
     * Comparing "wrf" and "er", we find that 'w' comes before 'e'. So we add an edge from 'w' to 'e' in the graph.
     * Comparing "er" and "ett", we find that 'r' comes before 't'. So we add an edge from 'r' to 't' in the graph.
     * Comparing "ett" and "rftt", we find that 'e' comes before 'r'. So we add an edge from 'e' to 'r' in the graph.
     * The resulting graph will look like this:
     *
     * arduino
     * Copy code
     * {
     *   'w': ['e'],
     *   'r': ['t'],
     *   'e': ['r'],
     *   't': ['f']
     * }
     * This graph represents the relationships between the characters 'w', 'r', 'e', 't', and 'f' in the alien language. In this case, the order of letters can be derived as "wertf".
     *
     * The subsequent topological sorting step performs a depth-first search (DFS) on the graph to determine the order of letters based on the edges.
     * @param args
     */

    public static void main(String[] args) {
        String[] words1 = {"caa", "aaa", "aab"};
        String[] words2 = {"baa", "abcd", "abca", "cab", "cad"};
        String[] words3 = {"z", "x"};
        String[] words4 = {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        };
        String[] words = {
                "z",
                "x",
                "z"
        };
        String s = alienOrder(words4);
        System.out.println(s);
    }

    public static String alienOrder(String[] words) {
        StringBuilder sb = new StringBuilder();

        HashMap<Character, List<Character>> graph = new HashMap<>();

        buildGraph(graph, words);
        //no of vertices
        int totalChar = graph.keySet().size();
        boolean[] seen = new boolean[26];
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : graph.keySet()) {
            if (!seen[c - 'a']) {
                topologicalSorting(graph, c, stack, seen);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        //System.out.println(totalChar);
        return sb.toString();
    }

    private static void topologicalSorting(HashMap<Character, List<Character>> graph, char c, Deque<Character> stack, boolean[] seen) {
        seen[c - 'a'] = true;

        List<Character> edges = graph.get(c);
        for (char u : edges) {
            if (!seen[u - 'a']) {
                topologicalSorting(graph, u, stack, seen); //dfs
            }
        }

        stack.push(c);
    }


    private static void buildGraph(HashMap<Character, List<Character>> graph, String[] words) {
        /**
         * Let's consider the example input words: ["wrt", "wrf", "er", "ett", "rftt"].
         *
         * Initialize an empty graph, represented by a HashMap<Character, List<Character>> called graph.
         *
         * Iterate through each word in the given array of words.
         *
         * For each character in the current word, check if it is already present as a key in the graph.
         * If not, add the character as a key in the graph with an empty list as its value. This step ensures that all characters are included in the graph, even if they do not have any outgoing edges.
         * After this step, the graph will contain all the characters as keys, but their corresponding lists of neighbors will be empty.
         *
         * The current state of the graph:

         * {
         *   'w': [],
         *   'r': [],
         *   't': [],
         *   'e': [],
         *   'f': []
         * }
         * Create edges between characters by comparing adjacent words and finding the first mismatching character.
         *
         * Iterate through the words from the first word to the second-to-last word.
         * For each pair of adjacent words, compare the characters from the beginning until the length of the smaller word.
         * If the length of the first word is greater than the second word and the second word is a prefix of the first word, it implies an invalid order. In this case, return from the method.
         * For each character at the same position in the current word and the next word, compare them.
         * If the characters are different, it indicates an ordering relationship.
         * Add an edge from the character of the first word to the character of the second word in the graph by adding the second character to the list of neighbors of the first character.
         * After this step, the graph will contain the appropriate edges based on the order of characters in the given words.
         *
         * The updated state of the graph:

         * {
         *   'w': ['e'],
         *   'r': ['t'],
         *   't': ['f'],
         *   'e': ['r'],
         *   'f': []
         * }
         * The resulting graph represents the relationships between the characters 'w', 'r', 't', 'e', and 'f' in the alien language. In this case, the order of letters can be derived as "wertf".
         *
         * Note that the implementation also includes a check to handle cases where a word is a prefix of the next word. This indicates an invalid order, and the method returns without further processing.
         */
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
            }
        }

        //create edges
        // Take the current two words and find the first mismatching character
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int len = Math.min(word1.length(), word2.length());
            if (word1.length() > word2.length() && word1.substring(0, len).equals(word2)) {
                return;
            }
            for (int j = 0; j < len; j++) {
                char out = word1.charAt(j);
                char in = word2.charAt(j);
                // If we find a mismatching character, then
                // add an edge from character of word1 to
                // that of word2
                if (out != in) {
                    graph.get(out).add(in);
                    break;
                }

            }
        }
    }
}
