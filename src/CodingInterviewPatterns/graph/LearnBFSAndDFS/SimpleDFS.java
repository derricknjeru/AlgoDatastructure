package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import CodingInterviewPatterns.graph.Graph;

import java.util.Stack;

public class SimpleDFS {
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

        if (hasPathDFS(g, src, dest, visited, path1)) {
            System.out.println("There is a path from " + src + " to " + dest);
            System.out.println("The complete path is " + path1);
        } else System.out.println("There is no path from " + src + " to " + dest);

        src = 3;
        dest = 1;
        // To store the complete path between source and destination
        Stack<Integer> path2 = new Stack<>();

        if (hasPathDFS(g, src, dest, visited, path2)) System.out.println("There is a path from " + src + " to " + dest);
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
        Stack<Integer> path = new Stack<>();
        boolean[] visited2 = new boolean[n];

        if (hasPathDFS(g2, src, dest, visited2, path)) {
            System.out.println("There is a path from " + src + " to " + dest);
            System.out.println("The complete path is " + path);
        } else System.out.println("There is no path from " + src + " to " + dest);


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

    public static boolean hasPathDFS(Graph g, int src, int dest, boolean[] visited, Stack<Integer> path) {
        visited[src] = true;
        // include the current node in the path
        path.add(src);

        if (src == dest) return true;

        for (int u : g.adjacencyList.get(src)) {
            if (!visited[u]) {
                if (hasPathDFS(g, u, dest, visited, path)) return true;
            }
        }

        // backtrack: remove the current node from the path
        path.pop();
        return false;
    }
}
