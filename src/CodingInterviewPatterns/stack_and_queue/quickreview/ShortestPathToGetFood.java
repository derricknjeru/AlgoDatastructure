package CodingInterviewPatterns.stack_and_queue.quickreview;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathToGetFood {
    //https://leetcode.com/problems/shortest-path-to-get-food/
    //https://leetcode.ca/all/1730.html
    //https://www.youtube.com/watch?v=bY4I3pCZLTg
    //https://leetcode.ca/2021-03-13-1730-Shortest-Path-to-Get-Food/#:~:text=You%20can%20travel%20to%20any,reach%20food%2C%20return%20%2D1%20.

    public static void main(String[] args) {
        //char[][] grid = {{'X', 'X', 'X', 'X', 'X', 'X' }, {'X', '*', 'O', 'O', 'O', 'X' }, {'X', 'O', 'O', '#', 'O', 'X' }, {'X', 'X', 'X', 'X', 'X', 'X' }};
        //char[][] grid = {{'O', '*' }, {'#', 'O' }};
        char[][] grid = {{'X', '*' }, {'#', 'X' }};
        System.out.println(getFood(grid));
    }

    public static int getFood(char[][] grid) {
        //find the starting point
        int startRow = -1, startCol = -1;

        int steps = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '*') {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }

        }
        steps = bfs(grid, startRow, startCol);

        return steps;
    }

    private static int bfs(char[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        q.add(new int[]{row, col});
        visited[row][col] = true;

        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {

                int[] cell = q.remove();

                int currX = cell[0];
                int currY = cell[1];

                if (grid[currX][currY] == '#') {
                    return steps;
                }

                for (int j = 0; j < 4; j++) {
                    int r = currX + dir[j][0];
                    int c = currY + dir[j][1];

                    if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] != 'X' && !visited[r][c]) {
                        visited[r][c] = true;
                        q.add(new int[]{r, c});
                    }
                }

            }
            steps++;
        }
        return -1;
    }
}
