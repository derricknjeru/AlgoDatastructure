package CodingInterviewPatterns.UnionFind;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIslandII {
    //https://aaronice.gitbook.io/lintcode/union_find/number_of_islands_ii
    //https://evanyang.gitbooks.io/leetcode/content/LeetCode/number_of_islands_ii.html
    //https://www.youtube.com/watch?v=1SFrVShoRYU

    static class Node {
        int parent;
        int rank;
    }

    static class UnionFindByRank {
        private List<Node> dsuf;
        private int count;

        /**
         * Constructs a UnionFind object with the specified size and initializes the disjoint set.
         *
         * @param size the number of elements in the disjoint set
         */
        public UnionFindByRank(int size) {
            dsuf = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Node node = new Node();
                node.parent = i;
                node.rank = 0;
                dsuf.add(node);
                count = 0;
            }
        }

        /**
         * Finds the absolute parent of the subset to which a particular element belongs.
         * Performs path compression optimization by updating the parent during the find operation.
         *
         * @param v the element for which to find the parent
         * @return the absolute parent of the subset
         */
        public int find(int v) {
            if (dsuf.get(v).parent != v) {
                dsuf.get(v).parent = find(dsuf.get(v).parent);  // Path compression: recursively update the parent of x to the root
            }
            return dsuf.get(v).parent;
        }

        /**
         * Performs the UNION operation of two subsets based on their ranks.
         * Updates the parent and rank values accordingly.
         */
        public void union(int x, int y) {
            int absoluteParent1 = find(x);
            int absoluteParent2 = find(y);

            if (absoluteParent1 == absoluteParent2) return;

            if (dsuf.get(absoluteParent1).rank > dsuf.get(absoluteParent2).rank)
                dsuf.get(absoluteParent2).parent = absoluteParent1;
            else if (dsuf.get(absoluteParent1).rank < dsuf.get(absoluteParent2).rank)
                dsuf.get(absoluteParent1).parent = absoluteParent2;
            else {
                dsuf.get(absoluteParent1).parent = absoluteParent2;
                dsuf.get(absoluteParent2).rank += 1;
            }

            // Decrement count after merging subsets
            count--;
        }

        public int getCount() {
            return count;
        }
    }


    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];
        UnionFindByRank uf = new UnionFindByRank(m * n);
        List<Integer> result = new ArrayList<>();

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] position : positions) {
            int row = position[0];
            int col = position[1];

            if (grid[row][col] == 0) {
                grid[row][col] = 1;
                uf.count++;

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
           System.out.println(uf.count);
            result.add(uf.getCount());
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
