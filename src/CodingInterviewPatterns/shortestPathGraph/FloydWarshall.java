package CodingInterviewPatterns.shortestPathGraph;

public class FloydWarshall {
    static final int V = 4; // No of vertices
    static final int INF = Integer.MAX_VALUE; // Infinity

    void floydWarshall(int[][] graph) {
        int[][] dist = new int[V][V];

        // Assign all values of graph to allPairs_SP
        for (int i = 0; i < V; ++i)
            System.arraycopy(graph[i], 0, dist[i], 0, V);

        // Find all pairs shortest path by trying all possible paths
        for (int k = 0; k < V; ++k) { // Try all intermediate nodes
            for (int i = 0; i < V; ++i) { // Try for all possible starting position
                for (int j = 0; j < V; ++j) { // Try for all possible ending position
                    if (dist[i][k] == INF || dist[k][j] == INF)
                        // SKIP if K is unreachable from i or j is unreachable from k
                        continue;
                    else if (dist[i][k] + dist[k][j] < dist[i][j])
                        // Check if new distance is shorter via vertex K
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        // Print Shortest Path Graph
        // (Values printed as Integer.MAX_VALUE defines there is no path)
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.println(i + " to " + j + " distance is Infinity");
                else
                    System.out.println(i + " to " + j + " distance is " + dist[i][j]);
            }
            System.out.println("=================================");
        }
    }

    public static void main(String[] args) {
        /**
         * int graph[][] = { { 0,   5,  INF, 10 },
         *                   { INF, 0,  3,  INF },
         *                   { INF, INF, 0,   1 },
         *                   { INF, INF, INF, 0 } };
         *
         * Above represents the following graph:
         *         10
         *    (0)----->(3)
         *     |        /|\
         *   5 |         | 1
         *     |         |
         *    \|/        |
         *   (1)----->(2)
         *       3
         *
         *
         * The given illustration represents a directed graph with four vertices labeled 0, 1, 2, and 3. The numbers on the edges indicate the weights or distances between the connected vertices.
         *
         * There is an edge from vertex 0 to vertex 3 with a weight of 10.
         * There is an edge from vertex 0 to vertex 1 with a weight of 5.
         * There is an edge from vertex 1 to vertex 2 with a weight of 3.
         * There is an edge from vertex 2 to vertex 3 with a weight of 1.
         * Additionally, the symbol \|/ in the illustration represents the existence of multiple paths or routes between vertex 1 and vertex 3. In this graph, there are two distinct paths from vertex 1 to vertex 3:
         *
         * Path 1: Starting from vertex 1, we can traverse the edge with weight 3 to reach vertex 2. From vertex 2, we can then traverse the edge with weight 1 to reach vertex 3. This path has a total weight of 4 (3 + 1).
         *
         * Path 2: Starting from vertex 1, we can directly traverse the edge with weight 10 to reach vertex 3. This path has a total weight of 10.
         *
         * The presence of \|/ indicates that there are multiple possible paths between vertex 1 and vertex 3 in the graph, providing different options for traversal.
         */
        int[][] graph = {{0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}};

        FloydWarshall fw = new FloydWarshall();
        fw.floydWarshall(graph);

        /**
         * The following matrix shows the shortest distances between every pair of vertices
         *       0      5      8      9
         *     INF      0      3      4
         *     INF    INF      0      1
         *     INF    INF    INF      0
         */

        //which is  as shown below per row

        /**
         * 0 to 0 distance is 0
         * 0 to 1 distance is 5
         * 0 to 2 distance is 8
         * 0 to 3 distance is 9
         * =================================
         * 1 to 0 distance is Infinity
         * 1 to 1 distance is 0
         * 1 to 2 distance is 3
         * 1 to 3 distance is 4
         * =================================
         * 2 to 0 distance is Infinity
         * 2 to 1 distance is Infinity
         * 2 to 2 distance is 0
         * 2 to 3 distance is 1
         * =================================
         * 3 to 0 distance is Infinity
         * 3 to 1 distance is Infinity
         * 3 to 2 distance is Infinity
         * 3 to 3 distance is 0
         * =================================
         */
    }
}
