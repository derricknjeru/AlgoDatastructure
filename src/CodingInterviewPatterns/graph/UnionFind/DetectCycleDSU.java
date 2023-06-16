package CodingInterviewPatterns.graph.UnionFind;

import java.util.*;
import java.lang.*;

public class DetectCycleDSU {
    //https://practice.geeksforgeeks.org/problems/detect-cycle-using-dsu/1
    //https://www.youtube.com/watch?v=eTaWFhPXPz4

    public static void main(String[] args) {
        int V;
        ArrayList<ArrayList<Integer>> adj;

        // Example 1: Cycle Detected
        V = 4;
        adj = createGraph(V);
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 0);
        detectCycleAndPrintResult(V, adj);

        // Example 2: No Cycle Detected
        V = 4;
        adj = createGraph(V);
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        detectCycleAndPrintResult(V, adj);

        // Example 3: Cycle Detected (Self-loop)
        V = 3;
        adj = createGraph(V);
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 2); // Self-loop
        detectCycleAndPrintResult(V, adj);

        //Example 4 : Cycle detected
        V = 5;
        adj = createGraph(V);
        addEdge(adj, 0, 2);
        addEdge(adj, 0, 3);
        addEdge(adj, 0, 4);
        addEdge(adj, 1, 3);
        addEdge(adj, 2, 4);
        detectCycleAndPrintResult(V, adj);
    }

    static ArrayList<ArrayList<Integer>> createGraph(int V) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        return adj;
    }

    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    static void detectCycleAndPrintResult(int V, ArrayList<ArrayList<Integer>> adj) {
        int result = detectCycle(V, adj);

        if (result == 1) {
            System.out.println("Cycle detected");
        } else {
            System.out.println("No cycle detected");
        }
    }


    static class UnionFind {
        UnionFind() {

        }

        public int find(int[] parent, int i) {
            if (parent[i] == -1)
                return i;
            return find(parent, parent[i]);
        }

        public void union(int[] parent, int x, int y) {
            int X = find(parent, x);
            int Y = find(parent, y);
            parent[X] = Y;
        }
    }

    //Function to detect cycle using DSU in an undirected CodingInterviewPatterns.graph.
    public static int detectCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        if (V == 2) return 0;
        // Allocate memory for creating V subsets
        int[] parent = new int[V];

        Arrays.fill(parent, -1);

        UnionFind un = new UnionFind();

        for (int v = 0; v < V; v++) {
            List<Integer> edges = adj.get(v);
            for (int u : edges) {
                //This condition is used to avoid processing the same edge twice since the CodingInterviewPatterns.graph is undirected.
                if (v < u) continue;

                int X = un.find(parent, v);
                int Y = un.find(parent, u);

                if (X == Y) return 1;

                un.union(parent, v, u);
            }

        }

        return 0;
    }

}
