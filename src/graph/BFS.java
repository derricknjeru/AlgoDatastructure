package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BFS {

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // create a queue for doing BFS
        Queue<Integer> pq = new ArrayDeque<>();

        // to keep track of whether a vertex is discovered or not
        boolean[] discovered = new boolean[V];

        // enqueue the source vertex as discovered
        pq.add(0);

        // mark the source vertex as discovered
        discovered[0]=true;

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0; i<V; i++){
            if(discovered[i]==false){
                bfs(pq,discovered,ans,adj);
            }
        }


        return ans;

    }

    private void bfs(Queue<Integer> pq, boolean[] discovered,  ArrayList<Integer> ans, ArrayList<ArrayList<Integer>> adj){

        while(!pq.isEmpty()){
            int v = pq.poll();
            ans.add(v);
            // do for every edge (v, u)
            for(int u : adj.get(v)){
                if(discovered[u] == false){
                    pq.add(u);
                    discovered[u]=true;
                }
            }
        }

    }
}

