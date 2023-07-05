package CodingInterviewPatterns.graph.UnionFind;

import java.util.ArrayList;
import java.util.List;

public class GraphValidTree {
    /**
     * Depth-First Search (DFS):
     *
     * The DFS operation is performed to check for the presence of cycles in the graph. In the given implementation, DFS is implemented using recursive calls.
     * In the worst-case scenario, where the graph is a linear chain or a complete graph, the time complexity of DFS is O(V + E), where V is the number of nodes (vertices) and E is the number of edges.
     * However, in the context of the given problem, where the graph is assumed to be a tree, the number of edges E is always equal to V - 1. Therefore, the time complexity of DFS simplifies to O(V).
     * So, in terms of the number of nodes, the time complexity of DFS in this implementation is O(n).
     * Union-Find:
     *
     * The Union-Find data structure is used to determine if the given edges form a valid tree by checking for cycles and performing union operations.
     * The time complexity of both the find and union operations in Union-Find is nearly constant on average. It can be considered as O(α(n)), where α(n) is the inverse Ackermann function.
     * In practice, the inverse Ackermann function is extremely slow-growing and is practically regarded as a constant. Therefore, we can consider the time complexity of both find and union operations in Union-Find as effectively constant time, i.e., O(1).
     * Therefore, in the given implementation:
     *
     * The time complexity of the DFS operation is O(n), where n is the number of nodes.
     * The time complexity of the Union-Find operations is O(1) per operation.
     */
    /**
     * Question:
     * Given a range of nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
     * write a function to determine whether these edges form a valid tree.
     * <p>
     * Write a Java function with the following signature:
     * <p>
     * public boolean isValidTree(int n, int[][] edges)
     * Input:
     * <p>
     * n: An integer representing the number of nodes.
     * edges: A 2D array of integers representing the undirected edges between nodes. Each edge is represented as a pair [u, v], where u and v are node labels.
     * Output:
     * <p>
     * Returns true if the given edges form a valid tree, and false otherwise.
     * Examples:
     * <p>
     * int n1 = 5;
     * int[][] edges1 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
     * System.out.println(isValidTree(n1, edges1));  // true
     * <p>
     * int n2 = 5;
     * int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
     * System.out.println(isValidTree(n2, edges2));  // false
     * Note:
     * <p>
     * You can assume that no duplicate edges will appear in the edges list. Since all edges are undirected, [0, 1] is considered the same as [1, 0] and will not appear together in the edges list.
     */

    static class Solution {
        public boolean isValidTree(int n, int[][] edges) {
            if (edges.length != n - 1) {
                return false;
            }

            UnionFindByRank uf = new UnionFindByRank(n);

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                if (uf.find(u) == uf.find(v)) {
                    return false;
                }

                uf.union(u, v);
            }

            return true;
        }
    }

    static class Solution2 {
        //https://www.youtube.com/watch?v=bXsUuownnoQ
        public boolean isValidTree(int n, int[][] edges) {
            if (edges.length != n - 1) {
                // If the number of edges is not equal to n-1, it cannot form a tree
                return false;
            }

            // Create an adjacency list to represent the graph
            List<List<Integer>> adjacencyList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            // Build the adjacency list from the given edges
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                adjacencyList.get(u).add(v);
                adjacencyList.get(v).add(u);
            }

            // Perform a depth-first search (DFS) to check if the graph is acyclic
            boolean[] visited = new boolean[n];
            if (hasCycle(adjacencyList, visited, 0, -1)) {
                // If the graph contains a cycle, it cannot be a valid tree
                return false;
            }

            // Check if all nodes are connected
            for (boolean nodeVisited : visited) {
                if (!nodeVisited) {
                    // If there is an unvisited node, the graph is not fully connected
                    return false;
                }
            }

            return true;
        }

        private boolean hasCycle(List<List<Integer>> adjacencyList, boolean[] visited, int node, int parent) {
            visited[node] = true;

            // Visit all neighbors of the current node
            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    // If the neighbor is not visited, recursively check for a cycle
                    if (hasCycle(adjacencyList, visited, neighbor, node)) {
                        return true;
                    }
                } else if (neighbor != parent) {
                    // If the neighbor is visited and not the parent, it means there is a cycle
                    return true;
                }
            }

            return false;
        }

    }

    public static void main(String[] args) {
        Solution treeValidator = new Solution();

        int n1 = 5;
        int[][] edges1 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println(treeValidator.isValidTree(n1, edges1));  // true

        int n2 = 5;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println(treeValidator.isValidTree(n2, edges2));  // false
    }
}
