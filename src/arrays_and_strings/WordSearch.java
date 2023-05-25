package arrays_and_strings;

public class WordSearch {
    public static void main(String[] args) {
        /**
         * Given an m x n board and a word, find if the word exists in the grid.
         *
         * The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
         *
         *
         *
         * Example 1:
         *
         *
         * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
         * Output: true
         * Example 2:
         *
         *
         * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
         * Output: true
         * Example 3:
         *
         *
         * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
         * Output: false
         *
         *
         * Constraints:
         *
         * m == board.length
         * n = board[i].length
         * 1 <= m, n <= 200
         * 1 <= word.length <= 103
         * board and word consists only of lowercase and uppercase English letters.
         */
    }

    //https://leetcode.com/problems/word-search/discuss/1028109/Java-99
    //https://www.youtube.com/watch?v=z8pSRwYJxyk
    //https://www.youtube.com/watch?v=vYYNp0Jrdv0&ab_channel=KevinNaughtonJr.
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (word.charAt(0) == board[i][j] && dfsSearch(i, j, 0, word, board)) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean dfsSearch(int i, int j, int index, String word, char[][] board) {

        if (index == word.length()) return true;

        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length
                || board[i][j] != word.charAt(index)) {
            return false;
        }

        // character matches
        char temp = board[i][j];
        board[i][j] = ' ';

        /*if (dfsSearch(i + 1, j, index + 1, word, board) ||
                dfsSearch(i, j + 1, index + 1, word, board) ||
                dfsSearch(i - 1, j, index + 1, word, board) ||
                dfsSearch(i, j - 1, index + 1, word, board)) {

            return true;
        }*/

        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (dfsSearch(x, y, index + 1, word, board)) return true;

        }

        board[i][j] = temp;

        return false;

    }
}
