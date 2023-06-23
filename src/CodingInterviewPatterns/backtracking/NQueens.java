package CodingInterviewPatterns.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    //https://leetcode.com/problems/n-queens/
    //https://www.youtube.com/watch?v=xFv_Hl4B83A&ab_channel=AbdulBari
    //https://www.youtube.com/watch?v=0DeznFqrgAI&ab_channel=GeeksforGeeks
    //https://www.youtube.com/watch?v=Ph95IHmRp5M&ab_channel=NeetCode


    class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> result = new ArrayList<>();
            int[] queens = new int[n]; // queens[i] represents the column position of the queen in row i
            solveNQueensHelper(queens, 0, result);
            return result;
        }

        private void solveNQueensHelper(int[] queens, int row, List<List<String>> result) {
            if (row == queens.length) {
                // All queens have been successfully placed
                List<String> solution = generateSolution(queens);
                result.add(solution);
                return;
            }

            for (int col = 0; col < queens.length; col++) {
                if (isValid(queens, row, col)) {
                    queens[row] = col; // Place queen at (row, col)
                    solveNQueensHelper(queens, row + 1, result); // Move to the next row
                }
            }
        }

        private boolean isValid(int[] queens, int row, int col) {
            for (int i = 0; i < row; i++) {
                int diff = Math.abs(queens[i] - col);
                if (diff == 0 || diff == row - i) {
                    // Two queens are in the same column or diagonal
                    return false;
                }
            }
            return true;
        }

        private List<String> generateSolution(int[] queens) {
            List<String> solution = new ArrayList<>();
            for (int i = 0; i < queens.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < queens.length; j++) {
                    sb.append(queens[i] == j ? 'Q' : '.');
                }
                solution.add(sb.toString());
            }
            return solution;
        }
    }
}
