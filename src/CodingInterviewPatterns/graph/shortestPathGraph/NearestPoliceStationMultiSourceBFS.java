package CodingInterviewPatterns.graph.shortestPathGraph;

import java.util.*;

public class NearestPoliceStationMultiSourceBFS {
    public static void main(String[] args) {
        /**
         * Input :
         * Number of Vertices = 6
         * Number of Edges = 9
         * Towns with Police Station : 1, 5
         * Edges:
         * 1 2
         * 1 6
         * 2 6
         * 2 3
         * 3 6
         * 5 4
         * 6 5
         * 3 4
         * 5 3
         */

        int V = 6;
        int E = 9;
        int[] sources = {1, 5};
        int[][] edges = {{1, 2}, {1, 6}, {2, 6}, {2, 3}, {3, 6}, {5, 4}, {6, 5}, {3, 4}, {5, 3}};


        // Return distances excluding the sentinel 0th index
        int[] dist = findNearestPoliceStation(V, E, sources, edges);

        // Printing the distances
        for (int i = 1; i <= V; i++) {
            System.out.println(i + " " + dist[i]);
        }

        /** expected results
         * This means that the distance of town 1 from the nearest police station is 0,
         * the distance of town 2 is 1,
         * town 3 is 2, town 4 is 1, town 5 is 1, and town 6 is 0.
         1 0
         2 1
         3 1
         4 1
         5 0
         6 1
         **/
    }

    private static int[] findNearestPoliceStation(int V, int E, int[] s, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        // This array stores the distances of the vertices
        // from the nearest source
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Create a queue for BFS
        Queue<Integer> q = new LinkedList<>();

        //This boolean array is true if the current vertex
        // is visited otherwise it is false
        boolean[] visited = new boolean[V + 1];

        // Mark all the source vertices as
        // visited and enqueue it and set their distance as 0
        for (int station : s) {
            dist[station] = 0;
            q.add(station);
            visited[station] = true;
        }
        // Multisource BFS
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int u : graph.get(curr)) {
                if (!visited[u]) {

                    // Pushing the adjacent unvisited vertices
                    // with distance from current source = this
                    // vertex's distance + 1
                    q.offer(u);
                    visited[u] = true;
                    dist[u] = dist[curr] + 1;
                }
            }

        }
        return dist;
    }
}
