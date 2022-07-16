package graph;

import java.util.*;

public class Dijkstras {


    public static void main(String[] args) {
        Map<Integer, List<Map.Entry<Integer, Integer>>> graph = new HashMap<>();// adj list graph

        int[][] arrayD = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        // int[][] arrayD = new int[][]{{1,2,1}};

        // Build the adjacency list
        for (int[] arr : arrayD) {
            int source = arr[0];
            int dest = arr[1];
            int weight = arr[2];

            graph.putIfAbsent(source, new ArrayList<>());
            Map<Integer, Integer> map = new HashMap<>();
            map.put(weight, dest);
            Map.Entry<Integer, Integer> entry = map.entrySet().iterator().next();
            graph.get(source).add(entry);
        }

        int n = 4;
        int k = 2;

        int[][] result = dijkstra(graph, n, k);

        System.out.println(Arrays.toString(result[0]));

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if(result[0][i]==Integer.MIN_VALUE){
                System.out.println(-1);
                return;
            }
            answer = Math.max(answer, result[0][i]);
        }

        System.out.println(answer);

    }

    private static int[][] dijkstra(Map<Integer, List<Map.Entry<Integer, Integer>>> graph, int vertices, int source) {
        //create a min heap of pairs <distance,node> sorted by distance
        Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                Comparator.comparing(Map.Entry::getKey)
        );

        int[] dist = new int[vertices + 1]; //shortest distance for each vertex
        Arrays.fill(dist, Integer.MAX_VALUE);

        int[] prev = new int[vertices + 1]; //represent nodes
        Arrays.fill(prev, -1);

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
                    prev[neighborNode] = currNode;

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

        return new int[][]{dist, prev};
    }


    private static void dijkstraSingleTarget(Map<Integer, List<Map.Entry<Integer, Integer>>> graph, int vertices, int source, int target) {
        //create a min heap of pairs <distance,node> sorted by distance
        Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                Comparator.comparing(Map.Entry::getKey)
        );

        int[] dist = new int[vertices + 1]; //shortest distance for each vertex
        Arrays.fill(dist, Integer.MAX_VALUE);

        int[] prev = new int[vertices + 1]; //represent nodes
        Arrays.fill(prev, -1);

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
            if(currNode==target)  break;

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
                    prev[neighborNode] = currNode;

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

        //shortest distance value
        //dist[target]

    }


}

