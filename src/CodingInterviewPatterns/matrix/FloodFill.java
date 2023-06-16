package CodingInterviewPatterns.matrix;

public class FloodFill {
    //https://leetcode.com/problems/flood-fill/editorial/
    public int[][] floodFill(int[][] image, int src, int sc, int color) {
        int currColor = image[src][sc];
        if (currColor != color) {
            int row = image.length;
            int col = image[0].length;
            dfs(image, src, sc, color, currColor, row, col);
        }
        return image;
    }

    private void dfs(int[][] image, int i, int j, int newColor, int oldColor, int row, int col) {
        if (i < 0 || i >= row || j < 0 || j >= col || image[i][j] == newColor) {
            return;
        }
        if (image[i][j] == oldColor) {
            image[i][j] = newColor;

            int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

            for (int[] dir : directions) {
                int x = i + dir[0];
                int y = j + dir[1];

                dfs(image, x, y, newColor, oldColor, row, col);
            }
        }

    }
}

