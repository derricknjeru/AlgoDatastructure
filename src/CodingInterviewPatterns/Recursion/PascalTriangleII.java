package CodingInterviewPatterns.Recursion;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangleII {
    //https://chat.openai.com/c/5635421d-757f-484b-9efa-a116ef70148a
    //https://leetcode.com/explore/learn/card/recursion-i/251/scenario-i-recurrence-relation/3234/

    public static List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            return row;
        }

        List<Integer> prevRow = getRow(rowIndex - 1);
        List<Integer> row = new ArrayList<>();
        row.add(1);

        for (int i = 1; i < prevRow.size(); i++) {
            int sum = prevRow.get(i - 1) + prevRow.get(i);
            row.add(sum);
        }

        row.add(1);
        return row;
    }

    public static void main(String[] args) {
        List<Integer> row = getRow(3);
        System.out.println(row);
        // Output: [1, 3, 3, 1]

        row = getRow(0);
        System.out.println(row);
        // Output: [1]

        row = getRow(1);
        System.out.println(row);
        // Output: [1, 1]
    }


}
