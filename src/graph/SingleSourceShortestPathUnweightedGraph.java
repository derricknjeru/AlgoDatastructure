package graph;

import java.util.LinkedList;
import java.util.Queue;

public class SingleSourceShortestPathUnweightedGraph {
    //https://www.youtube.com/watch?v=T_m27bhVQQQ
    //https://www.geeksforgeeks.org/shortest-path-unweighted-graph/
    public static void main(String[] args) {
        int V = 4;
        Graph g = new Graph(V);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(0, 2);
        g.addEdge(1, 3);

        int source = 0;

        printShortestDistance(g, source, V);

    }

    private static void printShortestDistance(Graph g, int source, int v) {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int[] dist = new int[v];
        int[] pred = new int[v];

        if (!BFS(g, dist, pred, source, v)) {
            System.out.println("Given source and destination" + "are not connected");
        }
        System.out.println("Path is ::");
        for (int i = 0; i < v; i++) {
            System.out.print(dist[i] + " ");
        }

    }

    private static boolean BFS(Graph g, int[] dist, int[] pred, int src, int V) {

        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        Queue<Integer> q = new LinkedList<>();

        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean[] visited = new boolean[V];

        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < V; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        q.add(src);

        while (!q.isEmpty()) {
            int v = q.poll();

            for (int u : g.adjacencyList.get(v)) {
                if (!visited[u]) {
                    visited[u] = true;
                    q.add(u);
                    dist[u] = dist[v] + 1;
                    pred[u] = v;

                    // stopping condition (when we find
                    // our destination)
                    //if (u == dest) return true;
                }
            }
        }
        return false;
    }

}

