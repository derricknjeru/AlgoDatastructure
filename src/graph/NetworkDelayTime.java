package graph;

import java.util.*;

public class NetworkDelayTime {

    static class Node {
        int weight;
        int vertex;

        Node(int weight, int vertex) {
            this.weight = weight;
            this.vertex = vertex;
        }

    }
    //https://www.youtube.com/watch?v=OHJpOGa_L34
    public int networkDelayTime(int[][] times, int n, int k) {

        Map<Integer, List<Node>> graph = new HashMap<>();// adj list graph


        // Build the adjacency list
        for (int[] arr : times) {
            int source = arr[0];
            int dest = arr[1];
            int weight = arr[2];

            graph.putIfAbsent(source, new ArrayList<>());
            graph.get(source).add(new Node(weight, dest));
        }

        int[] result = dijkstra(graph, n, k);

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, result[i]);
        }

        // INT_MAX signifies atleat one node is unreachable
        return answer == Integer.MAX_VALUE ? -1 : answer;

    }


    private static int[] dijkstra(Map<Integer, List<Node>> graph, int vertices, int source) {
        //create a min heap of pairs <distance,node> sorted by distance
        Queue<Node> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.weight)
        );

        int[] dist = new int[vertices + 1]; //shortest distance for each vertex
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean visited[] = new boolean[vertices + 1];

        // Distance for starting node is 0
        dist[source] = 0;
        minHeap.add((new Node(0, source)));

        while (!minHeap.isEmpty()) {
            Node topPair = minHeap.remove();
            int currNode = topPair.vertex;
            int currDistance = topPair.weight;

            if (!graph.containsKey(currNode)) {
                continue;
            }

            if (visited[currNode]) continue;
            visited[currNode] = true;


            for (Node edge : graph.get(currNode)) {
                int weight = edge.weight;
                int neighborNode = edge.vertex;
                int nextDist = currDistance + weight;

                if (!visited[neighborNode] && nextDist < dist[neighborNode]) {
                    dist[neighborNode] = nextDist;
                    minHeap.add((new Node(nextDist, neighborNode)));

                }

            }
        }

        return dist;
    }


}