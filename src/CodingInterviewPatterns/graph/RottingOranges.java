package CodingInterviewPatterns.graph;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    //https://www.youtube.com/watch?v=y704fEOx0s0&ab_channel=NeetCode

    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int fresh = 0;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh += 1;
                }
            }
        }

        // BFS
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int count = 0;

        while (!q.isEmpty() && fresh != 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] points = q.poll();

                for (int[] dir : directions) {
                    int x = points[0] + dir[0];
                    int y = points[1] + dir[1];

                    if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] != 1) {
                        continue;
                    }

                    grid[x][y] = 2;
                    q.offer(new int[]{x, y});
                    fresh -= 1;
                }
            }
            count++;
        }

        return fresh == 0 ? count : -1;
    }
}

