package CodingInterviewPatterns.graph;


public class FindMotherVertex {
    public static void main(String[] args) {
        //https://www.geeksforgeeks.org/find-a-mother-vertex-in-a-graph/
        //https://www.youtube.com/watch?v=gTt5idzVCgA&t=317s

        //How to find mother vertex

        //1. Undirected connected CodingInterviewPatterns.graph ---> All the vertices are mother vertices
        //as we can reach to all the other nodes in the CodingInterviewPatterns.graph.
        //2. Undirected/Directed disconnected CodingInterviewPatterns.graph ---> In this case, there is no mother vertices
        //as we can not reach all the other nodes in the CodingInterviewPatterns.graph
        //3. Directed connected CodingInterviewPatterns.graph , In this case, we have to find a vertex -v such that we can reach
        //to all the other nodes in the CodingInterviewPatterns.graph through a directed CodingInterviewPatterns.graph.

        /**
         * This solution is based on Kosaraju’s Strongly Connected Component Algorithm.
         * Initially, we run the DFS on the whole CodingInterviewPatterns.graph in a loop (line 13).
         * The DFS ensures that all the nodes in the CodingInterviewPatterns.graph are visited.
         * If the CodingInterviewPatterns.graph is disconnected, the visited array will still have some vertices which haven’t been set to true.
         *
         * The theory is that the last vertex visited in the recursive DFS will be the mother vertex.
         * This is because, at the last vertex, all slots in visited would be true (DFS only stops when all nodes are visited).
         * Hence, we keep track of this last vertex using lastV.
         *
         * Then, we reset the visited array and run the DFS only on lastV.
         * If it visits all nodes, it is a mother vertex. Otherwise, a mother vertex does not exist.
         * The only limitation in this algorithm is that it can detect one mother vertex, even if others exist.
         *
         * Time Complexity: O(V + E)
         * Auxiliary Space: O(V)
         *
         * The DFS of the whole CodingInterviewPatterns.graph works in O(V + E). If a mother vertex exists, the second DFS takes O(V + E) as well. Therefore, the complete time complexity for this algorithm is O(V + E).
         */

        int V = 7;
        int E = 8;

        Graph g = new Graph(V);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(4, 1);
        g.addEdge(6, 4);
        g.addEdge(5, 6);
        g.addEdge(5, 2);
        g.addEdge(6, 0);
        g.printGraph();


        // Function call
        System.out.println("A mother vertex is "
                + motherVertex(g, V));

        System.out.println();

        Graph g2 = new Graph(4);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(3, 0);
        g2.addEdge(3, 1);
        g2.printGraph();
        System.out.println("Mother Vertex is: " + motherVertex(g2, 4));


    }

    private static int motherVertex(Graph g, int V) {
        boolean[] visited = new boolean[V];
        // To store last finished vertex
        // (or mother vertex)
        int lastV = -1;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                DFS_util(g, i, visited);
                lastV = i;
            }
        }

        // If there exist mother vertex (or vertices)
        // in given CodingInterviewPatterns.graph, then v must be one
        // (or one of them)

        // Now check if v is actually a mother
        // vertex (or CodingInterviewPatterns.graph has a mother vertex).
        // We basically check if every vertex
        // is reachable from v or not.

        // Reset all values in visited[] as false
        // and do DFS beginning from v to check
        // if all vertices are reachable from
        // it or not.

        boolean[] check = new boolean[V];
        DFS_util(g, lastV, check);

        for (boolean val : check) if (!val) return -1;

        return lastV;
    }

    private static void DFS_util(Graph g, int v, boolean[] visited) {

        // Mark the current node as
        // visited and print it
        visited[v] = true;

        // Recur for all the vertices
        // adjacent to this vertex
        for (int u : g.adjacencyList.get(v)) {
            if (!visited[u]) DFS_util(g, u, visited);
        }

    }
}
