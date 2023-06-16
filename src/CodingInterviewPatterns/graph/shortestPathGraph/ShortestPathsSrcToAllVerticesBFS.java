package CodingInterviewPatterns.graph.shortestPathGraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathsSrcToAllVerticesBFS {
    //https://practice.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=shortest-path-in-undirected-graph-having-unit-distance
    //https://www.geeksforgeeks.org/shortest-path-unweighted-graph/
    //https://www.youtube.com/watch?v=T_m27bhVQQQ
    public static void main(String[] args) {


        int n = 9, m = 10;
        int[][] edges = {{0, 1}, {0, 3}, {3, 4}, {4, 5}, {5, 6}, {1, 2}, {2, 6}, {6, 7}, {7, 8}, {6, 8}};
        int src = 0;
        //Output:
        //0 1 2 1 2 3 3 4 4

        int[] dist = shortestPath(edges, n, m, src);

        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }

    }

    public static int[] shortestPath(int[][] edges, int n, int m, int src) {
        // Code here
        Graph g = new Graph(n);

        for (int[] edge : edges) {
            g.addEdgeUn(edge[0], edge[1]);
        }

        int[] dist = new int[n];

        bfs(g, src, n, dist);

        return dist;

    }

    private static void bfs(Graph g, int src, int v, int[] dist) {

        Arrays.fill(dist, -1);

        boolean[] seen = new boolean[v];

        Queue<Integer> q = new LinkedList<>();

        q.add(src);
        dist[src] = 0;
        seen[src] = true;

        while (!q.isEmpty()) {
            int s = q.poll();

            for (int u : g.adjacencyList.get(s)) {
                if (!seen[u]) {
                    dist[u] = dist[s] + 1;
                    q.add(u);
                    seen[u] = true;
                }

            }
        }

    }
}

