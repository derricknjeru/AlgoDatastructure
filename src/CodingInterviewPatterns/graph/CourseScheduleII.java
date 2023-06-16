package CodingInterviewPatterns.graph;

import java.util.*;

public class CourseScheduleII {
    //https://www.youtube.com/watch?v=ddTC4Zovtbc
    //https://www.youtube.com/watch?v=_BGK0kpE4oE
    //https://leetcode.com/problems/course-schedule-ii/

    static int WHITE = 0, GRAY = 1, BLACK = 2;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : prerequisites) {
            int u = edge[1];
            int v = edge[0];
            graph.get(u).add(v);
        }

        Stack<Integer> orderedList = new Stack<>();
        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == WHITE && isCycle(graph, i, visited, orderedList)) {
                return new int[0];
            }
        }

        int[] res = new int[orderedList.size()];
        int index = 0;
        while (!orderedList.isEmpty()) {
            res[index++] = orderedList.pop();
        }

        return res;
    }

    private boolean isCycle(List<List<Integer>> graph, int v, int[] visited, Stack<Integer> orderedList) {
        visited[v] = GRAY;

        for (int u : graph.get(v)) {
            if (visited[u] == GRAY) {
                return true;
            }

            if (visited[u] == WHITE && isCycle(graph, u, visited, orderedList)) {
                return true;
            }
        }

        visited[v] = BLACK;
        orderedList.push(v);

        return false;
    }


    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>(numCourses);
        for (int[] edge : prerequisites) {
            int u = edge[1];
            int v = edge[0];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }

        Stack<Integer> orderedList = new Stack<>();
        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == WHITE && isCycle2(graph, i, visited, orderedList)) {
                return new int[0];
            }
        }
        int[] res = new int[orderedList.size()];
        int index = 0;
        while (!orderedList.isEmpty()) {
            res[index++] = orderedList.pop();
        }
        return res;
    }

    private boolean isCycle2(Map<Integer, List<Integer>> graph, int v, int[] visited, Stack<Integer> orderedList) {
        visited[v] = GRAY;

        for (int u : graph.getOrDefault(v, new ArrayList<>())) {
            if (visited[u] == GRAY) {
                return true;
            }

            if (visited[u] == WHITE && isCycle2(graph, u, visited, orderedList)) {
                return true;
            }
        }

        visited[v] = BLACK;
        orderedList.push(v);

        return false;
    }

}
