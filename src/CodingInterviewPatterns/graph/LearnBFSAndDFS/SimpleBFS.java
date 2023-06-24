package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import CodingInterviewPatterns.graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SimpleBFS {

    public static void main(String args[]) {
        //https://www.techiedelight.com/find-path-between-vertices-directed-graph/
        //https://www.geeksforgeeks.org/find-if-there-is-a-path-between-two-vertices-in-a-given-graph/
        // Create a graph given in the above diagram
        int V = 4;
        Graph g = new Graph(V);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        int src = 1;
        int dest = 3;
        boolean[] visited = new boolean[V];

        // To store the complete path between source and destination
        Stack<Integer> path1 = new Stack<>();
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        if (hasPathBFS(g, src, dest, visited, parent)) {
            System.out.println("There is a path from " + src + " to " + dest);
            printPath(parent, dest);
        } else System.out.println("There is no path from " + src + " to " + dest);

        src = 3;
        dest = 1;
        // To store the complete path between source and destination
        int[] parent2 = new int[V];
        Arrays.fill(parent2, -1);

        if (hasPathBFS(g, src, dest, visited, parent2))
            System.out.println("There is a path from " + src + " to " + dest);
        else System.out.println("There is no path from " + src + " to " + dest);

        // total number of nodes in the graph (labeled from 0 to 7)
        int n = 8;

        // List of graph edges as per the above diagram
        Graph g2 = new Graph(n);

        g2.addEdge(0, 3);
        g2.addEdge(1, 0);
        g2.addEdge(1, 2);
        g2.addEdge(1, 4);
        g2.addEdge(2, 7);
        g2.addEdge(3, 4);
        g2.addEdge(3, 5);
        g2.addEdge(4, 3);
        g2.addEdge(4, 6);
        g2.addEdge(5, 6);
        g2.addEdge(6, 7);

        // source and destination vertex
        src = 0;
        dest = 7;

        // To store the complete path between source and destination
        int[] parent3 = new int[n];
        Arrays.fill(parent3, -1);
        boolean[] visited2 = new boolean[n];

        if (hasPathBFS(g2, src, dest, visited2, parent3)) {
            System.out.println("There is a path from " + src + " to " + dest);
            printPath(parent3, dest);
        } else System.out.println("There is no path from " + src + " to " + dest);


    }

    private static void printPath(int[] parent, int dest) {
        if (parent[dest] == -1) {
            System.out.println(dest);
            return;
        }

        LinkedList<Integer> path = new LinkedList<>();
        int crawl = dest;
        while (crawl != -1) {
            path.addFirst(crawl);
            crawl = parent[crawl];
        }

        for (int vertex : path) {
            System.out.print(vertex + " ");
        }
        System.out.println();
    }

    /**
     * Time Complexity: O(V+E) where V is number of vertices in the graph and E is number of edges in the graph.
     * Space Complexity: O(V).
     * There can be atmost V elements in the stack. So the space needed is O(V).
     *
     * @param g
     * @param src
     * @param dest
     * @param visited
     * @return
     */

    public static boolean hasPathBFS(Graph g, int src, int dest, boolean[] visited, int[] parent) {
        Queue<Integer> q = new LinkedList<>();

        // mark the source vertex as discovered
        visited[src] = true;

        // enqueue source vertex
        q.add(src);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int v = q.poll();

                if (v == dest) return true;

                for (int u : g.adjacencyList.get(v)) {
                    if (!visited[u]) {
                        visited[u] = true;
                        parent[u] = v;
                        q.add(u);
                    }
                }

            }

        }

        return false;
    }
}

