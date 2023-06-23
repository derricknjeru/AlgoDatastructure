package CodingInterviewPatterns.matrix;

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


        Solution wordSearch = new Solution();

        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String word = "ABCCED";
        System.out.println(wordSearch.exist(board, word));  // Output: true

        word = "SEE";
        System.out.println(wordSearch.exist(board, word));  // Output: true

        word = "ABCB";
        System.out.println(wordSearch.exist(board, word));  // Output: false

    }

    //https://leetcode.com/problems/word-search/discuss/1028109/Java-99
    //https://www.youtube.com/watch?v=z8pSRwYJxyk
    //https://www.youtube.com/watch?v=vYYNp0Jrdv0&ab_channel=KevinNaughtonJr.
    static class Solution {
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public boolean exist(char[][] board, String word) {
            int rows = board.length;
            if (rows == 0) return false;

            if (word.length() == 0) return false;

            int cols = board[0].length;
            boolean[][] visited = new boolean[rows][cols];


            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == word.charAt(0) && dfs(word, i, j, 0, board, visited)) return true;
                }
            }

            return false;
        }

        private boolean dfs(String word, int i, int j, int index, char[][] board, boolean[][] visited) {
            if (index == word.length()) return true;

            if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || visited[i][j])
                return false;

            if (board[i][j] != word.charAt(index))
                return false;

            visited[i][j] = true;

        /*for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];*/

            if (dfs(word, i + 1, j, index + 1, board, visited)) return true;
            if (dfs(word, i - 1, j, index + 1, board, visited)) return true;
            if (dfs(word, i, j + 1, index + 1, board, visited)) return true;
            if (dfs(word, i, j - 1, index + 1, board, visited)) return true;
            // }

            visited[i][j] = false;

            return false;
        }
    }

    public static class Solution2 {
        public boolean exist(char[][] board, String word) {
            int m = board.length;
            int n = board[0].length;
            boolean[][] visited = new boolean[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (backtrack(board, visited, i, j, word, 0))
                        return true;
                }
            }

            return false;
        }

        private boolean backtrack(char[][] board, boolean[][] visited, int row, int col, String word, int index) {
            if (index == word.length())
                return true;

            int m = board.length;
            int n = board[0].length;

            if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col])
                return false;

            if (board[row][col] != word.charAt(index))
                return false;

            visited[row][col] = true;

            if (backtrack(board, visited, row - 1, col, word, index + 1)
                    || backtrack(board, visited, row + 1, col, word, index + 1)
                    || backtrack(board, visited, row, col - 1, word, index + 1)
                    || backtrack(board, visited, row, col + 1, word, index + 1)) {
                return true;
            }

            visited[row][col] = false;
            return false;
        }
    }
}
