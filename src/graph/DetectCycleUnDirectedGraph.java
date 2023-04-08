package graph;

public class DetectCycleUnDirectedGraph {
    public static void main(String[] args) {
        //https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
        //https://www.youtube.com/watch?v=6ZRhq2oFCuo&t=4s


        int V = 5;

        Graph g1 = new Graph(V);

        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);


        if (isCyclic(g1))
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");

        Graph g2 = new Graph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);

        if (isCyclic(g2))
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");

    }

    private static boolean isCyclic(Graph g) {
        int v = g.vertices;

        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                if (detectCycleUtil(g, i, visited, -1)) return true;
            }
        }
        return false;
    }

    /**
     * Time Complexity: O(V+E), The program does a simple DFS Traversal of the graph which is
     * represented using an adjacency list. So the time complexity is O(V+E).
     * Auxiliary Space: O(V), To store the visited array O(V) space is required.
     *
     * @param g
     * @param v
     * @param visited
     * @param parent
     * @return
     */

    private static boolean detectCycleUtil(Graph g, int v, boolean[] visited, int parent) {
        // Mark the current node as visited
        visited[v] = true;

        for (int u : g.adjacencyList.get(v)) {
            // If an adjacent is not
            // visited, then recur for that
            // adjacent
            if (!visited[u]) {
                if (detectCycleUtil(g, u, visited, v)) return true;
            }
            // If an adjacent is visited
            // and not parent of current
            // vertex, then there is a cycle.
            else {
                if (u != parent) return true;
            }
        }

        return false;
    }
}
