package CodingInterviewPatterns.HashTable.quickreview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidSudoku {
    //https://www.youtube.com/watch?v=7gIlJKVK4Ok
    //https://www.youtube.com/watch?v=TjFXEUCMqI8

    /**
     * The time complexity of the provided isValidSudoku method is O(1) because the input Sudoku board is always a fixed size of 9x9.
     * The double nested loop iterates through each cell of the board, resulting in a constant number of iterations.
     * <p>
     * The space complexity of the method is also O(1). It uses three arrays of sets: rows, cols, and boxes, each with a fixed size of 9.
     * Therefore, the space required is constant and does not depend on the size of the input.
     * <p>
     * Overall, the time and space complexities of the isValidSudoku method are both O(1),
     * making it an efficient solution for validating a 9x9 Sudoku board.
     *
     * @return
     */

    class Solution {
        public boolean isValidSudoku(char[][] board) {

            for (int i = 0; i < 9; i++) {
                Set<Character> rowMap = new HashSet<>();
                Set<Character> colMap = new HashSet<>();
                Set<Character> boxMap = new HashSet<>();

                for (int j = 0; j < 9; j++) {

                    char cRow = board[i][j];
                    char cCol = board[j][i];

                    //Getting the board number 3*j + i;


                    if (cRow != '.' && !rowMap.add(cRow)) {
                        return false;
                    }

                    if (cCol != '.' && !colMap.add(cCol)) {
                        return false;
                    }

                    int rowIndex = 3 * (i / 3);
                    int colIndex = 3 * (i % 3);

                    char boxChar = board[rowIndex + j / 3][colIndex + j % 3];

                    if (boxChar != '.' && !boxMap.add(boxChar)) {
                        return false;
                    }

                }


            }

            return true;

        }
    }


    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Map<Integer, Set<Character>> boxes = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            rows.put(i, new HashSet<>());
            cols.put(i, new HashSet<>());
            boxes.put(i, new HashSet<>());
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char curr = board[r][c];
                if (curr == '.') {
                    continue;
                }

                if (rows.get(r).contains(curr) || cols.get(c).contains(curr) || boxes.get((r / 3) * 3 + c / 3).contains(curr)) {
                    return false;
                }

                rows.get(r).add(curr);
                cols.get(c).add(curr);
                boxes.get((r / 3) * 3 + c / 3).add(curr);
            }
        }

        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        Set<Integer>[] rows = new HashSet[9];
        Set<Integer>[] cols = new HashSet[9];
        Set<Integer>[] boxes = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = Character.getNumericValue(c);
                    int boxIndex = (i / 3) * 3 + j / 3;
                    if (rows[i].contains(num) || cols[j].contains(num) || boxes[boxIndex].contains(num)) {
                        return false;
                    }
                    rows[i].add(num);
                    cols[j].add(num);
                    boxes[boxIndex].add(num);
                }
            }
        }
        return true;
    }


    //https://www.youtube.com/watch?v=Pl7mMcBm2b8&t=558s
    public boolean isValidSudoku3(char[][] board) {

        HashSet<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                char currentVal = board[i][j];

                if (currentVal != '.') {
                    if (!seen.add("row " + i + " value" + currentVal) ||
                            !seen.add("col " + j + " value " + currentVal) ||
                            !seen.add("box " + i / 3 + " - " + j / 3 + " value" + currentVal)) {
                        return false;
                    }
                }

            }


        }

        return true;

    }
}
