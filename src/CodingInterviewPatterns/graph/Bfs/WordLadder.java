package CodingInterviewPatterns.graph.Bfs;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));

        Solution solution = new Solution();
        int result = solution.ladderLength(beginWord, endWord, wordList);

        System.out.println("Shortest transformation sequence length: " + result);
    }
    //https://www.youtube.com/watch?v=h9iTnkgv05E
    /**
     * he time complexity of the given code is O(M^2 * N), where M is the length of each word and N is the total number of words in the wordList.
     *
     * Let's analyze the code to understand the time complexity:
     *
     * Building the neighbors map:
     *
     * In the nested loop, for each word in the wordList, the code iterates over each character of the word.
     * For each character position, it creates a pattern by replacing the character with '*'.
     * The computeIfAbsent method adds the word to the neighbors map, which takes constant time on average.
     * Since there are M characters in each word, the nested loop runs M * N times.
     * Therefore, building the neighbors map takes O(M * N) time.
     * BFS traversal:
     *
     * The code uses a queue to perform a breadth-first search (BFS) traversal.
     * In the worst case, all words in the wordList will be added to the queue, resulting in N iterations.
     * For each word in the queue, the code checks all possible neighbors.
     * For each character position in the word, it generates a pattern and retrieves the neighbor words from the neighbors map.
     * The size of the neighbors map can vary, but in the worst case, it can have N words.
     * Therefore, for each word, the code may iterate through all N words in the neighbors map.
     * Since there are N words in the queue in the worst case, the total number of iterations is bounded by N^2.
     * Hence, the BFS traversal takes O(N^2) time.
     * Combining the above steps, the overall time complexity is O(M * N + N^2), which can be simplified to O(M^2 * N) because N^2 dominates M * N.
     *
     * Therefore, the time complexity of the given code is O(M^2 * N).
     */
    static class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) return 0;

            Map<String, List<String>> graph = new HashMap<>();
            wordList.add(endWord);

            for (String word : wordList) {
                for (int i = 0; i < word.length(); i++) {
                    String pattern = word.substring(0, i) + "*" + word.substring(i + 1);
                    graph.computeIfAbsent(pattern, key -> new ArrayList<>()).add(word);
                }
            }

            Set<String> visited = new HashSet<>();
            Queue<String> q = new LinkedList<>();

            visited.add(beginWord);
            q.offer(beginWord);

            int level = 1;

            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    String word = q.remove();
                    if (word.equals(endWord)) return level;

                    for (int j = 0; j < word.length(); j++) {
                        String pattern = word.substring(0, j) + "*" + word.substring(j + 1);
                        for (String neighborWord : graph.getOrDefault(pattern, new ArrayList<>())) {
                            if (!visited.contains(neighborWord)) {
                                visited.add(neighborWord);
                                q.offer(neighborWord);
                            }
                        }
                    }

                }

                level++;

            }

            return 0;
        }
    }


    class Solution2 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);

            if (!wordSet.contains(endWord))
                return 0;

            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);

            int steps = 1;

            while (!queue.isEmpty()) {
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    String currentWord = queue.poll();

                    if (currentWord.equals(endWord))
                        return steps;

                    char[] wordChars = currentWord.toCharArray();

                    for (int j = 0; j < wordChars.length; j++) {
                        char originalChar = wordChars[j];

                        for (char c = 'a'; c <= 'z'; c++) {
                            if (wordChars[j] == c)
                                continue;

                            wordChars[j] = c;
                            String transformedWord = String.valueOf(wordChars);

                            if (wordSet.contains(transformedWord)) {
                                queue.offer(transformedWord);
                                wordSet.remove(transformedWord);
                            }
                        }

                        wordChars[j] = originalChar;
                    }
                }

                steps++;
            }

            return 0;
        }
        /**
         * Create a set called wordSet and add all the words from the wordList into it. This set will be used for efficient word lookup.
         *
         * Check if the endWord is not present in wordSet. If it is not present, there is no valid transformation sequence, so return 0.
         *
         * Create a queue called queue and add the beginWord to it. This queue will be used for BFS traversal.
         *
         * Initialize a variable called steps to 1. This variable will keep track of the number of steps taken during the BFS traversal.
         *
         * Perform the BFS traversal while the queue is not empty:
         *
         * Get the current size of the queue.
         * Iterate size number of times to process each word at the current level.
         * Poll the front word from the queue and store it in currentWord.
         * If currentWord is equal to endWord, return steps since we have found the endWord and the shortest transformation sequence.
         * Convert currentWord to a character array called wordChars.
         * Iterate through each character in wordChars:
         * Store the original character at the current position in originalChar.
         * Iterate through all lowercase English letters from 'a' to 'z':
         * If the current character is the same as the original character, continue to the next iteration.
         * Replace the character at the current position in wordChars with the current lowercase letter.
         * Convert wordChars back to a string called transformedWord.
         * If wordSet contains transformedWord:
         * Add transformedWord to the queue for further exploration.
         * Remove transformedWord from wordSet to avoid revisiting it in the future.
         * Restore the original character at the current position in wordChars.
         * Increment steps by 1 to indicate that we have finished processing the current level.
         * If the queue becomes empty without finding the endWord, it means that there is no valid transformation sequence. Return 0.
         *
         * The code uses a breadth-first search (BFS) algorithm to explore all possible transformations of the words, starting from beginWord and moving towards endWord. By keeping track of the number of steps taken during the BFS traversal, we can find the length of the shortest transformation sequence from beginWord to endWord.
         */
    }


}
