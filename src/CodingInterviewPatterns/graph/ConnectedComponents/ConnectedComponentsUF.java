package CodingInterviewPatterns.graph.ConnectedComponents;

import CodingInterviewPatterns.graph.UnionFind.UnionFindByRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectedComponentsUF {
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
        int[][] edges = {{0, 7}, {0, 8}, {1, 6}, {2, 6}, {3, 5}, {4, 6}};

        Map<Integer, List<Integer>> componentMap = connectedComponents(V, edges);
        for (List<Integer> component : componentMap.values()) {
            System.out.println(component);
        }

    }

    private static Map<Integer, List<Integer>> connectedComponents(int v, int[][] edges) {
        if (v <= 0 || edges == null || edges.length == 0) return new HashMap<>();
        UnionFindByRank uf = new UnionFindByRank(v);

        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        Map<Integer, List<Integer>> components = new HashMap<>();
        for (int i = 0; i < v; i++) {
            int parent = uf.find(i);
            components.putIfAbsent(parent, new ArrayList<>());
            components.get(parent).add(i);
        }

        return components;
    }
}
