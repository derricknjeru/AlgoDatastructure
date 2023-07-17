package CodingInterviewPatterns.graph.Bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BFSRecursive {

    /**
     * Given a directed CodingInterviewPatterns.graph. The task is to do Breadth First Traversal of this CodingInterviewPatterns.graph starting from 0.
     * Note.md.md.md.md: One can move from node u to node v only if there's an edge from u to v and find the Bfs traversal of the CodingInterviewPatterns.graph starting from the 0th vertex, from left to right according to the CodingInterviewPatterns.graph. Also, you should only take nodes directly or indirectly connected from Node 0 in consideration.
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input:
     * <p>
     * Output: 0 1 2 3 4
     * Explanation:
     * 0 is connected to 1 , 2 , 3.
     * 2 is connected to 4.
     * so starting from 0, it will go to 1 then 2
     * then 3.After this 2 to 4, thus bfs will be
     * 0 1 2 3 4.
     * Example 2:
     * <p>
     * Input:
     * <p>
     * Output: 0 1 2
     * Explanation:
     * 0 is connected to 1 , 2.
     * so starting from 0, it will go to 1 then 2,
     * thus bfs will be 0 1 2 3 4.
     * <p>
     * Your task:
     * You don’t need to read input or print anything. Your task is to complete the function bfsOfGraph() which takes the integer V denoting the number of vertices and adjacency list as input parameters and returns  a list containing the Bfs traversal of the CodingInterviewPatterns.graph starting from the 0th vertex from left to right.
     * <p>
     * <p>
     * Expected Time Complexity: O(V + E)
     * Expected Auxiliary Space: O(V)
     *
     * @param V
     * @param adj
     * @return
     */
    //https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1/
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // create a queue for doing Bfs
        Queue<Integer> q = new ArrayDeque<>();

        // to keep track of whether a vertex is discovered or not
        boolean[] discovered = new boolean[V];

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (!discovered[i]) {
                // enqueue the source vertex as discovered
                q.add(i);

                // mark the source vertex as discovered
                discovered[i] = true;

                bfs(q, discovered, ans, adj);
            }
        }
        return ans;

    }

    private void bfs(Queue<Integer> pq, boolean[] discovered, ArrayList<Integer> ans, ArrayList<ArrayList<Integer>> adj) {
        if (pq.isEmpty()) return;

        int v = pq.poll();
        ans.add(v);

        for (int u : adj.get(v)) {
            if (!discovered[u]) {
                pq.add(u);
                discovered[u] = true;
            }
        }

        bfs(pq, discovered, ans, adj);
    }
}


