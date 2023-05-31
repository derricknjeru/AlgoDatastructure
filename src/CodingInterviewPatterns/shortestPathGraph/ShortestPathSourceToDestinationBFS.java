package CodingInterviewPatterns.shortestPathGraph;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathSourceToDestinationBFS {
    //https://www.geeksforgeeks.org/shortest-path-unweighted-graph/
    //https://www.youtube.com/watch?v=T_m27bhVQQQ
    public static void main(String[] args) {
        int V = 8;
        Graph g = new Graph(V);
        g.addEdgeUn(0, 1);
        g.addEdgeUn(0, 3);
        g.addEdgeUn(1, 2);
        g.addEdgeUn(3, 4);
        g.addEdgeUn(3, 7);
        g.addEdgeUn(4, 5);
        g.addEdgeUn(4, 6);
        g.addEdgeUn(4, 7);
        g.addEdgeUn(5, 6);
        g.addEdgeUn(6, 7);
        int source = 0, dest = 7;

        printShortestDistance(g, source, dest, V);

        //Directed graph
        Graph g2 = new Graph(5);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 3);
        g2.addEdge(3, 4);
        g2.addEdge(1, 4);

        System.out.println();
        printShortestDistance(g2, 0, 4, 5);
    }

    private static void printShortestDistance(Graph g, int source, int dest, int v) {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int[] dist = new int[v];
        int[] pred = new int[v];

        if (!BFS(g, dist, pred, source, dest, v)) {
            System.out.println("Given source and destination" +
                    "are not connected");
        }

        System.out.println("Target Vertex Distance from Source");
        System.out.println(dist[dest]);

        // LinkedList to store path
        List<Integer> path = new ArrayList<>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        // Print distance
        System.out.println("Shortest path length is: " + dist[dest]);

        // Print path
        System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }


    }

    private static boolean BFS(Graph g, int[] dist, int[] pred, int src, int dest, int V) {

        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        Queue<Integer> q = new LinkedList<>();

        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean[] visited = new boolean[V];

        // initially all vertices are unvisited
        // so v[i] for all I is false
        // and as no path is yet constructed
        // dist[i] for all I set to infinity
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
                    if (u == dest) return true;
                }
            }
        }

        return false;
    }

}
