package CodingInterviewPatterns.graph.shortestPathGraph;

import java.util.*;

public class ShortestPathsSrcToSingleTargetDijkstras {
    //https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
    //https://www.youtube.com/watch?v=pLElbKBc4RU&t=10s
    static class Node {
        int weight;
        int vertex;

        Node(int w, int v) {
            this.weight = w;
            this.vertex = v;
        }
    }

    static class Graph {
        int vertices;
        List<List<Node>> adj;

        Graph(int V) {
            this.vertices = V;
            adj = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int v, int u, int w) {
            adj.get(v).add(new Node(w, u));
            adj.get(u).add(new Node(w, v));
        }

    }

    public static void main(String[] args) {
        int V = 9;
        Graph g = new Graph(V);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);

        int[] dist = new int[V];
        int[] pred = new int[V];

        int target = 3;

        //Time complexity where v=vertices/nodes and e=edges O(v+ElogV)
        //Space complexity O(V+E)
        dijkstras(g, V, 0, dist, pred, target);

        System.out.println("Target Vertex Distance from Source");
        System.out.println(dist[target]);
        /**
         * Output: 0 4 12 19 21 11 9 8 14
         * Explanation: The distance from 0 to 1 = 4.
         * The minimum distance from 0 to 2 = 12. 0->1->2
         * The minimum distance from 0 to 3 = 19. 0->1->2->3
         * The minimum distance from 0 to 4 = 21. 0->7->6->5->4
         * The minimum distance from 0 to 5 = 11. 0->7->6->5
         * The minimum distance from 0 to 6 = 9. 0->7->6
         * The minimum distance from 0 to 7 = 8. 0->7
         * The minimum distance from 0 to 8 = 14. 0->1->2->8
         */
        /**
         * 0		0
         * 1		4
         * 2		12
         * 3		19
         * 4		21
         * 5		11
         * 6		9
         * 7		8
         * 8		14
         */

        constructPath(pred, target);

    }

    private static void constructPath(int[] pred, int target) {
        List<Integer> paths = new ArrayList<>();

        int crawl = target;
        paths.add(crawl);

        while (pred[crawl] != -1) {
            paths.add(pred[crawl]);
            crawl = pred[crawl];
        }
        // Print path
        System.out.println("Path is ::");
        for (int i = paths.size() - 1; i >= 0; i--) {
            System.out.print(paths.get(i) + " ");
        }

    }

    private static void dijkstras(Graph g, int v, int src, int[] dist, int[] pred, int target) {
        Queue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));

        Arrays.fill(dist, Integer.MAX_VALUE);  //space O(V) time O(V)
        Arrays.fill(pred, -1); //space O(V) time O(V)

        boolean[] visited = new boolean[v];

        dist[src] = 0;
        //visited[src] = true;
        minHeap.add(new Node(0, src));

        while (!minHeap.isEmpty()) {
            Node topPair = minHeap.remove();
            int currDist = topPair.weight;
            int currNode = topPair.vertex;

            if (currNode == target) break;

            if (visited[currNode]) continue;
            visited[currNode] = true;

            for (Node node : g.adj.get(currNode)) {
                int nextWeight = node.weight;
                int nextNode = node.vertex;
                int nextDist = currDist + nextWeight;
                if (!visited[nextNode] && nextDist < dist[nextNode]) {  //space O(E)-(minHeap) // time O(ElogE) - minheap
                    dist[nextNode] = nextDist;
                    pred[nextNode] = currNode;
                    minHeap.add(new Node(nextDist, nextNode));
                }
            }
        }
    }

}
