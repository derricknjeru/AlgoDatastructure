package CodingInterviewPatterns.graph.shortestPathGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class PathWithMinimalCosts {
    public static void main(String[] args) {
        /*
         * Here is a problem that can be modeled using a directed graph with different costs on each edge.
         *-
         * Our input is an array of edges.
         *-
         * const edges = [['A', 'B', 20], ['B', 'C', 10], ['A', 'C', 50], ['B', 'D', 5], ['D', 'C', 2]]
         * ['A', 'B', 20], meaning that from A to B the cost is 20. I wanted to write a function that returns the path between a given place to another place which has the minimal cost. Note that there is no cycles in this graph.
         *-
         * In this case, if the starting place is A and the destination is C then there are three ways to go
         *-
         * path 1: A => C - cost
         * path 2: A => B => C - cost 30
         * path 3: A => B => D => C - cost 27
         * so the function should return the path that has the minimal cost which is A, B, D, C
         */

        Solution sln = new Solution();
        sln.getMinimumCost();


    }

    static class Solution {
        // Create mapping
        public void getMinimumCost() {
            Map<Integer, Character> graphMapping = new HashMap<>();
            graphMapping.put(0, 'A');
            graphMapping.put(1, 'B');
            graphMapping.put(2, 'C');
            graphMapping.put(3, 'D');

            Map<Integer, List<Node>> graph = new HashMap<>();
            int[][] edges = {{0, 1}, {1, 2}, {0, 2}, {1, 3}, {3, 2}};
            // [['A', 'B', 20], ['B', 'C', 10], ['A', 'C', 50], ['B', 'D', 5], ['D', 'C', 2]]
            int[] weights = {20, 10, 50, 5, 2};
            int index = 0;
            for (int[] edge : edges) {
                graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new Node(edge[1], weights[index++]));
            }

            int numVertices = 4; // Corrected the number of vertices
            int[] dist = new int[numVertices];
            int[] pred = new int[numVertices];

            int src = 0;
            dijkstras(graph, dist, pred, numVertices, src); // Corrected the method name

            for (int i = 0; i < numVertices; i++) {
                System.out.println("Shortest distance from " + graphMapping.get(src) + " to " + graphMapping.get(i) + ": " + dist[i]);
                System.out.println("Shortest path: " + constructPath(pred, i, graphMapping)); // Pass the target index and mapping
            }
        }

        private String constructPath(int[] pred, int target, Map<Integer, Character> graphMapping) {
            LinkedList<Integer> paths = new LinkedList<>();

            int crawl = target;
            paths.addFirst(crawl);

            while (pred[crawl] != -1) {
                paths.addFirst(pred[crawl]);
                crawl = pred[crawl];
            }

            StringBuilder pathBuilder = new StringBuilder();
            for (int i = 0; i < paths.size(); i++) {
                pathBuilder.append(graphMapping.get(paths.get(i)));
                if (i < paths.size() - 1) {
                    pathBuilder.append(" -> ");
                }
            }

            return pathBuilder.toString();
        }

        private void dijkstras(Map<Integer, List<Node>> graph, int[] dist, int[] pred, int v, int src) {
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(pred, -1);

            boolean[] visited = new boolean[v];

            pq.add(new Node(src, 0));

            while (!pq.isEmpty()) {
                Node currNode = pq.poll();
                int currV = currNode.vertex;
                int currW = currNode.weight;

                if (visited[currV]) continue;
                visited[currV] = true;

                List<Node> neighbors = graph.get(currV);
                if (neighbors != null) {
                    for (Node nextNode : neighbors) {
                        int nextWeight = nextNode.weight + currW;
                        if (!visited[nextNode.vertex] && nextWeight < dist[nextNode.vertex]) {
                            pq.add(new Node(nextNode.vertex, nextWeight));
                            dist[nextNode.vertex] = nextWeight;
                            pred[nextNode.vertex] = currV;
                        }
                    }
                }
            }
        }

        class Node {
            int weight;
            int vertex;

            Node(int vertex, int weight) {
                this.vertex = vertex;
                this.weight = weight;
            }
        }
    }


    static class Solution2 {

        public void getMinimumCost() {
            String[][] edges = {{"A", "B", String.valueOf(20)}, {"B", "C", String.valueOf(10)}, {"A", "C", String.valueOf(50)}, {"B", "D", "5"}, {"D", "C", String.valueOf(2)}};
            Map<String, List<Node>> graph = new HashMap<>();

            for (String[] edge : edges) {
                addEdge(graph, edge[0], edge[1], Integer.parseInt(edge[2]));
            }

            String startNode = "A";
            int numVertices = graph.size();

            String[] dist = new String[numVertices];
            String[] pred = new String[numVertices];

            dijkstras(graph, numVertices, startNode, dist, pred);

            // Print the shortest distances and paths from the startNode to all other nodes
            for (int i = 0; i < numVertices; i++) {
                System.out.println("Shortest distance from " + startNode + " to " + (char) ('A' + i) + ": " + dist[i]);
                System.out.println("Shortest path: " + getShortestPath(startNode, (char) ('A' + i), pred));
            }
        }

        private String getShortestPath(String start, char end, String[] pred) {
            StringBuilder path = new StringBuilder();
            char currentNode = end;

            while (currentNode != start.charAt(0)) {
                path.insert(0, currentNode);
                currentNode = pred[currentNode - 'A'].charAt(0);
            }

            path.insert(0, start);
            return path.toString();
        }

        public void addEdge(Map<String, List<Node>> graph, String source, String destination, int weight) {
            graph.putIfAbsent(source, new ArrayList<>());
            graph.putIfAbsent(destination, new ArrayList<>());
            graph.get(source).add(new Node(destination, weight));
            graph.get(destination).add(new Node(source, weight)); // Assuming it's an undirected graph
        }


        private void dijkstras(Map<String, List<Node>> g, int v, String src, String[] dist, String[] pred) {
            Queue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));

            // Initialize distances and predecessors
            Arrays.fill(dist, String.valueOf(Integer.MAX_VALUE));
            Arrays.fill(pred, null);
            dist[src.charAt(0) - 'A'] = "0";

            // Add the source node to the minHeap
            minHeap.offer(new Node(src, 0));

            while (!minHeap.isEmpty()) {
                Node currentNode = minHeap.poll();
                String currentVertex = currentNode.vertex;

                // Visit all neighbors of the current node
                for (Node neighbor : g.get(currentVertex)) {
                    int newDist = Integer.parseInt(dist[currentVertex.charAt(0) - 'A']) + neighbor.weight;

                    if (newDist < Integer.parseInt(dist[neighbor.vertex.charAt(0) - 'A'])) {
                        // Update the distance and predecessor
                        dist[neighbor.vertex.charAt(0) - 'A'] = String.valueOf(newDist);
                        pred[neighbor.vertex.charAt(0) - 'A'] = currentVertex;

                        // Add the neighbor to the minHeap for further exploration
                        minHeap.offer(new Node(neighbor.vertex, newDist));
                    }
                }
            }
        }

        class Node {
            int weight;
            String vertex;

            Node(String vertex, int weight) {
                this.vertex = vertex;
                this.weight = weight;
            }
        }

    }


}


