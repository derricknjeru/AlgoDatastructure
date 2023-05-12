package CodingInterviewPatterns.Interesting;

import java.util.*;

public class Dijkstras {

    static class Node {
        int weight;
        int vertex;

        Node(int weight, int vertex) {
            this.weight = weight;
            this.vertex = vertex;
        }

    }
    // https://www.youtube.com/watch?v=pLElbKBc4RU
    //Space complexity
    //V stands for Nodes
    //E stands for edges

    //The dist & prev array will use -> O(V) -> space complexity;
    //space complexity of heap -> O(V+E);
    //Time complexity O(V+ E log E)


    public static void main(String[] args) {

        int[][] arrayD = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        // int[][] arrayD = new int[][]{{1,2,1}};

        Map<Integer, List<Node>> graph = new HashMap<>();// adj list graph


        // Build the adjacency list
        for (int[] arr : arrayD) {
            int source = arr[0];
            int dest = arr[1];
            int weight = arr[2];

            graph.putIfAbsent(source, new ArrayList<>());
            graph.get(source).add(new Node(weight, dest));
        }

        int n = 4;
        int k = 2;

        int[][] result = dijkstra(graph, n, k);

       // System.out.println(Arrays.toString(result[1]));
        constructPath(result[1]);

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (result[0][i] == Integer.MIN_VALUE) {
                System.out.println(-1);
                return;
            }
            answer = Math.max(answer, result[0][i]);
        }

        System.out.println(answer);

    }

    private static int[][] dijkstra(Map<Integer, List<Node>> graph, int vertices, int source) {
        //create a min heap of pairs <distance,node> sorted by distance
        Queue<Node> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.weight)
        );

        int[] dist = new int[vertices + 1]; //shortest distance for each vertex
        Arrays.fill(dist, Integer.MAX_VALUE);

        int[] prev = new int[vertices + 1]; //represent nodes
        Arrays.fill(prev, -1);

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
                    prev[neighborNode] = currNode;
                    minHeap.add((new Node(nextDist, neighborNode)));

                }

            }


        }

        return new int[][]{dist, prev};
    }


    private static int dijkstraSingleTarget(Map<Integer, List<Node>> graph, int vertices, int source, int target) {
        //create a min heap of pairs <distance,node> sorted by distance
        Queue<Node> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.weight)
        );

        int[] dist = new int[vertices + 1]; //shortest distance for each vertex
        Arrays.fill(dist, Integer.MAX_VALUE);

        int[] prev = new int[vertices + 1]; //represent nodes
        Arrays.fill(prev, -1);

        boolean visited[] = new boolean[vertices + 1];

        // Distance for starting node is 0
        dist[source] = 0;
        minHeap.add((new Node(0, source)));

        while (!minHeap.isEmpty()) {
            Node topPair = minHeap.remove();
            int currNode = topPair.vertex;
            int currDistance = topPair.weight;
            if (currNode == target) {
                break;
            }
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
                    prev[neighborNode] = currNode;
                    minHeap.add((new Node(nextDist, neighborNode)));

                }

            }


        }

        return dist[target];
    }
    public static void constructPath(int[] prev){
        for(int i =prev.length; i>=0; i--){
            System.out.println(prev[0]);
        }
    }

}

