package CodingInterviewPatterns.Interesting;

import java.util.*;

public class PathWithMaximumProbability{
    //https://www.youtube.com/watch?v=OHJpOGa_L34
    static class Node {

        double weight;
        int vertex;

        Node(double weight, int vertex) {
            this.weight = weight;
            this.vertex = vertex;
        }

    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {

        Map<Integer, List<Node>> graph = new HashMap<>();// adj list graph
        int i = 0;

        // Build the adjacency list
        for (int[] arr : edges) {
            int source = arr[0];
            int dest = arr[1];
            double weight= succProb[i++];

            graph.putIfAbsent(source, new ArrayList<>());
            graph.putIfAbsent(dest, new ArrayList<>());
            //since it is undirected
            graph.get(source).add(new Node(weight, dest));
            graph.get(dest).add(new Node(weight, source));
        }


        return dijkstraSingleTarget(graph, n, start,end);
    }

    private  double dijkstraSingleTarget(Map<Integer, List<Node>> graph, int vertices, int source, int target) {
        //create a min heap of pairs <distance,node> sorted by distance
        Queue<Node> minHeap = new PriorityQueue<>(
                Comparator.comparingDouble(a -> a.weight)
        );

        double[] dist = new double[vertices + 1]; //shortest distance for each vertex
        Arrays.fill(dist, 0.0);

        boolean visited[] = new boolean[vertices + 1];

        // Distance for starting node is 0
        dist[source] = -1;
        minHeap.add((new Node(-1,source)));

        while (!minHeap.isEmpty()) {
            Node topPair = minHeap.remove();
            int currNode = topPair.vertex;
            double currDistance = topPair.weight;

            if (!graph.containsKey(currNode)) {
                continue;
            }

            if (visited[currNode]) continue;
            visited[currNode] = true;


            for (Node edge : graph.get(currNode)) {
                double weight = edge.weight;
                int neighborNode = edge.vertex;
                double nextDist = currDistance * weight;

                if (!visited[neighborNode] && nextDist < dist[neighborNode]) {
                    dist[neighborNode] = nextDist;
                    minHeap.add((new Node(nextDist, neighborNode)));

                }

            }


        }

        return -dist[target];
    }
}