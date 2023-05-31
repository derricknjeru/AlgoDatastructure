package CodingInterviewPatterns.shortestPathGraph;

import java.util.Arrays;

public class CheapestFlightsWithinKStopsBellmanFord {
    //https://www.youtube.com/watch?v=5eIK3zUdYmE
    public static void main(String[] args) {

        // Test case 1
        int n1 = 4;
        int[][] flights1 = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int src1 = 0;
        int dst1 = 3;
        int k1 = 1;
        int result1 = findCheapestPrice(n1, flights1, src1, dst1, k1);
        System.out.println("Test case 1: " + result1); // Expected output: 700

        // Test case 2
        int n2 = 3;
        int[][] flights2 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src2 = 0;
        int dst2 = 2;
        int k2 = 1;
        int result2 = findCheapestPrice(n2, flights2, src2, dst2, k2);
        System.out.println("Test case 2: " + result2); // Expected output: 200

        // Test case 3
        int n3 = 3;
        int[][] flights3 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src3 = 0;
        int dst3 = 2;
        int k3 = 0;
        int result3 = findCheapestPrice(n3, flights3, src3, dst3, k3);
        System.out.println("Test case 3: " + result3); // Expected output: 500

        // Additional test case
        int n4 = 7;
        int[][] flights4 = {{0, 3, 7}, {4, 5, 3}, {6, 4, 8}, {2, 0, 10}, {6, 5, 6}, {1, 2, 2}, {2, 5, 9}, {2, 6, 8}, {3, 6, 3}, {4, 0, 10}, {4, 6, 8}, {5, 2, 6}, {1, 4, 3}, {4, 1, 6}, {0, 5, 10}, {3, 1, 5}, {4, 3, 1}, {5, 4, 10}, {0, 1, 6}};
        int src4 = 2;
        int dst4 = 4;
        int k4 = 1;
        int result4 = findCheapestPrice(n4, flights4, src4, dst4, k4);
        System.out.println("Additional test case: " + result4); // Expected output: 16

    }
    //https://www.youtube.com/watch?v=5eIK3zUdYmE
    //https://leetcode.com/problems/cheapest-flights-within-k-stops/editorial/
    //This will run at a Time complexity of O(E * K). It is usually O(E* V)
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(dist, n);
            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int p = flight[2];
                int currDist = dist[u] + p;

                if (dist[u] != Integer.MAX_VALUE && currDist < temp[v]) {
                    temp[v] = currDist;
                }
            }
            dist = temp;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];

    }

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        int E = flights.length;
        int V = n;

        Graph g = new Graph(V, E);

        for (int i = 0; i < E; i++) {
            g.edges[i].src = flights[i][0];
            g.edges[i].dst = flights[i][1];
            g.edges[i].weight = flights[i][2];
        }

        return BellmanFord(g, src, dst, k);
    }

    private int BellmanFord(Graph g, int src, int dst, int K) {
        int V = g.V;
        int E = g.E;

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;

        for (int i = 0; i <= K; i++) {
            int[] temp = Arrays.copyOf(dist, V);
            for (int j = 0; j < E; j++) {
                int u = g.edges[j].src;
                int v = g.edges[j].dst;
                int w = g.edges[j].weight;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < temp[v]) {
                    temp[v] = dist[u] + w;
                }
            }

            dist = temp;
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }


    static class Edge {
        int src, dst, weight;

        Edge() {
            src = dst = weight = 0;
        }
    }

    static class Graph {
        int V, E;
        Edge[] edges;

        Graph(int V, int E) {
            edges = new Edge[E];
            this.V = V;
            this.E = E;

            for (int i = 0; i < E; i++) {
                edges[i] = new Edge();
            }
        }
    }


}
