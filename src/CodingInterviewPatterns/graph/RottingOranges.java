package CodingInterviewPatterns.graph;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    //https://leetcode.com/problems/rotting-oranges/description/
    //https://www.youtube.com/watch?v=y704fEOx0s0&ab_channel=NeetCode


    static class Solution {
        public int orangesRotting(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            Queue<int[]> queue = new ArrayDeque<>();
            int freshOranges = 0;

            // Count fresh oranges and add rotten oranges to the queue
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        freshOranges++;
                    } else if (grid[i][j] == 2) {
                        queue.offer(new int[]{i, j});
                    }
                }
            }

            int minutes = 0;
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            while (!queue.isEmpty() && freshOranges > 0) {
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    int[] current = queue.poll();

                    for (int[] dir : directions) {
                        int newRow = current[0] + dir[0];
                        int newCol = current[1] + dir[1];

                        if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1) {
                            grid[newRow][newCol] = 2;
                            freshOranges--;
                            queue.offer(new int[]{newRow, newCol});
                        }
                    }
                }

                minutes++;
            }

            return freshOranges == 0 ? minutes : -1;
        }
    }

    class Solution2 {
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

            // Bfs
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

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid1 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int result1 = solution.orangesRotting(grid1);
        System.out.println("Result 1: " + result1); // Output: 4

        int[][] grid2 = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        int result2 = solution.orangesRotting(grid2);
        System.out.println("Result 2: " + result2); // Output: -1

        int[][] grid3 = {{0, 2}};
        int result3 = solution.orangesRotting(grid3);
        System.out.println("Result 3: " + result3); // Output: 0
    }
}

