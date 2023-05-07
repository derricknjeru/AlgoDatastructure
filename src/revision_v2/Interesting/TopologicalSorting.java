package revision_v2.Interesting;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TopologicalSorting {
    //https://www.youtube.com/watch?v=Q9PIxaNGnig
    //
    static class Graph {
        // No. of vertices
        private int V;
        // Adjacency List as ArrayList of ArrayList's
        private ArrayList<ArrayList<Integer>> adj;

        // Constructor
        Graph(int v) {
            V = v;
            adj = new ArrayList<ArrayList<Integer>>(v);
            for (int i = 0; i < v; ++i)
                adj.add(new ArrayList<Integer>());
        }

        // Function to add an edge into the graph
        void addEdge(int v, int w) {
            adj.get(v).add(w);
        }
    }

    public static void main(String[] args) {
        // Create a graph given in the above diagram
        int V = 6;
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Following is a Topological "
                + "sort of the given graph");

        boolean[] seen = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < V; i++) {
            if (!seen[i]) {
                topologicalSorting(g, i, seen, stack); //dfs
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    /**
     * Complexity Analysis:
     * <p>
     * Time Complexity: O(V+E).
     * The above algorithm is simply DFS with an extra stack. So time complexity is the same as DFS which is.
     * Auxiliary space: O(V).
     * The extra space is needed for the stack.
     *
     * @param graph
     * @param v
     * @param seen
     * @param stack
     */

    //NB: Note.md.md: Here, we can also use vector/list/array instead of the stack. If the vector is used then print the elements in reverse order to get the topological sorting.
    private static void topologicalSorting(Graph graph, int v, boolean[] seen, Deque<Integer> stack) {
        seen[v] = true;
        ArrayList<Integer> edges = graph.adj.get(v);
        for (int u : edges) {
            if (!seen[u]) {
                topologicalSorting(graph, u, seen, stack); //dfs
            }
        }
        stack.push(v);
    }
}
