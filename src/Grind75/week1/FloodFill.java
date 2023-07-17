package Grind75.week1;

public class FloodFill {
    //https://leetcode.com/problems/flood-fill/description/
    //https://leetcode.com/problems/flood-fill/editorial/
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[][] image1 = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println("Original Image 1:");
        printImage(image1);
        int sr1 = 1, sc1 = 1, color1 = 2;
        int[][] result1 = solution.floodFill(image1, sr1, sc1, color1);
        System.out.println("Modified Image 1:");
        printImage(result1);

        // Example 2
        int[][] image2 = {{0, 0, 0}, {0, 0, 0}};
        System.out.println("Original Image 2:");
        printImage(image2);
        int sr2 = 0, sc2 = 0, color2 = 0;
        int[][] result2 = solution.floodFill(image2, sr2, sc2, color2);
        System.out.println("Modified Image 2:");
        printImage(result2);
    }

    // Helper method to print the image
    private static void printImage(int[][] image) {
        for (int[] row : image) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static class Solution {
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int rows = image.length;
            int cols = image[0].length;
            int sourceColor = image[sr][sc];

            // If the starting pixel color is already the newColor, no need to perform flood fill
            if (sourceColor == newColor) {
                return image;
            }

            // Call the helper function to perform the flood fill
            floodFillHelper(image, sr, sc, sourceColor, newColor);

            return image;
        }

        private void floodFillHelper(int[][] image, int row, int col, int sourceColor, int newColor) {
            // Check boundary conditions and if the current pixel has the same color as the source pixel
            if (row < 0 || row >= image.length || col < 0 || col >= image[0].length || image[row][col] != sourceColor) {
                return;
            }

            // Update the current pixel color to the newColor
            image[row][col] = newColor;

            // Recursively call the floodFillHelper for the neighboring pixels
            floodFillHelper(image, row - 1, col, sourceColor, newColor); // Up
            floodFillHelper(image, row + 1, col, sourceColor, newColor); // Down
            floodFillHelper(image, row, col - 1, sourceColor, newColor); // Left
            floodFillHelper(image, row, col + 1, sourceColor, newColor); // Right
        }
    }
}
