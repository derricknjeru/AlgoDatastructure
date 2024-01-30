package CodingInterviewPatterns.matrix;

import java.util.ArrayList;
import java.util.List;

public class UniquePathsInGridWithObstaclesUsing {
    public static void main(String[] args) {
        //https://www.geeksforgeeks.org/unique-paths-in-a-grid-with-obstacles/
        // Problem:
        // Imagine a robot sitting on the upper left corner of a grid with r rows and c columns.
        // The robot can only move in two directions, right and down, but certain cells are "off limits"
        // such that the robot cannot step on them. Design an algorithm to find a path for the robot from
        // the top left to the bottom right.
        // "Off limits" and empty grid are represented by 1 and 0 respectively.
        // Return a valid path, consisting of row number and column number of grids in the path.
        //
        // Example:
        // Input:
        // [
        //   [0,0,0],
        //   [0,1,0],
        //   [0,0,0]
        // ]
        // Output: [[0,0],[0,1],[0,2],[1,2],[2,2]]
        // Note: r, c <= 100

        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        List<List<Integer>> res = Solution.countPath(grid);
        System.out.println("------------Single path ------"); //single path
        System.out.println(res); //single path
        System.out.println("------------Multiple paths ------"); //single path

        //multiple paths:

        List<List<List<Integer>>> allPaths = new Solution2().findAllPaths(grid);
        System.out.println("The size: " + allPaths.size());
        // Print all paths
        for (List<List<Integer>> path : allPaths) {
            System.out.println(path);
        }
    }

    static class Solution {
        // Function to find paths in the grid
        private static List<List<Integer>> countPath(int[][] grid) {
            List<List<Integer>> result = new ArrayList<>();
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];

            // Use an array to act as a mutable boolean (Java doesn't allow modification of primitive parameters)
            boolean[] foundPath = {false};

            // Start DFS from the top-left corner
            dfs(grid, result, 0, 0, visited, foundPath);

            return result;
        }

        // Depth-First Search (DFS) algorithm to traverse the grid
        private static void dfs(int[][] grid, List<List<Integer>> result, int i, int j, boolean[][] visited, boolean[] foundPath) {
            int m = grid.length;
            int n = grid[0].length;

            if (foundPath[0]) return;  // If a path has already been found, stop further exploration

            if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) return;

            if (i == m - 1 && j == n - 1) {
                // Base case: Reached the bottom-right corner
                addPoint(result, i, j);
                foundPath[0] = true;  // Set the flag to true to indicate a path has been found
                return;
            }

            if (grid[i][j] == 0) {
                // Add the current point to the path
                addPoint(result, i, j);
                visited[i][j] = true;

                // Explore right and down directions
                dfs(grid, result, i, j + 1, visited, foundPath); // right
                dfs(grid, result, i + 1, j, visited, foundPath); // down

                visited[i][j] = false;  // Backtrack
            }
        }


        // Helper function to add a point to the result path
        private static void addPoint(List<List<Integer>> result, int i, int j) {
            List<Integer> path = new ArrayList<>();
            path.add(i);
            path.add(j);
            result.add(path);
        }
    }

    static class Solution2 {
        //multiple paths:
        private List<List<List<Integer>>> findAllPaths(int[][] grid) {
            List<List<List<Integer>>> allPaths = new ArrayList<>();
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];

            List<List<Integer>> currentPath = new ArrayList<>();
            dfs(grid, allPaths, currentPath, 0, 0, visited);

            return allPaths;
        }

        private void dfs(int[][] grid, List<List<List<Integer>>> allPaths, List<List<Integer>> currentPath, int i, int j, boolean[][] visited) {
            int m = grid.length;
            int n = grid[0].length;

            if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] == 1) {
                return;
            }

            List<Integer> currentPoint = new ArrayList<>();
            currentPoint.add(i);
            currentPoint.add(j);
            currentPath.add(new ArrayList<>(currentPoint));
            visited[i][j] = true;

            if (i == m - 1 && j == n - 1) {
                // Reached the bottom-right corner, add the current path to allPaths
                allPaths.add(new ArrayList<>(currentPath));
            } else {
                // Explore right and down directions
                dfs(grid, allPaths, currentPath, i, j + 1, visited); // right
                dfs(grid, allPaths, currentPath, i + 1, j, visited); // down
            }

            visited[i][j] = false;  // Backtrack
            currentPath.remove(currentPath.size() - 1);
        }
    }
}

