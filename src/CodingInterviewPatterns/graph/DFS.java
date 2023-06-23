package CodingInterviewPatterns.graph;

import java.util.List;

public class DFS {
    //https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
    //https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1/#
    //https://www.techiedelight.com/depth-first-search/
    //https://www.youtube.com/watch?v=Y40bRyPQQr0
    //Best https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/

    //Time complexity is O(V+E) DFS visits every vertex and edge in the CodingInterviewPatterns.graph
    //Space O(V+E) because of the stack

    //Like the Bfs, this algorithm traverses the whole list once. Hence, it’s time complexity is O(V + E)
    private static void dfs(int v, boolean[] discovered, Graph g, List<Integer> ans) {
        //A Depth–first search (DFS) is a way of traversing graphs closely related to the preorder traversal of a tree.
        ans.add(v);

        discovered[v] = true;

        for (int u : g.adjacencyList.get(v)) {

            if (!discovered[u]) {

                dfs(u, discovered, g, ans);
            }
        }

        System.out.println("test ---> "+v);

    }

    public static void main(String[] args) {
        int V = 5;
        Graph g = new Graph(V);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);

        // 0 -----1---3   (u,v)
        // |      |
        // 2

        //(0,1), (0,2), (1,3) (1,4)
        //Expected results "01342" or "02143"

        g.printGraph();


        /*boolean[] discovered = new boolean[V];
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < V; i++) { //We do this to cater for disconnected CodingInterviewPatterns.graph
            if (!discovered[i]) {
                dfs(i, discovered, g, ans);
            }
        }

        for (int a : ans) System.out.print(a + " ");*/

    }


}
