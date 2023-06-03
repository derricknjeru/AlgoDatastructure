package CodingInterviewPatterns.shortestPathGraph;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheapestFlightsWithinKStopsDijkstras {
    //https://www.youtube.com/watch?v=vWgoPTvQ3Rw

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

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
       // (u,v,p)
        for (int[] flight : flights) {
            int u = flight[0];
            int v = flight[1];
            int p = flight[2];
            graph.get(u).add(new int[]{v, p, 0});
        }

        return dijkstras(graph, dist, n, dst, src, k);
    }

    private static int dijkstras(List<List<int[]>> graph, int[] dist, int n, int dst, int src, int k) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int[] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);

        dist[src] = 0;
        stops[src] = 0;
        pq.offer(new int[]{src, 0, 0});

        while (!pq.isEmpty()) {
            int[] currNodeInfo = pq.poll();
            int currNode = currNodeInfo[0];
            int currPrice = currNodeInfo[1];
            int currStop = currNodeInfo[2];

            if (currNode == dst) return currPrice;

            if (currStop == k + 1) continue;

            for (int[] neighbor : graph.get(currNode)) {
                int nextPrice = currPrice + neighbor[1];
                int nextStop = currStop + 1;
                int nextNode = neighbor[0];
                if (nextPrice < dist[nextNode] || nextStop < stops[nextNode]) {
                    dist[nextNode] = nextPrice;
                    stops[nextNode] = nextStop;
                    pq.offer(new int[]{nextNode, nextPrice, nextStop});
                }
            }
        }
        return -1;
    }
}



