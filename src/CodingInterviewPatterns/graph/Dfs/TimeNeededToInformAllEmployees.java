package CodingInterviewPatterns.graph.Dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TimeNeededToInformAllEmployees {
    //https://leetcode.com/problems/time-needed-to-inform-all-employees/description/

    /**
     * The time complexity of the code remains the same after the variable renaming. It is still O(n), where n is the number of nodes in the input graph. Here's the breakdown of the time complexity:
     * <p>
     * Constructing the adjacency list: The code iterates over the manager array and adds each employee to the corresponding manager's list of subordinates. This operation takes O(n) time.
     * <p>
     * Depth-first search (DFS) traversal: The code uses a DFS traversal to calculate the maximum time it takes for information to reach all employees. In the worst case, all nodes in the graph will be visited, which takes O(n) time.
     * <p>
     * Overall, the time complexity of the code is O(n) because the dominant factor is the DFS traversal. Other operations such as constructing the adjacency list contribute linearly to the time complexity but are overshadowed by the DFS traversal.
     */

    static class Solution3 {
        private int maxTime = Integer.MIN_VALUE;

        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            if (n == 1) return 0;

            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < n; i++) {
                if (manager[i] != -1) {
                    adj.get(manager[i]).add(i);
                }
            }

            boolean[] visited = new boolean[n];
            dfs(adj, headID, 0, informTime, visited);

            return maxTime;
        }

        private void dfs(List<List<Integer>> adj, int src, int time, int[] informTime, boolean[] visited) {
            maxTime = Math.max(maxTime, time);
            visited[src] = true;

            for (int u : adj.get(src)) {
                if (!visited[u]) {
                    dfs(adj, u, time + informTime[src], informTime, visited);
                }
            }
        }
    }

    static class Solution {
        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            List<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < n; i++) {
                if (manager[i] != -1) {
                    graph[manager[i]].add(i);
                }
            }

            // dfs

            return dfs(graph, headID, informTime);
        }

        private int dfs(List<Integer>[] graph, int node, int[] informTime) {
            if (graph[node].size() == 0) return 0;

            int nodeTime = informTime[node];
            int maxValue = 0;
            for (int child : graph[node]) {
                int childTime = nodeTime + dfs(graph, child, informTime);
                maxValue = Math.max(maxValue, childTime);
            }
            return maxValue;
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int n1 = 1;
        int headID1 = 0;
        int[] manager1 = {-1};
        int[] informTime1 = {0};
        int result1 = solution.numOfMinutes(n1, headID1, manager1, informTime1);
        System.out.println("Time needed to inform all employees in Example 1: " + result1); //0

        // Example 2
        int n2 = 6;
        int headID2 = 2;
        int[] manager2 = {2, 2, -1, 2, 2, 2};
        int[] informTime2 = {0, 0, 1, 0, 0, 0};
        int result2 = solution.numOfMinutes(n2, headID2, manager2, informTime2);
        System.out.println("Time needed to inform all employees in Example 2: " + result2); //1
    }

    class Solution2 {
        //since there are no cycles
        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            // Construct the adjacency list
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < n; i++) {
                int u = manager[i];
                if (u != -1) {
                    adj.get(u).add(i);
                }
            }

            return dfs(adj, headID, informTime);
        }

        private int dfs(List<List<Integer>> adj, int src, int[] informTime) {
            int maxTime = 0;

            for (int u : adj.get(src)) {
                maxTime = Math.max(maxTime, dfs(adj, u, informTime));
            }

            return maxTime + informTime[src];
        }
    }

}
