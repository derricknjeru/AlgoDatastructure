package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CheckPathExistDirected {
    //https://www.geeksforgeeks.org/find-if-there-is-a-path-between-two-vertices-in-a-given-graph/
    public static void main(String[] args) {
        Graph2 g = new Graph2(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        int u = 1;
        int v = 3;
        if (isReachableDFS(g, u, v, 4))
            System.out.println("There is a path from " + u + " to " + v);
        else
            System.out.println("There is no path from " + u + " to " + v);

        u = 3;
        v = 1;
        if (isReachableDFS(g, u, v, 4))
            System.out.println("There is a path from " + u + " to " + v);
        else
            System.out.println("There is no path from " + u + " to " + v);

    }

    public static boolean isReachableBFS(Graph2 g, int s, int d, int V) {
        boolean[] visited = new boolean[V];
        return bfs(g, s, visited, d);
    }

    private static boolean bfs(Graph2 g, int s, boolean[] visited, int d) {
        Queue<Integer> q = new LinkedList<>();
        visited[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            int v = q.poll();

            for (int u : g.adj[v]) {
                if (u == d) return true;
                if (!visited[u]) {
                    // mark it as discovered and enqueue it
                    q.add(u);
                    visited[u] = true;
                }
            }
        }

        return false;
    }

    public static boolean isReachableDFS(Graph2 g, int s, int d, int V) {
        boolean[] visited = new boolean[V];
        //return dfsIterative(g, s, visited, d);
        dfs(g, s, visited);
        return visited[d];
    }

    private static boolean dfsIterative(Graph2 g, int s, boolean[] visited, int d) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            // Pop a vertex from the stack
            s = stack.pop();

            // if the vertex is already discovered yet, ignore it
            if (visited[s]) {
                continue;
            }

            // we will reach here if the popped vertex `v` is not discovered yet;
            // print `v` and process its undiscovered adjacent nodes into the stack
            visited[s] = true;

            for (int u : g.adj[s]) {

                if (u == d) return true;

                if (!visited[u]) {
                    stack.push(u);
                }
            }
        }

        return false;
    }

    private static void dfs(Graph2 g, int s, boolean[] visited) {
        visited[s] = true;

        for (int u : g.adj[s]) {
            if (!visited[u]) {
                dfs(g, u, visited);
            }
        }
    }
}
