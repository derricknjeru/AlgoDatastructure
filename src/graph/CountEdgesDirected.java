package graph;

public class CountEdgesDirected {

    private static int numberOfEdges(Graph g, int V) {
        int totalEdges = 0;
        for (int i = 0; i < V; i++) {
            totalEdges += g.adjacencyList.get(i).size();
        }
        return totalEdges;
    }
}
