package CodingInterviewPatterns.graph;

import java.util.LinkedList;

public class Graph2 {
    int vertices;
    // Array  of lists for
    // Adjacency List Representation
    LinkedList<Integer>[] adj;

    public Graph2(int V) {
        vertices = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // Function to add an edge into the CodingInterviewPatterns.graph
    public void addEdge(int source, int destination) {
        if (source < vertices && destination < vertices) {
            this.adj[source].add(destination);

            //for undirected CodingInterviewPatterns.graph uncomment the line below
            //this.adj[destination].add(source);
        }
    }
}
