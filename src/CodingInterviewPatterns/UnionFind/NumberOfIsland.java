package CodingInterviewPatterns.UnionFind;


import java.util.ArrayList;
import java.util.List;

public class NumberOfIsland {
    class Node {
        int parent;
        int rank;
    }

    class UnionFindByRank {
        private List<Node> dsuf;
        private int count = 0;

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
         *
         * @param absoluteParent1 the absolute parent of the first subset
         * @param absoluteParent2 the absolute parent of the second subset
         */
        public void union(int absoluteParent1, int absoluteParent2) {
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

        private int getCount() {
            return count;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int size = m * n;
        UnionFindByRank uf = new UnionFindByRank(size);

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    // Calculate the index of the current cell in the flattened grid representation
                    int currentIndex = i * n + j;
                    uf.count++;

                    // Iterate through the directions array to explore the neighbors of the current cell
                    for (int[] dir : directions) {
                        // Calculate the indices of the neighboring cell
                        int ni = i + dir[0]; // Neighbor's row index
                        int nj = j + dir[1]; // Neighbor's column index

                        // Check if the neighboring cell is within the grid boundaries and is also a valid island cell
                        if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == '1') {
                            // Calculate the index of the neighboring cell in the flattened grid representation
                            int neighborIndex = ni * n + nj;

                            // Find the absolute parent (representative) of the current cell and the neighboring cell
                            int absoluteParent1 = uf.find(currentIndex);
                            int absoluteParent2 = uf.find(neighborIndex);

                            // Check if the cells belong to different islands (different subsets)
                            if (absoluteParent1 != absoluteParent2) {
                                // Merge the subsets by performing the union operation
                                uf.union(absoluteParent1, absoluteParent2);

                                // Decrement the count as the two subsets have been merged into one
                            }
                        }
                    }

                }
            }
        }

        return uf.count;
    }
}

