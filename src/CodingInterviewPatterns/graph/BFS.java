package CodingInterviewPatterns.graph;

import java.util.*;

public class BFS {
    //https://www.techiedelight.com/breadth-first-search/
    //https://www.youtube.com/watch?v=TIbUeeksXcI&t=21s

    public static void main(String[] args) {
        int V = 15;
        Graph g = new Graph(V);

        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);
        g.addEdge(5, 9);
        g.addEdge(5, 10);
        g.addEdge(4, 7);
        g.addEdge(4, 8);
        g.addEdge(7, 11);
        g.addEdge(7, 12);

        //vertex 0, 13, and 14 are single nodes

        bfsWrapper(V, g);


    }

    private static void bfsWrapper(int V, Graph g) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                bfs(g, i, visited);
            }
        }
    }

    /**
     * The bfsWrapper() function is a wrapper for the bfs() function which actually performs the traversal on one source vertex at a time
     * and outputs all vertices reachable from the source. The reason for using the wrapper function is to make sure we traverse all
     * vertices even when they are not reachable from any other vertex in the CodingInterviewPatterns.graph.
     *
     * @param g
     * @param v
     * @param visited
     */

    /*Time Complexity*/
    //Since this algorithm traverses the whole CodingInterviewPatterns.graph once, its time complexity is O(V + E).
    private static void bfs(Graph g, int v, boolean[] visited) {
        // create a queue for doing Bfs
        Queue<Integer> q = new LinkedList<>();

        visited[v] = true; //mark as visited
        q.add(v); //add to queue

        while (!q.isEmpty()) {
            v = q.poll();
            System.out.print(v + " "); // print value

            // do for every edge (v, u)
            for (int u : g.adjacencyList.get(v)) {
                if (!visited[u]) {
                    // mark it as discovered and enqueue it
                    q.add(u);
                    visited[u] = true;
                }
            }

        }

    }

}
