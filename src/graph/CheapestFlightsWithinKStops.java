package graph;

import java.util.*;

public class CheapestFlightsWithinKStops {
    //https://leetcode.com/problems/cheapest-flights-within-k-stops/
    //https://www.youtube.com/watch?v=vWgoPTvQ3Rw
    //https://www.youtube.com/watch?v=IQOG3w4abAg

    class Solution {
        class Node {
            int weight;
            int vertex;
            int stops;

            Node(int weight, int vertex, int stops) {
                this.weight = weight;
                this.vertex = vertex;
                this.stops = stops;
            }

        }

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<Node>> graph = new HashMap<>();// adj list graph

            // Build the adjacency list
            for (int[] arr : flights) {
                int source = arr[0];
                int dest = arr[1];
                int weight = arr[2];

                graph.putIfAbsent(source, new ArrayList<>());
                graph.get(source).add(new Node(weight, dest, k));
            }

            return dijkstraSingleTarget(graph, n, src, dst, k);
        }

        private int dijkstraSingleTarget(Map<Integer, List<Node>> graph, int vertices, int source, int target, int k) {
            //create a min heap of pairs <distance,node> sorted by distance
            Queue<Node> minHeap = new PriorityQueue<>(
                    Comparator.comparingInt(a -> a.weight)
            );

            int[] dist = new int[vertices + 1]; //shortest distance for each vertex
            Arrays.fill(dist, Integer.MAX_VALUE);

            int[] stops = new int[vertices + 1];
            Arrays.fill(stops, Integer.MAX_VALUE);

            // Distance for starting node is 0
            dist[source] = 0;
            stops[source] = 0;
            minHeap.add((new Node(0, source, 0)));

            while (!minHeap.isEmpty()) {
                Node topPair = minHeap.remove();
                int currNode = topPair.vertex;
                int currDistance = topPair.weight;
                int curStop = topPair.stops;

                if (currNode == target) {
                    return currDistance;
                }

                if (curStop == k + 1) {
                    continue;
                }

                if (!graph.containsKey(currNode)) {
                    continue;
                }

                for (Node edge : graph.get(currNode)) {
                    int weight = edge.weight;
                    int neighborNode = edge.vertex;


                    int nextDist = currDistance + weight;
                    int nextStops = curStop + 1;

                    if (nextDist < dist[neighborNode] || nextStops < stops[neighborNode]) {
                        dist[neighborNode] = nextDist;
                        stops[neighborNode] = nextStops;
                        minHeap.add((new Node(nextDist, neighborNode, nextStops)));
                    }

                }

            }

            return -1;
        }
    }
}
