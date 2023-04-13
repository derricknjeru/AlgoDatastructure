package graph;


import java.util.Stack;

public class SCCKosarajusAlgo {


    public static void main(String[] args) {
        // Create a graph given in the above diagram
        Graph g = new Graph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        printSCCs(g, 5);

        Graph g2 = new Graph(4);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        System.out.println("\nSSC in second graph ");
        printSCCs(g2, 4);

        Graph g3 = new Graph(7);
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 0);
        g3.addEdge(1, 3);
        g3.addEdge(1, 4);
        g3.addEdge(1, 6);
        g3.addEdge(3, 5);
        g3.addEdge(4, 5);
        System.out.println("\nSSC in third graph ");
        printSCCs(g3, 7);

        Graph g4 = new Graph(11);
        g4.addEdge(0, 1);
        g4.addEdge(0, 3);
        g4.addEdge(1, 2);
        g4.addEdge(1, 4);
        g4.addEdge(2, 0);
        g4.addEdge(2, 6);
        g4.addEdge(3, 2);
        g4.addEdge(4, 5);
        g4.addEdge(4, 6);
        g4.addEdge(5, 6);
        g4.addEdge(5, 7);
        g4.addEdge(5, 8);
        g4.addEdge(5, 9);
        g4.addEdge(6, 4);
        g4.addEdge(7, 9);
        g4.addEdge(8, 9);
        g4.addEdge(9, 8);
        System.out.println("\nSSC in fourth graph ");
        printSCCs(g4, 11);

        Graph g5 = new Graph(5);
        g5.addEdge(0, 1);
        g5.addEdge(1, 2);
        g5.addEdge(2, 3);
        g5.addEdge(2, 4);
        g5.addEdge(3, 0);
        g5.addEdge(4, 2);
        System.out.println("\nSSC in fifth graph ");
        printSCCs(g5, 5);
    }

    //Time Complexity: The above algorithm calls DFS, finds reverse of the graph and again calls DFS.
    // DFS takes O(V+E) for a graph represented using adjacency list. Reversing a graph also takes O(V+E) time.
    // For reversing the graph, we simple traverse all adjacency lists. The above algorithm is asymptotically best algorithm,
    //Space Complexity: O(V) as we are using a stack to store the vertices.



    private static void printSCCs(Graph g, int V) {

        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        // Fill vertices in stack according to their finishing
        // times
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                DFSFillOrder_util(g, i, visited, stack);
            }
        }


        // Create a reversed graph
        Graph gr = getTranspose(g, V);

        // Mark all the vertices as not visited (For second
        // DFS)
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Now process all vertices in order defined by
        // Stack

        while (!stack.isEmpty()) {
            int v = stack.pop();

            // Print Strongly connected component of the
            // popped vertex

            if (!visited[v]) {
                DFS_util(gr, v, visited);
                System.out.println();
            }
        }

    }

    private static void DFS_util(Graph gr, int v, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v);
        // Recur for all the vertices adjacent to this vertex
        for (int u : gr.adjacencyList.get(v)) {
            if (!visited[u]) {
                DFS_util(gr, u, visited);
            }
        }
    }

    private static Graph getTranspose(Graph g, int V) {
        Graph reverseGraph = new Graph(V);
        for (int i = 0; i < V; i++) {
            for (int u : g.adjacencyList.get(i)) {
                reverseGraph.addEdge(u, i);
            }
        }
        return reverseGraph;
    }

    private static void DFSFillOrder_util(Graph g, int v, boolean[] visited, Stack<Integer> stack) {
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (int u : g.adjacencyList.get(v)) {
            if (!visited[u]) {
                DFSFillOrder_util(g, u, visited, stack);
            }
        }
        // All vertices reachable from v are processed by now,
        // push v to Stack
        stack.push(v);
    }


}
