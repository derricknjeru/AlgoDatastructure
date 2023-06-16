package CodingInterviewPatterns.graph;

import java.util.*;

public class NetworkDelayTime {
    //https://www.youtube.com/watch?v=OHJpOGa_L34&t=26s
    class Solution {

        Map<Integer, List<Node>> graph;

        class Node {
            int weight;
            int vertex;

            Node(int weight, int vertex) {
                this.weight = weight;
                this.vertex = vertex;
            }

        }

        private void buildGraph(int[][] times, int n) {
            for (int i = 1; i <= n; i++) {
                graph.put(i, new ArrayList<>());
            }
            for (int[] time : times) {
                int source = time[0];
                int target = time[1];
                int weight = time[2];
                graph.get(source).add(new Node(weight, target));
            }
        }

        private int[] dijikstra(int n, int source) {
            //create result array
            int[] dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            //create a priority queue
            PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
            minHeap.add(new Node(0, source));
            dist[source] = 0;

            boolean[] visited = new boolean[n + 1];

            while (!minHeap.isEmpty()) {

                Node node = minHeap.remove();

                int currNode = node.vertex;
                int weight = node.weight;

                if (visited[currNode]) continue;
                visited[currNode] = true;

                if (!graph.containsKey(currNode)) continue;

                for (Node edge : graph.get(currNode)) {
                    int neiNode = edge.vertex;
                    int neiWeight = edge.weight;

                    int nextWeight = weight + neiWeight;

                    if (!visited[neiNode] && nextWeight < dist[neiNode]) {
                        dist[neiNode] = nextWeight;
                        minHeap.add(new Node(nextWeight, neiNode));
                    }
                }

            }

            return dist;
        }

        public int networkDelayTime(int[][] times, int n, int k) {
            graph = new HashMap<>();

            buildGraph(times, n);

            int[] dist = dijikstra(n, k);
            int ans = 0;

            for (int i = 1; i <= n; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    return -1;
                }

                ans = Math.max(dist[i], ans);
            }

            return ans;

        }


    }
}
