package CodingInterviewPatterns.graph.ConnectedComponents;

public class NumberOfProvincesDFS {
    static class Solution {
        //https://www.youtube.com/watch?v=CT1xJyM_o9g

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


    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage:
        int[][] isConnectedExample = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        int result = solution.findCircleNum(isConnectedExample);
        System.out.println("Number of provinces: " + result); // Output: 2
    }
}
