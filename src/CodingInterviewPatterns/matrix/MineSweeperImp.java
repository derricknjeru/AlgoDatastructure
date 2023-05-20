package CodingInterviewPatterns.matrix;

import java.util.Arrays;

public class MineSweeperImp {
    static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public static void main(String[] args) {
        String[][] board = {
                {"*", "", "", "*", "", "", ""},
                {"*", "", "", "", "*", "", ""},
                {"", "", "*", "", "", "", ""}
        };

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (!board[i][j].equals("*")) {
                    board[i][j] = String.valueOf(count(board, i, j));
                }
            }
        }

        for (String[] boardRes : board) {
            System.out.println(Arrays.toString(boardRes));
        }

    }

    private static int count(String[][] board, int i, int j) {
        int count = 0;
        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) continue;
            if (board[x][y].equals("*")) count++;
        }
        return count;
    }
}
