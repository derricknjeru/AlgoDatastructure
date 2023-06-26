package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import CodingInterviewPatterns.graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPathsUndirected {

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

        Graph graph = new Graph(5); // Create a graph with 5 vertices
        graph.addEdgeUn(0, 1);
        graph.addEdgeUn(0, 2);
        graph.addEdgeUn(1, 3);
        graph.addEdgeUn(2, 3);
        graph.addEdgeUn(3, 4);

        // arbitrary source
        int s = 0;

        // arbitrary destination
        int d = 4;

        System.out.println(
                "Following are all different paths from "
                        + s + " to " + d);

        boolean[] visited1 = new boolean[5];
        List<Integer> path1 = new ArrayList<>();

        dfsPrintAllPath(graph, s, d, visited1, path1, -1);


    }

    private static boolean dfsPrintAllPath(Graph g, int src, int dest, boolean[] visited, List<Integer> path, int parent) {
        visited[src] = true;

        path.add(src);

        if (src == dest) {
            //Instead of printing we can add it to newList
            //e.g.  simplePaths.add(new ArrayList<>(currentPath));
            printPath(path);
        }

        for (int u : g.adjacencyList.get(src)) {
            if (u != parent && !visited[u] && dfsPrintAllPath(g, u, dest, visited, path, src)) return true;
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


