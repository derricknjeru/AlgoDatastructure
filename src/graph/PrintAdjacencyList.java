package graph;

import java.util.ArrayList;

public class PrintAdjacencyList {
    /**
     * Given the adjacency list of a bidirectional graph.
     * Your task is to copy/clone the adjacency list for each vertex and return a new list.
     */

    /**
     * You dont need to read input or print anything.
     * Your task is to complete the function printGraph() which takes the integer V denoting the number of vertices
     * and adjacency list as input parameters and returns the list of list  contains the node itself
     * with its connected nodes(as same as it is given in input adjacency  list).The returned list is a copy/clone of the actual list.
     * <p>
     * <p>
     * Expected Time Complexity: O(V + E)
     * Expected Auxiliary Space: O(V + E)
     */

    //https://practice.geeksforgeeks.org/problems/print-adjacency-list-1587115620/1

    public ArrayList<ArrayList<Integer>> printGraph(
            int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();


        for (int i = 0; i < V; i++) {
            ArrayList<Integer> x = new ArrayList<>();
            x.add(i);

            for (int a :  adj.get(i)) {
                x.add(a);
            }
            result.add(x);

        }

        return result;
    }
}
