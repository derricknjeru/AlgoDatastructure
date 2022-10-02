package arrays_and_strings;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
        int matrix[][] = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
    }

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> ans = new ArrayList<>();

        if (matrix.length == 0) {
            return ans;
        }

        int left = 0;

        int right = matrix[0].length - 1;

        int bottom = matrix.length - 1;

        int top = 0;

        int size = matrix.length * matrix[0].length;

        while (ans.size() < size) {
            //ans.add(1);

            for (int i = left; i <= right && ans.size() < size; i++) {
                ans.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= bottom && ans.size() < size; i++) {
                ans.add(matrix[i][right]);
            }
            right--;

            for (int i = right; i >= left && ans.size() < size; i--) {
                ans.add(matrix[bottom][i]);
            }
            bottom--;

            for (int i = bottom; i >= top && ans.size() < size; i--) {
                ans.add(matrix[i][left]);
            }
            left++;

        }

        return ans;

    }
}
