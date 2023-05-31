package graph;

public class RepresentingEdges {
    public static void main(String[] args) {
        //what does this mean
        //graph =[[1, 2], [2, 3], [3], [4], []]

        /**
         * Vertex 0: [1, 2]
         * Vertex 1: [2, 3]
         * Vertex 2: [3]
         * Vertex 3: [4]
         * Vertex 4: []
         */

        // Create the graph
        Graph graph = new Graph(5);

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // Print the graph
        graph.printGraph();
    }
}
