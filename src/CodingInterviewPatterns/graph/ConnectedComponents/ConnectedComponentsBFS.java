package CodingInterviewPatterns.graph.ConnectedComponents;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConnectedComponentsBFS {
    public static void main(String[] args) {
        int V = 9;
        int[][] edges = {{0, 7}, {0, 8}, {1, 6}, {2, 6}, {3, 5}, {4, 6}};
        System.out.println(countIslands(V, edges));

    }

    static int countIslands(int V, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[V];
        int count = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                bfs(v, visited, graph, component);
                count++;
                result.add(component);
            }
        }
        System.out.println("------components");
        System.out.println(result);
        return count;
    }

    static void bfs(int v, boolean[] visited, List<List<Integer>> graph, List<Integer> component) {
        Queue<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.add(v);
        while (!queue.isEmpty()) {
            v = queue.poll();
            component.add(v);
            for (int neighbor : graph.get(v)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}
