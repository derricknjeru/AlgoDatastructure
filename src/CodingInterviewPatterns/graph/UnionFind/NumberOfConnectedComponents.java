package CodingInterviewPatterns.graph.UnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfConnectedComponents {
    //https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
    //https://leetcode.ca/all/323.html
    //https://www.youtube.com/watch?v=8f1XPm4WOUc

    /**
     * 323. Number of Connected Components in an Undirected Graph
     *
     * Given an undirected graph with n nodes labeled from 0 to n - 1 and
     * a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components
     * in the graph.
     *
     * Example 1:
     *
     * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
     *      0          3
     *      |          |
     *      1 --- 2    4
     * Output: 2
     *
     * Example 2:
     *
     * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
     *      0           4
     *      |           |
     *      1 --- 2 --- 3
     * Output: 1
     *
     * Note:
     *
     * You can assume that no duplicate edges will appear in edges.
     * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
     */

    /**
     * The performance of Depth-First Search (DFS) and Union-Find algorithms can vary
     * depending on the specific characteristics of the graph and the input data.
     * In general, both algorithms have different time complexities and may perform better in different scenarios.
     * <p>
     * DFS has a time complexity of O(V + E), where V is the number of nodes (vertices) and E is the number of edges in the graph.
     * It explores all reachable nodes from a given starting node, which makes it efficient for sparse graphs with fewer edges.
     * <p>
     * Union-Find has a time complexity of O(E * α(V)), where V is the number of nodes (vertices),
     * E is the number of edges, and α(V) is the inverse Ackermann function, which grows very slowly.
     * The time complexity is dominated by the union operations performed on each edge.
     * Union-Find is particularly efficient for dense graphs with many edges.
     * <p>
     * In terms of time complexity, Union-Find has a potentially better performance than DFS.
     */

    static class Solution {
        public int countComponents(int n, int[][] edges) {
            UnionFindByRank un = new UnionFindByRank(n);

            for (int[] edge : edges) {
                un.union(edge[0], edge[1]);
            }
            return un.getCount();
        }
    }


    static class Solution2 {
        public int countComponents(int n, int[][] edges) {
            // Create an adjacency list to represent the graph
            List<List<Integer>> adjacencyList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            // Build the adjacency list from the edges
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                adjacencyList.get(u).add(v);
                adjacencyList.get(v).add(u);
            }

            boolean[] visited = new boolean[n];
            int count = 0;

            // Perform DFS for each unvisited node
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(adjacencyList, visited, i);
                    count++;
                }
            }

            return count;
        }

        private void dfs(List<List<Integer>> adjacencyList, boolean[] visited, int node) {
            visited[node] = true;

            // Visit all neighbors of the current node
            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    dfs(adjacencyList, visited, neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage
        int n1 = 5;
        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        int count1 = solution.countComponents(n1, edges1);
        System.out.println("Number of connected components: " + count1);
        // Expected output: 2

        int n2 = 5;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        int count2 = solution.countComponents(n2, edges2);
        System.out.println("Number of connected components: " + count2);
        // Expected output: 1
    }


}
