package CodingInterviewPatterns.graph.shortestPathGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class PathWithMaximumProbability {
    //https://www.youtube.com/watch?v=kPsDTGcrzGM
    //https://www.youtube.com/watch?v=OHJpOGa_L34&t=202s
    static class Node {
        int node;
        double probability;

        Node(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // Step 1: Create adjacency list
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            double prob = succProb[i];
            graph.get(a).add(new Node(b, prob));
            graph.get(b).add(new Node(a, prob));
        }

        // Step 2: Initialize visited array and minHeap
        boolean[] visited = new boolean[n];
        Queue<Node> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.probability, a.probability));
        double[] maxProb = new double[n];
        Arrays.fill(maxProb, 0);
        maxProb[start] = 1;
        maxHeap.offer(new Node(start, 1));

        // Step 5: Dijkstra's algorithm modified to maximize probability
        while (!maxHeap.isEmpty()) {
            Node currNode = maxHeap.poll();
            int node = currNode.node;

            if (node == end) {
                return currNode.probability;
            }

            if (visited[node]) {
                continue;
            }

            visited[node] = true;

            for (Node neighbor : graph.get(node)) {
                int neighborNode = neighbor.node;
                double prob = neighbor.probability;
                double newProb = currNode.probability * prob;
                if (!visited[neighborNode] && newProb > maxProb[neighborNode]) {
                    maxProb[neighborNode] = newProb;
                    maxHeap.offer(new Node(neighborNode, newProb));
                }
            }
        }

        // Step 6: No path found
        return 0;
    }
}

