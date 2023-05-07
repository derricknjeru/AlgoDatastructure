package graph;

import java.util.Arrays;
import java.util.List;

public class TransitiveClosureOfAgraph {
    //https://www.techiedelight.com/transitive-closure-graph/
    //https://techiedelight.com/practice/?problem=ConnectivityMatrix
    /*

The transitive closure for a digraph G is a digraph G’ with an edge (i, j) corresponding to each directed path from i to j in G. The resultant digraph G’ representation in the form of the adjacency matrix is called the connectivity matrix.

Given a directed graph, return its connectivity matrix. The value of a cell C[i][j] in connectivity matrix C is 1 only if a directed path exists from vertex i to vertex j.

Input: Graph [edges = [(0, 2), (1, 0), (3, 1)], n = 4]
Output: [
	[1, 0, 1, 0],
	[1, 1, 1, 0],
	[0, 0, 1, 0],
	[1, 1, 1, 1]
]

Note.md.md that all diagonal elements in the connectivity matrix are 1 since a path exists from every vertex to itself.

Constraints:

• The graph is implemented using an adjacency list.
• The maximum number of nodes in the graph is 100, i.e., 0 <= n < 100, and each node is represented by its numeric value.

*/


    // Definition for a Graph
    class Graph {
        // List of lists to represent an adjacency list
        List<List<Integer>> adjList;

        // Total number of nodes in the graph
        int n;
    }


    public static int[][] findConnectivityMatrix(Graph graph) {
        int n = graph.n;
        int C[][] = new int[n][n];
        // Write your code here...
        for (int v = 0; v < n; v++) {
            C[v][v] = 1;

            dfs(graph, C, v, v);

            System.out.println(Arrays.toString(C[v]));
        }

        return C;

    }

    private static void dfs(Graph graph, int C[][], int root, int descendant) {
        for (int child : graph.adjList.get(descendant)) {
            // if `child` is an adjacent vertex of descendant(which we are checking by using descendant in for loop), we have
            // found a path from root->child
            if (C[root][child] == 0) {
                C[root][child] = 1;
                dfs(graph, C, root, child);
            }
        }

    }
}

