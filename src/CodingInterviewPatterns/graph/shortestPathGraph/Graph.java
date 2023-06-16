package CodingInterviewPatterns.graph.shortestPathGraph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int vertices; //total number of vertices in a CodingInterviewPatterns.graph
    ArrayList<ArrayList<Integer>> adjacencyList; //An array of linked lists to store adjacent vertices.

    public Graph(int V) {
        this.vertices = V;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        if (source < vertices && destination < vertices) {
            this.adjacencyList.get(source).add(destination);

            //for undirected CodingInterviewPatterns.graph uncomment the line below
            //this.adjacencyList.get(destination).add(source);
        }
    }

    public void addEdgeUn(int source, int destination) {
        if (source < vertices && destination < vertices) {
            this.adjacencyList.get(source).add(destination);

            //for undirected CodingInterviewPatterns.graph uncomment the line below
            this.adjacencyList.get(destination).add(source);
        }
    }

    public void printGraph() {
        System.out.println(">>Adjacency List of Directed Graph<<");
        for (int i = 0; i < vertices; i++) {
            if (adjacencyList != null) {
                System.out.print("|" + i + "| => ");

                List<Integer> tempArrayList = adjacencyList.get(i);
                for (int data : tempArrayList) {
                    System.out.print("[" + data + "] -> ");
                }
                System.out.println("null");
            } else {
                System.out.println("|" + i + "| => " + "null");
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);

        g.printGraph();
    }

    private class Graph3 {
        int vertices;
        List<List<Integer>> adj;

        Graph3(int V) {
            this.vertices = V;
            this.adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination) {
            if (source < vertices && destination < vertices) {
                adj.get(source).add(destination);
            }
        }
    }

}
