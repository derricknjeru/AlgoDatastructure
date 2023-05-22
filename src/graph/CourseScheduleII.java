package graph;

import java.util.*;

public class CourseScheduleII {
    //https://www.youtube.com/watch?v=ddTC4Zovtbc
    //https://www.youtube.com/watch?v=_BGK0kpE4oE
    //https://leetcode.com/problems/course-schedule-ii/

    private boolean dfs(int u, List<Integer>[] adj, List<Integer> s, int[] visited) {
        visited[u] = 1;
        for (int v : adj[u]) {
            if (visited[v] == 1) return true;
            if (visited[v] == 0 && dfs(v, adj, s, visited)) return true;
        }
        visited[u] = 2;
        s.add(u);
        return false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; ++i)
            adj[i] = new ArrayList<Integer>();
        for (int[] courses : prerequisites)
            adj[courses[1]].add(courses[0]);

        List<Integer> s = new LinkedList();
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; ++i)
            if (visited[i] == 0 && dfs(i, adj, s, visited)) return new int[0];
        Collections.reverse(s);
        int[] result = s.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }

    private boolean dfs2(int u, Map<Integer, List<Integer>> adj, List<Integer> s, int[] visited) {
        visited[u] = 1;
        for (int v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (visited[v] == 1) return true;
            if (visited[v] == 0 && dfs2(v, adj, s, visited)) return true;
        }
        visited[u] = 2;
        s.add(u);
        return false;
    }

    public int[] findOrder2(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> adj = new HashMap<Integer, List<Integer>>();
        for (int[] courses : prerequisites) {

            List<Integer> lst = adj.getOrDefault(courses[1], new ArrayList<Integer>());
            lst.add(courses[0]);
            adj.put(courses[1], lst);
        }
        List<Integer> s = new LinkedList();
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; ++i)
            if (visited[i] == 0 && dfs2(i, adj, s, visited)) return new int[0];
        Collections.reverse(s);
        int[] result = s.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }

}
