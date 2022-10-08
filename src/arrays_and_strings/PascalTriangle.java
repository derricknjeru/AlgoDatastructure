package arrays_and_strings;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    //https://leetcode.com/problems/pascals-triangle/

    /**
     * Given an integer numRows, return the first numRows of Pascal's triangle.
     * <p>
     * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
     * <p>
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: numRows = 5
     * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     * Example 2:
     * <p>
     * Input: numRows = 1
     * Output: [[1]]
     */

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows == 0) return triangle;

        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);

        for (int i = 1; i < numRows; i++) {
            List<Integer> prev_row = triangle.get(i - 1);
            List<Integer> row = new ArrayList<>();

            row.add(1);

            for (int j = 1; j < i; j++) {
                row.add(prev_row.get(j) + prev_row.get(j - 1));
            }

            row.add(1);

            triangle.add(row);
        }

        return triangle;


    }
}
