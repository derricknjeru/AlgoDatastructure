package CodingInterviewPatterns.Interesting;

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
     *  Note.md.md:
     *      You may assume all letters are in lowercase.
     *      If the order is invalid, return an empty string.
     *      There may be multiple valid order of letters, return any one of them is fine.
     */

    /**
     * Soln. Build a graph, then run topological sorting
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
        String s = alienOrder(words);
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

        System.out.println(totalChar);
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
