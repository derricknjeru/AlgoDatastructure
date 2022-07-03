package graph;

import java.util.ArrayList;

public class DFS {
    //https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1/#
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean [] discovered = new boolean[V];

        ArrayList<Integer>ans = new ArrayList<>();

        for(int i = 0; i<V; i++){
            if(!discovered[i]){
                dfs(i,discovered,adj,ans);
            }
        }

        return ans;
    }

    private void dfs(int v, boolean [] discovered, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer>ans){

        ans.add(v);

        discovered[v] = true;

        for(int u : adj.get(v)){

            if(!discovered[u]){

                dfs(u,discovered,adj,ans);
            }
        }
    }

}
