package CodingInterviewPatterns.graph.UnionFind;

import java.util.Arrays;

public class CheckingExistenceOfEdgeLengthLimitedPaths {
    class Solution {
        public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
            UnionFindByRank dsu = new UnionFindByRank(n);
            for (int i = 0; i < queries.length; i++) {
                queries[i] = new int[]{queries[i][0], queries[i][1], queries[i]

                        [2], i};
            }
            Arrays.sort(queries, (a, b) -> Integer.compare(a[2], b[2]));
            Arrays.sort(edgeList, (a, b) -> Integer.compare(a[2], b[2]));
            int i = 0;
            boolean[] res = new boolean[queries.length];
            for (int[] q : queries) {
                while (i < edgeList.length && edgeList[i][2] < q[2]) {
                    dsu.union(edgeList[i][0], edgeList[i][1]);
                    i++;
                }
                if (dsu.find(q[0]) == dsu.find(q[1])) {
                    res[q[3]] = true;
                }
            }
            return res;
        }
    }

    class UnionFindByRank {
        private final int[] parent;
        private final int[] rank;
        private int count;

        public UnionFindByRank(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // FIND operation with Path Compression
        // Time Complexity: O(log(n)) (amortized)
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path Compression
            }
            return parent[x];
        }

        /**
         * Performs the UNION operation on two elements by rank-based merging.
         *
         * @param element1 the first element to be unioned
         * @param element2 the second element to be unioned
         */
        // Time Complexity: O(1) (amortized)
        public void union(int element1, int element2) {
            int root1 = find(element1);
            int root2 = find(element2);

            if (root1 != root2) {
                if (rank[root1] > rank[root2]) {
                    parent[root2] = root1;
                } else if (rank[root1] < rank[root2]) {
                    parent[root1] = root2;
                } else {
                    parent[root2] = root1;
                    rank[root1]++;
                }

                count--; // Decrease count when union operation is performed
            }
        }

        /**
         * Returns the current count of disjoint sets.
         *
         * @return the count of disjoint sets
         */
        // Time Complexity: O(1)
        public int getCount() {
            return count;
        }
    }
}
