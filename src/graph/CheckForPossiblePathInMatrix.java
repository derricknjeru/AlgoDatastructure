package graph;

import java.util.LinkedList;
import java.util.Queue;

public class CheckForPossiblePathInMatrix {
    public static void main(String[] args) {
        // Given array
        int arr2[][] = {{0, 0, 0, -1, 0},
                {-1, 0, 0, -1, -1},
                {0, 0, 0, -1, 0},
                {-1, 0, -1, 0, -1},
                {0, 0, -1, 0, 0}};

        int arr[][] = {{-1, 0, -1, 0, 0},
                {-1, 0, 0, -1, 0},
                {-1, -1, 0, 0, -1},
                {-1, -1, -1, 0, 0}};

        // path from arr[0][0]
        // to arr[row][col]
        if (isPath(arr, arr.length, arr[0].length))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    private static boolean isPath(int[][] mat, int row, int col) {

        // Directions
        int[][] dir
                = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // Queue
        Queue<pair> q = new LinkedList<>();

        // Insert the top right corner.
        q.add(new pair(0, 0));

        // Until queue is empty
        while (!q.isEmpty()) {
            pair p = q.remove();

            // Mark as visited
            mat[p.Item1][p.Item2] = -1;

            // Destination is reached.
            if (p.Item1 == row - 1 && p.Item2 == col - 1)
                return true;

            // Check all four directions
            for (int i = 0; i < 4; i++) {

                // Using the direction array
                int a = p.Item1 + dir[i][0];
                int b = p.Item2 + dir[i][1];

                // Not blocked and valid
                if (a >= 0 && b >= 0 && a < row && b < col
                        && mat[a][b] != -1) {
                    if (a == row - 1 && b == col - 1)
                        return true;

                    q.add(new pair(a, b));
                }
            }
        }
        return false;
    }

    static class pair {
        int Item1, Item2;

        pair(int x, int y) {
            this.Item1 = x;
            this.Item2 = y;
        }
    }
}
