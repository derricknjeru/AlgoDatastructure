package CodingInterviewPatterns.graph;

import java.util.List;

public class DetectCycleDirectedGraph {
    //https://www.youtube.com/watch?v=wQqFQeucFDc
    //https://www.geeksforgeeks.org/detect-cycle-direct-graph-using-colors/
    static int WHITE = 0, GRAY = 1, BLACK = 2;

    public static void main(String[] args) {
        int V = 4;
        Graph g = new Graph(V);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        //There is a cycle in a CodingInterviewPatterns.graph only if there is a back edge present in the CodingInterviewPatterns.graph.
        // A back edge is an edge that is from a node to itself (selfloop) or one of its ancestor in the tree produced by DFS.
        //https://en.wikipedia.org/wiki/Depth-first_search#Output_of_a_depth-first_search
        if (isCyclic(g, V)) System.out.println("Graph contains cycle");
        else System.out.println("Graph doesn't contain cycle");


        if (isCyclic2(g, V)) System.out.println("Graph contains cycle");
        else System.out.println("Graph doesn't contain cycle");

    }

    private static boolean isCyclic(Graph g, int v) {
        int[] visited = new int[v];

        for (int i = 0; i < v; i++) {
            if (visited[i] == WHITE) {
                if (detectCycle_util(g, i, visited)) return true;
            }
        }

        return false;
    }

    // Recursive function to find if there is back edge
    // in DFS subtree tree rooted with 'u''

    /**
     * Complexity Analysis:
     * Time complexity: O(V + E), where V is the number of vertices and E is the number of edges in the CodingInterviewPatterns.graph.
     * Space Complexity :O(V). Since an extra color array is needed of size V.
     */

    private static boolean detectCycle_util(Graph g, int v, int[] visited) {
        // GRAY : This vertex is being processed (DFS
        // for this vertex has started, but not
        // ended (or this vertex is in function
        // call stack)

        visited[v] = GRAY;

        // Iterate through all adjacent vertices
        for (int u : g.adjacencyList.get(v)) {

            if (visited[u] == GRAY) return true;

            // If v is not processed and there is a back
            // edge in subtree rooted with v
            //A back edge is an edge that is from a node to itself (selfloop) or one of its ancestor in the tree produced by DFS
            if (visited[u] == WHITE && detectCycle_util(g, u, visited)) return true;

        }
        // Mark this vertex as processed
        visited[v] = BLACK;
        return false;
    }

    private static boolean isCyclic2(Graph g, int V) {
        //https://www.youtube.com/watch?v=joqmqvHC_Bo&ab_channel=GeeksforGeeks
        // Mark all the vertices as not visited and
        // not part of recursion stack
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        // Call the recursive helper function to
        // detect cycle in different DFS trees
        for (int i = 0; i < V; i++)
            if (isCyclicUtil(g, i, visited, recStack))
                return true;

        return false;
    }

    // Function to check if cycle exists
    private static boolean isCyclicUtil(Graph g, int i, boolean[] visited,
                                        boolean[] recStack) {
        // Mark the current node as visited and
        // part of recursion stack
        if (recStack[i])
            return true;

        if (visited[i])
            return false;

        visited[i] = true;
        recStack[i] = true;

        List<Integer> children = g.adjacencyList.get(i);

        for (int c : children) {
            if (isCyclicUtil(g, c, visited, recStack))
                return true;
        }

        // Mark this vertex as processed
        recStack[i] = false;
        return false;
    }

}
