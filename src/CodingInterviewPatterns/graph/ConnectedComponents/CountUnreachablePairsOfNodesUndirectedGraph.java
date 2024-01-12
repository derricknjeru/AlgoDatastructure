package CodingInterviewPatterns.graph.ConnectedComponents;

import CodingInterviewPatterns.graph.UnionFind.UnionFindByRank;

import java.util.*;

public class CountUnreachablePairsOfNodesUndirectedGraph {
    public static void main(String[] args) {
        //https://www.youtube.com/watch?v=j6A7H0-sKVI

        Solution solution = new Solution();

        // Example 1
        int n1 = 4;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 0}, {1, 3}};
        long result1 = solution.countPairs(n1, edges1);
        System.out.println("Example 1 Result: " + result1);

        // Example 2
        int n2 = 5;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 0}, {1, 3}, {3, 4}};
        long result2 = solution.countPairs(n2, edges2);
        System.out.println("Example 2 Result: " + result2);

        // Add more test cases as needed


    }

    static class Solution {
        // Time Complexity: O(n log n)
        public long countPairs(int n, int[][] edges) {
            // Initialize UnionFind object, O(n)
            UnionFindByRank uf = new UnionFindByRank(n);
            // Union operation for each edge, O(n)
            for (int[] edge : edges) {
                uf.union(edge[0], edge[1]);
            }

            int[] componentSize = new int[n];
            // Calculate component sizes, O(n log n)
            for (int i = 0; i < n; i++) {
                componentSize[uf.find(i)]++;
            }

            long totalPairs = (long) n * (n - 1) / 2; // Total number of pairs O(1)

            // Subtract pairs within the same connected component, O(n)
            for (int size : componentSize) {
                if (size > 1) {
                    totalPairs -= (long) size * (size - 1) / 2; // Subtract pairs within the same connected component
                }
            }

            return totalPairs;

        }
    }

    class Solution2 {
        // Time Complexity: O(n + m), where n is the number of nodes and m is the number of edges
        public long countPairs(int n, int[][] edges) {
            // Create adjacency list
            List<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }

            // Build graph from edges
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }

            boolean[] visited = new boolean[n];
            Arrays.fill(visited, false);
            int count = 0;

            long totalPairs = (long) n * (n - 1) / 2; // Total number of pairs

            // Traverse each component using DFS
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    count++;
                    int size = dfs(i, graph, visited);

                    // Subtract pairs within the same connected component
                    if (size > 1) {
                        totalPairs -= (long) size * (size - 1) / 2;
                    }
                }
            }

            return totalPairs;
        }

        // Depth-First Search to calculate the size of connected components
        // Time Complexity: O(m), where m is the number of edges in the connected component
        private int dfs(int node, List<Integer>[] graph, boolean[] visited) {
            visited[node] = true;
            int size = 1;

            for (int neighbor : graph[node]) {
                if (!visited[neighbor]) {
                    size += dfs(neighbor, graph, visited);
                }
            }

            return size;
        }
    }
}
