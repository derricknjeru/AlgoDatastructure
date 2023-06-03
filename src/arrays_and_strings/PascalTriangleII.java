package arrays_and_strings;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangleII {
    //https://leetcode.com/problems/pascals-triangle-ii/description/
    //https://chat.openai.com/c/5635421d-757f-484b-9efa-a116ef70148a

    /**
     * In this algorithm, we start by adding the first element of the row as 1. Then, for each subsequent element from index 1 to rowIndex, we calculate its value by summing the previous element and the element at the same index minus one. We update the elements in-place by using the set method of the List class.
     * <p>
     * The time complexity of this algorithm is O(rowIndex^2) because for each row, we iterate through its elements to calculate their values. The space complexity is O(rowIndex) because we only use the space for the current row.
     * <p>
     * I hope this helps! Let me know if you have any further questions.
     *
     * @param rowIndex
     * @return
     */

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            row.add(1); // Set the first element of the row to 1

            for (int j = i - 1; j > 0; j--) {
                // Calculate the current element by summing the previous two elements
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }

        return row;
    }


}
