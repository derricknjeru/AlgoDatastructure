package CodingInterviewPatterns.graph.UnionFind;

import java.util.*;

//https://www.youtube.com/watch?v=kaBX2s3pYO4
//https://www.geeksforgeeks.org/union-by-rank-and-path-compression-in-union-find-algorithm/


public class UnionFindByRank {
    private Node[] dsuf;
    int count;

    public UnionFindByRank(int size) {
        dsuf = new Node[size];
        for (int i = 0; i < size; i++) {
            dsuf[i] = new Node(-1, 0);
        }
        count = size;
    }

    // FIND operation with Path Compression
    // Time Complexity: O(log(n)) (amortized)
    public int find(int x) {
        if (dsuf[x].parent == -1)
            return x;
        dsuf[x].parent = find(dsuf[x].parent); // path compression
        return dsuf[x].parent;
    }

    /**
     * Performs the UNION operation on two elements by rank-based merging.
     *
     * @param element1 the first element to be unioned
     * @param element2 the second element to be unioned
     */
    // Time Complexity: O(1) (amortized)
    public void union(int element1, int element2) {
        int root1 = find(element1);
        int root2 = find(element2);

        if (dsuf[root1].rank > dsuf[root2].rank) {
            dsuf[root2].parent = root1;
        } else if (dsuf[root1].rank < dsuf[root2].rank) {
            dsuf[root1].parent = root2;
        } else {
            dsuf[root1].parent = root2;
            dsuf[root2].rank += 1;
        }

        count--; // Decrease count when union operation is performed
    }

    /**
     * Returns the current count of disjoint sets.
     *
     * @return the count of disjoint sets
     */
    // Time Complexity: O(1)
    public int getCount() {
        return count;
    }

    class Node {
        int parent;
        int rank;

        public Node(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }
}


