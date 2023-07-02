package CodingInterviewPatterns.BinarySearch;

public class Search2DMatrix {
    /**
     * We use a single binary search on the flattened matrix (treated as a sorted 1D array)
     * instead of performing a search row by row. We calculate the mid index and then convert
     * it back to the row and column indices to access the corresponding element in the 2D matrix.
     *
     * @param matrix
     * @param target
     * @return
     */

    /**
     * here's the corrected comparison of the three solutions:
     * <p>
     * searchMatrix3
     * <p>
     * Time Complexity: O(m + log(n))
     * Approach: Uses a combination of linear search and binary search. Performs a linear search to identify the potential row where the target might be located and then performs a binary search within that row.
     * Flattened Array Binary Search Solution:
     * <p>
     * searchMatrix
     * Time Complexity: O(log(m * n))
     * Approach: Treats the 2D matrix as a flattened sorted array. Performs a binary search on the entire array by converting the 2D indices to a 1D index.
     * Two-Step Binary Search Solution:
     * <p>
     * searchMatrix2
     * Time Complexity: O(log(m) + log(n))
     * Approach: Performs two separate binary searches. First, a binary search is used to find the target row. Then, another binary search is performed within that row to find the target element.
     *
     * @param matrix
     * @param target
     * @return
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        /**
         * In the line int midValue = matrix[mid / n][mid % n];
         * the expression matrix[mid / n][mid % n] is used to calculate the value at the middle index (mid) in a 2D matrix.
         *
         * Here's a breakdown of what each part of the expression means:
         *
         * mid / n: This division calculates the row index (mid / n) of the middle element in the 2D matrix. Since the 2D matrix is treated as a 1D array, dividing mid by the number of columns (n) gives the quotient representing the row index.
         *
         * mid % n: This modulo operation calculates the column index (mid % n) of the middle element in the 2D matrix. It calculates the remainder when mid is divided by the number of columns (n), which represents the column index.
         *
         * By combining these two operations, the expression matrix[mid / n][mid % n] accesses the element at the middle index (mid) in the 2D matrix.
         *
         * In the context of the binary search algorithm for searching a target value in a matrix, this expression is used to compare the middle element (midValue) with the target value to determine the next step in the binary search process.
         */
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }


    public boolean searchMatrix2(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Binary search to find row
        int left = 0;
        int right = rows - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == matrix[mid][cols - 1]) {
                return true;
            } else if (target > matrix[mid][cols - 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (left >= rows) {
            return false;
        }

        int row = left;
        left = 0;
        right = cols - 1;

        // Binary search in the columns
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == matrix[row][mid]) {
                return true;
            }

            if (target > matrix[row][mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }


    public boolean searchMatrix3(int[][] matrix, int target) {
        int row = matrix.length;

        for (int i = 0; i < row; i++) {
            int j = matrix[i].length - 1;
            if (target <= matrix[i][j]) {
                return binarySearch(matrix, target, i, j);
            }
        }

        return false;
    }

    private boolean binarySearch(int[][] matrix, int target, int row, int right) {
        int left = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (matrix[row][mid] == target) {
                return true;
            }

            if (target > matrix[row][mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return false;

    }


    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };

        int target = 3;
        Search2DMatrix solution = new Search2DMatrix();
        System.out.println(solution.searchMatrix(matrix, target)); // Output: true

        target = 13;
        System.out.println(solution.searchMatrix(matrix, target)); // Output: false
    }


}
