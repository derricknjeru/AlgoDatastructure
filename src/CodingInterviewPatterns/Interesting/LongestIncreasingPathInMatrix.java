package CodingInterviewPatterns.Interesting;

public class LongestIncreasingPathInMatrix {
    //https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
    //https://www.youtube.com/watch?v=bI27Vnwakxc&t=2s
    //https://www.youtube.com/watch?v=uLjO2LUlLN4
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int row = matrix.length;
        int col = matrix[0].length;

        int longestPath = 0;

        int[][] cache = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                longestPath = Math.max(longestPath, calculateLongestPath(matrix, cache, i, j, row, col));
            }
        }

        return longestPath;
    }


    private int calculateLongestPath(int[][] matrix, int[][] cache, int i, int j, int row, int col) {
        if (cache[i][j] > 0) return cache[i][j];

        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        int longestPathDir = 0;

        for (int[] dir : direction) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && y >= 0 && x < row && y < col && matrix[x][y] > matrix[i][j]) {
                longestPathDir = Math.max(longestPathDir, calculateLongestPath(matrix, cache, x, y, row, col));
            }
        }

        cache[i][j] = longestPathDir + 1;

        return cache[i][j];
    }
}
