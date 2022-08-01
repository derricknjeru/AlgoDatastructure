package revision_v2.Interesting;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class WordSearch {
    //https://leetcode.com/problems/word-search/
    //https://www.youtube.com/watch?v=vYYNp0Jrdv0&t=30s

    /**
     * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
     * <p>
     * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * Output: true
     * Example 2:
     * <p>
     * <p>
     * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
     * Output: true
     * Example 3:
     * <p>
     * <p>
     * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
     * Output: false
     * <p>
     * <p>
     * Constraints:
     * <p>
     * m == board.length
     * n = board[i].length
     * 1 <= m, n <= 6
     * 1 <= word.length <= 15
     * board and word consists of only lowercase and uppercase English letters.
     * <p>
     * <p>
     * Follow up: Could you use search pruning to make your solution faster with a larger board?
     */
    class Solution {

        int row, col;
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] seen;

        public boolean exist(char[][] board, String word) {

            if (board == null || board.length == 0) return false;

            row = board.length;
            col = board[0].length;
            seen = new boolean[row][col];

            boolean ans = false;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == word.charAt(0) && dfs(board, word, 0, i, j)) {
                        return true;
                    }
                }
            }

            return ans;
        }

        private boolean dfs(char[][] board, String word, int index, int i, int j) {

            if (index == word.length())
                return true;

            char c = word.charAt(index);

            if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != c || seen[i][j]) return false;

            seen[i][j] = true;

            for (int[] dir : directions) {
                int x = dir[0] + i;
                int y = dir[1] + j;

                if (dfs(board, word, index + 1, x, y)) return true;
            }

            seen[i][j] = false;

            return false;

        }

        private boolean dfs2(char[][] board, String word, int index, int i, int j) {

            if (index == word.length())
                return true;

            char c = word.charAt(index);

            if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != c) return false;

            char temp = board[i][j];
            board[i][j] = ' ';
            for (int[] dir : directions) {
                int x = dir[0] + i;
                int y = dir[1] + j;

                if (dfs(board, word, index + 1, x, y)) return true;
            }

            board[i][j] = temp;

            return false;

        }
    }

    public static void main(String[] args) {


    }
}
