package CodingInterviewPatterns.graph;

import java.util.*;

public class DetonateTheMaximumBombs {
//https://www.youtube.com/watch?v=8NPbAvVXKR4
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int V = bombs.length;

        // Build the CodingInterviewPatterns.graph
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {

                int xi = bombs[i][0], yi = bombs[i][1], ri = bombs[i][2];
                int xj = bombs[j][0], yj = bombs[j][1], r2 = bombs[j][2];

                long d = (long) (xi - xj) * (xi - xj) + (long) (yi - yj) * (yi - yj);

                if (d <= (long) ri * ri) {
                    graph.putIfAbsent(i, new ArrayList<>());
                    graph.get(i).add(j);
                }

                if (d <= (long) r2 * r2) {
                    graph.putIfAbsent(j, new ArrayList<>());
                    graph.get(j).add(i);
                }

            }
        }

        int answer = 0;
        for (int i = 0; i < V; i++) {
            answer = Math.max(answer, bfs(graph, i, new HashSet<>()));
        }

        return answer;
    }

    class Solution {
        public int maximumDetonation(int[][] bombs) {
            int V = bombs.length; // Number of vertices (bombs)
            // Declaring an array of ArrayLists to represent the graph
            List<Integer>[] graph = new ArrayList[V];

            // Initializing each element of the array with a new ArrayList
            for (int i = 0; i < V; i++) {
                graph[i] = new ArrayList<Integer>();
            }

            // Constructing the graph based on bomb proximity
            // Time Complexity: O(V^3)
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    long x1 = bombs[i][0], y1 = bombs[i][1], r1 = bombs[i][2];
                    long x2 = bombs[j][0], y2 = bombs[j][1], r2 = bombs[j][2];

                    long d = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);

                    // If bomb i can reach bomb j or vice versa, add an edge
                    if (d <= r1 * r1) {
                        graph[i].add(j);
                    }

                    if (d <= r2 * r2) {
                        graph[j].add(i);
                    }
                }
            }

            int max = 0;
            // Finding the maximum detonation using DFS
            // Time Complexity: O(V^2)
            for (int i = 0; i < V; i++) {
                boolean[] visited = new boolean[V];
                max = Math.max(max, dfs(graph, i, visited));
            }
            return max;
        }

        // Depth-First Search (DFS) to traverse the graph and count detonations
        // Time Complexity: O(V^2)
        private int dfs(List<Integer>[] graph, int i, boolean[] visited) {
            if (visited[i]) return 0;

            visited[i] = true;
            ;

            int count = 1; // Counting the current bomb
            for (int u : graph[i]) {
                if (!visited[u]) {
                    count += dfs(graph, u, visited);
                }
            }
            return count;
        }
    }

    private int dfs(Map<Integer, List<Integer>> graph, int src, Set<Integer> visited) {
        visited.add(src);
        int count = 1;
        for (int neib : graph.getOrDefault(src, new ArrayList<>())) {
            if (!visited.contains(neib)) {
                count += dfs(graph, neib, visited);
            }
        }
        return count;
    }

    private int dfsRecursive(Map<Integer, List<Integer>> graph, int src, Set<Integer> visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(src);
        visited.add(src);
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (int neib : graph.getOrDefault(curr, new ArrayList<>())) {
                if (!visited.contains(neib)) {
                    visited.add(neib);
                    stack.push(neib);
                }
            }
        }
        return visited.size();
    }

    private int bfs(Map<Integer, List<Integer>> graph, int src, Set<Integer> visited) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(src);
        visited.add(src);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int neib : graph.getOrDefault(cur, new ArrayList<>())) {
                if (!visited.contains(neib)) {
                    visited.add(neib);
                    queue.offer(neib);
                }
            }
        }
        return visited.size();
    }


}
