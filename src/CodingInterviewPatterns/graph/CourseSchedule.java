package CodingInterviewPatterns.graph;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
    //https://www.youtube.com/watch?v=kXy0ABd1vwo&t=12s
    //https://www.youtube.com/watch?v=iaaObeAEgxI&t=82s
    //https://leetcode.com/problems/course-schedule/description/
    //https://www.youtube.com/watch?v=EgI5nU9etnU&ab_channel=NeetCode

    static int WHITE = 0, GRAY = 1, BLACK = 2;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int V = numCourses;
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        int[] visited = new int[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == WHITE) {
                if (detectCycleUtil(graph, i, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean detectCycleUtil(List<List<Integer>> graph, int v, int[] visited) {
        visited[v] = GRAY;
        for (int u : graph.get(v)) {
            if (visited[u] == GRAY) {
                return true;
            }

            if (visited[u] == WHITE && detectCycleUtil(graph, u, visited)) {
                return true;
            }
        }
        visited[v] = BLACK;

        return false;
    }


}

