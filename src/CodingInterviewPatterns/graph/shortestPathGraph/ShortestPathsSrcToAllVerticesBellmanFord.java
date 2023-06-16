package CodingInterviewPatterns.graph.shortestPathGraph;

public class ShortestPathsSrcToAllVerticesBellmanFord {
    //Bellman Ford Algorithm
    //https://www.youtube.com/watch?v=FtN3BYH2Zes
    //https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
    //https://www.geeksforgeeks.org/bellman-ford-algorithm-simple-implementation/
    //https://www.youtube.com/watch?v=24HziTZ8_xo
    static class Edge {
        int src = 0, dest = 0, weight = 0;

        Edge() {

        }
    }

    static class Graph {
        int V;
        int E;
        Edge[] edge;

        Graph(int v, int e) {
            V = v;
            E = e;
            edge = new Edge[e];
            for (int i = 0; i < e; i++)
                edge[i] = new Edge();
        }
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices in CodingInterviewPatterns.graph
        int E = 8; // Number of edges in CodingInterviewPatterns.graph

        Graph graph = new Graph(V, E);

        // add edge 0-1 (or A-B in above figure)
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = -1;

        // add edge 0-2 (or A-C in above figure)
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 4;

        // add edge 1-2 (or B-C in above figure)
        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 3;

        // add edge 1-3 (or B-D in above figure)
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 2;

        // add edge 1-4 (or B-E in above figure)
        graph.edge[4].src = 1;
        graph.edge[4].dest = 4;
        graph.edge[4].weight = 2;

        // add edge 3-2 (or D-C in above figure)
        graph.edge[5].src = 3;
        graph.edge[5].dest = 2;
        graph.edge[5].weight = 5;

        // add edge 3-1 (or D-B in above figure)
        graph.edge[6].src = 3;
        graph.edge[6].dest = 1;
        graph.edge[6].weight = 1;

        // add edge 4-3 (or E-D in above figure)
        graph.edge[7].src = 4;
        graph.edge[7].dest = 3;
        graph.edge[7].weight = -3;

        // Function call
        BellmanFord(graph, 0);
    }

    private static void BellmanFord(Graph graph, int start) {
        int V = graph.V;
        int E = graph.E;

        int[] dist = new int[V];

        // Step 1: Initialize distances from src to all
        // other vertices as INFINITE
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        dist[start] = 0;

        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < E; j++) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int w = graph.edge[j].weight;

                int currDist = w + dist[u];
                if (dist[u] != Integer.MAX_VALUE && currDist < dist[v]) {
                    dist[v] = currDist;
                }
            }
        }
        // Step 3: check for negative-weight cycles. The
        // above step guarantees shortest distances if CodingInterviewPatterns.graph
        // doesn't contain negative weight cycle. If we get
        // a shorter path, then there is a cycle.
        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < E; j++) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int w = graph.edge[j].weight;

                int currDist = w + dist[u];
                if (dist[u] != Integer.MAX_VALUE && currDist < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }
        }

        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }
}
