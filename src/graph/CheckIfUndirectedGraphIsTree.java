package graph;

public class CheckIfUndirectedGraphIsTree {
    public static void main(String[] args) {
        //https://www.geeksforgeeks.org/check-given-graph-tree/

        /**
         * Time Complexity: O(V + E)
         * Auxiliary Space: O(V) as we are using the visited array.
         */

        // Create a graph given in the above diagram
        Graph g1 = new Graph(5);
        g1.addEdgeUn(1, 0);
        g1.addEdgeUn(0, 2);
        g1.addEdgeUn(0, 3);
        g1.addEdgeUn(3, 4);

        if (isTree(g1, 5))
            System.out.println("Graph is Tree");
        else
            System.out.println("Graph is not Tree");

        Graph g2 = new Graph(5);
        g2.addEdgeUn(1, 0);
        g2.addEdgeUn(0, 2);
        g2.addEdgeUn(2, 1);
        g2.addEdgeUn(0, 3);
        g2.addEdgeUn(3, 4);

        if (isTree(g2, 5))
            System.out.println("Graph is Tree");
        else
            System.out.println("Graph is not Tree");
    }


    // A recursive function that uses visited[] and parent
    // to detect cycle in subgraph reachable from vertex v.
    static boolean isCyclicUtil(Graph g, int v, boolean[] visited, int parent) {
        // Mark the current node as visited
        visited[v] = true;
        // Recur for all the vertices adjacent to this vertex
        for (int u : g.adjacencyList.get(v)) {

            // If an adjacent is not visited, then recur for
            // that adjacent
            if (!visited[u]) {
                if (isCyclicUtil(g, u, visited, v)) return true;
            }

            // If an adjacent is visited and not parent of
            // current vertex, then there is a cycle.
            else if (u != parent)
                return true;
        }
        return false;
    }

    // Returns true if the graph is a tree, else false.
    static boolean isTree(Graph g, int V) {
        // Mark all the vertices as not visited and not part

        boolean[] visited = new boolean[V];

        // The call to isCyclicUtil serves multiple purposes
        // It returns true if graph reachable from vertex 0
        // is cyclic. It also marks all vertices reachable
        // from 0.
        if (isCyclicUtil(g, 0, visited, -1))
            return false;

        // If we find a vertex which is not reachable from 0
        // (not marked by isCyclicUtil(), then we return false
        for (int u = 0; u < V; u++)
            if (!visited[u])
                return false;

        return true;
    }
}
