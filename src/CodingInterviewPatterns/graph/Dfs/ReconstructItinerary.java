package CodingInterviewPatterns.graph.Dfs;

import java.util.*;

public class ReconstructItinerary {
    //https://leetcode.com/problems/reconstruct-itinerary/description/
    //https://www.youtube.com/watch?v=j31ZOupyrAs&t=1246s
    public static void main(String[] args) {
        /**
         * Time Complexity:
         *
         * Building the graph: O(N), where N is the number of tickets. It iterates over each ticket once.
         * Depth-First Search (DFS): O(log E), where E is the number of edges in the graph. The time complexity arises from retrieving the neighbors from the graph's HashMap, which uses a PriorityQueue with a logarithmic time complexity.
         * Therefore, the overall time complexity is O(N + log E), as we consider both the time complexity of building the graph and the time complexity of performing DFS.
         *
         * Space Complexity:
         *
         * Building the graph: O(V + E), where V is the number of vertices and E is the number of edges in the graph. The space complexity accounts for the graph representation using a HashMap and PriorityQueue.
         * Depth-First Search (DFS): O(1) for constant additional space used by variables and the function call stack.
         */

        Solution solution = new Solution();

        // Example 1
        List<List<String>> tickets1 = new ArrayList<>();
        tickets1.add(Arrays.asList("MUC", "LHR"));
        tickets1.add(Arrays.asList("JFK", "MUC"));
        tickets1.add(Arrays.asList("SFO", "SJC"));
        tickets1.add(Arrays.asList("LHR", "SFO"));
        List<String> itinerary1 = solution.findItinerary(tickets1);
        System.out.println("Example 1:");
        System.out.println(itinerary1); // Expected output: [JFK, MUC, LHR, SFO, SJC]

        // Example 2
        List<List<String>> tickets2 = new ArrayList<>();
        tickets2.add(Arrays.asList("JFK", "SFO"));
        tickets2.add(Arrays.asList("JFK", "ATL"));
        tickets2.add(Arrays.asList("SFO", "ATL"));
        tickets2.add(Arrays.asList("ATL", "JFK"));
        tickets2.add(Arrays.asList("ATL", "SFO"));
        List<String> itinerary2 = solution.findItinerary(tickets2);
        System.out.println("Example 2:");
        System.out.println(itinerary2); // Expected output: [JFK, ATL, JFK, SFO, ATL, SFO]

        // Additional Example
        List<List<String>> tickets3 = new ArrayList<>();
        tickets3.add(Arrays.asList("A", "B"));
        tickets3.add(Arrays.asList("B", "C"));
        tickets3.add(Arrays.asList("C", "D"));
        List<String> itinerary3 = solution.findItinerary(tickets3);
        System.out.println("Additional Example:");
        System.out.println(itinerary3); // Expected output: [A, B, C, D]
    }

    static class Solution {
        public List<String> findItinerary(List<List<String>> tickets) {
            // Build the graph
            // Time complexity: O(N), where N is the number of tickets
            // Space complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph
            Map<String, PriorityQueue<String>> graph = new HashMap<>();
            for (List<String> ticket : tickets) {
                String source = ticket.get(0);
                String destination = ticket.get(1);

                // Add destination to the neighbors of the source node in the graph
                graph.computeIfAbsent(source, key -> new PriorityQueue<>()).offer(destination);
            }

            // Perform Depth-First Search (DFS) to find the itinerary
            LinkedList<String> result = new LinkedList<>();
            dfs(graph, result, "JFK");

            return result;
        }

        private void dfs(Map<String, PriorityQueue<String>> graph, LinkedList<String> result, String source) {
            // Retrieve the neighbors of the source node
            // Time complexity: O(log E), where E is the number of edges in the graph
            // Space complexity: O(1)
            PriorityQueue<String> neighbors = graph.get(source);

            // Traverse the neighbors recursively
            while (neighbors != null && !neighbors.isEmpty()) {
                String next = neighbors.poll();
                dfs(graph, result, next); // Recursive call to DFS
            }

            // Add the source node to the front of the result list
            // Time complexity: O(1)
            // Space complexity: O(L), where L is the length of the itinerary
            result.addFirst(source);
        }
    }
}
