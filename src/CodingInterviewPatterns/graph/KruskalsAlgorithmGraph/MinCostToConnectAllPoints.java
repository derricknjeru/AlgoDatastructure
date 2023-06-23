package CodingInterviewPatterns.graph.KruskalsAlgorithmGraph;

import CodingInterviewPatterns.graph.UnionFind.UnionFindByRank;

import java.util.*;

public class MinCostToConnectAllPoints {
//https://leetcode.com/problems/min-cost-to-connect-all-points/description/
    // https://www.youtube.com/watch?v=4ZlRH0eK-qQ&t=2s
    //https://www.youtube.com/watch?v=Ub-fJ-KoBQM


    class Solution {
        /**
         * Overall, the dominant factor in terms of time complexity is sorting the edges list,
         * which has a time complexity of O(n^2 log n).
         * Therefore, the time complexity of the minCostConnectPoints method is O(n^2 log n).
         */
        public int minCostConnectPoints(int[][] points) {
            if (points == null || points.length == 0) {
                return -1; // Invalid input
            }

            int n = points.length;
            int totalWeight = 0;
            UnionFindByRank uf = new UnionFindByRank(n);

            List<int[]> edges = new ArrayList<>();

            // Step 1: Calculate the Manhattan distance between all pairs of points
            /**
             * Constructing the edges list:
             *
             * The nested for loop iterates over all pairs of points, resulting in a total of n * (n-1) / 2 iterations.
             * Calculating the Manhattan distance between two points takes constant time.
             * Hence, constructing the edges list has a time complexity of O(n^2).
             */
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int cost = manhattanDistance(points[i], points[j]);
                    edges.add(new int[]{cost, i, j});
                }
            }
            /**
             * Sorting the edges list:
             *
             * Sorting the edges list takes O(E log E) time, where E is the number of edges.
             * In this case, E is n * (n-1) / 2, so the time complexity is O(n^2 log n).
             */
            edges.sort(Comparator.comparingInt(a -> a[0]));

            // Step 3: Apply Kruskal's algorithm to find the minimum cost spanning tree
            /**
             * Applying Kruskal's algorithm:
             *
             * The while loop executes n-1 times since we want to construct a minimum spanning tree with n-1 edges.
             * Inside the loop, the operations performed are constant time.
             * The find and union operations using the UnionFindByRank data structure have an amortized time complexity of O(log n).
             * Hence, applying Kruskal's algorithm takes O((n-1) log n) time.
             */
            int noOfEdges = 0;
            int j = 0;
            while (noOfEdges < n - 1) {
                int x = edges.get(j)[1];
                int y = edges.get(j)[2];
                int cost = edges.get(j)[0];

                int X = uf.find(x);
                int Y = uf.find(y);

                if (X != Y) {
                    uf.union(x, y); // Union the two nodes to build the graph
                    totalWeight += cost;
                    noOfEdges++;
                }
                j++;
            }

            return totalWeight;
        }

        private int manhattanDistance(int[] p1, int[] p2) {
            return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
        }
    }


    //
// - sort all edges by weight
// - add edge if it does not create a cycle
// - continue until you added n-1 edges
    class Solution2 {
        /**
         * Overall, the dominant factor in terms of time complexity is constructing the PriorityQueue,
         * which has a time complexity of O(n^2 log n).
         * Therefore, the time complexity of the minCostConnectPoints method in Solution2 is O(n^2 log n).
         *
         * @param points
         * @return
         */
        public int minCostConnectPoints(int[][] points) {
            // edge cases
            if (points == null || points.length == 0) {
                return -1;
            }

            // init
            int edges = 0;
            int n = points.length;
            int totalWeight = 0;
            UnionFindByRank uf = new UnionFindByRank(n);

            // use a PQ to sort min weighted edge first
            PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);

            // we need a nested look to calculate all the weights
            /**
             * Constructing the PriorityQueue (queue):
             *
             * The nested for loop iterates over all pairs of points, resulting in a total of n * (n-1) / 2 iterations.
             * Calculating the weight for each pair of points takes constant time.
             * Adding an element to the PriorityQueue takes O(log E) time, where E is the number of elements in the PriorityQueue.
             * Hence, constructing the PriorityQueue takes O((n^2 / 2) log (n^2 / 2)) time, which simplifies to O(n^2 log n) time.
             */
            for (int i = 0; i < points.length; i++) {
                int[] coords = points[i];
                int x1 = coords[0];
                int y1 = coords[1];
                for (int j = i + 1; j < points.length; j++) {
                    int[] coords2 = points[j];
                    int x2 = coords2[0];
                    int y2 = coords2[1];

                    // calc weight and add it to queue
                    int weight = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                    queue.offer(new int[]{i, j, weight});
                }
            }

            // iterate over each edge until we are done
            /**
             * Applying Kruskal's algorithm:
             *
             * The while loop executes until either the PriorityQueue is empty or the number of edges (edges) reaches n - 1.
             * The operations performed inside the loop are constant time.
             * The find and union operations using the UnionFindByRank data structure have an amortized time complexity of O(log n).
             * Hence, applying Kruskal's algorithm takes O((n-1) log n) time.
             */
            while (!queue.isEmpty() && edges < n - 1) {
                int[] edge = queue.poll();

                // if the two nodes are already connected in the graph
                // we should not add this edge
                if (uf.find(edge[0]) == uf.find(edge[1])) {
                    continue;
                }

                // else union the two edges to build the graph
                uf.union(edge[0], edge[1]);

                // incrememnt counters
                totalWeight += edge[2];
                edges++;
            }
            return totalWeight;
        }


    }
}
