package Grind75.Week3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Matrix01 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[][] mat1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] result1 = solution.updateMatrix(mat1);
        System.out.println("Example 1:");
        for (int[] row : result1) {
            System.out.println(Arrays.toString(row));
        }

        // Example 2
        int[][] mat2 = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] result2 = solution.updateMatrix(mat2);
        System.out.println("Example 2:");
        for (int[] row : result2) {
            System.out.println(Arrays.toString(row));
        }
    }
    //https://www.youtube.com/watch?v=UWykmfK7ta4
    //https://www.youtube.com/watch?v=KpOoGf1fWzE
    //https://leetcode.com/problems/01-matrix/

    /**
     * The time complexity of the updateMatrix method can be analyzed as follows:
     * <p>
     * The nested for loop at the beginning has a time complexity of O(m * n), where m is the number of rows and n is the number of columns in the input matrix mat.
     * <p>
     * The subsequent loop that iterates until the queue q is empty executes a number of times proportional to the number of elements in the queue. In the worst case, each cell of the matrix could be added to the queue, resulting in a total of m * n iterations.
     * <p>
     * Within this loop, there is another loop that runs for a maximum of 4 iterations. Hence, the time complexity of the inner loop is constant (O(1)).
     * <p>
     * Overall, the time complexity of the algorithm can be approximated as O(m * n).
     * <p>
     * The space complexity of the algorithm is determined by the auxiliary data structures used:
     * <p>
     * The queue q can hold a maximum of m * n elements in the worst case, where m is the number of rows and n is the number of columns.
     * <p>
     * The boolean matrix seen also has the same dimensions as the input matrix mat.
     * <p>
     * Therefore, the space complexity is O(m * n) as well.
     *
     * @param
     * @return
     */

   static class Solution {
        public int[][] updateMatrix(int[][] mat) {
            int rows = mat.length;
            if (rows == 0)
                return mat;
            int cols = mat[0].length;

            Queue<int[]> q = new LinkedList<>();

            // Use -1 to represent cells that are visited but not yet updated
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (mat[i][j] == 0) {
                        q.add(new int[]{i, j});
                    } else {
                        mat[i][j] = -1;
                    }
                }
            }

            int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            while (!q.isEmpty()) {
                int[] point = q.poll();
                int p1 = point[0];
                int p2 = point[1];

                // Check all four directions (up, down, left, right) using the directions array
                for (int[] dir : directions) {
                    int x = p1 + dir[0];
                    int y = p2 + dir[1];

                    if (x >= 0 && x < rows && y >= 0 && y < cols && mat[x][y] == -1) {
                        mat[x][y] = mat[p1][p2] + 1;
                        q.add(new int[]{x, y});
                    }
                }
            }

            return mat;
        }
    }

    class Solution2 {
        public int[][] updateMatrix(int[][] mat) {
            int rows = mat.length;
            if (rows == 0)
                return mat;
            int cols = mat[0].length;

            Queue<int[]> q = new LinkedList<>();
            boolean[][] seen = new boolean[rows][cols];

            // multisource BFS
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (mat[i][j] == 0) {
                        q.add(new int[]{i, j});
                        seen[i][j] = true;
                    }
                }
            }
            int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] point = q.poll();
                    int p1 = point[0];
                    int p2 = point[1];

                    for (int[] dir : directions) {
                        int x = p1 + dir[0];
                        int y = p2 + dir[1];

                        if (x >= 0 && x < rows && y >= 0 && y < cols && !seen[x][y]) {
                            mat[x][y] = mat[p1][p2] + 1;
                            q.add(new int[]{x, y});
                            seen[x][y] = true;
                        }
                    }
                }
            }

            return mat;
        }
    }
}
