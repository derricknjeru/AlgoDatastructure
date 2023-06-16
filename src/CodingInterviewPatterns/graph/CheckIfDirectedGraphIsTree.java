package CodingInterviewPatterns.graph;

public class CheckIfDirectedGraphIsTree {
    //https://www.baeldung.com/cs/determine-graph-is-tree
    //https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/6546848606322688/5366307739664384
    public static boolean isTree(Graph g) {
        /**
         * Explanation
         * To check whether a directed CodingInterviewPatterns.graph is a tree or not, we’ll check the following :
         *
         * Each node (except root) has exactly one parent
         * There is no cycle in the CodingInterviewPatterns.graph.
         * The CodingInterviewPatterns.graph is connected.
         * We check the first condition in checkOneParent method by traversing the adjacency list of the CodingInterviewPatterns.graph. If the first condition is not satisfied, we return false and don’t check further.
         *
         * For a directed CodingInterviewPatterns.graph, We can use DFS to detect the next two conditions.
         *
         * To check for cycles, we use the same detectCycle function that was used in Challenge: Cycle Detection in a Directed Graph. If we come across any vertex that has already been visited then there is a cycle. If we do not find such an adjacent for any vertex, we say that there is no cycle.
         *
         * Then we check for connectivity in the checkConnected method and traverse all the vertices on the CodingInterviewPatterns.graph to check if they have been visited from the source. If we find any vertex that is not visited, we conclude that vertex is not reachable from the source. Therefore, the CodingInterviewPatterns.graph is not connected and hence, is not a tree.
         *
         * Time Complexity
         * The CodingInterviewPatterns.graph is traversed in both functions. Hence, the time complexity is O(V + E).
         */

        //1. Check each node other than root has exactly one parent.
        if (!checkOneParent(g)) return false;

        //2. Check for Cycle
        if (detectCycle(g)) return false;

        //2. Check for connectivity
        if (!checkConnected(g)) return false;

        return true;
    }

    public static boolean detectCycle(Graph g) {
        int num_of_vertices = g.vertices;

        //Boolean Array to hold the history of visited nodes (by default-false)
        int[] visited = new int[num_of_vertices];

        for (int i = 0; i < num_of_vertices; i++) {
            //Check cyclic on each node
            if (visited[i] == 0) {
                if (cyclic(g, i, visited)) return true;
            }
        }
        return false;
    }

    public static boolean cyclic(Graph g, int v, int[] visited) {

        visited[v] = 1;

        // check adjacency list of the node
        for (int u : g.adjacencyList.get(v)) {
            if (visited[u] == 1) {
                return true;
            }
            //run cyclic function recursively on each outgowing path
            if (visited[u] == 0 && cyclic(g, u, visited)) {
                return true;
            }
        }
        visited[v] = 2;

        return false;
    }

    public static boolean checkOneParent(Graph g) {
        int V = g.vertices;
        boolean[] hasOneParent = new boolean[V];

        //traverse adjacency list and mark the nodes which have a parent
        for (int i = 0; i < V; i++) {
            for (int u : g.adjacencyList.get(i)) {
                if (hasOneParent[u]) //if a node has more than one parent
                    return false;            //then return false
                hasOneParent[u] = true;
            }

        }
        for (int i = 0; i < V; i++) {
            if (i == 0 && hasOneParent[i]) {
                // root should not have parent
                return false;
            } else if (i != 0 && !hasOneParent[i]) {
                //will be false if the node had no parent.
                return false;
            }
        }
        return true;
    }

    private static boolean checkConnected(Graph g) {

        int V = g.vertices;

        //Boolean Array to hold the history of visited nodes (by default-false)
        //Make a node visited whenever you push it into stack
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(g, i, visited);
            }
        }

        //if there is a unvisited node, there not connected
        for (int i = 0; i < V; i++) {
            if (!visited[i]) return false;
        }

        return true;
    }

    private static void dfs(Graph g, int v, boolean[] visited) {
        visited[v] = true;
        for (int u : g.adjacencyList.get(v)) {
            if (!visited[u]) {
                dfs(g, u, visited);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.printGraph();
        System.out.println("isTree : " + isTree(g));

        Graph g2 = new Graph(4);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(0, 3);
        g2.addEdge(3, 2);
        g2.printGraph();
        System.out.println("isTree : " + isTree(g2));

        Graph g3 = new Graph(6);
        g3.addEdge(0, 1);
        g3.addEdge(0, 2);
        g3.addEdge(0, 3);
        g3.addEdge(4, 5);
        g3.printGraph();
        System.out.println("isTree : " + isTree(g3));
    }
}
