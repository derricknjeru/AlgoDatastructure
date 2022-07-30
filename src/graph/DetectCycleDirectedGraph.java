package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetectCycleDirectedGraph {
    static HashMap<Integer, List<Integer>> graph;


    //https://www.youtube.com/watch?v=0dJmTuMrUZM
    //Graph coloring: 0->not visited...1->visited...2->visited & processed
    static boolean detectCycleDirectedGraph(HashMap<Integer, List<Integer>> graph, int n) {
        int[] visited = new int[n];
        for (int i = 0; i < n; ++i)
            if (visited[i] == 0) {
                if (detectCycle_util(graph, visited, i)) return true;
            }
        return false;
    }

    //Graph coloring: 0->not visited...1->visited...2->visited & processed
    private static boolean detectCycle_util(HashMap<Integer, List<Integer>> graph, int[] visited, int v) {
        if (visited[v] == 1) return true;
        if (visited[v] == 2) return false;

        visited[v] = 1;   //Mark current as visited
        for (int u : graph.get(v)) {
            if (detectCycle_util(graph, visited, u)) return true;
        }
        visited[v] = 2;   //Mark current node as processed
        return false;

    }

    public static void buildGraph(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++)
            graph.putIfAbsent(i, new ArrayList<>());

        for (int[] courses : prerequisites) {
            int course1 = courses[1];
            int course2 = courses[0];
            graph.get(course1).add(course2);
        }
    }

    //https://www.youtube.com/watch?v=L0DcePeWHnM
    static boolean detectCycleUnDirectedGraph(HashMap<Integer, List<Integer>> graph, int n) {
        int[] visited = new int[n];
        for (int i = 0; i < n; ++i) {
            visited[i] = 1;
            // Call the recursive helper
            // function to detect cycle in
            // different DFS trees
            for (int j = 0; j < graph.size(); ++j) {
                if (visited[i] == 0) {
                    if (detectUnCycle_util(graph, visited, i)) return true;
                }
            }

            visited[i] = 0;
        }
        return false;
    }

    private static boolean detectUnCycle_util(HashMap<Integer, List<Integer>> graph, int[] visited, int v) {
        if (visited[v] == 2)
            return true;

        visited[v] = 1;
        for (int u : graph.get(v)) {
            if (visited[u] == 1)
                visited[u] = 2;
            else {
                if (detectUnCycle_util(graph, visited, v)) return true;

            }
        }
        return false;
    }

    public static void main(String[] args) {
        //-->directed
        // int[][] prerequisites2 = {{1, 0}};
        // int numCourses2 = 4;
        //-->directed
        int[][] prerequisites = {{0, 1}, {1, 0}};
        int numCourses = 2;
        //--> undirected
        //int[][] prerequisites2 = {{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}};
        //int numCourses2 = 5;
        //int[][] prerequisites = {{0, 1}, {1, 2}};
        //int numCourses = 3;


        graph = new HashMap<>();
        buildGraph(numCourses, prerequisites);

        if (detectCycleDirectedGraph(graph, numCourses)) {
            System.out.println("Directed there is a cycle");
        } else {
            System.out.println("Directed there is no cycle");
        }

        /*if (detectCycleUnDirectedGraph(graph, numCourses)) {
            System.out.println("UnDirected there is a cycle");
        } else {
            System.out.println("UnDirected there is no cycle");
        }*/
    }
}
