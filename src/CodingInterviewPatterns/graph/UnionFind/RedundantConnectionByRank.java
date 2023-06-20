package CodingInterviewPatterns.graph.UnionFind;

public class RedundantConnectionByRank {
    //https://leetcode.com/problems/redundant-connection/
    class Solution {
        public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;
            UnionFind un = new UnionFind(n + 1);

            for (int[] edge : edges) {
                int X = un.find(edge[0]);
                int Y = un.find(edge[1]);

                // If both nodes belong to the same set, it means adding this edge will create a cycle
                if (X == Y)
                    return edge;

                un.union(X, Y);
            }

            // No redundant connection found
            return new int[2];
        }

        class Node {
            int parent;
            int rank;

            Node(int parent, int rank) {
                this.parent = parent;
                this.rank = rank;
            }
        }

        class UnionFind {
            Node[] dsuf;

            UnionFind(int size) {
                dsuf = new Node[size];
                for (int i = 0; i < size; i++) {
                    dsuf[i] = new Node(-1, 0);
                }
            }

            // Find the parent of a node and perform path compression
            // Time complexity: O(log n) amortized
            public int find(int x) {
                if (dsuf[x].parent < 0)
                    return x;
                dsuf[x].parent = find(dsuf[x].parent);
                return dsuf[x].parent;
            }

            // Union two sets by rank
            // Time complexity: O(1) amortized
            public void union(int root1, int root2) {
                if (dsuf[root1].rank > dsuf[root2].rank) {
                    dsuf[root2].parent = root1;
                } else if (dsuf[root2].rank > dsuf[root1].rank) {
                    dsuf[root1].parent = root2;
                } else {
                    dsuf[root2].parent = root1;
                    dsuf[root1].rank += 1;
                }
            }
        }

        // Overall time complexity: O(n), where n is the number of edges
    }
}
