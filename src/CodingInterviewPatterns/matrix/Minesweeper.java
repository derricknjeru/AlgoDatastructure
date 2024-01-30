package CodingInterviewPatterns.matrix;

public class Minesweeper {
    //https://www.youtube.com/watch?v=1BJJvodXiUY
    //https://leetcode.com/problems/minesweeper/description/
    static class Solution {
        private int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1},
                {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

        public char[][] updateBoard(char[][] board, int[] click) {
            int row = click[0];
            int col = click[1];
            int m = board.length;
            int n = board[0].length;

            if (board[row][col] == 'M' || board[row][col] == 'X') {
                board[row][col] = 'X';
                return board;
            }

            int num = 0;
            for (int[] dir : directions) {
                int newRow = dir[0] + row;
                int newCol = dir[1] + col; // Fix: use col instead of row

                if (newRow >= 0 && newCol >= 0 && newRow < m && newCol < n &&
                        board[newRow][newCol] == 'M') {
                    num++;
                }
            }

            if (num > 0) {
                board[row][col] = (char) (num + '0');
                return board;
            }

            board[row][col] = 'B';

            for (int[] dir : directions) {
                int newRow = dir[0] + row;
                int newCol = dir[1] + col; // Fix: use col instead of row

                if (newRow >= 0 && newCol >= 0 && newRow < m && newCol < n &&
                        board[newRow][newCol] == 'E') {
                    updateBoard(board, new int[]{newRow, newCol});
                }
            }

            return board;
        }
    }


    public static void main(String[] args) {

        Solution solution = new Solution();

        char[][] board = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };

        int[] click = {3, 0};

        char[][] updatedBoard = solution.updateBoard(board, click);

        // Print the updated board
        for (char[] row : updatedBoard) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

}
