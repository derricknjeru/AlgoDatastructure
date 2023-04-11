package graph;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
    //https://www.youtube.com/watch?v=kXy0ABd1vwo&t=12s
    //https://www.youtube.com/watch?v=iaaObeAEgxI&t=82s
    static int  WHITE=0, GRAY=1, BLACK=2;
    class Graph{
        int vertices;
        List<ArrayList<Integer>> adjacencyList;

        public Graph(int v){
            vertices = v;
            adjacencyList = new ArrayList<>();
            for(int i=0; i<v; i++){
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination){
            adjacencyList.get(source).add(destination);
        }
    }

    public boolean hasCycle(Graph g, int v){
        int[] visited = new int[v];

        for(int i=0; i<v; i++){
            if(visited[i]==WHITE){
                if(detectCycle_util(g,i,visited)) return false;
            }
        }

        return true;
    }

    public boolean detectCycle_util(Graph g, int v, int[] visited){
        visited[v] = GRAY;

        for(int u : g.adjacencyList.get(v)){

            if(visited[u]==GRAY) return true;

            if(visited[u]==WHITE && detectCycle_util(g,u,visited)) return true;

        }

        visited[v]=BLACK;

        return false;

    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph g = new Graph(numCourses);
        for(int[]a : prerequisites){
            int source = a[0];
            int destination=a[1];
            g.addEdge(source,destination);
        }

        return hasCycle(g, numCourses);
    }
}

