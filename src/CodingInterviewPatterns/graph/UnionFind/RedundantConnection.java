package CodingInterviewPatterns.graph.UnionFind;

import java.util.Arrays;

public class RedundantConnection {

    class UnionFind {
        UnionFind() {

        }
        public int find(int[] parent, int i) {
            if (parent[i] == -1) return i;
            return find(parent, parent[i]);
        }

        public void union(int[] parent, int x, int y) {
            int X = find(parent, x);
            int Y = find(parent, y);

            parent[X] = Y;
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        UnionFind un = new UnionFind();
        int V = edges.length;

        int[] parent = new int[V + 1];
        Arrays.fill(parent, -1);

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            int X = un.find(parent, x);
            int Y = un.find(parent, y);

            if (X == Y) return new int[]{x, y};

            un.union(parent, x, y);

        }

        return new int[0];
    }
}

