package CodingInterviewPatterns.graph.ConnectedComponents;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponentsDFS {
    /**
     * DFS and Union-Find have their advantages and are suitable for different scenarios:
     * <p>
     * DFS is suitable when the graph is represented using an adjacency list or matrix,
     * and the focus is on exploring the entire graph or finding connected components starting from specific vertices.
     * DFS can be implemented recursively or iteratively using a stack, and it is often used in graph traversal problems.
     * <p>
     * Union-Find is suitable when the graph is given in terms of edges or when the focus is on connectivity between vertices.
     * It efficiently handles unions and finds the parent of a vertex,
     * making it a good choice for determining the number of connected components or finding connected vertices.
     * <p>
     * In summary, DFS is suitable when the graph is represented using an adjacency list or matrix
     * and the goal is to explore the entire graph or find connected components.
     * Union-Find is suitable when the graph is given in terms of edges or when the focus is on connectivity between vertices.
     * The choice between these algorithms depends on the specific problem requirements and the representation of the graph.
     *
     * @param args
     */
    //  /**
    //         *  Given an undirected graph, write a function to find the number of islands or connected components in the graph.
    //         *
    //         *                   0
    //         *                 /    \
    //         *                7----- 8
    //         *
    //         *                5-----3
    //         *
    //         *                  4
    //         *              /      \
    //         *              1      2
    //         *              \      /
    //         *                  6
    //         */
    public static void main(String[] args) {

        int V = 9;
        int[][] edges = {
                {0, 7},
                {0, 8},
                {1, 6},
                {2, 6},
                {3, 5},
                {4, 6}
        };

        List<List<Integer>> components = countConnectedComponents(V, edges);
        System.out.println("Components (DFS):");
        for (List<Integer> component : components) {
            System.out.println(component);
        }

    }

    private static List<List<Integer>> countConnectedComponents(int V, int[][] edges) {
        if (V <= 0 || edges == null || edges.length == 0) return new ArrayList<>();

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

        //store paths of connected components
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                count++;
                List<Integer> component = new ArrayList<>();
                exploreComponents(graph, i, visited, component);
                result.add(component);
            }
        }
        //count is equal to the component
        return result;
    }

    private static void exploreComponents(List<List<Integer>> graph, int src, boolean[] visited, List<Integer> paths) {
        visited[src] = true;
        paths.add(src);
        for (int u : graph.get(src)) {
            if (!visited[u]) {
                exploreComponents(graph, u, visited, paths);
            }
        }
    }
}
