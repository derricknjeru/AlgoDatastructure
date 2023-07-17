package CodingInterviewPatterns.graph.Dfs;

import CodingInterviewPatterns.graph.Graph;

import java.util.Stack;

public class DFSIterative {
    public static void main(String[] args) {
        int V = 5;
        CodingInterviewPatterns.graph.Graph g = new CodingInterviewPatterns.graph.Graph(V);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);

        //Expected results "01342" or "02143"

        boolean[] discovered = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!discovered[i]) {
                dfsIterative(g, i, discovered);
            }
        }
    }

    private static void dfsIterative(Graph g, int v, boolean[] discovered) {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        while (!stack.isEmpty()) {
            // Pop a vertex from the stack
            v = stack.pop();

            // if the vertex is already discovered yet, ignore it
            if (discovered[v]) {
                continue;
            }

            // we will reach here if the popped vertex `v` is not discovered yet;
            // print `v` and process its undiscovered adjacent nodes into the stack
            discovered[v] = true;
            System.out.print(v + " ");

            for (int u : g.adjacencyList.get(v)) {
                if (!discovered[u]) {
                    stack.push(u);
                }
            }
        }
    }
}
