package graph;

import java.util.*;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {

        Map<Integer, List<Map.Entry<Integer, Integer>>> graph = new HashMap<>();// adj list graph


        // Build the adjacency list
        for (int[] arr : times) {
            int source = arr[0];
            int dest = arr[1];
            int weight = arr[2];

            graph.putIfAbsent(source, new ArrayList<>());
            Map<Integer, Integer> map = new HashMap<>();
            map.put(weight, dest);
            Map.Entry<Integer, Integer> entry = map.entrySet().iterator().next();
            graph.get(source).add(entry);
        }

        int[] result = dijkstra(graph, n, k);

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, result[i]);
        }

        // INT_MAX signifies atleat one node is unreachable
        return answer == Integer.MAX_VALUE ? -1 : answer;

    }

    private static int[] dijkstra(Map<Integer, List<Map.Entry<Integer, Integer>>> graph, int vertices, int source) {
        //create a min heap of pairs <distance,node> sorted by distance
        Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                Comparator.comparing(Map.Entry::getKey)
        );

        int[] dist = new int[vertices + 1]; //shortest distance for each vertex
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean visited[] = new boolean[vertices + 1];

        // Distance for starting node is 0
        dist[source] = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, source);
        Map.Entry<Integer, Integer> entry = map.entrySet().iterator().next();
        minHeap.add((entry));

        while (!minHeap.isEmpty()) {
            Map.Entry<Integer, Integer> topPair = minHeap.remove();
            int currNode = topPair.getValue();
            int currDistance = topPair.getKey();

            if (!graph.containsKey(currNode)) {
                continue;
            }

            if (visited[currNode]) continue;
            visited[currNode] = true;


            for (Map.Entry<Integer, Integer> edge : graph.get(currNode)) {
                int weight = edge.getKey();
                int neighborNode = edge.getValue();
                int nextDist = currDistance + weight;

                if (!visited[neighborNode] && nextDist < dist[neighborNode]) {
                    dist[neighborNode] = nextDist;

                    /**
                     *
                     */

                    Map<Integer, Integer> newMap = new HashMap<>();
                    newMap.put(nextDist, neighborNode);
                    Map.Entry<Integer, Integer> entry1 = newMap.entrySet().iterator().next();

                    minHeap.add((entry1));

                }

            }


        }

        return dist;
    }

}
