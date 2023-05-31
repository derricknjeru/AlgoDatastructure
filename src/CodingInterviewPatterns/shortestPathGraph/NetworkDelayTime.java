package CodingInterviewPatterns.shortestPathGraph;

import java.util.*;

class NetworkDelayTime {
    //https://www.youtube.com/watch?v=EaphyqKU4PQ
    //https://www.youtube.com/watch?v=OHJpOGa_L34&t=202s
    class Node {
        int time;
        int v;

        Node(int time, int v) {
            this.v = v;
            this.time = time;
        }
    }

    class Graph {
        List<List<Node>> adj;

        Graph(int v) {
            adj = new ArrayList<>();
            for (int i = 0; i <= v; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int v, int u, int time) {
            adj.get(v).add(new Node(time, u));
        }
    }

    private void dijkstras(Graph g, int[] dist, int v, int src) {
        Queue<Node> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time));
        boolean[] visited = new boolean[v + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        minHeap.add(new Node(0, src));
        dist[src] = 0;

        while (!minHeap.isEmpty()) {
            Node topPair = minHeap.remove();
            int currTime = topPair.time;
            int currNode = topPair.v;

            if (visited[currNode]) continue;
            visited[currNode] = true;

            for (Node node : g.adj.get(currNode)) {
                int nextNode = node.v;
                int nextTime = currTime + node.time;

                if (!visited[nextNode] && nextTime < dist[nextNode]) {
                    dist[nextNode] = nextTime;
                    minHeap.add(new Node(nextTime, nextNode));
                }
            }
        }

    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Graph g = new Graph(n);

        for (int[] edge : times) {
            int v = edge[0];
            int u = edge[1];
            int time = edge[2];
            g.addEdge(v, u, time);
        }

        int[] dist = new int[n + 1];

        dijkstras(g, dist, n, k);

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

