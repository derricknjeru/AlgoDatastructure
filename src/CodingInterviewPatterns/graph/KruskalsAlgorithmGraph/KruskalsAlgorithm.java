package CodingInterviewPatterns.graph.KruskalsAlgorithmGraph;

import CodingInterviewPatterns.graph.UnionFind.UnionFindByRank;

import java.util.*;
//https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
//https://www.youtube.com/watch?v=4ZlRH0eK-qQ&t=2s
//https://www.youtube.com/watch?v=Ub-fJ-KoBQM
//Time complexity is O(n * e) No of vertices * No of edges ....> which can be O(N^2);
//If we use minHeap or we sort, Time complexity is O(n log n)

public class KruskalsAlgorithm {
    /**
     * Overall, the dominant factor in terms of space complexity is the edges list,
     * which has a space complexity of O(n^2).
     * Therefore, the space complexity of the Solution class is O(n^2).
     */
    static class Edge {
        int src;
        int dst;
        int wt;

        Edge(int src, int dst, int wt) {
            this.src = src;
            this.dst = dst;
            this.wt = wt;
        }
    }

    /**
     * Overall, the dominant factor in terms of time complexity is sorting the edge list,
     * which has a time complexity of O(E log E). Therefore, the time complexity of the kruskals method is O(E log E),
     * where E is the number of edges in the input graph. In the given code example,
     * E is equal to the number of elements in graphEdges, which is given by the variable E.
     *
     * @param edgeList
     * @param V
     * @param E
     */

    static void kruskals(List<Edge> edgeList, int V, int E) {
        List<Edge> mst = new ArrayList<>();
        /**
         * Sorting the edge list:
         *
         * The edgeList is sorted using the sort method, which takes O(E log E) time, where E is the number of edges.
         * In this case, E is equal to the number of elements in edgeList, which is given by the variable E.
         * Hence, sorting the edge list takes O(E log E) time.
         */
        /**
         * Edges list:
         *
         * The edges list stores the pairs of points along with their costs.
         * The maximum number of edges in a complete graph with n points is n * (n-1) / 2.
         * Therefore, the edges list can have a maximum of n * (n-1) / 2 elements.
         * The space complexity of the edges list is O(n^2).
         */
        edgeList.sort((a, b) -> Integer.compare(a.wt, b.wt));
        /**
         * UnionFindByRank:
         *
         * The space required by the UnionFindByRank data structure depends on the number of elements n it is initialized with.
         * The space complexity of UnionFindByRank is O(n).
         */
        UnionFindByRank unionF = new UnionFindByRank(V);

        int noOfEdges = 0, j = 0;
        /**
         * Applying Kruskal's algorithm:
         *
         * The while loop executes until noOfEdges reaches V - 1 or all edges have been considered.
         * The operations performed inside the loop are constant time.
         * The find and union operations using the UnionFindByRank data structure have an amortized time complexity of O(log V).
         * Hence, applying Kruskal's algorithm takes O((V-1) log V) time.
         */
        while (noOfEdges < V - 1) {
            int x = edgeList.get(j).src;
            int y = edgeList.get(j).dst;

            int fromP = unionF.find(x); // FIND absolute parent of the subset
            int toP = unionF.find(y);

            if (fromP != toP) {
                // UNION operation
                unionF.union(x, y); // UNION of 2 sets
                mst.add(edgeList.get(j));
                noOfEdges++;
            }
            j++;
        }

        printMST(mst);
    }

    static void printMST(List<Edge> mst) {
        /**
         * Printing the MST:
         *
         * Printing the MST involves iterating over the mst list, which contains V - 1 edges.
         * The operations performed inside the loop are constant time.
         * Hence, printing the MST takes O(V) time.
         */
        System.out.println("Following are the edges in the constructed MST");
        int minCost = 0;
        for (Edge p : mst) {
            System.out.println("src: " + p.src + "  dst: " + p.dst + "  wt: " + p.wt);
            minCost += p.wt;
        }
        System.out.println("Minimum Cost Spanning Tree: " + minCost);
    }

    public static void main(String[] args) {
        int V = 4;
        List<Edge> graphEdges = new ArrayList<>(
                List.of(new Edge(0, 1, 10), new Edge(0, 2, 6),
                        new Edge(0, 3, 5), new Edge(1, 3, 15),
                        new Edge(2, 3, 4)));

        kruskals(graphEdges, V, graphEdges.size());
    }
}



