package CodingInterviewPatterns.graph.ConnectedComponents;

import java.util.ArrayList;
import java.util.List;

public class NumberOfProvincesAdjList {
    //https://leetcode.com/problems/number-of-provinces/editorial/

    public static class Solution {
        // Time complexity: O(V^2) where V is the number of vertices in the graph
        public int findCircleNum(int[][] isConnected) {
            int V = isConnected.length;
            List<List<Integer>> graph = initializeGraph(V);
            createGraph(isConnected, graph);

            boolean[] visited = new boolean[V];
            int count = 0;

            // Time complexity: O(V^2) (in the worst case, when the graph is fully connected)
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    dfs(graph, i, visited);
                    count++;
                }
            }

            return count;
        }

        // Time complexity: O(V)
        private List<List<Integer>> initializeGraph(int V) {
            List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i < V; i++) {
                graph.add(new ArrayList<>());
            }

            return graph;
        }

        // Time complexity: O(V^2)
        private void createGraph(int[][] isConnected, List<List<Integer>> graph) {
            int V = isConnected.length;

            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (isConnected[i][j] == 1 && j != i) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }
        }

        // Time complexity: O(V)
        private void dfs(List<List<Integer>> graph, int i, boolean[] visited) {
            visited[i] = true;

            // Time complexity: O(E) (where E is the number of edges in the subgraph of vertex i)
            for (int u : graph.get(i)) {
                if (!visited[u]) {
                    dfs(graph, u, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage:
        int[][] isConnectedExample = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        int result = solution.findCircleNum(isConnectedExample);
        System.out.println("Number of provinces: " + result); // Output: 2
    }
}
