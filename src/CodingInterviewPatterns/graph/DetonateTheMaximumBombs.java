package CodingInterviewPatterns.graph;

import java.util.*;

public class DetonateTheMaximumBombs {

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
