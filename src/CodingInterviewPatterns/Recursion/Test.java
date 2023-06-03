package CodingInterviewPatterns.Recursion;

import java.util.ArrayList;
import java.util.List;

public class Test {

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


    public static List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            row.add(0, 1); // Add 1 at the beginning of the row
            System.out.println("what is I" + i);

            for (int j = 1; j < row.size() - 1; j++) {
                int sum = row.get(j) + row.get(j + 1);
                System.out.println("what is J" + j);
                System.out.println("what is J sum" + sum);
                row.set(j, sum); // Update the element in place
            }
        }

        return row;
    }

    public static void main(String[] args) {
        List<Integer> row = getRow(3);
        System.out.println(row);
        // Output: [1, 3, 3, 1]


    }


}
