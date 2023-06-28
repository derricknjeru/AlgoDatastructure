package CodingInterviewPatterns.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class Matrix01 {
    //https://www.youtube.com/watch?v=UWykmfK7ta4
    //https://www.youtube.com/watch?v=KpOoGf1fWzE
    //https://leetcode.com/problems/01-matrix/

    /**
     * The time complexity of the updateMatrix method can be analyzed as follows:
     *
     * The nested for loop at the beginning has a time complexity of O(m * n), where m is the number of rows and n is the number of columns in the input matrix mat.
     *
     * The subsequent loop that iterates until the queue q is empty executes a number of times proportional to the number of elements in the queue. In the worst case, each cell of the matrix could be added to the queue, resulting in a total of m * n iterations.
     *
     * Within this loop, there is another loop that runs for a maximum of 4 iterations. Hence, the time complexity of the inner loop is constant (O(1)).
     *
     * Overall, the time complexity of the algorithm can be approximated as O(m * n).
     *
     * The space complexity of the algorithm is determined by the auxiliary data structures used:
     *
     * The queue q can hold a maximum of m * n elements in the worst case, where m is the number of rows and n is the number of columns.
     *
     * The boolean matrix seen also has the same dimensions as the input matrix mat.
     *
     * Therefore, the space complexity is O(m * n) as well.
     * @param mat
     * @return
     */
    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return mat;
        }

        int m = mat.length;
        int n = mat[0].length;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] seen = new boolean[m][n];

        //Add all zeros to the queue and mark them as visited
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    seen[i][j] = true;
                    q.offer(new int[]{i, j});
                }
            }
        }

        //define the 4 possible directions
        int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int level = 0;

        while (!q.isEmpty()) {

            int size = q.size();
            level++;

            while (size-- > 0) {

                int[] cell = q.poll();
                int currX = cell[0];
                int currY = cell[1];

                //move in all the for directions
                for (int i = 0; i < 4; i++) {

                    int r = currX + dir[i][0];
                    int c = currY + dir[i][1];

                    //check for base condition/boundaries
                    if (r >= m || r < 0 || c >= n || c < 0 || seen[r][c]) {
                        continue;
                    }

                    q.offer(new int[]{r, c});
                    //  mat[r][c]=level;
                    mat[r][c] = mat[currX][currY] + 1;
                    seen[r][c] = true;
                }
            }

        }

        return mat;

    }
}
