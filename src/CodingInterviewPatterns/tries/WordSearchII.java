package CodingInterviewPatterns.tries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordSearchII {
    //https://leetcode.com/problems/word-search-ii/
    //https://www.youtube.com/watch?v=3PT9QjgYTQc
    class Solution {
        TrieNode root;
        int row, col;
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public List<String> findWords(char[][] board, String[] words) {
            if (board == null || board.length == 0) return new ArrayList<>();
            root = new TrieNode();
            insert(words);

            row = board.length;
            col = board[0].length;

            HashSet<String> result = new HashSet<>();

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    dfs(board, i, j, result, "", root);
                }
            }

            return new ArrayList<>(result);

        }

        private void dfs(char[][] board, int i, int j, HashSet<String> result, String s, TrieNode current) {
            if (i < 0 || j < 0 || i >= row || j >= col || board[i][j] == '#') {
                return;
            }

            char c = board[i][j];
            TrieNode node = current.children[c - 'a'];
            board[i][j] = '#';

            if (node != null) {
                String res = s + c;
                if (node.isEnd) {
                    result.add(res);
                }
                //call DFS for all the four directions.
                for (int[] dir : directions) {
                    int x = i + dir[0];
                    int y = j + dir[1];

                    dfs(board, x, y, result, res, node);
                }
            }

            board[i][j] = c;

        }

        private void insert(String[] words) {
            for (String word : words) {
                TrieNode current = root;
                for (char c : word.toCharArray()) {
                    TrieNode node = current.children[c - 'a'];
                    if (node == null) {
                        node = new TrieNode();
                        current.children[c - 'a'] = node;
                    }
                    current = node;
                }
                current.isEnd = true;
            }

        }

    }

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }
}
