package stack_and_queue;

import java.util.*;

public class Matrix01 {
    //https://www.youtube.com/watch?v=UWykmfK7ta4
    //https://www.youtube.com/watch?v=KpOoGf1fWzE
    //https://leetcode.com/problems/01-matrix/
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
