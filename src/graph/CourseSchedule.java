package graph;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
    //https://www.youtube.com/watch?v=kXy0ABd1vwo&t=12s
    //https://www.youtube.com/watch?v=iaaObeAEgxI&t=82s
    //https://leetcode.com/problems/course-schedule/description/

    static int WHITE = 0, GRAY = 1, BLACK = 2;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph g = buildGraph(numCourses, prerequisites);
        return !hasCycle(g, numCourses);
    }

    private boolean hasCycle(Graph g, int V) {
        int[] visited = new int[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == WHITE) {
                if (detectCycle_util(g, i, visited)) return true;
            }
        }
        return false;
    }

    private boolean detectCycle_util(Graph g, int v, int[] visited) {
        visited[v] = GRAY;
        for (int u : g.adj.get(v)) {
            if (visited[u] == GRAY) return true;

            if (visited[u] == WHITE && detectCycle_util(g, u, visited)) return true;
        }
        visited[v] = BLACK;

        return false;
    }

    private Graph buildGraph(int numCourses, int[][] prerequisites) {
        Graph g = new Graph(numCourses);
        for (int[] courses : prerequisites) {
            g.addEdg(courses[0], courses[1]);
        }
        return g;
    }

    class Graph {
        int vertices;
        List<List<Integer>> adj;

        Graph(int V) {
            this.vertices = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        public void addEdg(int source, int destination) {
            adj.get(source).add(destination);
        }
    }
}

