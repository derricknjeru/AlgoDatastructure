package graph;

public class NumberOfProvinces {
    //https://www.youtube.com/watch?v=CT1xJyM_o9g
    //https://www.youtube.com/watch?v=Kn-nw7VbWak

    public int findCircleNum(int[][] isConnected) {
        /**
         *        A  B  c
         *      A  1  1   0
         *      B  1  1   0
         *      c  0  0   1
         */

        int V = isConnected.length;
        boolean[] visited = new boolean[V];
        int noOfProvinces = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                noOfProvinces++;
            }
        }
        return noOfProvinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int v) {

        visited[v] = true;

        for (int j = 0; j < isConnected.length; j++) {
            if (!visited[j] && isConnected[v][j] == 1) {
                dfs(isConnected, visited, j);
            }
        }
    }
}
