package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.*;

public class EvaluateDivision {
    public static void main(String[] args) {
        //https://leetcode.com/problems/evaluate-division/
        //https://www.youtube.com/watch?v=Uei1fwDoyKk

        // Example usage:
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));

        double[] values = {2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c")); // Expected result: 6.0 (a -> b -> c)
        queries.add(Arrays.asList("b", "a")); // Expected result: 0.5 (b -> a)
        queries.add(Arrays.asList("a", "e")); // Expected result: -1.0 (No direct path)
        queries.add(Arrays.asList("a", "a")); // Expected result: 1.0 (a -> a)

        Solution solution = new Solution();
        double[] results = solution.calcEquation(equations, values, queries);

        for (double result : results) {
            System.out.println(result);
        }

    }

    static class Solution {
        // Time complexity ---> N * (V+E)
        // Space complexity ---> V+E
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // Build a graph
            Map<String, Map<String, Double>> graph = new HashMap<>();
            for (int i = 0; i < equations.size(); i++) {
                List<String> equation = equations.get(i);
                String eq1 = equation.get(0);
                String eq2 = equation.get(1);
                graph.putIfAbsent(eq1, new HashMap<>());
                graph.putIfAbsent(eq2, new HashMap<>());

                graph.get(eq1).put(eq2, values[i]);
                graph.get(eq2).put(eq1, 1.0 / values[i]);
            }

            double[] result = new double[queries.size()];
            int index = 0;
            for (List<String> query : queries) {
                String q1 = query.get(0);
                String q2 = query.get(1);

                if (!graph.containsKey(q1) || !graph.containsKey(q2)) {
                    result[index++] = -1.0;
                } else {
                    Set<String> visited = new HashSet<>();
                    double ans = dfs(q1, q2, graph, visited);
                    result[index++] = ans;
                }
            }

            return result;
        }

        private double dfs(String start, String end, Map<String, Map<String, Double>> graph, Set<String> visited) {
            if (start.equals(end)) {
                return 1.0;
            }

            visited.add(start);
            Map<String, Double> neighbors = graph.get(start);
            if (neighbors != null) {
                for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
                    String next = entry.getKey();
                    double weight = entry.getValue();
                    if (!visited.contains(next)) {
                        double result = dfs(next, end, graph, visited);
                        if (result != -1.0) {
                            return weight * result;
                        }
                    }
                }
            }

            return -1.0;
        }
    }


    static class Solution2 {
        // Time complexity ---> N * (V+E)
        // Space complexity ---> V+E
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // Build a graph
            Map<String, Map<String, Double>> graph = new HashMap<>();
            for (int i = 0; i < equations.size(); i++) {
                List<String> equation = equations.get(i);
                String eq1 = equation.get(0);
                String eq2 = equation.get(1);
                graph.putIfAbsent(eq1, new HashMap<>());
                graph.putIfAbsent(eq2, new HashMap<>());

                graph.get(eq1).put(eq2, values[i]);
                graph.get(eq2).put(eq1, 1.0 / values[i]);
            }

            double[] result = new double[queries.size()];
            int index = 0;
            for (List<String> query : queries) {
                String q1 = query.get(0);
                String q2 = query.get(1);

                if (!graph.containsKey(q1) || !graph.containsKey(q2)) {
                    result[index++] = -1.0;
                } else {
                    double[] ans = new double[]{-1.0}; // Initialize an array to hold the result
                    Set<String> visited = new HashSet<>();
                    dfs(q1, q2, graph, visited, ans, 1.0);
                    result[index++] = ans[0]; // Retrieve the result from the array
                }
            }

            return result;
        }

        private void dfs(String start, String end, Map<String, Map<String, Double>> graph, Set<String> visited, double[] ans, double temp) {
            if (start.equals(end)) {
                ans[0] = temp; // Update the value in the array
                return;
            }

            visited.add(start);
            Map<String, Double> neighbors = graph.get(start);
            if (neighbors != null) {
                for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
                    String next = entry.getKey();
                    double weight = entry.getValue();
                    if (!visited.contains(next)) {
                        dfs(next, end, graph, visited, ans, weight * temp);
                    }
                }
            }
        }
    }


    static class Solution3 {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // Build a graph
            Map<String, Map<String, Double>> graph = new HashMap<>();
            for (int i = 0; i < equations.size(); i++) {
                List<String> equation = equations.get(i);
                String eq1 = equation.get(0);
                String eq2 = equation.get(1);
                graph.putIfAbsent(eq1, new HashMap<>());
                graph.putIfAbsent(eq2, new HashMap<>());

                graph.get(eq1).put(eq2, values[i]);
                graph.get(eq2).put(eq1, 1.0 / values[i]);
            }

            double[] result = new double[queries.size()];
            int index = 0;
            for (List<String> query : queries) {
                String q1 = query.get(0);
                String q2 = query.get(1);

                if (!graph.containsKey(q1) || !graph.containsKey(q2)) {
                    result[index++] = -1.0;
                } else {
                    result[index++] = bfs(q1, q2, graph);
                }
            }

            return result;
        }

        private double bfs(String start, String end, Map<String, Map<String, Double>> graph) {
            Queue<Pair> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            queue.offer(new Pair(start, 1.0));
            visited.add(start);

            while (!queue.isEmpty()) {
                Pair currentPair = queue.poll();
                String current = currentPair.node;
                double currentValue = currentPair.value;

                if (current.equals(end)) {
                    return currentValue;
                }

                Map<String, Double> neighbors = graph.get(current);
                if (neighbors != null) {
                    for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
                        String next = entry.getKey();
                        double weight = entry.getValue();

                        if (!visited.contains(next)) {
                            queue.offer(new Pair(next, currentValue * weight));
                            visited.add(next);
                        }
                    }
                }
            }

            return -1.0; // If end is not reachable from start
        }

        private class Pair {
            String node;
            double value;

            Pair(String node, double value) {
                this.node = node;
                this.value = value;
            }
        }
    }
}
