package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.*;

public class LongestPathBetweenAnyVertices {
    //https://www.geeksforgeeks.org/longest-path-between-any-pair-of-vertices/

    public static void main(String[] args) {
        /**
         * We are given a map of cities connected with each other via cable lines such that there is no cycle between any two cities.
         * We need to find the maximum length of cable between any two cities for a given city map.
         *-
         * Input: n = 6
         *         1 2 3  // Cable length from 1 to 2 (or 2 to 1) is 3
         *         2 3 4
         *         2 6 2
         *         6 4 6
         *         6 5 5
         * Output: maximum length of cable = 12
         */

        int n = 6;

        // Create the graph using a HashMap and add edges
        HashMap<Integer, ArrayList<Adjacent>> graph = new HashMap<>();
        addBidirectionalConnection(graph, 1, 2, 3);
        addBidirectionalConnection(graph, 2, 3, 4);
        addBidirectionalConnection(graph, 2, 6, 2);
        addBidirectionalConnection(graph, 6, 4, 6);
        addBidirectionalConnection(graph, 6, 5, 5);

        int maxLength = longestCable(graph, n);
        System.out.println("Maximum length of cable = " + maxLength);
    }

    private static void addBidirectionalConnection(HashMap<Integer, ArrayList<Adjacent>> graph, int cityA, int cityB, int cableLength) {
        graph.computeIfAbsent(cityA, k -> new ArrayList<>()).add(new Adjacent(cityB, cableLength));
        graph.computeIfAbsent(cityB, k -> new ArrayList<>()).add(new Adjacent(cityA, cableLength));
    }

    // Helper class to represent an adjacent city and cable length
    static class Adjacent {
        int city;
        int length;

        Adjacent(int city, int length) {
            this.city = city;
            this.length = length;
        }
    }

    /**
     * Function to find the longest cable using Depth-First Search (DFS).
     * This function calls the 'dfs' function for each vertex in the graph.
     * <p>
     * Time Complexity: O(V * (V + E)), where V is the number of vertices (cities) and E is the number of edges (cable connections).
     * Space Complexity: O(V) for the 'visited' set used for tracking visited cities.
     */
    public static int longestCable(HashMap<Integer, ArrayList<Adjacent>> graph, int n) {
        int maxLength = 0;

        for (int i = 1; i <= n; i++) {
            // Call the 'dfs' function for each vertex.
            int currentMaxLength = dfs(graph, i, new HashSet<>(), 0);
            maxLength = Math.max(maxLength, currentMaxLength);
        }

        return maxLength;
    }

    /**
     * Depth-First Search (DFS) function to find the maximum cable length starting from a given city.
     * <p>
     * Time Complexity: O(V + E), where V is the number of vertices (cities) and E is the number of edges (cable connections).
     * Space Complexity: O(V) for the 'visited' set used for tracking visited cities and the function call stack.
     */
    public static int dfs(HashMap<Integer, ArrayList<Adjacent>> graph, int city, Set<Integer> visited, int prevLen) {
        visited.add(city);
        int maxLength = prevLen;

        for (Adjacent neighbor : graph.get(city)) {
            if (!visited.contains(neighbor.city)) {
                int current = prevLen + neighbor.length;
                int pathLength = dfs(graph, neighbor.city, visited, current);
                maxLength = Math.max(maxLength, pathLength);
            }
        }

        return maxLength;
    }

    /**
     * Function to find the longest cable using a different DFS approach.
     * This function calls the 'DFS2' function for each vertex in the graph.
     * -
     * Time Complexity: O(V * (V + E)), where V is the number of vertices (cities) and E is the number of edges (cable connections).
     * Space Complexity: O(V) for the 'visited' boolean array used for tracking visited cities and the function call stack.
     * -
     * In the worst case, this function may visit all vertices (cities) in the graph once, and for each vertex, it may traverse all of its adjacent neighbors.
     * The worst-case time complexity for this function is O(V + E), where V is the number of vertices and E is the number of edges.
     */
    static int longestCable2(HashMap<Integer, ArrayList<Adjacent>> graph, int n) {
        int[] maxLen = {Integer.MIN_VALUE};

        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            // Call the 'DFS2' function for each vertex.
            DFS2(graph, i, 0, maxLen, visited);
        }

        return maxLen[0];
    }

    /**
     * Depth-First Search (DFS) function to find the maximum cable length starting from a given city.
     * This function uses a different approach and is called by 'longestCable2'.
     * <p>
     * Time Complexity: O(V + E), where V is the number of vertices (cities) and E is the number of edges (cable connections).
     * Space Complexity: O(V) for the 'visited' boolean array used for tracking visited cities and the function call stack.
     * <p>
     * In the worst case, this function may visit all vertices (cities) in the graph once, and for each vertex, it may traverse all of its adjacent neighbors.
     * The worst-case time complexity for this function is O(V + E), where V is the number of vertices and E is the number of edges.
     */
    static int DFS2(HashMap<Integer, ArrayList<Adjacent>> graph, int src, int prevLen, int[] maxLen, boolean[] visited) {
        visited[src] = true;
        int currLen = 0;

        for (Adjacent adjacent : graph.get(src)) {
            int adjacentCity = adjacent.city;
            int cableLength = adjacent.length;

            if (!visited[adjacentCity]) {
                currLen = prevLen + cableLength;
                DFS2(graph, adjacentCity, currLen, maxLen, visited);
            }

            if (maxLen[0] < currLen) {
                maxLen[0] = currLen;
            }

            currLen = 0;
        }

        return maxLen[0];
    }
}
