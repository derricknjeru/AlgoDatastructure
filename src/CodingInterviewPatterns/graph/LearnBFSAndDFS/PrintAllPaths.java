package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import CodingInterviewPatterns.graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPaths {
    /**
     * Complexity Analysis:
     * <p>
     * Time Complexity: O(2^V), The time complexity is exponential. Given a source and destination, the source and destination nodes are going to be in every path. Depending upon edges, taking the worst case where every node has a directed edge to every other node, there can be at max 2^V different paths possible in the directed graph from a given source to destination.
     * Auxiliary space: O(2^V), To store the paths 2^V space is needed.
     *
     * @param args
     */
    //Print all paths

    //https://www.geeksforgeeks.org/find-paths-given-source-destination/
    //https://techiedelight.com/compiler/
    public static void main(String[] args) {

        // total number of nodes in the graph (labeled from 0 to 7)
        int n = 8;

        // List of graph edges as per the above diagram
        Graph g2 = new Graph(n);

        g2.addEdge(0, 3);
        g2.addEdge(1, 0);
        g2.addEdge(1, 2);
        g2.addEdge(1, 4);
        g2.addEdge(2, 7);
        g2.addEdge(3, 4);
        g2.addEdge(3, 5);
        g2.addEdge(4, 3);
        g2.addEdge(4, 6);
        g2.addEdge(5, 6);
        g2.addEdge(6, 7);

        // source and destination vertex
        int src = 0;
        int dest = 7;

        boolean[] visited = new boolean[n];
        List<Integer> path = new ArrayList<>();
        System.out.println(
                "Following are all different paths from "
                        + src + " to " + dest);
        dfsPrintAllPath(g2, src, dest, visited, path);


        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(1, 3);

        // arbitrary source
        int s = 2;

        // arbitrary destination
        int d = 3;

        System.out.println(
                "Following are all different paths from "
                        + s + " to " + d);

        boolean[] visited1 = new boolean[n];
        List<Integer> path1 = new ArrayList<>();

        dfsPrintAllPath(g, s, d, visited1, path1);


    }

    private static boolean dfsPrintAllPath(Graph g, int src, int dest, boolean[] visited, List<Integer> path) {
        visited[src] = true;

        path.add(src);

        if (src == dest) {
            //Instead of printing we can add it to newList
            //e.g.  simplePaths.add(new ArrayList<>(currentPath));
            printPath(path);
        }

        for (int u : g.adjacencyList.get(src)) {
            if (!visited[u] && dfsPrintAllPath(g, u, dest, visited, path)) return true;
        }

        // backtrack: remove the current node from the path and mark it as not discovered
        path.remove(path.size() - 1);
        visited[src] = false;
        return false;
    }

    static void printPath(List<Integer> path) {
        for (int i : path) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
