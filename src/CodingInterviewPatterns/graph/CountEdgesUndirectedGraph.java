package CodingInterviewPatterns.graph;

public class CountEdgesUndirectedGraph {
    /**
     * Idea is based on Handshaking Lemma. Handshaking lemma is about undirected CodingInterviewPatterns.graph.
     * In every finite undirected CodingInterviewPatterns.graph number of vertices with odd degree is always even.
     * The handshaking lemma is a consequence of the degree sum formula (also sometimes called the handshaking lemma)
     * <p>
     * <p>
     * So we traverse all vertices, compute sum of sizes of their adjacency lists, and finally returns sum/2.
     *
     * Nothing too tricky going on here. We simply traverse through the complete adjacency list and count the size of each linked list. In an undirected CodingInterviewPatterns.graph, the number of edges is always even because the edges are bidirectional. Hence, to get the number of unique edges, we halve the total sum.
     */

    //https://www.youtube.com/watch?v=RBhqV0ZXYi0
    //https://www.geeksforgeeks.org/connected-components-in-an-undirected-graph/
    public static void main(String[] args) {
        int V = 9;
        Graph g = new Graph(V);

        // making above shown CodingInterviewPatterns.graph
        g.addEdgeUn(0, 1);
        g.addEdgeUn(0, 7);
        g.addEdgeUn(1, 2);
        g.addEdgeUn(1, 7);
        g.addEdgeUn(2, 3);
        g.addEdgeUn(2, 8);
        g.addEdgeUn(2, 5);
        g.addEdgeUn(3, 4);
        g.addEdgeUn(3, 5);
        g.addEdgeUn(4, 5);
        g.addEdgeUn(5, 6);
        g.addEdgeUn(6, 7);
        g.addEdgeUn(6, 8);
        g.addEdgeUn(7, 8);

        System.out.println(countEdges(g, V));
    }

    private static int countEdges(Graph g, int V) {

        //Sum of degree = 2(edges)

        int sumOfDegree = 0;
        for (int i = 0; i < V; i++) sumOfDegree += g.adjacencyList.get(i).size();

        return sumOfDegree / 2;

    }
}
