package CodingInterviewPatterns.matrix;

import java.util.HashSet;
import java.util.Set;

public class NumberOfDistinctIslands {
    //https://aaronice.gitbook.io/lintcode/graph_search/number-of-distinct-islands
    //https://www.youtube.com/watch?v=c1ZxUOHlulo

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };

        int result1 = solution.countDistinctIslands(grid1);
        System.out.println("Result 1: " + result1); // Expected output: 1

        int[][] grid2 = {
                {1, 1, 0, 0, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };

        int result2 = solution.countDistinctIslands(grid2);
        System.out.println("Result 2: " + result2); // Expected output: 3
    }


    static class Solution {

        // Time Complexity: O(rows * cols)
        public int countDistinctIslands(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            Set<String> islands = new HashSet<>();

            // Iterate through the entire matrix
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        StringBuilder islandRepresentation = new StringBuilder();
                        // Explore the island and generate its unique representation
                        dfs(grid, i, j, "o", islandRepresentation);
                        // Add the unique representation to the set
                        islands.add(islandRepresentation.toString());
                    }
                }
            }

            // The size of the set represents the number of distinct islands
            return islands.size();
        }

        // Depth-first search to explore the island and generate its unique representation
        private void dfs(int[][] grid, int i, int j, String direction, StringBuilder islandRepresentation) {
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
                return;
            }

            grid[i][j] = 0; // Mark the cell as visited
            islandRepresentation.append(direction); // Append the current direction to the representation

            // Explore neighboring cells in all directions
            dfs(grid, i, j - 1, "l", islandRepresentation);
            dfs(grid, i, j + 1, "r", islandRepresentation);
            dfs(grid, i + 1, j, "d", islandRepresentation);
            dfs(grid, i - 1, j, "u", islandRepresentation);

            islandRepresentation.append("x"); // Append "x" to signify the end of the island
        }
    }

}
