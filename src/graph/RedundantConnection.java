package graph;

public class RedundantConnection {
    /**
     * In this problem, a tree is an undirected graph that is connected and has no cycles.
     *
     * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
     *
     * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.
     * @param edges
     * @return
     */
    //https://leetcode.com/problems/redundant-connection/
    //https://www.geeksforgeeks.org/disjoint-set-data-structures/?ref=gcse
    public int[] findRedundantConnection(int[][] edges) {
        DisJointSetImp dsu = new DisJointSetImp(edges.length + 1);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) return edge;
        }
        throw new AssertionError();
    }


    class DisJointSetImp {
        int[] rank, parent;
        int n;

        // Constructor
        public DisJointSetImp(int n) {
            rank = new int[n];
            parent = new int[n];
            this.n = n;
            makeSet();
        }

        // Creates n sets with single item in each
        void makeSet() {
            for (int i = 0; i < n; i++) {
                // Initially, all elements are in
                // their own set.
                parent[i] = i;
            }
        }

        // Returns representative of x's set
        int find(int x) {
            // Finds the representative of the set
            // that x is an element of
            if (parent[x] != x) {
                // if x is not the parent of itself
                // Then x is not the representative of
                // his set,
                parent[x] = find(parent[x]);

                // so we recursively call Find on its parent
                // and move i's node directly under the
                // representative of this set
            }

            return parent[x];
        }

        // Unites the set that includes x and the set
        // that includes x
        boolean union(int x, int y) {
            // Find representatives of two sets
            int xRoot = find(x), yRoot = find(y);

            // Elements are in the same set, no need
            // to unite anything.
            if (xRoot == yRoot) return false;

            // If x's rank is less than y's rank
            if (rank[xRoot] < rank[yRoot])

                // Then move x under y  so that depth
                // of tree remains less
                parent[xRoot] = yRoot;

                // Else if y's rank is less than x's rank
            else if (rank[yRoot] < rank[xRoot])

                // Then move y under x so that depth of
                // tree remains less
                parent[yRoot] = xRoot;

            else // if ranks are the same
            {
                // Then move y under x (doesn't matter
                // which one goes where)
                parent[yRoot] = xRoot;

                // And increment the result tree's
                // rank by 1
                rank[xRoot] = rank[xRoot] + 1;
            }

            return true;
        }
    }
}
