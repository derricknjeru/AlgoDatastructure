package CodingInterviewPatterns.graph.UnionFind;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIslandII {
    //https://aaronice.gitbook.io/lintcode/union_find/number_of_islands_ii
    //https://evanyang.gitbooks.io/leetcode/content/LeetCode/number_of_islands_ii.html
    //https://www.youtube.com/watch?v=1SFrVShoRYU

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];
        UnionFindByRank uf = new UnionFindByRank(m * n);
        List<Integer> result = new ArrayList<>();

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int count = 0;

        for (int[] position : positions) {
            int row = position[0];
            int col = position[1];

            if (grid[row][col] == 0) {
                grid[row][col] = 1;
                count++;

                int index = row * n + col;

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1) {
                        int newIndex = newRow * n + newCol;
                        uf.union(index, newIndex);
                    }
                }
            }
            //System.out.println(count);
            result.add(count);
        }

        return result;
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}};

        List<Integer> islandCounts = numIslands2(m, n, positions);
        System.out.println(islandCounts); // Output: [1, 1, 2, 3]

        // Additional test cases
        m = 4;
        n = 5;
        int[][] positions1 = {{0, 0}, {0, 1}, {1, 2}, {2, 1}, {3, 3}};
        islandCounts = numIslands2(m, n, positions1);
        System.out.println(islandCounts); // Output: [1, 1, 2, 3, 4]

        m = 5;
        n = 5;
        int[][] positions2 = {{0, 0}, {1, 1}, {2, 2}, {3, 3}, {4, 4}};
        islandCounts = numIslands2(m, n, positions2);
        System.out.println(islandCounts); // Output: [1, 2, 3, 4, 5]

        m = 5;
        n = 5;
        int[][] positions3 = {{0, 0}, {0, 4}, {2, 2}, {3, 0}, {4, 4}};
        islandCounts = numIslands2(m, n, positions3);
        System.out.println(islandCounts); // Output: [1, 2, 3, 4, 5]
    }


}
