package CodingInterviewPatterns.graph.Bfs;

import java.util.*;

public class TimeNeededToInformAllEmployees {
    //https://leetcode.com/problems/time-needed-to-inform-all-employees/description/

    /**
     * The time complexity of the given code is O(n), where n is the number of nodes in the input graph. Here's the breakdown of the time complexity:
     * <p>
     * Constructing the adjacency list: The code iterates over the manager array and adds each employee to the corresponding manager's list of subordinates. This operation takes O(n) time.
     * <p>
     * Breadth-first search (BFS) traversal: The code uses a BFS traversal to calculate the maximum time it takes for information to reach all employees. In the worst case, all nodes in the graph will be visited, which takes O(n) time.
     * <p>
     * Overall, the time complexity of the code is O(n) because the dominant factor is the BFS traversal. Other operations such as constructing the adjacency list contribute linearly to the time complexity but are overshadowed by the BFS traversal.
     *
     * @return
     */
    static class Solution {

        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            if (n == 1) return 0;

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

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{headID, 0});

            boolean[] visited = new boolean[n];
            int maxTime = Integer.MIN_VALUE;
            visited[headID] = true;

            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] curr = q.poll();
                    int src = curr[0];
                    int time = curr[1];
                    maxTime = Math.max(time, maxTime);

                    for (int u : adj.get(src)) {
                        if (!visited[u]) {
                            visited[u] = true;
                            q.offer(new int[]{u, time + informTime[src]});
                        }
                    }
                }
            }

            return maxTime;
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
        System.out.println("Time needed to inform all employees in Example 1: " + result1);

        // Example 2
        int n2 = 6;
        int headID2 = 2;
        int[] manager2 = {2, 2, -1, 2, 2, 2};
        int[] informTime2 = {0, 0, 1, 0, 0, 0};
        int result2 = solution.numOfMinutes(n2, headID2, manager2, informTime2);
        System.out.println("Time needed to inform all employees in Example 2: " + result2);
    }

    class Solution2 {
        //since no cycle
        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
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

            return bfs(adj, headID, informTime);
        }

        private int bfs(List<List<Integer>> adj, int src, int[] informTime) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{src, 0});

            int maxTime = 0;
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int employee = curr[0];
                int time = curr[1];
                maxTime = Math.max(maxTime, time);

                for (int subordinate : adj.get(employee)) {
                    queue.offer(new int[]{subordinate, time + informTime[employee]});
                }
            }

            return maxTime;
        }
    }


}
